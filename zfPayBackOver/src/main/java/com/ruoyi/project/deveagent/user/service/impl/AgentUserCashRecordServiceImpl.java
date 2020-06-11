package com.ruoyi.project.deveagent.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.user.mapper.AgentUserCardMapper;
import com.ruoyi.project.devemana.notice.mapper.ManaSysNoticeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.BenefitRecordTypeContants;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.user.domain.AgentUserCashRecordExcel;
import com.ruoyi.project.deveagent.user.mapper.AgentUserCashRecordMapper;
import com.ruoyi.project.deveagent.user.mapper.AgentUserInfoMapper;
import com.ruoyi.project.deveagent.user.service.AgentUserCashRecordService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.user.domain.ManaUserCashRecord;


/**
 * 代理======用户取现记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserCashRecordServiceImpl implements AgentUserCashRecordService {
	
	@Autowired
	private AgentUserInfoMapper agentUserInfoMapper;
	@Autowired
	private AgentUserCashRecordMapper agentUserCashRecordMapper;

	@Autowired
	private ManaSysNoticeMapper sysNoticeMapper;


	
	/**
	 * 查询用户取现记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserCashRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserCashRecordMapper.getAgentUserCashRecordList(params);
	}
	
	
	/**
	 * 汇总取现记录
	 */
	@Override
	public Map<String, Object> summaryAgentUserCashRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserCashRecordMapper.summaryAgentUserCashRecordList(params);
	}
	
	
	/**
	 * 导出用户取现记录列表
	 */
	@Override
	public List<ManaUserCashRecord> selectAgentUserCashRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserCashRecordMapper.selectAgentUserCashRecordList(params);
	}


	/**
	 * 查询取现记录详情列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserCashRecordDetailList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserCashRecordMapper.getAgentUserCashRecordDetailList(params);
	}


	/**
	 * 导出用户待处理提现记录
	 */
	@Override
	public List<AgentUserCashRecordExcel> selectManaWaitUserCashRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());//代理编号
		params.put("batch_no", StringUtil.getDateTimeAndRandomForID_20());//批次号
		List<AgentUserCashRecordExcel> agentUserCashRecordExcelListDeal = agentUserCashRecordMapper.selectManaWaitUserCashRecordList(params);
		List<AgentUserCashRecordExcel> agentUserCashRecordExcelList = new ArrayList<>();
		int num = 0;
		if(agentUserCashRecordExcelListDeal != null && agentUserCashRecordExcelListDeal.size() > 0){
			for(AgentUserCashRecordExcel bean : agentUserCashRecordExcelListDeal){
				try{
					Map<String, Object> cashDealMap = new HashMap<String, Object>();
					cashDealMap.put("cash_id", bean.getId());//编号
					cashDealMap.put("old_status", TypeStatusConstant.user_cash_record_status_00);//旧状态：待处理
					cashDealMap.put("new_status", TypeStatusConstant.user_cash_record_status_02);//旧状态：处理中
					cashDealMap.put("batch_no", params.get("batch_no"));//批次号
					cashDealMap.put("cre_date", TimeUtil.getDayString());
					cashDealMap.put("cre_time", TimeUtil.getTimeString());
					cashDealMap.put("create_by", ShiroUtils.getLoginName());
					//（1）更新取现状态
					num = agentUserCashRecordMapper.updateAgentUserCashRecordStatus(cashDealMap);
					if(num != 1){
						continue;
					}
					//（2）记录提现流水
					cashDealMap.put("note", "提现处理中");
					cashDealMap.put("cash_status", TypeStatusConstant.user_cash_record_status_02);
					agentUserCashRecordMapper.addAgentUserCashRecordDetail(cashDealMap);
					agentUserCashRecordExcelList.add(bean);
				}catch(Exception e){
					e.printStackTrace();
					continue;
				}
			}
		}
		return agentUserCashRecordExcelList;
	}


	/**
	 * 批量审核处理处理中的提现记录
	 */
	@Override
	public R batchSysAuditUserCashRecord(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
        	return R.error(Type.WARN, "请输入操作备注");
        }
        if(TypeStatusConstant.user_cash_record_status_09.equals(StringUtil.getMapValue(params, "status"))) {
        	//批量提现成功
        	return this.batchSuccessUserCashRecord(params);
        }else if(TypeStatusConstant.user_cash_record_status_08.equals(StringUtil.getMapValue(params, "status"))){
        	//批量提现失败
        	return this.batchFaileUserCashRecord(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}

	
	/**
	 * 批量提现成功（更新到账金额、结算单笔手续费金额、结算比例手续费金额）
	 * @param params
	 * @return
	 */
	private R batchSuccessUserCashRecord(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] cash_ids = Convert.toStrArray(StringUtil.getMapValue(params, "cash_ids"));
        for(int i=0;i<cash_ids.length;i++) {
        	Map<String, Object> cashMap = new HashMap<>();
        	cashMap.put("remark", params.get("remark"));
        	cashMap.put("cash_id", cash_ids[i]);
        	//依次处理每一个
        	R result = SpringUtils.getAopProxy(this).successUserCashRecord(cashMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}
	
	
	/**
	 * 处理单个提现记录（处理中=====》处理成功）（更新到账金额、结算单笔手续费金额、结算比例手续费金额）
	 * @param cashMap
	 * @return
	 */
	@Transactional
	public R successUserCashRecord(Map<String, Object> cashMap) {
		try {
			int i=0;
			//（1）查询提现详情
			Map<String, Object> cashDetailMap = agentUserCashRecordMapper.getAgentUserCashRecordById(cashMap.get("cash_id").toString());
			if(!TypeStatusConstant.user_cash_record_status_02.equals(StringUtil.getMapValue(cashDetailMap, "status"))) {
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：还未导出处理");
			}
			//（2）更新提现状态
			cashMap.put("cash_id", cashDetailMap.get("id").toString());
			cashMap.put("old_status", TypeStatusConstant.user_cash_record_status_02);
			cashMap.put("new_status", TypeStatusConstant.user_cash_record_status_09);
			cashMap.put("update_by", ShiroUtils.getLoginName());
			cashMap.put("cre_date", TimeUtil.getDayString());
			cashMap.put("cre_time", TimeUtil.getTimeString());
			i = agentUserCashRecordMapper.updateAgentUserCashRecordStatus(cashMap);
			if(i != 1) {
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：状态更新失败");
			}
			//（3）记录提现流水
			cashMap.put("note", "提现处理成功");
			cashMap.put("cash_status", TypeStatusConstant.user_cash_record_status_09);
			i = agentUserCashRecordMapper.addAgentUserCashRecordDetail(cashMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：详情信息记录失败");
			}
			//（4）更新到账金额、结算单笔手续费金额、结算比例手续费金额
			cashMap.put("user_id", cashDetailMap.get("user_id"));
			cashMap.put("manager_id", ShiroUtils.getUserId());
			cashMap.put("settle_money", cashDetailMap.get("cash_actual_money"));//到账金额
			cashMap.put("settle_single_feet_money", cashDetailMap.get("single_feet_money"));//结算单笔手续费金额
			cashMap.put("single_rate_feet_money", cashDetailMap.get("rate_feet_money"));//结算比例手续费金额
			i = agentUserInfoMapper.updateAgentUserByCashSuccess(cashMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：用户账户更新失败");
			}
			//add 小红点消息置为未读 begin byqh 201912
			Map<String,Object> hashMap = new HashMap<>();
			hashMap.put("read_flag","0");
			hashMap.put("sys_user_id",cashDetailMap.get("user_id"));
			hashMap.put("news_type","cashFlag");
			sysNoticeMapper.updateNewsReadFlag(hashMap);
			//add 小红点消息置为未读 end byqh 201912
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "提现编号"+cashMap.get("exchange_id").toString()+"：更新异常");
		}
	}


	/**
	 * 批量提现失败
	 * @param params
	 * @return
	 */
	private R batchFaileUserCashRecord(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] cash_ids = Convert.toStrArray(StringUtil.getMapValue(params, "cash_ids"));
        for(int i=0;i<cash_ids.length;i++) {
        	Map<String, Object> cashMap = new HashMap<>();
        	cashMap.put("remark", params.get("remark"));
        	cashMap.put("cash_id", cash_ids[i]);
        	//依次处理每一个
        	R result = SpringUtils.getAopProxy(this).faileUserCashRecord(cashMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 处理单个提现记录（处理中=====》处理失败）
	 * @param cashMap
	 * @return
	 */
	@Transactional
	public R faileUserCashRecord(Map<String, Object> cashMap) {
		try {
			int i=0;
			//（1）查询提现详情
			Map<String, Object> cashDetailMap = agentUserCashRecordMapper.getAgentUserCashRecordById(cashMap.get("cash_id").toString());
			if(!TypeStatusConstant.user_cash_record_status_02.equals(StringUtil.getMapValue(cashDetailMap, "status"))) {
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：还未导出处理");
			}
			//（2）更新提现状态
			cashMap.put("cash_id", cashDetailMap.get("id").toString());
			cashMap.put("old_status", TypeStatusConstant.user_cash_record_status_02);
			cashMap.put("new_status", TypeStatusConstant.user_cash_record_status_08);
			cashMap.put("update_by", ShiroUtils.getLoginName());
			cashMap.put("cre_date", TimeUtil.getDayString());
			cashMap.put("cre_time", TimeUtil.getTimeString());
			i = agentUserCashRecordMapper.updateAgentUserCashRecordStatus(cashMap);
			if(i != 1) {
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：状态更新失败");
			}
			//（3）记录提现流水
			cashMap.put("note", "提现处理成功");
			cashMap.put("cash_status", TypeStatusConstant.user_cash_record_status_08);
			i = agentUserCashRecordMapper.addAgentUserCashRecordDetail(cashMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：详情信息记录失败");
			}
			//（4）退还提币数量
			cashMap.put("op_type", BenefitRecordTypeContants.benefit_record_type_07);//用户提现失败退还
			cashMap.put("order_id", cashDetailMap.get("order_id"));
			cashMap.put("user_id", cashDetailMap.get("user_id"));
			cashMap.put("manager_id", ShiroUtils.getUserId());
			cashMap.put("cash_money", cashDetailMap.get("cash_money"));
			cashMap.put("deduct_money", cashDetailMap.get("deduct_money"));
			i = agentUserInfoMapper.updateAgentUserByCashFaile(cashMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "提现编号"+cashMap.get("cash_id").toString()+"：用户账户更新失败");
			}

			//add 小红点消息置为未读 begin byqh 201912
			Map<String,Object> hashMap = new HashMap<>();
			hashMap.put("read_flag","0");
			hashMap.put("sys_user_id",cashDetailMap.get("user_id"));
			hashMap.put("news_type","cashFlag");
			sysNoticeMapper.updateNewsReadFlag(hashMap);
			//add 小红点消息置为未读 end byqh 201912
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "提现编号"+cashMap.get("exchange_id").toString()+"：更新异常");
		}
	}
	
}

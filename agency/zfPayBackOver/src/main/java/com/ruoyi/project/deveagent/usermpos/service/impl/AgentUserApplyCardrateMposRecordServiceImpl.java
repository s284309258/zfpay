package com.ruoyi.project.deveagent.usermpos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.devemana.notice.mapper.ManaSysNoticeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.syspos.mapper.AgentSysMposInfoMapper;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserApplyCardrateMposRecord;
import com.ruoyi.project.deveagent.usermpos.mapper.AgentUserApplyCardrateMposRecordMapper;
import com.ruoyi.project.deveagent.usermpos.service.AgentUserApplyCardrateMposRecordService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;


/**
 * 代理====》用户费率申请记录(MPOS)管理
 * @author Administrator
 *
 */
@Service
public class AgentUserApplyCardrateMposRecordServiceImpl implements AgentUserApplyCardrateMposRecordService {
	
	
	@Autowired
	private AgentUserApplyCardrateMposRecordMapper agentUserApplyCardrateMposRecordMapper;
	@Autowired
	private AgentSysMposInfoMapper agentSysMposInfoMapper;

	@Autowired
	private ManaSysNoticeMapper sysNoticeMapper;


	/**
	 * 查询用户用户费率申请记录(MPOS)列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserApplyCardrateMposRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserApplyCardrateMposRecordMapper.getAgentUserApplyCardrateMposRecordList(params);
	}
	
	
	/**
	 * 导出用户用户费率申请记录(MPOS)列表
	 */
	@Override
	public List<AgentUserApplyCardrateMposRecord> selectAgentUserApplyCardrateMposRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserApplyCardrateMposRecordMapper.selectAgentUserApplyCardrateMposRecordList(params);
	}


	/**
	 * 系统批量刷卡费率(MPOS)申请
	 */
	@Override
	public R batchAgentSysAuditUserApplyCardrateMposRecord(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
        	return R.error(Type.WARN, "请输入操作备注");
        }
        if(TypeStatusConstant.user_apply_cardrate_record_status_09.equals(StringUtil.getMapValue(params, "status"))) {
        	//批量审核通过
        	return this.batchAcceptAgentUserApplyCardrateMposRecord(params);
        }else if(TypeStatusConstant.user_apply_cardrate_record_status_08.equals(StringUtil.getMapValue(params, "status"))){
        	//批量审核不通过
        	return this.batchRefuseAgentUserApplyCardrateMposRecord(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}
	

	/**
	 * 批量审核通过刷卡费率(MPOS)申请
	 * @param params
	 * @return
	 */
	private R batchAcceptAgentUserApplyCardrateMposRecord(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] apply_ids = Convert.toStrArray(StringUtil.getMapValue(params, "apply_ids"));
    	Map<String, Object> applyMap = new HashMap<>();
        for(int i=0;i<apply_ids.length;i++) {
        	applyMap.put("remark", params.get("remark"));
        	applyMap.put("apply_id", apply_ids[i]);
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).acceptAgentUserApplyCardrateMposRecord(applyMap);
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
	 * 单个审核通过刷卡费率(MPOS)申请
	 * @param applyMap
	 * @return
	 */
	@Transactional
	public R acceptAgentUserApplyCardrateMposRecord(Map<String, Object> applyMap) {
		try {
			int i=0;
			//（1）更新审核状态
			applyMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			applyMap.put("old_status", TypeStatusConstant.user_apply_cardrate_record_status_00);//旧状态：待审核
			applyMap.put("new_status", TypeStatusConstant.user_apply_cardrate_record_status_09);//新状态：审核通过
			applyMap.put("update_by", ShiroUtils.getLoginName());//修改人
			applyMap.put("up_date", TimeUtil.getDayString());//修改日期
			applyMap.put("up_time", TimeUtil.getTimeString());//修改时间
			i = agentUserApplyCardrateMposRecordMapper.updateAgentUserApplyCardrateMposRecordStatus(applyMap);
			if(i!=1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "申请(MPOS)编号"+applyMap.get("apply_id").toString()+"：状态更新失败");
			}
			//（2）根据id查询详情
			Map<String, Object> applyDetailMap = agentUserApplyCardrateMposRecordMapper.getAgentUserApplyCardrateMposRecordById(applyMap.get("apply_id").toString());
			//（3）更新系统MPOS机的刷卡费率
			applyMap.put("sn", applyDetailMap.get("sn").toString());
			applyMap.put("credit_card_rate", applyDetailMap.get("credit_card_rate_new").toString());
			i = agentSysMposInfoMapper.updateAgentSysMposInfoCreditCardRate(applyMap);
			if(i!=1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "申请(MPOS)编号"+applyMap.get("apply_id").toString()+"：刷卡费率修改失败");
			}
			//add 小红点消息置为未读 begin byqh 201912
			Map<String,Object> hashMap = new HashMap<>();
			hashMap.put("read_flag","0");
			hashMap.put("sys_user_id",applyDetailMap.get("user_id"));
			hashMap.put("news_type","applyRateFlag");
			sysNoticeMapper.updateNewsReadFlag(hashMap);
			//add 小红点消息置为未读 end byqh 201912
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "申请(MPOS)编号"+applyMap.get("apply_id").toString()+"：审核异常");
		}
	}
	
	
	/**
	 * 批量审核不通过刷卡费率(MPOS)申请
	 * @param params
	 * @return
	 */
	private R batchRefuseAgentUserApplyCardrateMposRecord(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] apply_ids = Convert.toStrArray(StringUtil.getMapValue(params, "apply_ids"));
    	Map<String, Object> applyMap = new HashMap<>();
        for(int i=0;i<apply_ids.length;i++) {
        	applyMap.put("remark", params.get("remark"));
        	applyMap.put("apply_id", apply_ids[i]);
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).refuseAgentUserApplyCardrateMposRecord(applyMap);
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
	 * 单个审核不通过刷卡费率(MPOS)申请
	 * @param applyMap
	 * @return
	 */
	@Transactional
	public R refuseAgentUserApplyCardrateMposRecord(Map<String, Object> applyMap) {
		try {
			int i=0;
			//（1）更新审核状态
			applyMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			applyMap.put("old_status", TypeStatusConstant.user_pos_activity_info_status_00);//旧状态：待审核
			applyMap.put("new_status", TypeStatusConstant.user_pos_activity_info_status_08);//新状态：审核不通过
			applyMap.put("update_by", ShiroUtils.getLoginName());//修改人
			applyMap.put("up_date", TimeUtil.getDayString());//修改日期
			applyMap.put("up_time", TimeUtil.getTimeString());//修改时间
			i = agentUserApplyCardrateMposRecordMapper.updateAgentUserApplyCardrateMposRecordStatus(applyMap);
			if(i!=1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "申请(MPOS)编号"+applyMap.get("apply_id").toString()+"：状态更新失败");
			}

			Map<String, Object> applyDetailMap = agentUserApplyCardrateMposRecordMapper.getAgentUserApplyCardrateMposRecordById(applyMap.get("apply_id").toString());
			//add 小红点消息置为未读 begin byqh 201912
			Map<String,Object> hashMap = new HashMap<>();
			hashMap.put("read_flag","0");
			hashMap.put("sys_user_id",applyDetailMap.get("user_id"));
			hashMap.put("news_type","applyRateFlag");
			sysNoticeMapper.updateNewsReadFlag(hashMap);
			//add 小红点消息置为未读 end byqh 201912
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "申请(MPOS)编号"+applyMap.get("apply_id").toString()+"：审核异常");
		}
	}
	
}

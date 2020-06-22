package com.ruoyi.project.deveagent.usertrapos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.ruoyi.project.deveagent.syspos.mapper.AgentSysTraditionalPosInfoMapper;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserApplyScanRecordInfo;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserApplyScanRecordInfoMapper;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserApplyScanRecordInfoService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;


/**
 * 代理====》用户申请扫码支付管理
 * @author Administrator
 *
 */
@Service
public class AgentUserApplyScanRecordInfoServiceImpl implements AgentUserApplyScanRecordInfoService {
	
	
	@Autowired
	private AgentUserApplyScanRecordInfoMapper agentUserApplyScanRecordInfoMapper;
	@Autowired
	private AgentSysTraditionalPosInfoMapper agentSysTraditionalPosInfoMapper;


	
	/**
	 * 查询用户申请扫码支付列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserApplyScanRecordInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserApplyScanRecordInfoMapper.getAgentUserApplyScanRecordInfoList(params);
	}
	
	
	/**
	 * 导出用户申请扫码支付列表
	 */
	@Override
	public List<AgentUserApplyScanRecordInfo> selectAgentUserApplyScanRecordInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserApplyScanRecordInfoMapper.selectAgentUserApplyScanRecordInfoList(params);
	}


	/**
	 * 系统批量审核扫码支付申请
	 */
	@Override
	public R batchAgentSysAuditUserApplyScanRecordInfo(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
        	return R.error(Type.WARN, "请输入操作备注");
        }
        if(TypeStatusConstant.user_apply_cardrate_record_status_09.equals(StringUtil.getMapValue(params, "status"))) {
        	//批量审核通过
        	return this.batchAcceptAgentUserApplyScanRecordInfo(params);
        }else if(TypeStatusConstant.user_apply_cardrate_record_status_08.equals(StringUtil.getMapValue(params, "status"))){
        	//批量审核不通过
        	return this.batchRefuseAgentUserApplyScanRecordInfo(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}
	

	/**
	 * 批量审核通过扫码支付申请
	 * @param params
	 * @return
	 */
	private R batchAcceptAgentUserApplyScanRecordInfo(Map<String, Object> params) {
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
        	R result = SpringUtils.getAopProxy(this).acceptAgentUserApplyScanRecordInfo(applyMap);
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
	 * 单个审核通过扫码支付申请
	 * @param applyMap
	 * @return
	 */
	@Transactional
	public R acceptAgentUserApplyScanRecordInfo(Map<String, Object> applyMap) {
		try {
			int i=0;
			//（1）更新审核状态
			applyMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			applyMap.put("old_status", TypeStatusConstant.user_apply_scan_record_info_status_00);//旧状态：待审核
			applyMap.put("new_status", TypeStatusConstant.user_apply_scan_record_info_status_09);//新状态：审核通过
			applyMap.put("update_by", ShiroUtils.getLoginName());//修改人
			applyMap.put("up_date", TimeUtil.getDayString());//修改日期
			applyMap.put("up_time", TimeUtil.getTimeString());//修改时间
			i = agentUserApplyScanRecordInfoMapper.updateAgentUserApplyScanRecordInfoStatus(applyMap);
			if(i!=1) {
				return R.error(Type.WARN, "申请活动(MPOS)编号"+applyMap.get("apply_id").toString()+"：状态更新失败");
			}
			//（2）查询详情，获取sn
			Map<String, Object> applyDetailMap = agentUserApplyScanRecordInfoMapper.getAgentUserApplyScanRecordInfoById(applyMap.get("apply_id").toString());
			//（3）更新用户pos机的申请扫码支付状态
			applyMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			applyMap.put("user_id", applyDetailMap.get("user_id").toString());//用户编号
			applyMap.put("sn", applyDetailMap.get("sn").toString());//pos机信息
			applyMap.put("old_scan_status", TypeStatusConstant.sys_pos_info_scan_status_0);//旧状态：否
			applyMap.put("new_scan_status", TypeStatusConstant.sys_pos_info_scan_status_1);//新状态：是
			i = agentSysTraditionalPosInfoMapper.updateAgentSysTraditionalPosInfoScanStatus(applyMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "申请编号"+applyMap.get("apply_id").toString()+"：更新扫码支付状态失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "申请(MPOS)编号"+applyMap.get("apply_id").toString()+"：审核异常");
		}
	}
	
	
	/**
	 * 批量审核不通过扫码支付申请
	 * @param params
	 * @return
	 */
	private R batchRefuseAgentUserApplyScanRecordInfo(Map<String, Object> params) {
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
        	R result = SpringUtils.getAopProxy(this).refuseAgentUserApplyScanRecordInfo(applyMap);
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
	 * 单个审核不通过扫码支付申请
	 * @param applyMap
	 * @return
	 */
	@Transactional
	public R refuseAgentUserApplyScanRecordInfo(Map<String, Object> applyMap) {
		try {
			int i=0;
			//（1）更新审核状态
			applyMap.put("old_status", TypeStatusConstant.user_apply_scan_record_info_status_00);//旧状态：待审核
			applyMap.put("new_status", TypeStatusConstant.user_apply_scan_record_info_status_08);//新状态：审核不通过
			applyMap.put("update_by", ShiroUtils.getLoginName());//修改人
			applyMap.put("up_date", TimeUtil.getDayString());//修改日期
			applyMap.put("up_time", TimeUtil.getTimeString());//修改时间
			i = agentUserApplyScanRecordInfoMapper.updateAgentUserApplyScanRecordInfoStatus(applyMap);      
			if(i != 1) {
				return R.error(Type.WARN, "申请编号"+applyMap.get("apply_id").toString()+"：状态更新失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "申请编号"+applyMap.get("apply_id").toString()+"：审核异常");
		}
	}


	/**
	 * 根据编号查询用户申请扫码支付详情
	 */
	@Override
	public Map<String, Object> getAgentUserApplyScanRecordInfoById(String id) {
		return agentUserApplyScanRecordInfoMapper.getAgentUserApplyScanRecordInfoById(id);
	}
	
}

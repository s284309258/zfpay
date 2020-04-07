package com.ruoyi.project.deveagent.usertrapos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.syspospolicy.mapper.SysPosPolicyMapper;
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
import com.ruoyi.project.deveagent.syspos.mapper.AgentSysTraditionalPosInfoMapper;
import com.ruoyi.project.deveagent.user.mapper.AgentUserInfoMapper;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposUnbindRecordInfo;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraditionalPosInfoMapper;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraposUnbindRecordInfoMapper;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposUnbindRecordInfoService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;


/**
 * 代理====》用户传统POS解绑记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserTraposUnbindRecordInfoServiceImpl implements AgentUserTraposUnbindRecordInfoService {
	
	@Autowired
	private AgentSysTraditionalPosInfoMapper agentSysTraditionalPosInfoMapper;
	@Autowired
	private AgentUserTraditionalPosInfoMapper agentUserTraditionalPosInfoMapper;
	@Autowired
	private AgentUserTraposUnbindRecordInfoMapper agentUserTraposUnbindRecordInfoMapper;
	@Autowired
	private AgentUserInfoMapper agentUserInfoMapper;
	@Autowired
	private SysPosPolicyMapper sysPosPolicyMapper;


	
	/**
	 * 查询用户传统POS解绑记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTraposUnbindRecordInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserTraposUnbindRecordInfoMapper.getAgentUserTraposUnbindRecordInfoList(params);
	}
	
	
	/**
	 * 导出用户传统POS解绑记录列表
	 */
	@Override
	public List<AgentUserTraposUnbindRecordInfo> selectAgentUserTraposUnbindRecordInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserTraposUnbindRecordInfoMapper.selectAgentUserTraposUnbindRecordInfoList(params);
	}


	/**
	 * 系统批量审核解绑申请
	 */
	@Override
	public R batchAgentSysAuditUserTraposUnbindRecordInfo(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
        	return R.error(Type.WARN, "请输入操作备注");
        }
        if(TypeStatusConstant.user_trapos_unbind_record_info_status_09.equals(StringUtil.getMapValue(params, "status"))) {
        	//批量审核通过
        	return this.batchAcceptAgentUserTraposUnbindRecordInfo(params);
        }else if(TypeStatusConstant.user_trapos_unbind_record_info_status_08.equals(StringUtil.getMapValue(params, "status"))){
        	//批量审核不通过
        	return this.batchRefuseAgentUserTraposUnbindRecordInfo(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}
	
	
	/**
	 * 批量审核通过用户传统POS解绑申请
	 * @param params
	 * @return
	 */
	private R batchAcceptAgentUserTraposUnbindRecordInfo(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] unbind_ids = Convert.toStrArray(StringUtil.getMapValue(params, "unbind_ids"));
    	Map<String, Object> unbindMap = new HashMap<>();
        for(int i=0;i<unbind_ids.length;i++) {
        	unbindMap.put("remark", params.get("remark"));
        	unbindMap.put("unbind_id", unbind_ids[i]);
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).acceptAgentUserTraposUnbindRecordInfo(unbindMap);
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
	 * 单个审核通过用户传统POS解绑申请
	 * @param applyMap
	 * @return
	 */
	@Transactional
	public R acceptAgentUserTraposUnbindRecordInfo(Map<String, Object> unbindMap) {
		try {
			int i=0;
			//（1）更新审核状态
			unbindMap.put("old_status", TypeStatusConstant.user_trapos_unbind_record_info_status_00);//旧状态：待审核
			unbindMap.put("new_status", TypeStatusConstant.user_trapos_unbind_record_info_status_09);//新状态：审核通过
			unbindMap.put("update_by", ShiroUtils.getLoginName());//修改人
			unbindMap.put("up_date", TimeUtil.getDayString());//修改日期
			unbindMap.put("up_time", TimeUtil.getTimeString());//修改时间
			i = agentUserTraposUnbindRecordInfoMapper.updateAgentUserTraposUnbindRecordInfoStatus(unbindMap);
			if(i!=1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "编号"+unbindMap.get("unbind_id").toString()+"：解绑状态更新失败");
			}
			//（2）根据id查询详情，主要是获取设备号（机器编号）
			Map<String, Object> unbindDetailMap = agentUserTraposUnbindRecordInfoMapper.getAgentUserTraposUnbindRecordInfoById(unbindMap.get("unbind_id").toString());
			//（3）更新该POS机的分配状态
			unbindMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			unbindMap.put("sn", unbindDetailMap.get("sn").toString());//设备号（机器编号）
			unbindMap.put("old_dis_status", TypeStatusConstant.sys_pos_info_dis_status_1);//旧状态：已分配
			unbindMap.put("new_dis_status", TypeStatusConstant.sys_pos_info_dis_status_0);//新状态：未分配
			i = agentSysTraditionalPosInfoMapper.updateAgentSysTraditionalPosInfoDisStatus(unbindMap);
			if(i!=1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "编号"+unbindMap.get("unbind_id").toString()+"：分配状态更新失败");
			}
			//（4）根据用户编号查询用户详情，主要是获取父级链
			Map<String, Object> userDetailMap = agentUserInfoMapper.getAgentUserMapById(unbindDetailMap.get("user_id").toString());
			//（5）删除整条链上面的用户MPOS关系
			if(!StringUtils.isEmpty(StringUtil.getMapValue(userDetailMap, "parent_chain"))) {
				unbindMap.put("parent_chain", userDetailMap.get("parent_chain").toString()+","+unbindDetailMap.get("user_id").toString());
			}else{
				unbindMap.put("parent_chain", unbindDetailMap.get("user_id").toString());
			}
			unbindMap.put("user_id", unbindDetailMap.get("user_id").toString());//用户编号
			unbindMap.put("old_del", TypeStatusConstant.user_pos_info_del_0);//旧状态：未删除
			unbindMap.put("new_del", TypeStatusConstant.user_pos_info_del_1);//新状态：已删除
			i = agentUserTraditionalPosInfoMapper.updateAgentSameChainUserTraditionalPosInfoDel(unbindMap);
			if(i<1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "编号"+unbindMap.get("unbind_id").toString()+"：关系解绑失败");
			}
			//(6)清楚机器标记
			sysPosPolicyMapper.delPolicySNInfoBySN(unbindDetailMap.get("sn").toString());
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "申请(MPOS)编号"+unbindMap.get("unbind_id").toString()+"：审核异常");
		}
	}
	
	
	/**
	 * 批量审核不通过用户传统POS解绑申请
	 * @param params
	 * @return
	 */
	private R batchRefuseAgentUserTraposUnbindRecordInfo(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        //拼接id转换成long型数组
        String[] unbind_ids = Convert.toStrArray(StringUtil.getMapValue(params, "unbind_ids"));
    	Map<String, Object> unbindMap = new HashMap<>();
        for(int i=0;i<unbind_ids.length;i++) {
        	unbindMap.put("remark", params.get("remark"));
        	unbindMap.put("unbind_id", unbind_ids[i]);
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).refuseAgentUserTraposUnbindRecordInfo(unbindMap);
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
	 * 单个审核不通过用户MPOS解绑申请
	 * @param applyMap
	 * @return
	 */
	@Transactional
	public R refuseAgentUserTraposUnbindRecordInfo(Map<String, Object> unbindMap) {
		try {
			int i=0;
			//（1）更新审核状态
			unbindMap.put("old_status", TypeStatusConstant.user_trapos_unbind_record_info_status_00);//旧状态：待审核
			unbindMap.put("new_status", TypeStatusConstant.user_trapos_unbind_record_info_status_08);//新状态：审核不通过
			unbindMap.put("update_by", ShiroUtils.getLoginName());//修改人
			unbindMap.put("up_date", TimeUtil.getDayString());//修改日期
			unbindMap.put("up_time", TimeUtil.getTimeString());//修改时间
			i = agentUserTraposUnbindRecordInfoMapper.updateAgentUserTraposUnbindRecordInfoStatus(unbindMap);
			if(i!=1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "编号"+unbindMap.get("unbind_id").toString()+"：解绑状态更新失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "申请(MPOS)编号"+unbindMap.get("unbind_id").toString()+"：审核异常");
		}
	}


}

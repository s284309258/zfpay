package com.ruoyi.project.deveagent.activitytrapos.service.impl;

import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.activitytrapos.domain.AgentUserTraposActivityApplyInfo;
import com.ruoyi.project.deveagent.activitytrapos.mapper.AgentUserEposActivityApplyInfoMapper;
import com.ruoyi.project.deveagent.activitytrapos.mapper.AgentUserTraposActivityApplyInfoMapper;
import com.ruoyi.project.deveagent.activitytrapos.service.AgentUserEposActivityApplyInfoService;
import com.ruoyi.project.deveagent.activitytrapos.service.AgentUserTraposActivityApplyInfoService;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraditionalPosInfoMapper;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 代理====》用户线上活动(传统POS)申请记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserEposActivityApplyInfoServiceImpl implements AgentUserEposActivityApplyInfoService {


	@Autowired
	private AgentUserEposActivityApplyInfoMapper agentUserEposActivityApplyInfoMapper;
	@Autowired
	private AgentUserTraditionalPosInfoMapper agentUserTraposInfoMapper;



	/**
	 * 查询用户线上活动(传统POS)申请记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTraposActivityApplyInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserEposActivityApplyInfoMapper.getAgentUserTraposActivityApplyInfoList(params);
	}


	/**
	 * 导出用户线上活动(传统POS)申请记录列表
	 */
	@Override
	public List<AgentUserTraposActivityApplyInfo> selectAgentUserTraposActivityApplyInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserEposActivityApplyInfoMapper.selectAgentUserTraposActivityApplyInfoList(params);
	}


	/**
	 * 查询用户线上活动(传统POS)申请记录详情
	 */
	@Override
	public Map<String, Object> getAgentUserTraposActivityApplyInfoDetailById(String id) {
		return agentUserEposActivityApplyInfoMapper.getAgentUserTraposActivityApplyInfoDetailById(id);
	}


	/**
	 * 系统批量审核线上活动(传统POS)申请
	 */
	@Override
	public R batchSysAuditUserTraposActivityApplyInfo(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
        	return R.error(Type.WARN, "请输入操作备注");
        }
        if(TypeStatusConstant.user_pos_activity_info_status_09.equals(StringUtil.getMapValue(params, "status"))) {
        	//批量审核通过
        	return this.batchAcceptAgentUserTraposActivityApplyInfo(params);
        }else if(TypeStatusConstant.user_pos_activity_info_status_08.equals(StringUtil.getMapValue(params, "status"))){
        	//批量审核不通过
        	return this.batchRefuseAgentUserTraposActivityApplyInfo(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}


	/**
	 * 批量审核通过线上活动(传统POS)申请
	 * @param params
	 * @return
	 */
	private R batchAcceptAgentUserTraposActivityApplyInfo(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] apply_ids = Convert.toStrArray(StringUtil.getMapValue(params, "apply_ids"));
    	Map<String, Object> activityApplyMap = new HashMap<>();
        for(int i=0;i<apply_ids.length;i++) {
        	activityApplyMap.put("remark", params.get("remark"));
        	activityApplyMap.put("apply_id", apply_ids[i]);
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).acceptAgentUserTraposActivityApplyInfo(activityApplyMap);
        	if(Type.SUCCESS.value.equals(result.get("code").toString())) {
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
	 * 单个审核通过线上活动(传统POS)申请
	 * @param activityApplyMap
	 * @return
	 */
	@Transactional
	public R acceptAgentUserTraposActivityApplyInfo(Map<String, Object> activityApplyMap) {
		try {
			int i=0;
			//（1）更新审核状态
			activityApplyMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			activityApplyMap.put("old_status", TypeStatusConstant.user_pos_activity_info_status_00);//旧状态：待审核
			activityApplyMap.put("new_status", TypeStatusConstant.user_pos_activity_info_status_09);//新状态：审核通过
			activityApplyMap.put("update_by", ShiroUtils.getLoginName());//修改人
			activityApplyMap.put("up_date", TimeUtil.getDayString());//修改日期
			activityApplyMap.put("up_time", TimeUtil.getTimeString());//修改时间
			i = agentUserEposActivityApplyInfoMapper.updateAgentUserTraposActivityApplyInfoStatus(activityApplyMap);
			if(i!=1) {
				return R.error(Type.WARN, "申请活动(传统POS)编号"+activityApplyMap.get("apply_id").toString()+"：状态更新失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.WARN, "申请活动(传统POS)编号"+activityApplyMap.get("apply_id").toString()+"：审核异常");
		}
	}


	/**
	 * 批量审核不通过线上活动(传统POS)申请
	 * @param params
	 * @return
	 */
	private R batchRefuseAgentUserTraposActivityApplyInfo(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] apply_ids = Convert.toStrArray(StringUtil.getMapValue(params, "apply_ids"));
    	Map<String, Object> activityApplyMap = new HashMap<>();
        for(int i=0;i<apply_ids.length;i++) {
        	activityApplyMap.put("remark", params.get("remark"));
        	activityApplyMap.put("apply_id", apply_ids[i]);
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).refuseAgentUserTraposActivityApplyInfo(activityApplyMap);
        	if(Type.SUCCESS.value.equals(result.get("code").toString())) {
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
	 * 单个审核不通过线上活动(传统POS)申请
	 * @param activityApplyMap
	 * @return
	 */
	@Transactional
	public R refuseAgentUserTraposActivityApplyInfo(Map<String, Object> activityApplyMap) {
		try {
			int i=0;
			//（1）更新审核状态
			activityApplyMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			activityApplyMap.put("old_status", TypeStatusConstant.user_pos_activity_info_status_00);//旧状态：待审核
			activityApplyMap.put("new_status", TypeStatusConstant.user_pos_activity_info_status_08);//新状态：审核不通过
			activityApplyMap.put("update_by", ShiroUtils.getLoginName());//修改人
			activityApplyMap.put("up_date", TimeUtil.getDayString());//修改日期
			activityApplyMap.put("up_time", TimeUtil.getTimeString());//修改时间
			i = agentUserEposActivityApplyInfoMapper.updateAgentUserTraposActivityApplyInfoStatus(activityApplyMap);
			if(i!=1) {
				return R.error(Type.WARN, "申请活动(传统POS)编号"+activityApplyMap.get("apply_id").toString()+"：状态更新失败");
			}
			//（2）查询详情，获取sn_list
			Map<String, Object> applyDetailMap = agentUserEposActivityApplyInfoMapper.getAgentUserTraposActivityApplyInfoMapById(activityApplyMap.get("apply_id").toString());
			//（3）更新用户pos机的参与活动状态
			int m = applyDetailMap.get("sn_list").toString().split(",").length;
			activityApplyMap.put("user_id", applyDetailMap.get("user_id").toString());//用户编号
			activityApplyMap.put("sn_list", applyDetailMap.get("sn_list").toString());//pos机信息
			activityApplyMap.put("old_activity_status", TypeStatusConstant.user_pos_info_activity_status_1);//旧状态：是
			activityApplyMap.put("new_activity_status", TypeStatusConstant.user_pos_info_activity_status_0);//新状态：否
			int n = agentUserTraposInfoMapper.updateAgentUserTraditionalPosInfoActivityStatus(activityApplyMap);
			if(m != n) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "申请活动(传统POS)编号"+activityApplyMap.get("apply_id").toString()+"：更新数量不对");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "申请活动(传统POS)编号"+activityApplyMap.get("apply_id").toString()+"：审核异常");
		}
	}
}

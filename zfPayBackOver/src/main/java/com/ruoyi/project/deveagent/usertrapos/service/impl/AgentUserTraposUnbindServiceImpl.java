package com.ruoyi.project.deveagent.usertrapos.service.impl;

import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.syspos.mapper.AgentSysMposInfoMapper;
import com.ruoyi.project.deveagent.syspos.mapper.AgentSysTraditionalPosInfoMapper;
import com.ruoyi.project.deveagent.syspospolicy.mapper.SysPosPolicyMapper;
import com.ruoyi.project.deveagent.user.mapper.AgentUserInfoMapper;
import com.ruoyi.project.deveagent.usermpos.mapper.AgentUserMposInfoMapper;
import com.ruoyi.project.deveagent.usermpos.mapper.AgentUserMposUnbindMapper;
import com.ruoyi.project.deveagent.usermpos.service.AgentUserMposUnbindService;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraditionalPosInfoMapper;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraposUnbindMapper;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposUnbindService;
import com.ruoyi.project.develop.common.domain.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 代理====》用户MPOS解绑记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserTraposUnbindServiceImpl implements AgentUserTraposUnbindService {

	@Autowired
	private AgentUserTraposUnbindMapper agentUserTraposUnbindMapper;

	@Autowired
	private AgentSysTraditionalPosInfoMapper agentSysTraposInfoMapper;
	@Autowired
	private AgentUserTraditionalPosInfoMapper agentUserTraposInfoMapper;
	@Autowired
	private AgentUserInfoMapper agentUserInfoMapper;
	@Autowired
	private SysPosPolicyMapper sysPosPolicyMapper;



	/**
	 * 查询用户MPOS解绑记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTraposUnbindList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserTraposUnbindMapper.getAgentUserTraposUnbindList(params);
	}

	@Override
	public R removePosBind(Map<String, Object> params) {

		//拼接id转换成long型数组
		String[] unbind_ids = Convert.toStrArray(StringUtil.getMapValue(params, "ids"));
		Map<String, Object> unbindMap = new HashMap<>();
		for(int ii=0;ii<unbind_ids.length;ii++) {
			unbindMap.put("unbind_id", unbind_ids[ii]);
			try {
				int i=0;
				//（1）更新审核状态
				unbindMap.put("old_status", TypeStatusConstant.user_trapos_unbind_record_info_status_00);//旧状态：待审核
				unbindMap.put("new_status", TypeStatusConstant.user_trapos_unbind_record_info_status_09);//新状态：审核通过
				unbindMap.put("update_by", ShiroUtils.getLoginName());//修改人
				unbindMap.put("up_date", TimeUtil.getDayString());//修改日期
				unbindMap.put("up_time", TimeUtil.getTimeString());//修改时间
				//（2）根据id查询详情，主要是获取设备号（机器编号）
				Map<String, Object> unbindDetailMap = agentUserTraposInfoMapper.getAgentUserTraditionalPosInfoById(unbind_ids[ii]);
				//（3）更新该POS机的分配状态
				unbindMap.put("manager_id", ShiroUtils.getUserId());//代理编号
				unbindMap.put("sn", unbindDetailMap.get("sn").toString());//设备号（机器编号）
				unbindMap.put("old_dis_status", TypeStatusConstant.sys_pos_info_dis_status_1);//旧状态：已分配
				unbindMap.put("new_dis_status", TypeStatusConstant.sys_pos_info_dis_status_0);//新状态：未分配
				i = agentSysTraposInfoMapper.updateAgentSysTraditionalPosInfoDisStatus(unbindMap);
				if(i!=1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(R.Type.WARN, "编号"+unbindMap.get("unbind_id").toString()+"：分配状态更新失败");
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
				i = agentUserTraposInfoMapper.updateAgentSameChainUserTraditionalPosInfoDel(unbindMap);
				if(i<1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(R.Type.WARN, "编号"+unbindMap.get("unbind_id").toString()+"：关系解绑失败");
				}
				//(6)清楚机器标记
				sysPosPolicyMapper.delPolicySNInfoBySN(unbindDetailMap.get("sn").toString());
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(R.Type.WARN, "申请(MPOS)编号"+unbindMap.get("unbind_id").toString()+"：审核异常");
			}
		}
		return R.ok("操作成功");
	}


}

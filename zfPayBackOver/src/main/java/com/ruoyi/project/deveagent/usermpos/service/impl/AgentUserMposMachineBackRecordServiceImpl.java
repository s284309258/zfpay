package com.ruoyi.project.deveagent.usermpos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposMachineBackRecord;
import com.ruoyi.project.deveagent.usermpos.mapper.AgentUserMposMachineBackRecordMapper;
import com.ruoyi.project.deveagent.usermpos.service.AgentUserMposMachineBackRecordService;


/**
 * 代理====》用户MPOS机具返现记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserMposMachineBackRecordServiceImpl implements AgentUserMposMachineBackRecordService {
	
	
	@Autowired
	private AgentUserMposMachineBackRecordMapper agentUserMposMachineBackRecordMapper;


	
	/**
	 * 查询用户MPOS机具返现记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserMposMachineBackRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserMposMachineBackRecordMapper.getAgentUserMposMachineBackRecordList(params);
	}
	
	
	/**
	 * 导出用户MPOS机具返现记录列表
	 */
	@Override
	public List<AgentUserMposMachineBackRecord> selectAgentUserMposMachineBackRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserMposMachineBackRecordMapper.selectAgentUserMposMachineBackRecordList(params);
	}

}

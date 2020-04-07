package com.ruoyi.project.deveagent.usertrapos.service.impl;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposMachineBackRecord;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserEposMachineBackRecordMapper;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraposMachineBackRecordMapper;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserEposMachineBackRecordService;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposMachineBackRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 代理====》用户传统POS机具返现记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserEposMachineBackRecordServiceImpl implements AgentUserEposMachineBackRecordService {
	
	
	@Autowired
	private AgentUserEposMachineBackRecordMapper agentUserEposMachineBackRecordMapper;


	
	/**
	 * 查询用户传统POS机具返现记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTraposMachineBackRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserEposMachineBackRecordMapper.getAgentUserTraposMachineBackRecordList(params);
	}
	
	
	/**
	 * 导出用户传统POS机具返现记录列表
	 */
	@Override
	public List<AgentUserTraposMachineBackRecord> selectAgentUserTraposMachineBackRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserEposMachineBackRecordMapper.selectAgentUserTraposMachineBackRecordList(params);
	}

}

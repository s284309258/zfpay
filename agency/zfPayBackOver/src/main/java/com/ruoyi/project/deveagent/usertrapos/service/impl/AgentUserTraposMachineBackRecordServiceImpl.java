package com.ruoyi.project.deveagent.usertrapos.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposMachineBackRecord;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraposMachineBackRecordMapper;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposMachineBackRecordService;


/**
 * 代理====》用户传统POS机具返现记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserTraposMachineBackRecordServiceImpl extends BasicSerivce implements AgentUserTraposMachineBackRecordService {
	
	
	@Autowired
	private AgentUserTraposMachineBackRecordMapper agentUserTraposMachineBackRecordMapper;


	
	/**
	 * 查询用户传统POS机具返现记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTraposMachineBackRecordList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserTraposMachineBackRecordMapper.getAgentUserTraposMachineBackRecordList(params);
	}
	
	
	/**
	 * 导出用户传统POS机具返现记录列表
	 */
	@Override
	public List<AgentUserTraposMachineBackRecord> selectAgentUserTraposMachineBackRecordList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserTraposMachineBackRecordMapper.selectAgentUserTraposMachineBackRecordList(params);
	}

}

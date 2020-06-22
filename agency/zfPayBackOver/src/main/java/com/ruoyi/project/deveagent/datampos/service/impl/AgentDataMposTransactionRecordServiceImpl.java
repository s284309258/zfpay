package com.ruoyi.project.deveagent.datampos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.datampos.domain.AgentDataMposTransactionRecord;
import com.ruoyi.project.deveagent.datampos.mapper.AgentDataMposTransactionRecordMapper;
import com.ruoyi.project.deveagent.datampos.service.AgentDataMposTransactionRecordService;


/**
 * 代理======MPOS交易记录管理
 * @author Administrator
 *
 */
@Service
public class AgentDataMposTransactionRecordServiceImpl implements AgentDataMposTransactionRecordService {
	
	
	@Autowired
	private AgentDataMposTransactionRecordMapper agentDataMposTransactionRecordMapper;


	
	/**
	 * 查询MPOS交易记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentDataMposTransactionRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataMposTransactionRecordMapper.getAgentDataMposTransactionRecordList(params);
	}
	
	
	/**
	 * 导出MPOS交易记录
	 */
	@Override
	public List<AgentDataMposTransactionRecord> selectAgentDataMposTransactionRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataMposTransactionRecordMapper.selectAgentDataMposTransactionRecordList(params);
	}
	
}

package com.ruoyi.project.deveagent.datatrapos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.datatrapos.domain.AgentDataTraposTransactionRecord;
import com.ruoyi.project.deveagent.datatrapos.mapper.AgentDataTraposTransactionRecordMapper;
import com.ruoyi.project.deveagent.datatrapos.service.AgentDataTraposTransactionRecordService;


/**
 * 代理======Trapos交易记录管理
 * @author Administrator
 *
 */
@Service
public class AgentDataTraposTransactionRecordServiceImpl implements AgentDataTraposTransactionRecordService {
	
	
	@Autowired
	private AgentDataTraposTransactionRecordMapper agentDataTraposTransactionRecordMapper;


	
	/**
	 * 查询Trapos交易记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentDataTraposTransactionRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataTraposTransactionRecordMapper.getAgentDataTraposTransactionRecordList(params);
	}
	
	
	/**
	 * 导出Trapos交易记录
	 */
	@Override
	public List<AgentDataTraposTransactionRecord> selectAgentDataTraposTransactionRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataTraposTransactionRecordMapper.selectAgentDataTraposTransactionRecordList(params);
	}
	
}

package com.ruoyi.project.deveagent.datatrapos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.datatrapos.domain.AgentDataTraposTransactionRecordDeal;
import com.ruoyi.project.deveagent.datatrapos.mapper.AgentDataTraposTransactionRecordDealMapper;
import com.ruoyi.project.deveagent.datatrapos.service.AgentDataTraposTransactionRecordDealService;


/**
 * 代理======Trapos交易记录管理
 * @author Administrator
 *
 */
@Service
public class AgentDataTraposTransactionRecordDealServiceImpl implements AgentDataTraposTransactionRecordDealService {
	
	
	@Autowired
	private AgentDataTraposTransactionRecordDealMapper agentDataTraposTransactionRecordDealMapper;


	
	/**
	 * 查询Trapos交易记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentDataTraposTransactionRecordDealList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataTraposTransactionRecordDealMapper.getAgentDataTraposTransactionRecordDealList(params);
	}
	
	
	/**
	 * 导出Trapos交易记录
	 */
	@Override
	public List<AgentDataTraposTransactionRecordDeal> selectAgentDataTraposTransactionRecordDealList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataTraposTransactionRecordDealMapper.selectAgentDataTraposTransactionRecordDealList(params);
	}
	
}

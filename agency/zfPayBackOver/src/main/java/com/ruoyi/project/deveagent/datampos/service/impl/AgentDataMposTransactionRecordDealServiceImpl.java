package com.ruoyi.project.deveagent.datampos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.datampos.domain.AgentDataMposTransactionRecordDeal;
import com.ruoyi.project.deveagent.datampos.mapper.AgentDataMposTransactionRecordDealMapper;
import com.ruoyi.project.deveagent.datampos.service.AgentDataMposTransactionRecordDealService;


/**
 * 代理======MPOS交易记录管理
 * @author Administrator
 *
 */
@Service
public class AgentDataMposTransactionRecordDealServiceImpl implements AgentDataMposTransactionRecordDealService {
	
	
	@Autowired
	private AgentDataMposTransactionRecordDealMapper agentDataMposTransactionRecordDealMapper;


	
	/**
	 * 查询MPOS交易记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentDataMposTransactionRecordDealList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataMposTransactionRecordDealMapper.getAgentDataMposTransactionRecordDealList(params);
	}
	
	
	/**
	 * 导出MPOS交易记录
	 */
	@Override
	public List<AgentDataMposTransactionRecordDeal> selectAgentDataMposTransactionRecordDealList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataMposTransactionRecordDealMapper.selectAgentDataMposTransactionRecordDealList(params);
	}
	
}

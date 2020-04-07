package com.ruoyi.project.deveagent.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.user.domain.AgentBenefitRecordMoney;
import com.ruoyi.project.deveagent.user.mapper.AgentBenefitRecordMoneyMapper;
import com.ruoyi.project.deveagent.user.service.AgentBenefitRecordMoneyService;


/**
 * 代理====》用户资金流水管理
 * @author Administrator
 *
 */
@Service
public class AgentBenefitRecordMoneyServiceImpl implements AgentBenefitRecordMoneyService {
	
	
	@Autowired
	private AgentBenefitRecordMoneyMapper agentBenefitRecordMoneyMapper;


	
	/**
	 * 查询用户资金流水列表
	 */
	@Override
	public List<Map<String, Object>> getAgentBenefitRecordMoneyList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentBenefitRecordMoneyMapper.getAgentBenefitRecordMoneyList(params);
	}
	
	
	/**
	 * 导出用户资金流水列表
	 */
	@Override
	public List<AgentBenefitRecordMoney> selectAgentBenefitRecordMoneyList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentBenefitRecordMoneyMapper.selectAgentBenefitRecordMoneyList(params);
	}
	
}

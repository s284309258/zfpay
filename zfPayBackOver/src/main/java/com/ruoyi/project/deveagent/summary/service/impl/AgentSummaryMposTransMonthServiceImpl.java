package com.ruoyi.project.deveagent.summary.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryMposTransMonth;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryMposTransMonthMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryMposTransMonthService;


/**
 * 代理======MPOS商户数据汇总(每月)管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryMposTransMonthServiceImpl implements AgentSummaryMposTransMonthService {
	
	
	@Autowired
	private AgentSummaryMposTransMonthMapper agentSummaryMposTransMonthMapper;


	
	/**
	 * 查询MPOS商户数据汇总(每月)列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryMposTransMonthList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSummaryMposTransMonthMapper.getAgentSummaryMposTransMonthList(params);
	}
	
	
	/**
	 * 导出MPOS商户数据汇总(每月)列表
	 */
	@Override
	public List<AgentSummaryMposTransMonth> selectAgentSummaryMposTransMonthList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSummaryMposTransMonthMapper.selectAgentSummaryMposTransMonthList(params);
	}
	
}

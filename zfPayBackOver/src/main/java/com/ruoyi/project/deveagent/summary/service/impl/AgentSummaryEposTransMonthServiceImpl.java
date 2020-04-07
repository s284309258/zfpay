package com.ruoyi.project.deveagent.summary.service.impl;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryTraditionalPosTransMonth;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryEposTransMonthMapper;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryTraditionalPosTransMonthMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryEposTransMonthService;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryTraditionalPosTransMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 代理======传统POS商户数据汇总(每月)管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryEposTransMonthServiceImpl implements AgentSummaryEposTransMonthService {
	
	
	@Autowired
	private AgentSummaryEposTransMonthMapper agentSummaryEposTransMonthMapper;


	
	/**
	 * 查询传统POS商户数据汇总(每月)列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryTraditionalPosTransMonthList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSummaryEposTransMonthMapper.getAgentSummaryTraditionalPosTransMonthList(params);
	}
	
	
	/**
	 * 导出传统POS商户数据汇总(每月)列表
	 */
	@Override
	public List<AgentSummaryTraditionalPosTransMonth> selectAgentSummaryTraditionalPosTransMonthList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSummaryEposTransMonthMapper.selectAgentSummaryTraditionalPosTransMonthList(params);
	}
	
}

package com.ruoyi.project.deveagent.summary.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryTraditionalPosTransMonth;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryTraditionalPosTransMonthMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryTraditionalPosTransMonthService;


/**
 * 代理======传统POS商户数据汇总(每月)管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryTraditionalPosTransMonthServiceImpl extends BasicSerivce implements AgentSummaryTraditionalPosTransMonthService {
	
	
	@Autowired
	private AgentSummaryTraditionalPosTransMonthMapper agentSummaryTraditionalPosTransMonthMapper;


	
	/**
	 * 查询传统POS商户数据汇总(每月)列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryTraditionalPosTransMonthList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryTraditionalPosTransMonthMapper.getAgentSummaryTraditionalPosTransMonthList(params);
	}
	
	
	/**
	 * 导出传统POS商户数据汇总(每月)列表
	 */
	@Override
	public List<AgentSummaryTraditionalPosTransMonth> selectAgentSummaryTraditionalPosTransMonthList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryTraditionalPosTransMonthMapper.selectAgentSummaryTraditionalPosTransMonthList(params);
	}

	@Override
	public Map<String, Object> summaryTraditionalPosTransAllList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryTraditionalPosTransMonthMapper.summaryTraditionalPosTransAllList(params);
	}

}

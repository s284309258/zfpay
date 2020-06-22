package com.ruoyi.project.deveagent.summary.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserTraditionalPosBenefitMonth;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryUserTraditionalPosBenefitMonthMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryUserTraditionalPosBenefitMonthService;


/**
 * 代理=====传统POS代理数据汇总（每月）管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryUserTraditionalPosBenefitMonthServiceImpl extends BasicSerivce implements AgentSummaryUserTraditionalPosBenefitMonthService {
	
	
	@Autowired
	private AgentSummaryUserTraditionalPosBenefitMonthMapper agentSummaryUserTraditionalPosBenefitMonthMapper;


	
	/**
	 * 查询传统POS代理数据汇总（每月）列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryUserTraditionalPosBenefitMonthList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryUserTraditionalPosBenefitMonthMapper.getAgentSummaryUserTraditionalPosBenefitMonthList(params);
	}
	
	
	/**
	 * 导出传统POS代理数据汇总（每月）列表
	 */
	@Override
	public List<AgentSummaryUserTraditionalPosBenefitMonth> selectAgentSummaryUserTraditionalPosBenefitMonthList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryUserTraditionalPosBenefitMonthMapper.selectAgentSummaryUserTraditionalPosBenefitMonthList(params);
	}
	
}

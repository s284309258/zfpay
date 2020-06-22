package com.ruoyi.project.deveagent.summary.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserMposBenefitMonth;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryUserMposBenefitMonthMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryUserMposBenefitMonthService;


/**
 * 代理=====MPOS代理数据汇总（每月）管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryUserMposBenefitMonthServiceImpl extends BasicSerivce implements AgentSummaryUserMposBenefitMonthService {
	
	
	@Autowired
	private AgentSummaryUserMposBenefitMonthMapper agentSummaryUserMposBenefitMonthMapper;


	
	/**
	 * 查询MPOS代理数据汇总（每月）列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryUserMposBenefitMonthList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryUserMposBenefitMonthMapper.getAgentSummaryUserMposBenefitMonthList(params);
	}
	
	
	/**
	 * 导出MPOS代理数据汇总（每月）列表
	 */
	@Override
	public List<AgentSummaryUserMposBenefitMonth> selectAgentSummaryUserMposBenefitMonthList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryUserMposBenefitMonthMapper.selectAgentSummaryUserMposBenefitMonthList(params);
	}
	
}

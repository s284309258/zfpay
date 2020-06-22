package com.ruoyi.project.deveagent.summary.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserTraditionalPosBenefitEveryDay;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryUserTraditionalPosBenefitEveryDayMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryUserTraditionalPosBenefitEveryDayService;


/**
 * 代理=====传统POS代理数据汇总（每日）管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryUserTraditionalPosBenefitEveryDayServiceImpl extends BasicSerivce implements AgentSummaryUserTraditionalPosBenefitEveryDayService {
	
	
	@Autowired
	private AgentSummaryUserTraditionalPosBenefitEveryDayMapper agentSummaryUserTraditionalPosBenefitEveryDayMapper;


	
	/**
	 * 查询传统POS代理数据汇总（每日）列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryUserTraditionalPosBenefitEveryDayList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryUserTraditionalPosBenefitEveryDayMapper.getAgentSummaryUserTraditionalPosBenefitEveryDayList(params);
	}
	
	
	/**
	 * 导出传统POS代理数据汇总（每日）列表
	 */
	@Override
	public List<AgentSummaryUserTraditionalPosBenefitEveryDay> selectAgentSummaryUserTraditionalPosBenefitEveryDayList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryUserTraditionalPosBenefitEveryDayMapper.selectAgentSummaryUserTraditionalPosBenefitEveryDayList(params);
	}
	
}

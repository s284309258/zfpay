package com.ruoyi.project.deveagent.summary.service.impl;

import com.ruoyi.common.utils.BasicSerivce;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserTraditionalPosBenefitEveryDay;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryUserEposBenefitEveryDayMapper;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryUserTraditionalPosBenefitEveryDayMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryUserEposBenefitEveryDayService;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryUserTraditionalPosBenefitEveryDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 代理=====传统POS代理数据汇总（每日）管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryUserEposBenefitEveryDayServiceImpl extends BasicSerivce implements AgentSummaryUserEposBenefitEveryDayService {
	
	
	@Autowired
	private AgentSummaryUserEposBenefitEveryDayMapper agentSummaryUserEposBenefitEveryDayMapper;


	
	/**
	 * 查询传统POS代理数据汇总（每日）列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryUserTraditionalPosBenefitEveryDayList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryUserEposBenefitEveryDayMapper.getAgentSummaryUserTraditionalPosBenefitEveryDayList(params);
	}
	
	
	/**
	 * 导出传统POS代理数据汇总（每日）列表
	 */
	@Override
	public List<AgentSummaryUserTraditionalPosBenefitEveryDay> selectAgentSummaryUserTraditionalPosBenefitEveryDayList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryUserEposBenefitEveryDayMapper.selectAgentSummaryUserTraditionalPosBenefitEveryDayList(params);
	}
	
}

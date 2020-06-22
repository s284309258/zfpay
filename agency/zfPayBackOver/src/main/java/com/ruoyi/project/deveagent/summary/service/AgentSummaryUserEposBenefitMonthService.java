package com.ruoyi.project.deveagent.summary.service;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserTraditionalPosBenefitMonth;

import java.util.List;
import java.util.Map;

/**
 * 代理======传统POS代理数据汇总（每月）管理
 * @author Administrator
 *
 */
public interface AgentSummaryUserEposBenefitMonthService {

	
	/**
	 * 查询传统POS代理数据汇总（每月）列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryUserTraditionalPosBenefitMonthList(Map<String, Object> params);
	
	
	/**
	 * 导出传统POS代理数据汇总（每月）信息
	 * @param params
	 * @return
	 */
	List<AgentSummaryUserTraditionalPosBenefitMonth> selectAgentSummaryUserTraditionalPosBenefitMonthList(Map<String, Object> params);

}
	
	

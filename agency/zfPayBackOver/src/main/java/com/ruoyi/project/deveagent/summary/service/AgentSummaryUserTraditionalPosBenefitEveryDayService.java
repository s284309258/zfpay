package com.ruoyi.project.deveagent.summary.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserTraditionalPosBenefitEveryDay;

/**
 * 代理======传统POS代理数据汇总（每日）管理
 * @author Administrator
 *
 */
public interface AgentSummaryUserTraditionalPosBenefitEveryDayService {

	
	/**
	 * 查询传统POS代理数据汇总（每日）列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryUserTraditionalPosBenefitEveryDayList(Map<String, Object> params);
	
	
	/**
	 * 导出传统POS代理数据汇总（每日）信息
	 * @param params
	 * @return
	 */
	List<AgentSummaryUserTraditionalPosBenefitEveryDay> selectAgentSummaryUserTraditionalPosBenefitEveryDayList(Map<String, Object> params);

}
	
	

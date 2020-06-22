package com.ruoyi.project.deveagent.summary.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserMposBenefitEveryDay;

/**
 * 代理======MPOS代理数据汇总（每日）管理
 * @author Administrator
 *
 */
public interface AgentSummaryUserMposBenefitEveryDayService {

	
	/**
	 * 查询MPOS代理数据汇总（每日）列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryUserMposBenefitEveryDayList(Map<String, Object> params);
	
	
	/**
	 * 导出MPOS代理数据汇总（每日）信息
	 * @param params
	 * @return
	 */
	List<AgentSummaryUserMposBenefitEveryDay> selectAgentSummaryUserMposBenefitEveryDayList(Map<String, Object> params);

}
	
	

package com.ruoyi.project.deveagent.summary.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserMposBenefitMonth;

/**
 * 代理======MPOS代理数据汇总（每月）管理
 * @author Administrator
 *
 */
public interface AgentSummaryUserMposBenefitMonthService {

	
	/**
	 * 查询MPOS代理数据汇总（每月）列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryUserMposBenefitMonthList(Map<String, Object> params);
	
	
	/**
	 * 导出MPOS代理数据汇总（每月）信息
	 * @param params
	 * @return
	 */
	List<AgentSummaryUserMposBenefitMonth> selectAgentSummaryUserMposBenefitMonthList(Map<String, Object> params);

}
	
	

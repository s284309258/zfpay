package com.ruoyi.project.deveagent.summary.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryMposTransMonth;

/**
 * 代理======MPOS商户数据汇总(每月)管理
 * @author Administrator
 *
 */
public interface AgentSummaryMposTransMonthService {

	
	/**
	 * 查询MPOS商户数据汇总(每月)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryMposTransMonthList(Map<String, Object> params);
	
	
	/**
	 * 导出MPOS商户数据汇总(每月)信息
	 * @param params
	 * @return
	 */
	List<AgentSummaryMposTransMonth> selectAgentSummaryMposTransMonthList(Map<String, Object> params);

	/***
	 *
	 * @param params
	 * @return
	 */
	Map<String,Object> summaryMposTransAllList(Map<String, Object> params);

}
	
	

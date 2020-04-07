package com.ruoyi.project.deveagent.summary.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryTraditionalPosTransMonth;

/**
 * 代理======传统POS商户数据汇总(每月)管理
 * @author Administrator
 *
 */
public interface AgentSummaryTraditionalPosTransMonthService {

	
	/**
	 * 查询传统POS商户数据汇总(每月)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryTraditionalPosTransMonthList(Map<String, Object> params);
	
	
	/**
	 * 导出传统POS商户数据汇总(每月)信息
	 * @param params
	 * @return
	 */
	List<AgentSummaryTraditionalPosTransMonth> selectAgentSummaryTraditionalPosTransMonthList(Map<String, Object> params);

}
	
	

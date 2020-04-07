package com.ruoyi.project.deveagent.summary.service;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryTraditionalPosTransEveryDay;

import java.util.List;
import java.util.Map;

/**
 * 代理======传统POS商户数据汇总(每日)管理
 * @author Administrator
 *
 */
public interface AgentSummaryEposTransEveryDayService {

	
	/**
	 * 查询传统POS商户数据汇总(每日)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryTraditionalPosTransEveryDayList(Map<String, Object> params);
	
	
	/**
	 * 导出传统POS商户数据汇总(每日)信息
	 * @param params
	 * @return
	 */
	List<AgentSummaryTraditionalPosTransEveryDay> selectAgentSummaryTraditionalPosTransEveryDayList(Map<String, Object> params);

}
	
	

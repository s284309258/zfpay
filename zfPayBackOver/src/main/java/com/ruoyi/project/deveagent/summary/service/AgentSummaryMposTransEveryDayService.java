package com.ruoyi.project.deveagent.summary.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryMposTransEveryDay;

/**
 * 代理======MPOS商户数据汇总(每日)管理
 * @author Administrator
 *
 */
public interface AgentSummaryMposTransEveryDayService {

	
	/**
	 * 查询MPOS商户数据汇总(每日)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryMposTransEveryDayList(Map<String, Object> params);
	
	
	/**
	 * 导出MPOS商户数据汇总(每日)信息
	 * @param params
	 * @return
	 */
	List<AgentSummaryMposTransEveryDay> selectAgentSummaryMposTransEveryDayList(Map<String, Object> params);

}
	
	

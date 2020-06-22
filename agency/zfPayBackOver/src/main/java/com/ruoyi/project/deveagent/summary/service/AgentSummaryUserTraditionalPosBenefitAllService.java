package com.ruoyi.project.deveagent.summary.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserTraditionalPosBenefitAll;

/**
 * 代理======传统POS代理数据汇总管理
 * @author Administrator
 *
 */
public interface AgentSummaryUserTraditionalPosBenefitAllService {

	
	/**
	 * 查询传统POS代理数据汇总列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryUserTraditionalPosBenefitAllList(Map<String, Object> params);
	
	
	/**
	 * 导出传统POS代理数据汇总信息
	 * @param params
	 * @return
	 */
	List<AgentSummaryUserTraditionalPosBenefitAll> selectAgentSummaryUserTraditionalPosBenefitAllList(Map<String, Object> params);

}
	
	

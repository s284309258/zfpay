package com.ruoyi.project.deveagent.summary.service;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserTraditionalPosBenefitAll;

import java.util.List;
import java.util.Map;

/**
 * 代理======传统POS代理数据汇总管理
 * @author Administrator
 *
 */
public interface AgentSummaryUserEposBenefitAllService {

	
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
	
	

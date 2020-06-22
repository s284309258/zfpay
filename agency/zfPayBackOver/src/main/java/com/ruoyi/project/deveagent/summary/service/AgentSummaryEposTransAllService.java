package com.ruoyi.project.deveagent.summary.service;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryTraditionalPosTransAll;

import java.util.List;
import java.util.Map;

/**
 * 代理======传统POS商户数据汇总管理
 * @author Administrator
 *
 */
public interface AgentSummaryEposTransAllService {

	
	/**
	 * 查询传统POS商户数据汇总列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryTraditionalPosTransAllList(Map<String, Object> params);
	
	
	/**
	 * 导出传统POS商户数据汇总信息
	 * @param params
	 * @return
	 */
	List<AgentSummaryTraditionalPosTransAll> selectAgentSummaryTraditionalPosTransAllList(Map<String, Object> params);


	Map<String,Object> summaryTraditionalPosTransAllList(Map<String, Object> params);

}
	
	

package com.ruoyi.project.deveagent.summary.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryTraditionalPosTransAll;

/**
 * 代理======传统POS商户数据汇总管理
 * @author Administrator
 *
 */
public interface AgentSummaryTraditionalPosTransAllService {

	
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


	/***
	 *
	 * @param params
	 * @return
	 */
	Map<String,Object> summaryTraditionalPosTransAllList(Map<String, Object> params);



}
	
	

package com.ruoyi.project.deveagent.summary.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryMposTransAll;

/**
 * 代理======MPOS商户数据汇总管理
 * @author Administrator
 *
 */
public interface AgentSummaryMposTransAllService {

	
	/**
	 * 查询MPOS商户数据汇总列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryMposTransAllList(Map<String, Object> params);
	
	
	/**
	 * 导出MPOS商户数据汇总信息
	 * @param params
	 * @return
	 */
	List<AgentSummaryMposTransAll> selectAgentSummaryMposTransAllList(Map<String, Object> params);

}
	
	

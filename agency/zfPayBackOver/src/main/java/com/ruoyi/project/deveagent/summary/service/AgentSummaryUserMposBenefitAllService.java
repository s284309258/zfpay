package com.ruoyi.project.deveagent.summary.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserMposBenefitAll;

/**
 * 代理======MPOS代理数据汇总管理
 * @author Administrator
 *
 */
public interface AgentSummaryUserMposBenefitAllService {

	
	/**
	 * 查询MPOS代理数据汇总列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryUserMposBenefitAllList(Map<String, Object> params);
	
	
	/**
	 * 导出MPOS代理数据汇总信息
	 * @param params
	 * @return
	 */
	List<AgentSummaryUserMposBenefitAll> selectAgentSummaryUserMposBenefitAllList(Map<String, Object> params);

}
	
	

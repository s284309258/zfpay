package com.ruoyi.project.deveagent.summary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryTraditionalPosTransMonth;



/**
 * 代理======传统POS商户数据汇总(每月)管理
 * @author Administrator
 *
 */
public interface AgentSummaryTraditionalPosTransMonthMapper {


	/**
	 * 查询传统POS商户数据汇总(每月)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryTraditionalPosTransMonthList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出传统POS商户数据汇总(每月)列表
	 * @param params
	 * @return
	 */
	List<AgentSummaryTraditionalPosTransMonth> selectAgentSummaryTraditionalPosTransMonthList(@Param("map") Map<String, Object> params);


	Map<String,Object> summaryTraditionalPosTransAllList(@Param("map") Map<String, Object> params);


}

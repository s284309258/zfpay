package com.ruoyi.project.deveagent.summary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryMposTransMonth;



/**
 * 代理======MPOS商户数据汇总(每月)管理
 * @author Administrator
 *
 */
public interface AgentSummaryMposTransMonthMapper {


	/**
	 * 查询MPOS商户数据汇总(每月)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryMposTransMonthList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出MPOS商户数据汇总(每月)列表
	 * @param params
	 * @return
	 */
	List<AgentSummaryMposTransMonth> selectAgentSummaryMposTransMonthList(@Param("map") Map<String, Object> params);


	Map<String,Object> summaryMposTransAllList(@Param("map") Map<String, Object> params);


}

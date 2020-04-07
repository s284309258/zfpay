package com.ruoyi.project.deveagent.summary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryMposTransEveryDay;



/**
 * 代理======MPOS商户数据汇总(每日)管理
 * @author Administrator
 *
 */
public interface AgentSummaryMposTransEveryDayMapper {


	/**
	 * 查询MPOS商户数据汇总(每日)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryMposTransEveryDayList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出MPOS商户数据汇总(每日)列表
	 * @param params
	 * @return
	 */
	List<AgentSummaryMposTransEveryDay> selectAgentSummaryMposTransEveryDayList(@Param("map") Map<String, Object> params);


}

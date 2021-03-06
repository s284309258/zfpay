package com.ruoyi.project.deveagent.summary.mapper;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryTraditionalPosTransEveryDay;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 代理======传统POS商户数据汇总(每日)管理
 * @author Administrator
 *
 */
public interface AgentSummaryEposTransEveryDayMapper {


	/**
	 * 查询传统POS商户数据汇总(每日)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryTraditionalPosTransEveryDayList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出传统POS商户数据汇总(每日)列表
	 * @param params
	 * @return
	 */
	List<AgentSummaryTraditionalPosTransEveryDay> selectAgentSummaryTraditionalPosTransEveryDayList(@Param("map") Map<String, Object> params);


}

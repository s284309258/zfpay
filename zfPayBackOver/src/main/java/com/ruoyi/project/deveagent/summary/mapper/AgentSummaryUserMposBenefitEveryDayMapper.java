package com.ruoyi.project.deveagent.summary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserMposBenefitEveryDay;



/**
 * 代理=====MPOS代理数据汇总(每日)管理
 * @author Administrator
 *
 */
public interface AgentSummaryUserMposBenefitEveryDayMapper {


	/**
	 * 查询MPOS代理数据汇总(每日)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryUserMposBenefitEveryDayList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出MPOS代理数据汇总(每日)列表
	 * @param params
	 * @return
	 */
	List<AgentSummaryUserMposBenefitEveryDay> selectAgentSummaryUserMposBenefitEveryDayList(@Param("map") Map<String, Object> params);


}

package com.ruoyi.project.deveagent.summary.mapper;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserTraditionalPosBenefitEveryDay;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 代理=====传统POS代理数据汇总（每日）管理
 * @author Administrator
 *
 */
public interface AgentSummaryUserEposBenefitEveryDayMapper {


	/**
	 * 查询传统POS代理数据汇总（每日）列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryUserTraditionalPosBenefitEveryDayList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出传统POS代理数据汇总（每日）列表
	 * @param params
	 * @return
	 */
	List<AgentSummaryUserTraditionalPosBenefitEveryDay> selectAgentSummaryUserTraditionalPosBenefitEveryDayList(@Param("map") Map<String, Object> params);


}

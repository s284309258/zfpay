package com.ruoyi.project.deveagent.summary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserTraditionalPosBenefitMonth;



/**
 * 代理=====传统POS代理数据汇总（每月）管理
 * @author Administrator
 *
 */
public interface AgentSummaryUserTraditionalPosBenefitMonthMapper {


	/**
	 * 查询传统POS代理数据汇总（每月）列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryUserTraditionalPosBenefitMonthList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出传统POS代理数据汇总（每月）列表
	 * @param params
	 * @return
	 */
	List<AgentSummaryUserTraditionalPosBenefitMonth> selectAgentSummaryUserTraditionalPosBenefitMonthList(@Param("map") Map<String, Object> params);


}

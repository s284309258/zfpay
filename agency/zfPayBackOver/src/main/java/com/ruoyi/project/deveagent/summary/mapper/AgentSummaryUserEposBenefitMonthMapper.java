package com.ruoyi.project.deveagent.summary.mapper;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserTraditionalPosBenefitMonth;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 代理=====传统POS代理数据汇总（每月）管理
 * @author Administrator
 *
 */
public interface AgentSummaryUserEposBenefitMonthMapper {


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

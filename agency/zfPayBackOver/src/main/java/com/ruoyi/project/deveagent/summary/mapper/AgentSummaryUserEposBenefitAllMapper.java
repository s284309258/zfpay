package com.ruoyi.project.deveagent.summary.mapper;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserTraditionalPosBenefitAll;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 代理=====传统POS代理数据汇总管理
 * @author Administrator
 *
 */
public interface AgentSummaryUserEposBenefitAllMapper {


	/**
	 * 查询传统POS代理数据汇总列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryUserTraditionalPosBenefitAllList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出传统POS代理数据汇总列表
	 * @param params
	 * @return
	 */
	List<AgentSummaryUserTraditionalPosBenefitAll> selectAgentSummaryUserTraditionalPosBenefitAllList(@Param("map") Map<String, Object> params);


}

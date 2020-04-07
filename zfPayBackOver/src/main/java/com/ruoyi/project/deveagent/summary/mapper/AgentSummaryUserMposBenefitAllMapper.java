package com.ruoyi.project.deveagent.summary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserMposBenefitAll;



/**
 * 代理=====MPOS代理数据汇总管理
 * @author Administrator
 *
 */
public interface AgentSummaryUserMposBenefitAllMapper {


	/**
	 * 查询MPOS代理数据汇总列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryUserMposBenefitAllList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出MPOS代理数据汇总列表
	 * @param params
	 * @return
	 */
	List<AgentSummaryUserMposBenefitAll> selectAgentSummaryUserMposBenefitAllList(@Param("map") Map<String, Object> params);


}

package com.ruoyi.project.deveagent.summary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryMposTransAll;



/**
 * 代理======MPOS商户数据汇总管理
 * @author Administrator
 *
 */
public interface AgentSummaryMposTransAllMapper {


	/**
	 * 查询MPOS商户数据汇总列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryMposTransAllList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出MPOS商户数据汇总列表
	 * @param params
	 * @return
	 */
	List<AgentSummaryMposTransAll> selectAgentSummaryMposTransAllList(@Param("map") Map<String, Object> params);


}

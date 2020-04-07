package com.ruoyi.project.deveagent.summary.mapper;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryTraditionalPosTransAll;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 代理======传统POS商户数据汇总管理
 * @author Administrator
 *
 */
public interface AgentSummaryEposTransAllMapper {


	/**
	 * 查询传统POS商户数据汇总列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryTraditionalPosTransAllList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出传统POS商户数据汇总列表
	 * @param params
	 * @return
	 */
	List<AgentSummaryTraditionalPosTransAll> selectAgentSummaryTraditionalPosTransAllList(@Param("map") Map<String, Object> params);


}

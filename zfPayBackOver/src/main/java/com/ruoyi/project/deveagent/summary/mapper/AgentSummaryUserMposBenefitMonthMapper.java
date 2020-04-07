package com.ruoyi.project.deveagent.summary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserMposBenefitMonth;



/**
 * 代理======MPOS代理数据汇总(每月)管理
 * @author Administrator
 *
 */
public interface AgentSummaryUserMposBenefitMonthMapper {


	/**
	 * 查询MMPOS代理数据汇总(每月)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSummaryUserMposBenefitMonthList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出MPOS代理数据汇总(每月)列表
	 * @param params
	 * @return
	 */
	List<AgentSummaryUserMposBenefitMonth> selectAgentSummaryUserMposBenefitMonthList(@Param("map") Map<String, Object> params);


}

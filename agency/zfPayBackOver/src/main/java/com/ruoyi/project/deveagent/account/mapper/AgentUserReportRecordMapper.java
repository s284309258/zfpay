package com.ruoyi.project.deveagent.account.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.account.domain.AgentUserReportRecord;



/**
 * 代理====》代理报备管理
 * @author Administrator
 *
 */
public interface AgentUserReportRecordMapper {


	/**
	 * 查询代理报备列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserReportRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出代理报备列表
	 * @param params
	 * @return
	 */
	List<AgentUserReportRecord> selectAgentUserReportRecordList(@Param("map") Map<String, Object> params);


	/**
	 * 记录报备信息
	 * @param userReportRecordMap
	 * @return
	 */
	int addAgentUserReportRecord(@Param("map") Map<String, Object> userReportRecordMap);

}

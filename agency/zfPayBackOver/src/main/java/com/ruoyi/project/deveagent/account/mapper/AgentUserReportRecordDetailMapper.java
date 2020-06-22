package com.ruoyi.project.deveagent.account.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.account.domain.AgentUserReportRecordDetail;



/**
 * 代理====》代理报备详情管理
 * @author Administrator
 *
 */
public interface AgentUserReportRecordDetailMapper {


	/**
	 * 查询代理报备详情列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserReportRecordDetailList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出代理报备详情列表
	 * @param params
	 * @return
	 */
	List<AgentUserReportRecordDetail> selectAgentUserReportRecordDetailList(@Param("map") Map<String, Object> params);


	/**
	 * 记录代理报备详情
	 * @param userReportRecordMap
	 * @return
	 */
	int addAgentUserReportRecordDetail(@Param("map") Map<String, Object> userReportRecordMap);

}

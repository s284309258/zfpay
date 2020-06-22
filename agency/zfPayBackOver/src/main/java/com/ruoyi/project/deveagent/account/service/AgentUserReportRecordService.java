package com.ruoyi.project.deveagent.account.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.account.domain.AgentUserReportRecord;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======代理报备管理
 * @author Administrator
 *
 */
public interface AgentUserReportRecordService {

	
	/**
	 * 查询代理报备列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserReportRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出代理报备信息
	 * @param params
	 * @return
	 */
	List<AgentUserReportRecord> selectAgentUserReportRecordList(Map<String, Object> params);


	/**
	 * 代理报备
	 * @param params
	 * @return
	 */
	R sysReport(Map<String, Object> params);

}
	
	

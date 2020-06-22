package com.ruoyi.project.deveagent.account.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.account.domain.AgentUserReportRecordDetail;

/**
 * 代理======代理报备详情管理
 * @author Administrator
 *
 */
public interface AgentUserReportRecordDetailService {

	
	/**
	 * 查询代理报备详情列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserReportRecordDetailList(Map<String, Object> params);
	
	
	/**
	 * 导出代理报备详情信息
	 * @param params
	 * @return
	 */
	List<AgentUserReportRecordDetail> selectAgentUserReportRecordDetailList(Map<String, Object> params);

}
	
	

package com.ruoyi.project.deveagent.account.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.account.domain.AgentUserReportRecordDetail;
import com.ruoyi.project.deveagent.account.mapper.AgentUserReportRecordDetailMapper;
import com.ruoyi.project.deveagent.account.service.AgentUserReportRecordDetailService;


/**
 * 代理====》代理报备详情管理
 * @author Administrator
 *
 */
@Service
public class AgentUserReportRecordDetailServiceImpl implements AgentUserReportRecordDetailService {
	
	@Autowired
	private AgentUserReportRecordDetailMapper agentUserReportRecordDetailMapper;


	
	/**
	 * 查询代理报备详情列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserReportRecordDetailList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserReportRecordDetailMapper.getAgentUserReportRecordDetailList(params);
	}
	
	
	/**
	 * 导出代理报备详情列表
	 */
	@Override
	public List<AgentUserReportRecordDetail> selectAgentUserReportRecordDetailList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserReportRecordDetailMapper.selectAgentUserReportRecordDetailList(params);
	}

}

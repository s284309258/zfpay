package com.ruoyi.project.deveagent.summary.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryMposTransEveryDay;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryMposTransEveryDayMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryMposTransEveryDayService;


/**
 * 代理======MPOS商户数据汇总管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryMposTransAllServiceImpl implements AgentSummaryMposTransEveryDayService {
	
	
	@Autowired
	private AgentSummaryMposTransEveryDayMapper agentSummaryMposTransEveryDayMapper;


	
	/**
	 * 查询MPOS商户数据汇总列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryMposTransEveryDayList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSummaryMposTransEveryDayMapper.getAgentSummaryMposTransEveryDayList(params);
	}
	
	
	/**
	 * 导出MPOS商户数据汇总列表
	 */
	@Override
	public List<AgentSummaryMposTransEveryDay> selectAgentSummaryMposTransEveryDayList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSummaryMposTransEveryDayMapper.selectAgentSummaryMposTransEveryDayList(params);
	}
	
}

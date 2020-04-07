package com.ruoyi.project.deveagent.summary.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryTraditionalPosTransEveryDay;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryTraditionalPosTransEveryDayMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryTraditionalPosTransEveryDayService;


/**
 * 代理======传统POS商户数据汇总(每日)管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryTraditionalPosTransEveryDayServiceImpl implements AgentSummaryTraditionalPosTransEveryDayService {
	
	
	@Autowired
	private AgentSummaryTraditionalPosTransEveryDayMapper agentSummaryTraditionalPosTransEveryDayMapper;


	
	/**
	 * 查询传统POS商户数据汇总(每日)列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryTraditionalPosTransEveryDayList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSummaryTraditionalPosTransEveryDayMapper.getAgentSummaryTraditionalPosTransEveryDayList(params);
	}
	
	
	/**
	 * 导出传统POS商户数据汇总(每日)列表
	 */
	@Override
	public List<AgentSummaryTraditionalPosTransEveryDay> selectAgentSummaryTraditionalPosTransEveryDayList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSummaryTraditionalPosTransEveryDayMapper.selectAgentSummaryTraditionalPosTransEveryDayList(params);
	}
	
}

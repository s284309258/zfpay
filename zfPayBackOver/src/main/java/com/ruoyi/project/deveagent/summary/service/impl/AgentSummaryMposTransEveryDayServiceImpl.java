package com.ruoyi.project.deveagent.summary.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryMposTransAll;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryMposTransAllMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryMposTransAllService;


/**
 * 代理======MPOS商户数据汇总(每日)管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryMposTransEveryDayServiceImpl implements AgentSummaryMposTransAllService {
	
	
	@Autowired
	private AgentSummaryMposTransAllMapper agentSummaryMposTransAllMapper;


	
	/**
	 * 查询MPOS商户数据汇总(每日)列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryMposTransAllList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSummaryMposTransAllMapper.getAgentSummaryMposTransAllList(params);
	}
	
	
	/**
	 * 导出MPOS商户数据汇总(每日)列表
	 */
	@Override
	public List<AgentSummaryMposTransAll> selectAgentSummaryMposTransAllList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSummaryMposTransAllMapper.selectAgentSummaryMposTransAllList(params);
	}
	
}

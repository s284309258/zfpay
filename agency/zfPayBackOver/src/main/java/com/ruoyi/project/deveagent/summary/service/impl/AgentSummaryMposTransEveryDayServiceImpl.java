package com.ruoyi.project.deveagent.summary.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryMposTransEveryDay;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryMposTransEveryDayMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryMposTransEveryDayService;
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
public class AgentSummaryMposTransEveryDayServiceImpl extends BasicSerivce implements AgentSummaryMposTransEveryDayService {
	
	
	@Autowired
	private AgentSummaryMposTransEveryDayMapper agentSummaryMposTransEveryDayMapper;


	
	/**
	 * 查询MPOS商户数据汇总(每日)列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryMposTransEveryDayList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryMposTransEveryDayMapper.getAgentSummaryMposTransEveryDayList(params);
	}
	
	
	/**
	 * 导出MPOS商户数据汇总(每日)列表
	 */
	@Override
	public List<AgentSummaryMposTransEveryDay> selectAgentSummaryMposTransEveryDayList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryMposTransEveryDayMapper.selectAgentSummaryMposTransEveryDayList(params);
	}

	@Override
	public Map<String, Object> summaryMposTransAllList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryMposTransEveryDayMapper.summaryMposTransAllList(params);
	}

}

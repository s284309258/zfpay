package com.ruoyi.project.deveagent.summary.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryMposTransAll;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryMposTransAllMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryMposTransAllService;
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
public class AgentSummaryMposTransAllServiceImpl extends BasicSerivce implements AgentSummaryMposTransAllService {
	
	
	@Autowired
	private AgentSummaryMposTransAllMapper agentSummaryMposTransAllMapper;


	
	/**
	 * 查询MPOS商户数据汇总列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryMposTransAllList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryMposTransAllMapper.getAgentSummaryMposTransAllList(params);
	}
	
	
	/**
	 * 导出MPOS商户数据汇总列表
	 */
	@Override
	public List<AgentSummaryMposTransAll> selectAgentSummaryMposTransAllList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryMposTransAllMapper.selectAgentSummaryMposTransAllList(params);
	}

	@Override
	public Map<String, Object> summaryMposTransAllList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryMposTransAllMapper.summaryMposTransAllList(params);
	}

}

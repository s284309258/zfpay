package com.ruoyi.project.deveagent.summary.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserTraditionalPosBenefitAll;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryUserTraditionalPosBenefitAllMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryUserTraditionalPosBenefitAllService;


/**
 * 代理=====传统POS代理数据汇总管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryUserTraditionalPosBenefitAllServiceImpl extends BasicSerivce implements AgentSummaryUserTraditionalPosBenefitAllService {
	
	
	@Autowired
	private AgentSummaryUserTraditionalPosBenefitAllMapper agentSummaryUserTraditionalPosBenefitAllMapper;


	
	/**
	 * 查询传统POS代理数据汇总列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryUserTraditionalPosBenefitAllList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryUserTraditionalPosBenefitAllMapper.getAgentSummaryUserTraditionalPosBenefitAllList(params);
	}
	
	
	/**
	 * 导出传统POS代理数据汇总列表
	 */
	@Override
	public List<AgentSummaryUserTraditionalPosBenefitAll> selectAgentSummaryUserTraditionalPosBenefitAllList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryUserTraditionalPosBenefitAllMapper.selectAgentSummaryUserTraditionalPosBenefitAllList(params);
	}
	
}

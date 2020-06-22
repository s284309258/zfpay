package com.ruoyi.project.deveagent.summary.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryTraditionalPosTransAll;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryTraditionalPosTransAllMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryTraditionalPosTransAllService;


/**
 * 代理======传统POS商户数据汇总管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryTraditionalPosTransAllServiceImpl extends BasicSerivce implements AgentSummaryTraditionalPosTransAllService {
	
	
	@Autowired
	private AgentSummaryTraditionalPosTransAllMapper agentSummaryTraditionalPosTransAllMapper;


	
	/**
	 * 查询传统POS商户数据汇总列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryTraditionalPosTransAllList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryTraditionalPosTransAllMapper.getAgentSummaryTraditionalPosTransAllList(params);
	}
	
	
	/**
	 * 导出传统POS商户数据汇总列表
	 */
	@Override
	public List<AgentSummaryTraditionalPosTransAll> selectAgentSummaryTraditionalPosTransAllList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryTraditionalPosTransAllMapper.selectAgentSummaryTraditionalPosTransAllList(params);
	}

	@Override
	public Map<String, Object> summaryTraditionalPosTransAllList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryTraditionalPosTransAllMapper.summaryTraditionalPosTransAllList(params);
	}

}

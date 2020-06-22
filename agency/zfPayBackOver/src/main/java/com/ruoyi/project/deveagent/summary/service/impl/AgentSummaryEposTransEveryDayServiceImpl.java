package com.ruoyi.project.deveagent.summary.service.impl;

import com.ruoyi.common.utils.BasicSerivce;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryTraditionalPosTransEveryDay;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryEposTransEveryDayMapper;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryTraditionalPosTransEveryDayMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryEposTransEveryDayService;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryTraditionalPosTransEveryDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 代理======传统POS商户数据汇总(每日)管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryEposTransEveryDayServiceImpl extends BasicSerivce implements AgentSummaryEposTransEveryDayService {
	
	
	@Autowired
	private AgentSummaryEposTransEveryDayMapper agentSummaryEposTransEveryDayMapper;


	
	/**
	 * 查询传统POS商户数据汇总(每日)列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryTraditionalPosTransEveryDayList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryEposTransEveryDayMapper.getAgentSummaryTraditionalPosTransEveryDayList(params);
	}
	
	
	/**
	 * 导出传统POS商户数据汇总(每日)列表
	 */
	@Override
	public List<AgentSummaryTraditionalPosTransEveryDay> selectAgentSummaryTraditionalPosTransEveryDayList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryEposTransEveryDayMapper.selectAgentSummaryTraditionalPosTransEveryDayList(params);
	}

	@Override
	public Map<String, Object> summaryTraditionalPosTransAllList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryEposTransEveryDayMapper.summaryTraditionalPosTransAllList(params);
	}
	
}

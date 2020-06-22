package com.ruoyi.project.deveagent.summary.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserMposBenefitAll;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryUserMposBenefitAllMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryUserMposBenefitAllService;


/**
 * 代理=====MPOS代理数据汇总管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryUserMposBenefitAllServiceImpl extends BasicSerivce implements AgentSummaryUserMposBenefitAllService {
	
	
	@Autowired
	private AgentSummaryUserMposBenefitAllMapper agentSummaryUserMposBenefitAllMapper;


	
	/**
	 * 查询MPOS代理数据汇总列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryUserMposBenefitAllList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryUserMposBenefitAllMapper.getAgentSummaryUserMposBenefitAllList(params);
	}
	
	
	/**
	 * 导出MPOS代理数据汇总列表
	 */
	@Override
	public List<AgentSummaryUserMposBenefitAll> selectAgentSummaryUserMposBenefitAllList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSummaryUserMposBenefitAllMapper.selectAgentSummaryUserMposBenefitAllList(params);
	}
	
}

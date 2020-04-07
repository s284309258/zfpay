package com.ruoyi.project.deveagent.summary.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.summary.domain.AgentSummaryUserMposBenefitEveryDay;
import com.ruoyi.project.deveagent.summary.mapper.AgentSummaryUserMposBenefitEveryDayMapper;
import com.ruoyi.project.deveagent.summary.service.AgentSummaryUserMposBenefitEveryDayService;


/**
 * 代理=====MPOS代理数据汇总管理
 * @author Administrator
 *
 */
@Service
public class AgentSummaryUserMposBenefitEveryDayServiceImpl implements AgentSummaryUserMposBenefitEveryDayService {
	
	
	@Autowired
	private AgentSummaryUserMposBenefitEveryDayMapper agentSummaryUserMposBenefitEveryDayMapper;


	
	/**
	 * 查询MPOS代理数据汇总列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSummaryUserMposBenefitEveryDayList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSummaryUserMposBenefitEveryDayMapper.getAgentSummaryUserMposBenefitEveryDayList(params);
	}
	
	
	/**
	 * 导出MPOS代理数据汇总列表
	 */
	@Override
	public List<AgentSummaryUserMposBenefitEveryDay> selectAgentSummaryUserMposBenefitEveryDayList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSummaryUserMposBenefitEveryDayMapper.selectAgentSummaryUserMposBenefitEveryDayList(params);
	}
	
}

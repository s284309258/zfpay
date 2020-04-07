package com.ruoyi.project.deveagent.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.user.domain.AgentUserMessageInfo;
import com.ruoyi.project.deveagent.user.mapper.AgentUserMessageInfoMapper;
import com.ruoyi.project.deveagent.user.service.AgentUserMessageInfoService;


/**
 * 代理====》用户通知管理
 * @author Administrator
 *
 */
@Service
public class AgentUserMessageInfoServiceImpl implements AgentUserMessageInfoService {
	
	
	@Autowired
	private AgentUserMessageInfoMapper agentUserMessageInfoMapper;


	
	/**
	 * 查询用户通知列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserMessageInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserMessageInfoMapper.getAgentUserMessageInfoList(params);
	}
	
	
	/**
	 * 统计数据
	 */
	@Override
	public Map<String, Object> summaryAgentUserMessageInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserMessageInfoMapper.summaryAgentUserMessageInfoList(params);
	}
	
	
	/**
	 * 导出用户通知列表
	 */
	@Override
	public List<AgentUserMessageInfo> selectAgentUserMessageInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserMessageInfoMapper.selectAgentUserMessageInfoList(params);
	}
	
}

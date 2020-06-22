package com.ruoyi.project.deveagent.usertracard.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.usertracard.domain.AgentUserTrafficCardRecallRecordInfo;
import com.ruoyi.project.deveagent.usertracard.mapper.AgentUserTrafficCardRecallRecordInfoMapper;
import com.ruoyi.project.deveagent.usertracard.service.AgentUserTrafficCardRecallRecordInfoService;


/**
 * 代理====》用户流量卡召回记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserTrafficCardRecallRecordInfoServiceImpl implements AgentUserTrafficCardRecallRecordInfoService {
	
	
	@Autowired
	private AgentUserTrafficCardRecallRecordInfoMapper agentUserTrafficCardRecallRecordInfoMapper;


	
	/**
	 * 查询用户流量卡召回记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTrafficCardRecallRecordInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserTrafficCardRecallRecordInfoMapper.getAgentUserTrafficCardRecallRecordInfoList(params);
	}
	
	
	/**
	 * 导出用户流量卡召回记录列表
	 */
	@Override
	public List<AgentUserTrafficCardRecallRecordInfo> selectAgentUserTrafficCardRecallRecordInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserTrafficCardRecallRecordInfoMapper.selectAgentUserTrafficCardRecallRecordInfoList(params);
	}

}

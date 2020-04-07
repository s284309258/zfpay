package com.ruoyi.project.deveagent.activitytrapos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.activitytrapos.domain.AgentUserTraposActivityRewardRecord;
import com.ruoyi.project.deveagent.activitytrapos.mapper.AgentUserTraposActivityRewardRecordMapper;
import com.ruoyi.project.deveagent.activitytrapos.service.AgentUserTraposActivityRewardRecordService;


/**
 * 代理====》用户线上活动(传统POS)申请记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserTraposActivityRewardRecordServiceImpl implements AgentUserTraposActivityRewardRecordService {
	
	
	@Autowired
	private AgentUserTraposActivityRewardRecordMapper agentUserTraposActivityRewardRecordMapper;


	
	/**
	 * 查询用户线上活动(传统POS)奖励记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTraposActivityRewardRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserTraposActivityRewardRecordMapper.getAgentUserTraposActivityRewardRecordList(params);
	}
	
	
	/**
	 * 导出用户线上活动(传统POS)奖励记录列表
	 */
	@Override
	public List<AgentUserTraposActivityRewardRecord> selectAgentUserTraposActivityRewardRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserTraposActivityRewardRecordMapper.selectAgentUserTraposActivityRewardRecordList(params);
	}
	
}

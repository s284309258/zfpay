package com.ruoyi.project.deveagent.activitympos.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.activitympos.domain.AgentUserMposActivityRewardRecord;
import com.ruoyi.project.deveagent.activitympos.mapper.AgentUserMposActivityRewardRecordMapper;
import com.ruoyi.project.deveagent.activitympos.service.AgentUserMposActivityRewardRecordService;


/**
 * 代理====》用户线上活动(MPOS)申请记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserMposActivityRewardRecordServiceImpl extends BasicSerivce implements AgentUserMposActivityRewardRecordService {
	
	
	@Autowired
	private AgentUserMposActivityRewardRecordMapper agentUserMposActivityRewardRecordMapper;


	
	/**
	 * 查询用户线上活动(MPOS)奖励记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserMposActivityRewardRecordList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserMposActivityRewardRecordMapper.getAgentUserMposActivityRewardRecordList(params);
	}
	
	
	/**
	 * 导出用户线上活动(MPOS)奖励记录列表
	 */
	@Override
	public List<AgentUserMposActivityRewardRecord> selectAgentUserMposActivityRewardRecordList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserMposActivityRewardRecordMapper.selectAgentUserMposActivityRewardRecordList(params);
	}
	
}

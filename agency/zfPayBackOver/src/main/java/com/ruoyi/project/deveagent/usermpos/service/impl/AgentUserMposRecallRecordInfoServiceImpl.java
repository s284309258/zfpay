package com.ruoyi.project.deveagent.usermpos.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposRecallRecordInfo;
import com.ruoyi.project.deveagent.usermpos.mapper.AgentUserMposRecallRecordInfoMapper;
import com.ruoyi.project.deveagent.usermpos.service.AgentUserMposRecallRecordInfoService;


/**
 * 代理====》用户MPOS召回记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserMposRecallRecordInfoServiceImpl extends BasicSerivce implements AgentUserMposRecallRecordInfoService {
	
	
	@Autowired
	private AgentUserMposRecallRecordInfoMapper agentUserMposRecallRecordInfoMapper;


	
	/**
	 * 查询用户MPOS召回记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserMposRecallRecordInfoList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserMposRecallRecordInfoMapper.getAgentUserMposRecallRecordInfoList(params);
	}
	
	
	/**
	 * 导出用户MPOS召回记录列表
	 */
	@Override
	public List<AgentUserMposRecallRecordInfo> selectAgentUserMposRecallRecordInfoList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserMposRecallRecordInfoMapper.selectAgentUserMposRecallRecordInfoList(params);
	}

}

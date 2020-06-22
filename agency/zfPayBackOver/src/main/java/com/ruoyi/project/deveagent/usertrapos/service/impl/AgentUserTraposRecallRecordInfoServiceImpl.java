package com.ruoyi.project.deveagent.usertrapos.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposRecallRecordInfo;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraposRecallRecordInfoMapper;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposRecallRecordInfoService;


/**
 * 代理====》用户传统POS召回记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserTraposRecallRecordInfoServiceImpl extends BasicSerivce implements AgentUserTraposRecallRecordInfoService {
	
	
	@Autowired
	private AgentUserTraposRecallRecordInfoMapper agentUserTraposRecallRecordInfoMapper;


	
	/**
	 * 查询用户传统POS召回记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTraposRecallRecordInfoList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserTraposRecallRecordInfoMapper.getAgentUserTraposRecallRecordInfoList(params);
	}
	
	
	/**
	 * 导出用户传统POS召回记录列表
	 */
	@Override
	public List<AgentUserTraposRecallRecordInfo> selectAgentUserTraposRecallRecordInfoList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserTraposRecallRecordInfoMapper.selectAgentUserTraposRecallRecordInfoList(params);
	}

}

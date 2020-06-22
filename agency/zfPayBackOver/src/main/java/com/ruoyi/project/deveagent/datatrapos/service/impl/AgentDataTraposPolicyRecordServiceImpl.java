package com.ruoyi.project.deveagent.datatrapos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.datatrapos.domain.AgentDataTraposPolicyRecord;
import com.ruoyi.project.deveagent.datatrapos.mapper.AgentDataTraposPolicyRecordMapper;
import com.ruoyi.project.deveagent.datatrapos.service.AgentDataTraposPolicyRecordService;


/**
 * 代理======账号政策记录管理
 * @author Administrator
 *
 */
@Service
public class AgentDataTraposPolicyRecordServiceImpl implements AgentDataTraposPolicyRecordService {
	
	
	@Autowired
	private AgentDataTraposPolicyRecordMapper agentDataTraposPolicyRecordMapper;


	
	/**
	 * 查询账号政策记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentDataTraposPolicyRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataTraposPolicyRecordMapper.getAgentDataTraposPolicyRecordList(params);
	}
	
	
	/**
	 * 导出账号政策记录
	 */
	@Override
	public List<AgentDataTraposPolicyRecord> selectAgentDataTraposPolicyRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataTraposPolicyRecordMapper.selectAgentDataTraposPolicyRecordList(params);
	}
	
}

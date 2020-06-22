package com.ruoyi.project.deveagent.datampos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.datampos.domain.AgentDataMposPolicyRecord;
import com.ruoyi.project.deveagent.datampos.mapper.AgentDataMposPolicyRecordMapper;
import com.ruoyi.project.deveagent.datampos.service.AgentDataMposPolicyRecordService;


/**
 * 代理======账号政策记录管理
 * @author Administrator
 *
 */
@Service
public class AgentDataMposPolicyRecordServiceImpl implements AgentDataMposPolicyRecordService {
	
	
	@Autowired
	private AgentDataMposPolicyRecordMapper agentDataMposPolicyRecordMapper;


	
	/**
	 * 查询账号政策记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentDataMposPolicyRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataMposPolicyRecordMapper.getAgentDataMposPolicyRecordList(params);
	}
	
	
	/**
	 * 导出账号政策记录
	 */
	@Override
	public List<AgentDataMposPolicyRecord> selectAgentDataMposPolicyRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataMposPolicyRecordMapper.selectAgentDataMposPolicyRecordList(params);
	}
	
}

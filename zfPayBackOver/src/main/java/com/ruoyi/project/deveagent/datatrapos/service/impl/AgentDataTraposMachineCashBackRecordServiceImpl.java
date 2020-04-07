package com.ruoyi.project.deveagent.datatrapos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.datatrapos.domain.AgentDataTraposMachineCashBackRecord;
import com.ruoyi.project.deveagent.datatrapos.mapper.AgentDataTraposMachineCashBackRecordMapper;
import com.ruoyi.project.deveagent.datatrapos.service.AgentDataTraposMachineCashBackRecordService;


/**
 * 代理======机具返现记录管理
 * @author Administrator
 *
 */
@Service
public class AgentDataTraposMachineCashBackRecordServiceImpl implements AgentDataTraposMachineCashBackRecordService {
	
	
	@Autowired
	private AgentDataTraposMachineCashBackRecordMapper agentDataTraposMachineCashBackRecordMapper;


	
	/**
	 * 查询机具返现记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentDataTraposMachineCashBackRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataTraposMachineCashBackRecordMapper.getAgentDataTraposMachineCashBackRecordList(params);
	}
	
	
	/**
	 * 导出机具返现记录列表
	 */
	@Override
	public List<AgentDataTraposMachineCashBackRecord> selectAgentDataTraposMachineCashBackRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataTraposMachineCashBackRecordMapper.selectAgentDataTraposMachineCashBackRecordList(params);
	}
	
}

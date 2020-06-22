package com.ruoyi.project.deveagent.datampos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.datampos.domain.AgentDataMposMachineCashBackRecord;
import com.ruoyi.project.deveagent.datampos.mapper.AgentDataMposMachineCashBackRecordMapper;
import com.ruoyi.project.deveagent.datampos.service.AgentDataMposMachineCashBackRecordService;


/**
 * 代理======机具返现记录管理
 * @author Administrator
 *
 */
@Service
public class AgentDataMposMachineCashBackRecordServiceImpl implements AgentDataMposMachineCashBackRecordService {
	
	
	@Autowired
	private AgentDataMposMachineCashBackRecordMapper agentDataMposMachineCashBackRecordMapper;


	
	/**
	 * 查询机具返现记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentDataMposMachineCashBackRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataMposMachineCashBackRecordMapper.getAgentDataMposMachineCashBackRecordList(params);
	}
	
	
	/**
	 * 导出机具返现记录列表
	 */
	@Override
	public List<AgentDataMposMachineCashBackRecord> selectAgentDataMposMachineCashBackRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataMposMachineCashBackRecordMapper.selectAgentDataMposMachineCashBackRecordList(params);
	}
	
}

package com.ruoyi.project.deveagent.datampos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.datampos.domain.AgentDataMposMachineCashBackRecordDeal;
import com.ruoyi.project.deveagent.datampos.mapper.AgentDataMposMachineCashBackRecordDealMapper;
import com.ruoyi.project.deveagent.datampos.service.AgentDataMposMachineCashBackRecordDealService;


/**
 * 代理======机具返现记录管理
 * @author Administrator
 *
 */
@Service
public class AgentDataMposMachineCashBackRecordDealServiceImpl implements AgentDataMposMachineCashBackRecordDealService {
	
	
	@Autowired
	private AgentDataMposMachineCashBackRecordDealMapper agentDataMposMachineCashBackRecordDealMapper;


	
	/**
	 * 查询机具返现记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentDataMposMachineCashBackRecordDealList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataMposMachineCashBackRecordDealMapper.getAgentDataMposMachineCashBackRecordDealList(params);
	}
	
	
	/**
	 * 导出机具返现记录列表
	 */
	@Override
	public List<AgentDataMposMachineCashBackRecordDeal> selectAgentDataMposMachineCashBackRecordDealList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataMposMachineCashBackRecordDealMapper.selectAgentDataMposMachineCashBackRecordDealList(params);
	}
	
}

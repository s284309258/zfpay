package com.ruoyi.project.deveagent.datatrapos.service.impl;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.datatrapos.domain.AgentDataTraposMachineCashBackRecordDeal;
import com.ruoyi.project.deveagent.datatrapos.mapper.AgentDataEposMachineCashBackRecordDealMapper;
import com.ruoyi.project.deveagent.datatrapos.mapper.AgentDataTraposMachineCashBackRecordDealMapper;
import com.ruoyi.project.deveagent.datatrapos.service.AgentDataEposMachineCashBackRecordDealService;
import com.ruoyi.project.deveagent.datatrapos.service.AgentDataTraposMachineCashBackRecordDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 代理======机具返现记录管理
 * @author Administrator
 *
 */
@Service
public class AgentDataEposMachineCashBackRecordDealServiceImpl implements AgentDataEposMachineCashBackRecordDealService {
	
	
	@Autowired
	private AgentDataEposMachineCashBackRecordDealMapper agentDataEposMachineCashBackRecordDealMapper;


	
	/**
	 * 查询机具返现记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentDataTraposMachineCashBackRecordDealList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataEposMachineCashBackRecordDealMapper.getAgentDataTraposMachineCashBackRecordDealList(params);
	}
	
	
	/**
	 * 导出机具返现记录列表
	 */
	@Override
	public List<AgentDataTraposMachineCashBackRecordDeal> selectAgentDataTraposMachineCashBackRecordDealList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentDataEposMachineCashBackRecordDealMapper.selectAgentDataTraposMachineCashBackRecordDealList(params);
	}
	
}

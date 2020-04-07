package com.ruoyi.project.deveagent.usertrapos.service.impl;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposDeductRecord;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserEposDeductRecordMapper;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraposDeductRecordMapper;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserEposDeductRecordService;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposDeductRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 代理====》用户传统POS未达标扣除记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserEposDeductRecordServiceImpl implements AgentUserEposDeductRecordService {
	
	
	@Autowired
	private AgentUserEposDeductRecordMapper agentUserEposDeductRecordMapper;


	
	/**
	 * 查询用户传统POS未达标扣除记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTraposDeductRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserEposDeductRecordMapper.getAgentUserTraposDeductRecordList(params);
	}
	
	
	/**
	 * 导出用户传统POS未达标扣除记录列表
	 */
	@Override
	public List<AgentUserTraposDeductRecord> selectAgentUserTraposDeductRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserEposDeductRecordMapper.selectAgentUserTraposDeductRecordList(params);
	}

}

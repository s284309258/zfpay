package com.ruoyi.project.deveagent.usertrapos.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposDeductRecord;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraposDeductRecordMapper;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposDeductRecordService;


/**
 * 代理====》用户传统POS未达标扣除记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserTraposDeductRecordServiceImpl extends BasicSerivce implements AgentUserTraposDeductRecordService {
	
	
	@Autowired
	private AgentUserTraposDeductRecordMapper agentUserTraposDeductRecordMapper;


	
	/**
	 * 查询用户传统POS未达标扣除记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTraposDeductRecordList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserTraposDeductRecordMapper.getAgentUserTraposDeductRecordList(params);
	}
	
	
	/**
	 * 导出用户传统POS未达标扣除记录列表
	 */
	@Override
	public List<AgentUserTraposDeductRecord> selectAgentUserTraposDeductRecordList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserTraposDeductRecordMapper.selectAgentUserTraposDeductRecordList(params);
	}

}

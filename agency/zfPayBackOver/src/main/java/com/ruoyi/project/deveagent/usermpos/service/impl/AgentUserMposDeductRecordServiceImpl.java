package com.ruoyi.project.deveagent.usermpos.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposDeductRecord;
import com.ruoyi.project.deveagent.usermpos.mapper.AgentUserMposDeductRecordMapper;
import com.ruoyi.project.deveagent.usermpos.service.AgentUserMposDeductRecordService;


/**
 * 代理====》用户MPOS未达标扣除记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserMposDeductRecordServiceImpl extends BasicSerivce implements AgentUserMposDeductRecordService {
	
	
	@Autowired
	private AgentUserMposDeductRecordMapper agentUserMposDeductRecordMapper;


	
	/**
	 * 查询用户MPOS未达标扣除记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserMposDeductRecordList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserMposDeductRecordMapper.getAgentUserMposDeductRecordList(params);
	}
	
	
	/**
	 * 导出用户MPOS未达标扣除记录列表
	 */
	@Override
	public List<AgentUserMposDeductRecord> selectAgentUserMposDeductRecordList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserMposDeductRecordMapper.selectAgentUserMposDeductRecordList(params);
	}

}

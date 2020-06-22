package com.ruoyi.project.deveagent.usermpos.service.impl;

import com.ruoyi.common.utils.BasicSerivce;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposAllotRecord;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposDeductRecord;
import com.ruoyi.project.deveagent.usermpos.mapper.AgentUserMposAllotRecordMapper;
import com.ruoyi.project.deveagent.usermpos.mapper.AgentUserMposDeductRecordMapper;
import com.ruoyi.project.deveagent.usermpos.service.AgentUserMposDeductRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 代理====》用户MPOS未达标扣除记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserMposAllotRecordServiceImpl extends BasicSerivce {
	
	
	@Autowired
	private AgentUserMposAllotRecordMapper agentUserMposAllotRecordMapper;


	
	/**
	 * 查询用户MPOS未达标扣除记录列表
	 */
	public List<Map<String, Object>> getAgentUserMposAllotRecordList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserMposAllotRecordMapper.getAgentUserMposAllotRecordList(params);
	}
	
	
	/**
	 * 导出用户MPOS未达标扣除记录列表
	 */
	public List<AgentUserMposAllotRecord> selectAgentUserMposAllotRecordList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserMposAllotRecordMapper.selectAgentUserMposAllotRecordList(params);
	}

}

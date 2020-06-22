package com.ruoyi.project.deveagent.usermpos.service.impl;

import com.ruoyi.common.utils.BasicSerivce;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposAllotRecord;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposMachineActiveRecord;
import com.ruoyi.project.deveagent.usermpos.mapper.AgentUserMposAllotRecordMapper;
import com.ruoyi.project.deveagent.usermpos.mapper.AgentUserMposMachineActiveRecordMapper;
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
public class AgentUserMposMachineActiveRecordServiceImpl extends BasicSerivce {
	
	
	@Autowired
	private AgentUserMposMachineActiveRecordMapper agentUserMposRecordMapper;


	
	/**
	 * 查询用户MPOS未达标扣除记录列表
	 */
	public List<Map<String, Object>> getAgentUserMposRecordList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserMposRecordMapper.getAgentUserMposRecordList(params);
	}
	
	
	/**
	 * 导出用户MPOS未达标扣除记录列表
	 */
	public List<AgentUserMposMachineActiveRecord> selectAgentUserMposRecordList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserMposRecordMapper.selectAgentUserMposRecordList(params);
	}

}

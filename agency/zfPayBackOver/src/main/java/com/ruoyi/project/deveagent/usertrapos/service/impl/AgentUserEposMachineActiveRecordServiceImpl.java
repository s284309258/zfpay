package com.ruoyi.project.deveagent.usertrapos.service.impl;

import com.ruoyi.common.utils.BasicSerivce;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposMachineActiveRecord;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserEposMachineActiveRecordMapper;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraposMachineActiveRecordMapper;
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
public class AgentUserEposMachineActiveRecordServiceImpl extends BasicSerivce {
	
	
	@Autowired
	private AgentUserEposMachineActiveRecordMapper agentUserTroposRecordMapper;


	
	/**
	 * 查询用户MPOS未达标扣除记录列表
	 */
	public List<Map<String, Object>> getAgentUserTroposRecordList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserTroposRecordMapper.getAgentUserTraposRecordList(params);
	}
	
	
	/**
	 * 导出用户MPOS未达标扣除记录列表
	 */
	public List<AgentUserMposMachineActiveRecord> selectAgentUserTroposRecordList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserTroposRecordMapper.selectAgentUserTraposRecordList(params);
	}

}

package com.ruoyi.project.deveagent.usertrapos.service.impl;

import com.ruoyi.common.utils.BasicSerivce;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposDeductRecord;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposAllotRecord;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserEposAllotRecordMapper;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraposAllotRecordMapper;
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
public class AgentUserEposAllotRecordServiceImpl extends BasicSerivce {
	
	
	@Autowired
	private AgentUserEposAllotRecordMapper agentUserTraposAllotRecordMapper;


	
	/**
	 * 查询用户MPOS未达标扣除记录列表
	 */
	public List<Map<String, Object>> getAgentUserTraposAllotRecordList(Map<String, Object> params) {
		MapChainParams(params);
		params.put("pos_type","epos");
		return agentUserTraposAllotRecordMapper.getAgentUserTraposAllotRecordList(params);
	}
	
	
	/**
	 * 导出用户MPOS未达标扣除记录列表
	 */
	public List<AgentUserTraposAllotRecord> selectAgentUserTraposAllotRecordList(Map<String, Object> params) {
		MapChainParams(params);
		params.put("pos_type","epos");
		return agentUserTraposAllotRecordMapper.selectAgentUserTraposAllotRecordList(params);
	}

}

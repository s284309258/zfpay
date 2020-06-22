package com.ruoyi.project.deveagent.usertrapos.service;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposMachineBackRecord;

import java.util.List;
import java.util.Map;

/**
 * 代理======用户传统POS机具返现记录管理
 * @author Administrator
 *
 */
public interface AgentUserEposMachineBackRecordService {

	
	/**
	 * 查询用户传统POS机具返现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposMachineBackRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出用户传统POS机具返现记录信息
	 * @param params
	 * @return
	 */
	List<AgentUserTraposMachineBackRecord> selectAgentUserTraposMachineBackRecordList(Map<String, Object> params);

}
	
	

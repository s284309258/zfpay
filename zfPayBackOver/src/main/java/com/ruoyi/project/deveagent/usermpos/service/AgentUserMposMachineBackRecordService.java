package com.ruoyi.project.deveagent.usermpos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposMachineBackRecord;

/**
 * 代理======用户MPOS机具返现记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposMachineBackRecordService {

	
	/**
	 * 查询用户MPOS机具返现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposMachineBackRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出用户MPOS机具返现记录信息
	 * @param params
	 * @return
	 */
	List<AgentUserMposMachineBackRecord> selectAgentUserMposMachineBackRecordList(Map<String, Object> params);

}
	
	

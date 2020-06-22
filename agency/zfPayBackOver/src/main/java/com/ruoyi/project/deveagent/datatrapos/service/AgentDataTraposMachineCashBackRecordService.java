package com.ruoyi.project.deveagent.datatrapos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.datatrapos.domain.AgentDataTraposMachineCashBackRecord;

/**
 * 代理======机具返现记录管理
 * @author Administrator
 *
 */
public interface AgentDataTraposMachineCashBackRecordService {

	
	/**
	 * 查询机具返现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentDataTraposMachineCashBackRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出机具返现记录
	 * @param params
	 * @return
	 */
	List<AgentDataTraposMachineCashBackRecord> selectAgentDataTraposMachineCashBackRecordList(Map<String, Object> params);

}
	
	

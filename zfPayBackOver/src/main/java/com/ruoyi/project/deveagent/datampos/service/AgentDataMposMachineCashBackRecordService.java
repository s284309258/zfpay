package com.ruoyi.project.deveagent.datampos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.datampos.domain.AgentDataMposMachineCashBackRecord;

/**
 * 代理======机具返现记录管理
 * @author Administrator
 *
 */
public interface AgentDataMposMachineCashBackRecordService {

	
	/**
	 * 查询机具返现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentDataMposMachineCashBackRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出机具返现记录
	 * @param params
	 * @return
	 */
	List<AgentDataMposMachineCashBackRecord> selectAgentDataMposMachineCashBackRecordList(Map<String, Object> params);

}
	
	

package com.ruoyi.project.deveagent.datampos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.datampos.domain.AgentDataMposTransactionRecord;

/**
 * 代理======MPOS交易记录管理
 * @author Administrator
 *
 */
public interface AgentDataMposTransactionRecordService {

	
	/**
	 * 查询MPOS交易记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentDataMposTransactionRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出MPOS交易记录
	 * @param params
	 * @return
	 */
	List<AgentDataMposTransactionRecord> selectAgentDataMposTransactionRecordList(Map<String, Object> params);

}
	
	

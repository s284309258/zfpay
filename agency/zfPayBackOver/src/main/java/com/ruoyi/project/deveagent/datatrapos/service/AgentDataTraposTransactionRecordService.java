package com.ruoyi.project.deveagent.datatrapos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.datatrapos.domain.AgentDataTraposTransactionRecord;

/**
 * 代理======Trapos交易记录管理
 * @author Administrator
 *
 */
public interface AgentDataTraposTransactionRecordService {

	
	/**
	 * 查询Trapos交易记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentDataTraposTransactionRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出Trapos交易记录
	 * @param params
	 * @return
	 */
	List<AgentDataTraposTransactionRecord> selectAgentDataTraposTransactionRecordList(Map<String, Object> params);

}
	
	

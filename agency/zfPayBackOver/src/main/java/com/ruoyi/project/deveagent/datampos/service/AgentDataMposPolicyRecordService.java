package com.ruoyi.project.deveagent.datampos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.datampos.domain.AgentDataMposPolicyRecord;

/**
 * 代理======账号政策记录管理
 * @author Administrator
 *
 */
public interface AgentDataMposPolicyRecordService {

	
	/**
	 * 查询账号政策记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentDataMposPolicyRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出账号政策记录
	 * @param params
	 * @return
	 */
	List<AgentDataMposPolicyRecord> selectAgentDataMposPolicyRecordList(Map<String, Object> params);

}
	
	

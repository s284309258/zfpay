package com.ruoyi.project.deveagent.usertrapos.service;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposDeductRecord;

import java.util.List;
import java.util.Map;

/**
 * 代理======用户传统POS未达标扣除记录管理
 * @author Administrator
 *
 */
public interface AgentUserEposDeductRecordService {

	
	/**
	 * 查询用户传统POS未达标扣除记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposDeductRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出用户传统POS未达标扣除记录信息
	 * @param params
	 * @return
	 */
	List<AgentUserTraposDeductRecord> selectAgentUserTraposDeductRecordList(Map<String, Object> params);

}
	
	

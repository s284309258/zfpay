package com.ruoyi.project.deveagent.usermpos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposDeductRecord;

/**
 * 代理======用户MPOS未达标扣除记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposDeductRecordService {

	
	/**
	 * 查询用户MPOS未达标扣除记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposDeductRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出用户MPOS未达标扣除记录信息
	 * @param params
	 * @return
	 */
	List<AgentUserMposDeductRecord> selectAgentUserMposDeductRecordList(Map<String, Object> params);

}
	
	

package com.ruoyi.project.deveagent.usertrapos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserApplyCardrateTraposRecord;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户费率申请记录(传统POS)管理
 * @author Administrator
 *
 */
public interface AgentUserApplyCardrateTraposRecordService {

	
	/**
	 * 查询用户费率申请记录(传统POS)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserApplyCardrateTraposRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出用户费率申请记录(传统POS)信息
	 * @param params
	 * @return
	 */
	List<AgentUserApplyCardrateTraposRecord> selectAgentUserApplyCardrateTraposRecordList(Map<String, Object> params);


	/**
	 * 系统批量刷卡费率(传统POS)申请
	 * @param params
	 * @return
	 */
	R batchAgentSysAuditUserApplyCardrateTraposRecord(Map<String, Object> params);

}
	
	

package com.ruoyi.project.deveagent.user.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.user.domain.AgentUserCashRecordExcel;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.devemana.user.domain.ManaUserCashRecord;

/**
 * 代理======用户取现记录管理
 * @author Administrator
 *
 */
public interface AgentUserCashRecordService {

	
	/**
	 * 查询用户取现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserCashRecordList(Map<String, Object> params);
	
	
	/**
	 * 统计取现信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryAgentUserCashRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出用户取现记录信息
	 * @param params
	 * @return
	 */
	List<ManaUserCashRecord> selectAgentUserCashRecordList(Map<String, Object> params);


	/**
	 * 查询取现记录详情列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserCashRecordDetailList(Map<String, Object> params);


	/**
	 * 导出用户待处理取现记录
	 * @param params
	 * @return
	 */
	List<AgentUserCashRecordExcel> selectManaWaitUserCashRecordList(Map<String, Object> params);


	/**
	 * 批量审核处理处理中的兑币记录
	 * @param params
	 * @return
	 */
	R batchSysAuditUserCashRecord(Map<String, Object> params);
	

}
	
	

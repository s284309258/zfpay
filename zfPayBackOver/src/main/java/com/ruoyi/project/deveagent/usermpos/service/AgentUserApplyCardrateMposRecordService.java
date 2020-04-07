package com.ruoyi.project.deveagent.usermpos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserApplyCardrateMposRecord;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户费率申请记录(MPOS)管理
 * @author Administrator
 *
 */
public interface AgentUserApplyCardrateMposRecordService {

	
	/**
	 * 查询用户费率申请记录(MPOS)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserApplyCardrateMposRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出用户费率申请记录(MPOS)信息
	 * @param params
	 * @return
	 */
	List<AgentUserApplyCardrateMposRecord> selectAgentUserApplyCardrateMposRecordList(Map<String, Object> params);


	/**
	 * 系统批量刷卡费率(MPOS)申请
	 * @param params
	 * @return
	 */
	R batchAgentSysAuditUserApplyCardrateMposRecord(Map<String, Object> params);

}
	
	

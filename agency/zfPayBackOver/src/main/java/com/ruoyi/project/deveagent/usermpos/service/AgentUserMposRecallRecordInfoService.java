package com.ruoyi.project.deveagent.usermpos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposRecallRecordInfo;

/**
 * 代理======用户MPOS召回记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposRecallRecordInfoService {

	
	/**
	 * 查询用户MPOS召回记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposRecallRecordInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出用户MPOS召回记录信息
	 * @param params
	 * @return
	 */
	List<AgentUserMposRecallRecordInfo> selectAgentUserMposRecallRecordInfoList(Map<String, Object> params);

}
	
	

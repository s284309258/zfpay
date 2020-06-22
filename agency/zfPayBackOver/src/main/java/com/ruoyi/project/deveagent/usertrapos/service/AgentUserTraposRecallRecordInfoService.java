package com.ruoyi.project.deveagent.usertrapos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposRecallRecordInfo;

/**
 * 代理======用户传统POS召回记录管理
 * @author Administrator
 *
 */
public interface AgentUserTraposRecallRecordInfoService {

	
	/**
	 * 查询用户传统POS召回记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposRecallRecordInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出用户传统POS召回记录信息
	 * @param params
	 * @return
	 */
	List<AgentUserTraposRecallRecordInfo> selectAgentUserTraposRecallRecordInfoList(Map<String, Object> params);

}
	
	

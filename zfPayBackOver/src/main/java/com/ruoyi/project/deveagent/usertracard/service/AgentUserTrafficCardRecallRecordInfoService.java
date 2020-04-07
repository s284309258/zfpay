package com.ruoyi.project.deveagent.usertracard.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usertracard.domain.AgentUserTrafficCardRecallRecordInfo;

/**
 * 代理======用户流量卡召回记录管理
 * @author Administrator
 *
 */
public interface AgentUserTrafficCardRecallRecordInfoService {

	
	/**
	 * 查询用户流量卡召回记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTrafficCardRecallRecordInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出用户流量卡召回记录信息
	 * @param params
	 * @return
	 */
	List<AgentUserTrafficCardRecallRecordInfo> selectAgentUserTrafficCardRecallRecordInfoList(Map<String, Object> params);

}
	
	

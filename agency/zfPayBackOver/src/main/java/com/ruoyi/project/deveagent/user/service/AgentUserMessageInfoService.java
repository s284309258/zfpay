package com.ruoyi.project.deveagent.user.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.user.domain.AgentUserMessageInfo;

/**
 * 代理======用户通知管理
 * @author Administrator
 *
 */
public interface AgentUserMessageInfoService {

	
	/**
	 * 查询用户通知列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMessageInfoList(Map<String, Object> params);
	
	
	/**
	 * 统计数据
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryAgentUserMessageInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出用户通知信息
	 * @param params
	 * @return
	 */
	List<AgentUserMessageInfo> selectAgentUserMessageInfoList(Map<String, Object> params);

}
	
	

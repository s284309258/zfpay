package com.ruoyi.project.deveagent.usertracard.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usertracard.domain.AgentSelectUserTrafficCardInfo;
import com.ruoyi.project.deveagent.usertracard.domain.AgentUserTrafficCardInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户流量卡信息管理
 * @author Administrator
 *
 */
public interface AgentUserTrafficCardInfoService {

	
	/**
	 * 查询用户流量卡信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTrafficCardInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出用户流量卡信息
	 * @param params
	 * @return
	 */
	List<AgentUserTrafficCardInfo> selectAgentUserTrafficCardInfoList(Map<String, Object> params);


	/**
	 * 导入用户流量卡信息
	 * @param agentUserMposInfoList
	 * @param user_id
	 * @return
	 */
	R importAgentUserTrafficCardInfoList(List<AgentUserTrafficCardInfo> agentUserTrafficCardInfoList, String user_id);


	/**
	 * 导出可分配的流量卡导入模板
	 * @param params
	 * @return
	 */
	List<AgentSelectUserTrafficCardInfo> selectAgentNoDisSysTrafficCardInfoList(Map<String, Object> params);


	/**
	 * 新增保存用户流量卡机信息
	 * @param params
	 * @return
	 */
	R addAgentUserTrafficCardInfo(Map<String, Object> params);

}
	
	

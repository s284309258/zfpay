package com.ruoyi.project.deveagent.user.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.user.domain.AgentUserCard;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户结算卡管理
 * @author Administrator
 *
 */
public interface AgentUserCardService {

	
	/**
	 * 查询用户结算卡列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserCardList(Map<String, Object> params);
	
	
	/**
	 * 导出用户结算卡信息
	 * @param params
	 * @return
	 */
	List<AgentUserCard> selectAgentUserCardList(Map<String, Object> params);


	/**
	 * 根据id查询用户结算卡详情
	 * @param id
	 * @return
	 */
	AgentUserCard getAgentUserCardById(String id);


	/**
	 * 批量审核用户结算卡
	 * @param params
	 * @return
	 */
	R batchSysAuditAgentUserCard(Map<String, Object> params);

}
	
	

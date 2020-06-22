package com.ruoyi.project.deveagent.usertrapos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraditionalPosInstallInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户传统POS商户进件管理
 * @author Administrator
 *
 */
public interface AgentUserTraditionalPosInstallInfoService {

	
	/**
	 * 查询用户传统POS商户进件列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraditionalPosInstallInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出用户传统POS商户进件信息
	 * @param params
	 * @return
	 */
	List<AgentUserTraditionalPosInstallInfo> selectAgentUserTraditionalPosInstallInfoList(Map<String, Object> params);


	/**
	 * 新增用户进件信息
	 * @param params
	 * @return
	 */
	R addAgentUserTraditionalPosInstallInfo(Map<String, Object> params);


	/**
	 * 根据编号查询进件详情
	 * @param id
	 * @return
	 */
	AgentUserTraditionalPosInstallInfo getAgentUserTraditionalPosInstallInfoById(String id);


	/**
	 * 修改保存进件信息
	 * @param params
	 * @return
	 */
	R editAgentUserTraditionalPosInstallInfo(Map<String, Object> params);


	/**
	 * 批量删除商户进件信息
	 * @param ids
	 * @return
	 */
	R batchRemoveAgentUserTraditionalPosInstallInfo(String ids);

}
	
	

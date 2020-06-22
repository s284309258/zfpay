package com.ruoyi.project.deveagent.syspos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.syspos.domain.AgentSysMposInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======系统MPOS信息管理
 * @author Administrator
 *
 */
public interface AgentSysMposInfoService {

	
	/**
	 * 查询系统MPOS信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSysMposInfoList(Map<String, Object> params);


	/**
	 * 查询系统一级代理MPOS信息列表byqh
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSubAgentSysMposInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出系统MPOS信息
	 * @param params
	 * @return
	 */
	List<AgentSysMposInfo> selectAgentSysMposInfoList(Map<String, Object> params);


	/**
	 * 导入系统MPOS信息数据
	 * @param agentSysMposInfoList
	 * @param account_id 
	 * @return
	 */
	R importAgentSysMposInfoList(List<AgentSysMposInfo> agentSysMposInfoList, String account_id);


	/**
	 * 跳转到新增系统MPOS
	 * @param params
	 * @return
	 */
	R addAgentSysMposInfo(Map<String, Object> params);


	/**
	 * 修改系统MPOS
	 * @param params
	 * @return
	 */
	R editAgentSysMposInfo(Map<String, Object> params);


	/**
	 * 根据编号查询系统MPOS信息
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentSysMposInfoById(String id);


	/**
	 * 批量删除系统MPOS信息====>只能删除未分配的MPOS
	 * @param params
	 * @return
	 */
	R batchRemoveAgentSysMposInfo(Map<String, Object> params);


	/**
	 * 根据编号查询系统MPOS详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentSysMposInfoDetailBySn(String id);

}
	
	

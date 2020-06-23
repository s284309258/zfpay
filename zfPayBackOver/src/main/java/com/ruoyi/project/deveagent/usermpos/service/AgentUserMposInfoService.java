package com.ruoyi.project.deveagent.usermpos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usermpos.domain.AgentSelectUserMposInfo;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户MPOS信息管理
 * @author Administrator
 *
 */
public interface AgentUserMposInfoService {

	
	/**
	 * 查询用户MPOS信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出用户MPOS信息
	 * @param params
	 * @return
	 */
	List<AgentUserMposInfo> selectAgentUserMposInfoList(Map<String, Object> params);


	/**
	 * 导入用户MPOS信息
	 * @param agentUserMposInfoList
	 * @param user_id
	 * @return
	 */
	R importAgentUserMposInfoList(List<AgentUserMposInfo> agentUserMposInfoList, String user_id);


	/**
	 * 导出可分配的Mpos导入模板
	 * @param params
	 * @return
	 */
	List<AgentSelectUserMposInfo> selectAgentNoDisSysMposInfoList(Map<String, Object> params);


	/**
	 * 新增保存用户MPOS机信息
	 * @param params
	 * @return
	 */
	R addAgentUserMposInfo(Map<String, Object> params);

	/***
	 * add byqh202006
	 * @param params
	 * @param list
	 * @return
	 */
	R batchUpdate(Map<String,Object> params,List<Map<String, Object>> list);


	/**
	 * 根据编号查询用户MPOS信息
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentUserMposInfoById(String id);


	/**
	 * 修改用户MPOS信息
	 * @param params
	 * @return
	 */
	R editAgentUserMposInfo(Map<String, Object> params);


	/***
	 * 查询一级代理的子代理byqh
	 * @param user_id
	 * @return
	 */
	List<Map<String,Object>> selectSubAgentInfo(String user_id);


	/**
	 * 代理的MPOS分配给二级代理保存操作byqh
	 * @param params
	 * @return
	 */
	R addSubAgentUserMposInfo(Map<String, Object> params);

}
	
	

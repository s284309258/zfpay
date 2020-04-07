package com.ruoyi.project.deveagent.usertrapos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentSelectUserTraditionalPosInfo;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraditionalPosInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户传统POS信息管理
 * @author Administrator
 *
 */
public interface AgentUserTraditionalPosInfoService {

	
	/**
	 * 查询用户传统POS信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraditionalPosInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出用户传统POS信息
	 * @param params
	 * @return
	 */
	List<AgentUserTraditionalPosInfo> selectAgentUserTraditionalPosInfoList(Map<String, Object> params);


	/**
	 * 导入用户传统POS信息数据
	 * @param agentuserTraditionalPosInfoList
	 * @param user_id
	 * @return
	 */
	R importAgentUserTraditionalPosInfoList(List<AgentUserTraditionalPosInfo> agentuserTraditionalPosInfoList,
			String user_id);


	/**
	 * 导出可分配的传统pos导入模板
	 * @param params
	 * @return
	 */
	List<AgentSelectUserTraditionalPosInfo> selectAgentNoDisSysTraditionalPosInfoList(Map<String, Object> params);


	/**
	 * 新增保存用户传统POS机信息
	 * @param params
	 * @return
	 */
	R addAgentUserTraditionalPosInfo(Map<String, Object> params);


	/**
	 * 代理的MPOS分配给二级代理保存操作byqh
	 * @param params
	 * @return
	 */
	R addSubAgentUserTraditionalPosInfo(Map<String, Object> params);
	/**
	 * 根据编号查询用户传统POS详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentUserTraditionalPosInfoById(String id);


	/**
	 * 修改用户传统POS信息
	 * @param params
	 * @return
	 */
	R editAgentUserTraditionalPosInfo(Map<String, Object> params);

	/***
	 * 查询一级代理的子代理byqh
	 * @param user_id
	 * @return
	 */
	List<Map<String,Object>> selectSubAgentInfo(String user_id);

}
	
	

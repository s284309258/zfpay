package com.ruoyi.project.deveagent.user.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.user.domain.AgentUserInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户信息管理
 * @author Administrator
 *
 */
public interface AgentUserInfoService {

	
	/**
	 * 查询用户列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserInfoList(Map<String, Object> params);
	
	
	/**
	 * 统计用户信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryAgentUserInfoList(Map<String, Object> params);

	
	/**
	 * 导出用户信息
	 * @param params
	 * @return
	 */
	List<AgentUserInfo> selectAgentUserInfoList(Map<String, Object> params);


	/**
	 * 批量系统冻结解冻用户
	 * @param params
	 * @return
	 */
	R batchSysFreezeAgentUser(Map<String, Object> params);
	
	
	/**
	 * 批量实名认证审核用户账号
	 * @param params
	 * @return
	 */
	R batchSysAuthAuditAgentUser(Map<String, Object> params);


	/**
	 * 根据用户id查询用户详情
	 * @param id
	 * @return
	 */
	AgentUserInfo getAgentUserInfoById(String id);


	/**
	 * 修改保存用户信息
	 * @param params
	 * @return
	 */
	R editAgentUserInfo(Map<String, Object> params);


	/**
	 * 查询父级团队成员列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getParentAgentUserInfoList(Map<String, Object> params);


	/**
	 * 导出父级成员信息列表
	 * @param params
	 * @return
	 */
	List<AgentUserInfo> selectParentAgentUserInfoList(Map<String, Object> params);


	/**
	 * 查询子级团队成员列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getChildrenAgentUserList(Map<String, Object> params);


	/**
	 * 导出子级团队成员列表
	 * @param params
	 * @return
	 */
	List<AgentUserInfo> selectChildrenAgentUserInfoList(Map<String, Object> params);


	/**
	 * 批量删除用户信息====>只能删除未实名的并且下面没有人的
	 * @param params
	 * @return
	 */
	R batchRemoveAgentUserInfo(Map<String, Object> params);

	/**
	 * 批量修改报备状态
	 * @param params
	 * @return
	 */
	R batchsysBatchFiling(Map<String, Object> params);
}
	
	

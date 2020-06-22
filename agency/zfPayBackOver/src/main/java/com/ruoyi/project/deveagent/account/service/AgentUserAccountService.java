package com.ruoyi.project.deveagent.account.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.account.domain.AgentUserAccount;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======中付账号管理
 * @author Administrator
 *
 */
public interface AgentUserAccountService {

	
	/**
	 * 查询中付账号列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserAccountList(Map<String, Object> params);
	
	
	/**
	 * 导出中付账号信息
	 * @param params
	 * @return
	 */
	List<AgentUserAccount> selectAgentUserAccountList(Map<String, Object> params);


	/**
	 * 根据id查询中付账号详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentUserAccountById(String id);


	/**
	 * 新增保存中付账号
	 * @param params
	 * @return
	 */
	R addAgentUserAccount(Map<String, Object> params);


	/**
	 * 修改保存中付账号
	 * @param params
	 * @return
	 */
	R editAgentUserAccount(Map<String, Object> params);


	/**
	 * 批量删除中付账号
	 * @param params
	 * @return
	 */
	R batchRemoveAgentUserAccount(Map<String, Object> params);


	/**
	 * 批量开启关闭中付账号
	 * @param params
	 * @return
	 */
	R batchSysOpenAgentUserAccount(Map<String, Object> params);


	/**
	 * 查询代理有效的中付账号列表
	 * @param params
	 * @return
	 */
	List<AgentUserAccount> getAgentValidUserAccountList();

}
	
	

package com.ruoyi.project.deveagent.account.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.account.domain.AgentUserAccount;



/**
 * 代理====》用户中付账号管理
 * @author Administrator
 *
 */
public interface AgentUserAccountMapper {


	/**
	 * 查询用户中付账号列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserAccountList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户中付账号列表
	 * @param params
	 * @return
	 */
	List<AgentUserAccount> selectAgentUserAccountList(@Param("map") Map<String, Object> params);


	/**
	 * 根据id查询用户中付账号详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentUserAccountById(@Param("account_id") String account_id);


	/**
	 * 新增保存中付账号
	 * @param map
	 * @return
	 */
	int addAgentUserAccount(@Param("map") Map<String, Object> params);


	/**
	 * 更新保存中付账号
	 * @param params
	 * @return
	 */
	int updateAgentUserAccount(@Param("map") Map<String, Object> params);


	/**
	 * 根据id删除中付账号
	 * @param string
	 * @return
	 */
	int delAgentUserAccountById(@Param("map") Map<String, Object> params);


	/**
	 * 更新中付账号启用状态
	 * @param accountMap
	 * @return
	 */
	int updateAgentUserAccountStart(@Param("map") Map<String, Object> accountMap);


	/**
	 * 查询有效的代理账号列表
	 * @param params 
	 * @return
	 */
	List<AgentUserAccount> getAgentValidUserAccountList(@Param("map") Map<String, Object> params);


	/**
	 * 查询中付代理账号
	 * @param params
	 * @return
	 */
	Map<String, Object> getAgentUserAccount(@Param("map") Map<String, Object> params);

}

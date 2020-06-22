package com.ruoyi.project.deveagent.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.user.domain.AgentUserInfo;




/**
 * 代理====》用户信息管理
 * @author Administrator
 *
 */
public interface AgentUserInfoMapper {


	/**
	 * 查询用户信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserInfoList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 统计用户信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryAgentUserInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户列表
	 * @param params
	 * @return
	 */
	List<AgentUserInfo> selectAgentUserInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 更新用户状态
	 * @param userMap
	 * @return
	 */
	int updateAgentUserStatus(@Param("map") Map<String, Object> userMap);
	
	
	/**
	 * 更新用户实名认证状态
	 * @param userMap
	 * @return
	 */
	int updateAgentUserAuthStatus(@Param("map") Map<String, Object> userMap);


	/**
	 * 记录冻结账号
	 * @param userMap
	 * @return
	 */
	int addFrozeAgentUser(@Param("map") Map<String, Object> userMap);


	/**
	 * 删除冻结账号
	 * @param userMap
	 * @return
	 */
	int deleteFrozeAgentUser(@Param("map") Map<String, Object> userMap);


	/**
	 * 根据用户id查询用户详情
	 * @param id
	 * @return
	 */
	AgentUserInfo getAgentUserInfoById(@Param("user_id") String user_id);


	/**
	 * 根据id查询用户信息
	 * @param string
	 * @return
	 */
	Map<String, Object> getAgentUserMapById(@Param("user_id") String user_id);


	/**
	 * 更新用户信息
	 * @param params
	 * @return
	 */
	int updateAgentUserInfo(@Param("map") Map<String, Object> params);


	/**
	 * 查询父级团队成员列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getParentAgentUserInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 导出父级成员信息列表
	 * @param params
	 * @return
	 */
	List<AgentUserInfo> selectParentAgentUserInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 查询父级团队成员列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getChildrenAgentUserList(@Param("map") Map<String, Object> params);


	/**
	 * 导出父级成员信息列表
	 * @param params
	 * @return
	 */
	List<AgentUserInfo> selectChildrenAgentUserInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 用户手机号信息校验
	 * @param params
	 * @return
	 */
	Map<String, Object> checkSysAgentUserTel(@Param("map") Map<String, Object> params);


	/**
	 * 更新提现信息====》提现失败退还
	 * @param cashDetailMap
	 * @return
	 */
	int updateAgentUserByCashFaile(@Param("map") Map<String, Object> cashDetailMap);
	
	
	/**
	 * 更新提现信息====》提现成功
	 * @param cashMap
	 * @return
	 */
	int updateAgentUserByCashSuccess(@Param("map") Map<String, Object> cashMap);


	/**
	 * 校验某个用户是否是当前代理的一级代理
	 * @param params
	 * @return
	 */
	Map<String, Object> getAgentFirstAgentUserInfo(@Param("map") Map<String, Object> params);


	/**
	 * 查询用户信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getAgentUserMapInfo(@Param("map") Map<String, Object> params);


	/**
	 * 更新父级链的伞下人数，伞下人数减1
	 * @param userMap
	 * @return
	 */
	int updateAgentParentUnderNum(@Param("map") Map<String, Object> userMap);


	/**
	 * 更新父级的直推人数，直推人数减1
	 * @param userMap
	 * @return
	 */
	int updateAgentUserReferNum(@Param("map") Map<String, Object> userMap);


	/**
	 * 删除用户信息
	 * @param userMap
	 * @return
	 */
	int deleteAgentUserInfo(@Param("map") Map<String, Object> userMap);


	/**
	 * 更新用户报备状态
	 * @param userReportRecordMap
	 * @return
	 */
	int updateAgentUserReportStatus(@Param("map") Map<String, Object> userReportRecordMap);


	/***
	 * 查询一级代理的子代理byqh
	 * @param user_id
	 * @return
	 */
	List<Map<String, Object>> selectSubAgentInfo(@Param("user_id") String user_id);

	/***
	 * 202006
	 * @param user_tel
	 * @return
	 */
	AgentUserInfo selectUserInfoAllByTel(@Param("user_tel") String user_tel);
	
}

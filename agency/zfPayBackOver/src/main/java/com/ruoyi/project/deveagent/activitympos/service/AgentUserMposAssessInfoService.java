package com.ruoyi.project.deveagent.activitympos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.activitympos.domain.AgentUserMposAssessInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户MPOS系统考核管理
 * @author Administrator
 *
 */
public interface AgentUserMposAssessInfoService {

	
	/**
	 * 查询用户MPOS系统考核列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposAssessInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出用户MPOS系统考核信息
	 * @param params
	 * @return
	 */
	List<AgentUserMposAssessInfo> selectAgentUserMposAssessInfoList(Map<String, Object> params);


	/**
	 * 新增保存用户MPOS系统考核信息
	 * @param params
	 * @return
	 */
	R addAgentUserMposAssessInfo(Map<String, Object> params);


	/**
	 * 批量删除用户MPOS系统考核
	 * @param params
	 * @return
	 */
	R batchRemoveAgentUserMposAssessInfo(Map<String, Object> params);


	/**
	 * 根据id查询考核活动详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentUserMposAssessInfoById(String id);

}
	
	

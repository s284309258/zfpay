package com.ruoyi.project.deveagent.activitytrapos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.activitytrapos.domain.AgentUserTraposAssessInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户传统POS系统考核管理
 * @author Administrator
 *
 */
public interface AgentUserTraposAssessInfoService {

	
	/**
	 * 查询用户传统POS系统考核列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposAssessInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出用户传统POS系统考核信息
	 * @param params
	 * @return
	 */
	List<AgentUserTraposAssessInfo> selectAgentUserTraposAssessInfoList(Map<String, Object> params);


	/**
	 * 新增用户保存传统POS系统考核信息
	 * @param params
	 * @return
	 */
	R addAgentUserTraposAssessInfo(Map<String, Object> params);


	/**
	 * 批量删除用户传统POS系统考核
	 * @param params
	 * @return
	 */
	R batchRemoveAgentUserTraposAssessInfo(Map<String, Object> params);


	/**
	 * 根据id查询考核活动详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentUserTraposAssessInfoById(String id);

}
	
	

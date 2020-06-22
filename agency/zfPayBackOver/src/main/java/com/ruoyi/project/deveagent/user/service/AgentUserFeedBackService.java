package com.ruoyi.project.deveagent.user.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.user.domain.AgentUserFeedBack;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 用户意见反馈管理
 * @author Administrator
 *
 */
public interface AgentUserFeedBackService {

	
	/**
	 * 查询用户意见反馈列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserFeedBackList(Map<String, Object> params);

	
	/**
	 * 导出用户意见反馈列表
	 * @param params
	 * @return
	 */
	List<AgentUserFeedBack> selectAgentUserFeedBackList(Map<String, Object> params);


	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentUserFeedBackById(String id);


	/**
	 * 修改系统回复
	 * @param params
	 * @return
	 */
	R editAgentUserFeedBack(Map<String, Object> params);
	
}
	
	

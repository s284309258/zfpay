package com.ruoyi.project.deveagent.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.user.domain.AgentUserFeedBack;



/**
 * 用户意见反馈信息管理
 * @author Administrator
 *
 */
public interface AgentUserFeedBackMapper {


	/**
	 * 查询用户意见反馈列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserFeedBackList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户意见反馈列表
	 * @param params
	 * @return
	 */
	List<AgentUserFeedBack> selectAgentUserFeedBackList(@Param("map") Map<String, Object> params);


	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentUserFeedBackById(@Param("feed_back_id") String feed_back_id);


	/**
	 * 更新系统回复
	 * @param params
	 * @return
	 */
	int updateAgentUserFeedBack(@Param("map") Map<String, Object> params);
	
}

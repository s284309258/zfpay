package com.ruoyi.project.deveagent.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.user.domain.AgentUserMessageInfo;



/**
 * 代理====》用户通知管理
 * @author Administrator
 *
 */
public interface AgentUserMessageInfoMapper {


	/**
	 * 查询用户通知列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMessageInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 统计数据
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryAgentUserMessageInfoList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 导出用户通知列表
	 * @param params
	 * @return
	 */
	List<AgentUserMessageInfo> selectAgentUserMessageInfoList(@Param("map") Map<String, Object> params);

}

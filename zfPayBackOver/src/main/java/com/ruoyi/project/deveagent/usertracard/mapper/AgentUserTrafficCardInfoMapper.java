package com.ruoyi.project.deveagent.usertracard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usertracard.domain.AgentUserTrafficCardInfo;



/**
 * 代理====》用户流量卡信息管理
 * @author Administrator
 *
 */
public interface AgentUserTrafficCardInfoMapper {


	/**
	 * 查询用户流量卡信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTrafficCardInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户流量卡信息列表
	 * @param params
	 * @return
	 */
	List<AgentUserTrafficCardInfo> selectAgentUserTrafficCardInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 查询是否存在该用户和流量卡机的关系
	 * @param agentUserTrafficCardInfo
	 * @return
	 */
	Map<String, Object> getAgentUserTrafficCardInfo(AgentUserTrafficCardInfo agentUserTrafficCardInfo);


	/**
	 * 用户分配更新用户流量卡信息
	 * @param agentUserTrafficCardInfo
	 * @return
	 */
	int updateAgentUserTrafficCardInfoByDis(AgentUserTrafficCardInfo agentUserTrafficCardInfo);


	/**
	 * 建立用户流量卡关系
	 * @param agentUserTrafficCardInfo
	 * @return
	 */
	int addAgentUserTrafficCardInfoByDis(AgentUserTrafficCardInfo agentUserTrafficCardInfo);

}

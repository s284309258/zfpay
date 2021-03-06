package com.ruoyi.project.deveagent.usertrapos.mapper;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraditionalPosInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 代理====》用户传统POS信息管理
 * @author Administrator
 *
 */
public interface AgentUserEPosInfoMapper {


	/**
	 * 查询用户传统POS信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraditionalPosInfoList(@Param("map") Map<String, Object> params);

	/**
	 * 导出用户传统POS信息列表
	 * @param params
	 * @return
	 */
	List<AgentUserTraditionalPosInfo> selectAgentUserTraditionalPosInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 更新用户传统POS机是否参与活动的状态
	 * @param activityApplyMap
	 * @return
	 */
	int updateAgentUserTraditionalPosInfoActivityStatus(@Param("map") Map<String, Object> activityApplyMap);

	
	/**
	 * 校验用户可以参与系统考核的传统POS信息
	 * @param params
	 * @return
	 */
	int getAgentJoinTraditionalposAssesInfoNum(@Param("map") Map<String, Object> params);

	
	/**
	 * 查询是否存在该用户和传统pos机的关系
	 * @param agentUserTraditionalPosInfo
	 * @return
	 */
	Map<String, Object> getAgentUserTraditionalPosInfo(AgentUserTraditionalPosInfo agentUserTraditionalPosInfo);

	
	/**
	 * 用户分配更新用户传统POS机信息
	 * @param agentUserTraditionalPosInfo
	 * @return
	 */
	int updateAgentUserTraditionalPosInfoByDis(AgentUserTraditionalPosInfo agentUserTraditionalPosInfo);

	
	/**
	 * 建立用户传统POS关系
	 * @param agentUserTraditionalPosInfo
	 * @return
	 */
	int addAgentUserTraditionalPosInfoByDis(AgentUserTraditionalPosInfo agentUserTraditionalPosInfo);

	
	/**
	 * 根据编号查询用户传统POS信息详情
	 * @param user_pos_id
	 * @return
	 */
	Map<String, Object> getAgentUserTraditionalPosInfoById(@Param("user_pos_id") String user_pos_id);

	
	/**
	 * 更新用户传统POS机信息
	 * @param params
	 * @return
	 */
	int updateAgentUserTraditionalPosInfo(@Param("map") Map<String, Object> params);

	
	/**
	 * 删除整条链上面的用户传统POS关系
	 * @param unbindMap
	 * @return
	 */
	int updateAgentSameChainUserTraditionalPosInfoDel(@Param("map") Map<String, Object> unbindMap);

	/***
	 * 查询一级代理的子代理byqh
	 * @param user_id
	 * @return
	 */
	List<Map<String, Object>> selectSubAgentInfo(@Param("user_id") String user_id);

}

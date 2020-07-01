package com.ruoyi.project.deveagent.usermpos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposInfo;
import org.apache.ibatis.annotations.Update;


/**
 * 代理====》用户MPOS信息管理
 * @author Administrator
 *
 */
public interface AgentUserMposInfoMapper {

	int checkIsReward(@Param("map") Map<String, Object> params);

	/**
	 * 查询用户MPOS信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposInfoList(@Param("map") Map<String, Object> params);



	List<Map<String, Object>> getAgentUserMposRecallList(@Param("map") Map<String, Object> params);


	List<Map<String, Object>> getAgentUserMposRecallHandleList(@Param("map") Map<String, Object> params);

	int recallUserPos(@Param("map") Map<String, Object> params);

	int agreeRecallUserPos(@Param("map") Map<String, Object> params);

	int refuseRecallUserPos(@Param("map") Map<String, Object> params);
	
	/**
	 * 导出用户MPOS信息列表
	 * @param params
	 * @return
	 */
	List<AgentUserMposInfo> selectAgentUserMposInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 更新用户Mpos机是否参与活动的状态
	 * @param activityApplyMap
	 * @return
	 */
	int updateAgentUserMposInfoActivityStatus(@Param("map") Map<String, Object> activityApplyMap);


	/**
	 * 校验用户可以参与系统考核的MPOS信息
	 * @param params
	 * @return
	 */
	int getAgentJoinMposAssesInfoNum(@Param("map") Map<String, Object> params);


	/**
	 * 查询是否存在该用户和Mpos机的关系
	 * @param agentUserMposInfo
	 * @return
	 */
	Map<String, Object> getAgentUserMposInfo(AgentUserMposInfo agentUserMposInfo);


	/**
	 * 用户分配更新用户MPOS机信息
	 * @param agentUserMposInfo
	 * @return
	 */
	int updateAgentUserMposInfoByDis(AgentUserMposInfo agentUserMposInfo);

	@Update("update t_user_mpos_info set state_status=0 where sn=#{sn} and user_id=#{user_id} and state_status = '1'")
	int updateAgentUserState0BySNUID(@Param("sn") String sn,@Param("user_id") String user_id);

	@Update("update t_user_mpos_info set is_reward=0 where sn=#{sn} and user_id=#{user_id} and is_reward=1")
	int upateIsReWard0BySNUID(@Param("sn") String sn,@Param("user_id") Integer user_id);

	@Update("update t_sys_pos_policy_info set module2_reward_flag=#{user_id} where sn=#{sn} and policy_id=#{policy_id} and module2_reward_flag is null")
	int updateIsReWard1BySNUID(@Param("policy_id") String policy_id,@Param("sn") String sn,@Param("user_id") String user_id);

	/**
	 * 建立用户MPOS关系
	 * @param agentUserMposInfo
	 * @return
	 */
	int addAgentUserMposInfoByDis(AgentUserMposInfo agentUserMposInfo);

	/***
	 * 更新t_user_mpos_info表where id=#{id} byqh
	 * @param agentUserMposInfo
	 * @return
	 */
	int updateAgentUserMposInfoBase(AgentUserMposInfo agentUserMposInfo);


	/**
	 * 根据编号查询用户MPOS信息详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentUserMposInfoById(@Param("user_pos_id") String user_pos_id);


	/**
	 * 更新用户MPOS信息
	 * @param params
	 * @return
	 */
	int updateAgentUserMposInfo(@Param("map") Map<String, Object> params);


	/**
	 * 删除整条链上面的用户MPOS关系
	 * @param unbindMap
	 * @return
	 */
	int updateAgentSameChainUserMposInfoDel(@Param("map") Map<String, Object> unbindMap);


}

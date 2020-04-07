package com.ruoyi.project.deveagent.activitympos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.activitympos.domain.AgentSysMposActivityRewardInfo;



/**
 * 代理====》线上活动(MPOS)奖励类型管理
 * @author Administrator
 *
 */
public interface AgentSysMposActivityRewardInfoMapper {


	/**
	 * 查询线上活动(MPOS)奖励类型列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSysMposActivityRewardInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出线上活动(MPOS)奖励类型列表
	 * @param params
	 * @return
	 */
	List<AgentSysMposActivityRewardInfo> selectAgentSysMposActivityRewardInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 根据id查看线上活动(MPOS)奖励类型详情
	 * @param params
	 * @return
	 */
	Map<String, Object> getAgentSysMposActivityRewardInfoById(@Param("reward_id") String reward_id);


	/**
	 * 新增保存线上活动（MPOS）奖励类型
	 * @param activityRewardMap
	 * @return
	 */
	int addAgentSysMposActivityRewardInfo(@Param("map") Map<String, Object> activityRewardMap);


	/**
	 * 更新线上活动（MPOS）奖励信息
	 * @param params
	 * @return
	 */
	int updateAgentSysMposActivityRewardInfo(@Param("map") Map<String, Object> params);


	/**
	 * 更新删除状态
	 * @param activityRewardMap
	 * @return
	 */
	int updateAgentSysMposActivityRewardInfoDel(@Param("map") Map<String, Object> activityRewardMap);


}

package com.ruoyi.project.deveagent.activitytrapos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.activitytrapos.domain.AgentSysTraposActivityRewardInfo;



/**
 * 代理====》线上活动(传统POS)奖励类型管理
 * @author Administrator
 *
 */
public interface AgentSysTraposActivityRewardInfoMapper {


	/**
	 * 查询线上活动(传统POS)奖励类型列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSysTraposActivityRewardInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出线上活动(传统POS)奖励类型列表
	 * @param params
	 * @return
	 */
	List<AgentSysTraposActivityRewardInfo> selectAgentSysTraposActivityRewardInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 根据id查看线上活动(传统POS)奖励类型详情
	 * @param params
	 * @return
	 */
	Map<String, Object> getAgentSysTraposActivityRewardInfoById(@Param("reward_id") String reward_id);


	/**
	 * 新增保存线上活动（传统POS）奖励类型
	 * @param activityRewardMap
	 * @return
	 */
	int addAgentSysTraposActivityRewardInfo(@Param("map") Map<String, Object> activityRewardMap);


	/**
	 * 更新线上活动（传统POS）奖励信息
	 * @param params
	 * @return
	 */
	int updateAgentSysTraposActivityRewardInfo(@Param("map") Map<String, Object> params);


	/**
	 * 更新删除状态
	 * @param activityRewardMap
	 * @return
	 */
	int updateAgentSysTraposActivityRewardInfoDel(@Param("map") Map<String, Object> activityRewardMap);


}

package com.ruoyi.project.deveagent.activitympos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.activitympos.domain.AgentSysMposActivityRewardInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======线上活动(MPOS)奖励类型管理
 * @author Administrator
 *
 */
public interface AgentSysMposActivityRewardInfoService {

	
	/**
	 * 查询线上活动(MPOS)奖励类型列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSysMposActivityRewardInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出线上活动(MPOS)奖励类型信息
	 * @param params
	 * @return
	 */
	List<AgentSysMposActivityRewardInfo> selectAgentSysMposActivityRewardInfoList(Map<String, Object> params);


	/**
	 * 批量新增线上活动(MPOS)奖励类型
	 * @param params
	 * @return
	 */
	R addAgentSysMposActivityRewardInfos(Map<String, Object> params);


	/**
	 * 根据id查询线上活动(MPOS)奖励类型详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentSysMposActivityRewardInfoById(String id);


	/**
	 * 修改线上活动(MPOS)奖励类型（只能待发布未删除的状态才能编辑）
	 * @param params
	 * @return
	 */
	R editAgentSysMposActivityRewardInfo(Map<String, Object> params);


	/**
	 * 批量删除线上活动(MPOS)奖励类型
	 * @param params
	 * @return
	 */
	R batchRemoveAgentSysMposActivityRewardInfo(Map<String, Object> params);

}
	
	

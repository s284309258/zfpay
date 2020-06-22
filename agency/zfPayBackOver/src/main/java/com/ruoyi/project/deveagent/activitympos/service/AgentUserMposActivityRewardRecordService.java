package com.ruoyi.project.deveagent.activitympos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.activitympos.domain.AgentUserMposActivityRewardRecord;

/**
 * 代理======用户线上活动(MPOS)奖励记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposActivityRewardRecordService {

	
	/**
	 * 查询用户线上活动(MPOS)奖励记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposActivityRewardRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出用户线上活动(MPOS)奖励记录
	 * @param params
	 * @return
	 */
	List<AgentUserMposActivityRewardRecord> selectAgentUserMposActivityRewardRecordList(Map<String, Object> params);

}
	
	

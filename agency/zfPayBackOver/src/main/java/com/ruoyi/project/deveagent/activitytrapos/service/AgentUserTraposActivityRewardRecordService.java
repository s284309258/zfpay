package com.ruoyi.project.deveagent.activitytrapos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.activitytrapos.domain.AgentUserTraposActivityRewardRecord;

/**
 * 代理======用户线上活动(传统POS)奖励记录管理
 * @author Administrator
 *
 */
public interface AgentUserTraposActivityRewardRecordService {

	
	/**
	 * 查询用户线上活动(传统POS)奖励记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposActivityRewardRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出用户线上活动(传统POS)奖励记录
	 * @param params
	 * @return
	 */
	List<AgentUserTraposActivityRewardRecord> selectAgentUserTraposActivityRewardRecordList(Map<String, Object> params);

}
	
	

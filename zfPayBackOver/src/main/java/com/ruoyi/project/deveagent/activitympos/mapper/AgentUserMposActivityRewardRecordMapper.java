package com.ruoyi.project.deveagent.activitympos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.activitympos.domain.AgentUserMposActivityRewardRecord;



/**
 * 代理====》用户线上活动(MPOS)奖励记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposActivityRewardRecordMapper {

	
	/**
	 * 查询用户线上活动(MPOS)奖励记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposActivityRewardRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户线上活动(MPOS)奖励记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserMposActivityRewardRecord> selectAgentUserMposActivityRewardRecordList(@Param("map") Map<String, Object> params);

}

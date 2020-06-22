package com.ruoyi.project.deveagent.usertracard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usertracard.domain.AgentUserTrafficCardRecallRecordInfo;



/**
 * 代理====》用户流量卡召回记录管理
 * @author Administrator
 *
 */
public interface AgentUserTrafficCardRecallRecordInfoMapper {


	/**
	 * 查询用户流量卡召回记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTrafficCardRecallRecordInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户流量卡召回记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserTrafficCardRecallRecordInfo> selectAgentUserTrafficCardRecallRecordInfoList(@Param("map") Map<String, Object> params);

}

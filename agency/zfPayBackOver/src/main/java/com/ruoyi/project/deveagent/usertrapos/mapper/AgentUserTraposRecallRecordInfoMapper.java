package com.ruoyi.project.deveagent.usertrapos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposRecallRecordInfo;



/**
 * 代理====》用户传统POS召回记录管理
 * @author Administrator
 *
 */
public interface AgentUserTraposRecallRecordInfoMapper {


	/**
	 * 查询用户传统POS召回记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposRecallRecordInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户传统POS召回记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserTraposRecallRecordInfo> selectAgentUserTraposRecallRecordInfoList(@Param("map") Map<String, Object> params);

}

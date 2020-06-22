package com.ruoyi.project.deveagent.usermpos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposRecallRecordInfo;



/**
 * 代理====》用户MPOS召回记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposRecallRecordInfoMapper {


	/**
	 * 查询用户MPOS召回记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposRecallRecordInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户MPOS召回记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserMposRecallRecordInfo> selectAgentUserMposRecallRecordInfoList(@Param("map") Map<String, Object> params);

}

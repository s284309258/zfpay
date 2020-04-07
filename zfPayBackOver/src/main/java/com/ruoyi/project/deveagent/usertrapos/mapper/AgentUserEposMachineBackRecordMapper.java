package com.ruoyi.project.deveagent.usertrapos.mapper;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposMachineBackRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 代理====》用户传统POS机具返现记录管理
 * @author Administrator
 *
 */
public interface AgentUserEposMachineBackRecordMapper {


	/**
	 * 查询用户传统POS机具返现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposMachineBackRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户传统POS机具返现记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserTraposMachineBackRecord> selectAgentUserTraposMachineBackRecordList(@Param("map") Map<String, Object> params);

}

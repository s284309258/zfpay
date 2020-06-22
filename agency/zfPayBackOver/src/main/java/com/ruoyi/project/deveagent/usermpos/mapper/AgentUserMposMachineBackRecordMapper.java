package com.ruoyi.project.deveagent.usermpos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposMachineBackRecord;



/**
 * 代理====》用户MPOS机具返现记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposMachineBackRecordMapper {


	/**
	 * 查询用户MPOS机具返现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposMachineBackRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户MPOS机具返现记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserMposMachineBackRecord> selectAgentUserMposMachineBackRecordList(@Param("map") Map<String, Object> params);

}

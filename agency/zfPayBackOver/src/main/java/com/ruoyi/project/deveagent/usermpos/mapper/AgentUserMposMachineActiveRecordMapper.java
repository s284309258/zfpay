package com.ruoyi.project.deveagent.usermpos.mapper;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposAllotRecord;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposMachineActiveRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 代理====》用户MPOS未达标扣除记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposMachineActiveRecordMapper {


	/**
	 * 查询用户MPOS未达标扣除记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户MPOS未达标扣除记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserMposMachineActiveRecord> selectAgentUserMposRecordList(@Param("map") Map<String, Object> params);

}

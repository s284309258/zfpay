package com.ruoyi.project.deveagent.usermpos.mapper;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposAllotRecord;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposDeductRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 代理====》用户MPOS未达标扣除记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposAllotRecordMapper {


	/**
	 * 查询用户MPOS未达标扣除记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposAllotRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户MPOS未达标扣除记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserMposAllotRecord> selectAgentUserMposAllotRecordList(@Param("map") Map<String, Object> params);

}

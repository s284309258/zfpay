package com.ruoyi.project.deveagent.usermpos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposDeductRecord;



/**
 * 代理====》用户MPOS未达标扣除记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposDeductRecordMapper {


	/**
	 * 查询用户MPOS未达标扣除记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposDeductRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户MPOS未达标扣除记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserMposDeductRecord> selectAgentUserMposDeductRecordList(@Param("map") Map<String, Object> params);

}

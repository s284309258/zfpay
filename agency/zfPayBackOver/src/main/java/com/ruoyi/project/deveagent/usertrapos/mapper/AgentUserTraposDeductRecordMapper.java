package com.ruoyi.project.deveagent.usertrapos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposDeductRecord;



/**
 * 代理====》用户传统POS未达标扣除记录管理
 * @author Administrator
 *
 */
public interface AgentUserTraposDeductRecordMapper {


	/**
	 * 查询用户传统POS未达标扣除记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposDeductRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户传统POS未达标扣除记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserTraposDeductRecord> selectAgentUserTraposDeductRecordList(@Param("map") Map<String, Object> params);

}

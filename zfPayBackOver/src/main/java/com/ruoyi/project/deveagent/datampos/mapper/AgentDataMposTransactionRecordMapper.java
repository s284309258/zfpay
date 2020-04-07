package com.ruoyi.project.deveagent.datampos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.datampos.domain.AgentDataMposTransactionRecord;



/**
 * 代理======MPOS交易记录管理
 * @author Administrator
 *
 */
public interface AgentDataMposTransactionRecordMapper {


	/**
	 * 查询MPOS交易记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentDataMposTransactionRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出MPOS交易记录
	 * @param params
	 * @return
	 */
	List<AgentDataMposTransactionRecord> selectAgentDataMposTransactionRecordList(@Param("map") Map<String, Object> params);


}

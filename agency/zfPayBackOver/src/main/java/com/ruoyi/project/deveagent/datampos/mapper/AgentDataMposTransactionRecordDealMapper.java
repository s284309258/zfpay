package com.ruoyi.project.deveagent.datampos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.datampos.domain.AgentDataMposTransactionRecordDeal;



/**
 * 代理======MPOS交易记录管理
 * @author Administrator
 *
 */
public interface AgentDataMposTransactionRecordDealMapper {


	/**
	 * 查询MPOS交易记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentDataMposTransactionRecordDealList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出MPOS交易记录
	 * @param params
	 * @return
	 */
	List<AgentDataMposTransactionRecordDeal> selectAgentDataMposTransactionRecordDealList(@Param("map") Map<String, Object> params);


}

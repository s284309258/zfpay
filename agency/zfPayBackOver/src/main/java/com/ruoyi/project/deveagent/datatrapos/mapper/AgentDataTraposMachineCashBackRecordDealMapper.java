package com.ruoyi.project.deveagent.datatrapos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.datatrapos.domain.AgentDataTraposMachineCashBackRecordDeal;



/**
 * 代理======机具返现记录管理
 * @author Administrator
 *
 */
public interface AgentDataTraposMachineCashBackRecordDealMapper {


	/**
	 * 查询机具返现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentDataTraposMachineCashBackRecordDealList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出机具返现记录列表
	 * @param params
	 * @return
	 */
	List<AgentDataTraposMachineCashBackRecordDeal> selectAgentDataTraposMachineCashBackRecordDealList(@Param("map") Map<String, Object> params);


}

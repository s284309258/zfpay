package com.ruoyi.project.deveagent.datatrapos.service;

import com.ruoyi.project.deveagent.datatrapos.domain.AgentDataTraposMachineCashBackRecordDeal;

import java.util.List;
import java.util.Map;

/**
 * 代理======机具返现记录管理
 * @author Administrator
 *
 */
public interface AgentDataEposMachineCashBackRecordDealService {

	
	/**
	 * 查询机具返现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentDataTraposMachineCashBackRecordDealList(Map<String, Object> params);
	
	
	/**
	 * 导出机具返现记录
	 * @param params
	 * @return
	 */
	List<AgentDataTraposMachineCashBackRecordDeal> selectAgentDataTraposMachineCashBackRecordDealList(Map<String, Object> params);

}
	
	

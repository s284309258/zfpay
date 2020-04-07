package com.ruoyi.project.deveagent.user.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.user.domain.AgentBenefitRecordMoney;

/**
 * 代理======用户资金流水管理
 * @author Administrator
 *
 */
public interface AgentBenefitRecordMoneyService {

	
	/**
	 * 查询用户资金流水列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentBenefitRecordMoneyList(Map<String, Object> params);
	
	
	/**
	 * 导出用户资金流水信息
	 * @param params
	 * @return
	 */
	List<AgentBenefitRecordMoney> selectAgentBenefitRecordMoneyList(Map<String, Object> params);

}
	
	

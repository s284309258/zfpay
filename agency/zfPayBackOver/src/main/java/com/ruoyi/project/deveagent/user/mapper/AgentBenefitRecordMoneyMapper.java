package com.ruoyi.project.deveagent.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.user.domain.AgentBenefitRecordMoney;



/**
 * 代理====》用户资金流水管理
 * @author Administrator
 *
 */
public interface AgentBenefitRecordMoneyMapper {


	/**
	 * 查询用户资金流水列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentBenefitRecordMoneyList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户资金流水列表
	 * @param params
	 * @return
	 */
	List<AgentBenefitRecordMoney> selectAgentBenefitRecordMoneyList(@Param("map") Map<String, Object> params);

}

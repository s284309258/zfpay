package com.ruoyi.project.deveagent.usertrapos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposShareBenefitRecord;

/**
 * 代理======用户传统POS分润记录管理
 * @author Administrator
 *
 */
public interface AgentUserTraposShareBenefitRecordService {

	
	/**
	 * 查询用户传统POS分润记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposShareBenefitRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出用户传统POS分润记录信息
	 * @param params
	 * @return
	 */
	List<AgentUserTraposShareBenefitRecord> selectAgentUserTraposShareBenefitRecordList(Map<String, Object> params);

}
	
	

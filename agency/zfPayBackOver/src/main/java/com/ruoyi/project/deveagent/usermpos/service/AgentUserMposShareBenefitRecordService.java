package com.ruoyi.project.deveagent.usermpos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposShareBenefitRecord;

/**
 * 代理======用户Mpos分润记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposShareBenefitRecordService {

	
	/**
	 * 查询用户Mpos分润记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposShareBenefitRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出用户Mpos分润记录信息
	 * @param params
	 * @return
	 */
	List<AgentUserMposShareBenefitRecord> selectAgentUserMposShareBenefitRecordList(Map<String, Object> params);

}
	
	

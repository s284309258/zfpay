package com.ruoyi.project.deveagent.usertrapos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposShareBenefitRecord;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraposShareBenefitRecordMapper;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposShareBenefitRecordService;


/**
 * 代理====》用户传统POS分润记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserTraposShareBenefitRecordServiceImpl implements AgentUserTraposShareBenefitRecordService {
	
	
	@Autowired
	private AgentUserTraposShareBenefitRecordMapper agentUserTraposShareBenefitRecordMapper;


	
	/**
	 * 查询用户传统POS分润记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTraposShareBenefitRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserTraposShareBenefitRecordMapper.getAgentUserTraposShareBenefitRecordList(params);
	}
	
	
	/**
	 * 导出用户传统POS分润记录列表
	 */
	@Override
	public List<AgentUserTraposShareBenefitRecord> selectAgentUserTraposShareBenefitRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserTraposShareBenefitRecordMapper.selectAgentUserTraposShareBenefitRecordList(params);
	}

}

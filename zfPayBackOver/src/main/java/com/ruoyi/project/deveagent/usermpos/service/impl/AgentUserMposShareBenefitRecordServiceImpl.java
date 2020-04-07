package com.ruoyi.project.deveagent.usermpos.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposShareBenefitRecord;
import com.ruoyi.project.deveagent.usermpos.mapper.AgentUserMposShareBenefitRecordMapper;
import com.ruoyi.project.deveagent.usermpos.service.AgentUserMposShareBenefitRecordService;


/**
 * 代理====》用户Mpos分润记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserMposShareBenefitRecordServiceImpl implements AgentUserMposShareBenefitRecordService {
	
	
	@Autowired
	private AgentUserMposShareBenefitRecordMapper agentUserMposShareBenefitRecordMapper;


	
	/**
	 * 查询用户Mpos分润记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserMposShareBenefitRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserMposShareBenefitRecordMapper.getAgentUserMposShareBenefitRecordList(params);
	}
	
	
	/**
	 * 导出用户Mpos分润记录列表
	 */
	@Override
	public List<AgentUserMposShareBenefitRecord> selectAgentUserMposShareBenefitRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserMposShareBenefitRecordMapper.selectAgentUserMposShareBenefitRecordList(params);
	}

}

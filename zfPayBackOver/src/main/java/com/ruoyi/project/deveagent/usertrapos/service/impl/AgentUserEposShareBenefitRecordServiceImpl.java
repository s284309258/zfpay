package com.ruoyi.project.deveagent.usertrapos.service.impl;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposShareBenefitRecord;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserEposShareBenefitRecordMapper;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraposShareBenefitRecordMapper;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserEposShareBenefitRecordService;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraposShareBenefitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 代理====》用户传统POS分润记录管理
 * @author Administrator
 *
 */
@Service
public class AgentUserEposShareBenefitRecordServiceImpl implements AgentUserEposShareBenefitRecordService {
	
	
	@Autowired
	private AgentUserEposShareBenefitRecordMapper agentUserEposShareBenefitRecordMapper;


	
	/**
	 * 查询用户传统POS分润记录列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTraposShareBenefitRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserEposShareBenefitRecordMapper.getAgentUserTraposShareBenefitRecordList(params);
	}
	
	
	/**
	 * 导出用户传统POS分润记录列表
	 */
	@Override
	public List<AgentUserTraposShareBenefitRecord> selectAgentUserTraposShareBenefitRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserEposShareBenefitRecordMapper.selectAgentUserTraposShareBenefitRecordList(params);
	}

}

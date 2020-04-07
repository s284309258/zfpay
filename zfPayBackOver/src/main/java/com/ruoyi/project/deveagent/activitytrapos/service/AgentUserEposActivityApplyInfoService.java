package com.ruoyi.project.deveagent.activitytrapos.service;

import com.ruoyi.project.deveagent.activitytrapos.domain.AgentUserTraposActivityApplyInfo;
import com.ruoyi.project.develop.common.domain.R;

import java.util.List;
import java.util.Map;

/**
 * 代理======用户线上活动(传统POS)申请记录管理
 * @author Administrator
 *
 */
public interface AgentUserEposActivityApplyInfoService {

	
	/**
	 * 查询用户线上活动(传统POS)申请记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposActivityApplyInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出线上活动(传统POS)信息
	 * @param params
	 * @return
	 */
	List<AgentUserTraposActivityApplyInfo> selectAgentUserTraposActivityApplyInfoList(Map<String, Object> params);


	/**
	 * 查询用户线上活动(传统POS)申请记录详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentUserTraposActivityApplyInfoDetailById(String id);


	/**
	 * 系统批量审核线上活动(传统POS)申请
	 * @param params
	 * @return
	 */
	R batchSysAuditUserTraposActivityApplyInfo(Map<String, Object> params);

}
	
	

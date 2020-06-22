package com.ruoyi.project.deveagent.activitympos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.activitympos.domain.AgentUserMposActivityApplyInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户线上活动(MPOS)申请记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposActivityApplyInfoService {

	
	/**
	 * 查询用户线上活动(MPOS)申请记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposActivityApplyInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出线上活动(MPOS)信息
	 * @param params
	 * @return
	 */
	List<AgentUserMposActivityApplyInfo> selectAgentUserMposActivityApplyInfoList(Map<String, Object> params);


	/**
	 * 查询用户线上活动(MPOS)申请记录详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentUserMposActivityApplyInfoDetailById(String id);


	/**
	 * 系统批量审核线上活动(MPOS)申请
	 * @param params
	 * @return
	 */
	R batchSysAuditUserMposActivityApplyInfo(Map<String, Object> params);

}
	
	

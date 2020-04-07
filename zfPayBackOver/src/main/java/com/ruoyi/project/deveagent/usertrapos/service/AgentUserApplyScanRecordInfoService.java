package com.ruoyi.project.deveagent.usertrapos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserApplyScanRecordInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户申请扫码支付管理
 * @author Administrator
 *
 */
public interface AgentUserApplyScanRecordInfoService {

	
	/**
	 * 查询用户申请扫码支付列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserApplyScanRecordInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出用户申请扫码支付信息
	 * @param params
	 * @return
	 */
	List<AgentUserApplyScanRecordInfo> selectAgentUserApplyScanRecordInfoList(Map<String, Object> params);


	/**
	 * 系统批量审核扫码支付申请
	 * @param params
	 * @return
	 */
	R batchAgentSysAuditUserApplyScanRecordInfo(Map<String, Object> params);


	/**
	 * 根据编号查询用户申请扫码支付详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentUserApplyScanRecordInfoById(String id);

}
	
	

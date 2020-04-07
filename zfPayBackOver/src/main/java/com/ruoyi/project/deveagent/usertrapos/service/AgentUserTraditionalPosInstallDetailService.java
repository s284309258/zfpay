package com.ruoyi.project.deveagent.usertrapos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraditionalPosInstallDetail;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户传统POS商户进件详情管理
 * @author Administrator
 *
 */
public interface AgentUserTraditionalPosInstallDetailService {

	
	/**
	 * 查询用户传统POS商户进件详情列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraditionalPosInstallDetailList(Map<String, Object> params);
	
	
	/**
	 * 导出用户传统POS商户进件详情信息
	 * @param params
	 * @return
	 */
	List<AgentUserTraditionalPosInstallDetail> selectAgentUserTraditionalPosInstallDetailList(Map<String, Object> params);


	/**
	 * 新增商户进件详情
	 * @param params
	 * @return
	 */
	R addAgentUserTraditionalPosInstallDetail(Map<String, Object> params);


	/**
	 * 根据编号查询商户进件详情
	 * @param id
	 * @return
	 */
	AgentUserTraditionalPosInstallDetail getAgentUserTraditionalPosInstallDetailById(String id);

	
	/**
	 * 修改商户进件详情
	 * @param params
	 * @return
	 */
	R editAgentUserTraditionalPosInstallDetail(Map<String, Object> params);


	/**
	 * 批量删除商户进件详情
	 * @param ids
	 * @return
	 */
	R batchRemoveAgentUserTraditionalPosInstallDetail(String ids);

}
	
	

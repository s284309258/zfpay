package com.ruoyi.project.deveagent.activitytrapos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.activitytrapos.domain.AgentSysTraposActivityInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======线上活动(传统POS)管理
 * @author Administrator
 *
 */
public interface AgentSysTraposActivityInfoService {

	
	/**
	 * 查询线上活动(传统POS)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSysTraposActivityInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出线上活动(传统POS)信息
	 * @param params
	 * @return
	 */
	List<AgentSysTraposActivityInfo> selectAgentSysTraposActivityInfoList(Map<String, Object> params);
	
	
	/**
	 * 新增线上活动(传统POS)
	 * @param params
	 * @return
	 */
	R addAgentSysTraposActivityInfo(Map<String, Object> params);


	/**
	 * 根据id查询线上活动详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentSysTraposActivityInfoById(String id);


	/**
	 * 修改保存线上活动（只能待发布未删除的状态才能编辑）
	 * @param params
	 * @return
	 */
	R editAgentSysTraposActivityInfo(Map<String, Object> params);


	/**
	 * 批量删除线上活动（传统POS）====>只能删除未发布且未删除的活动
	 * @param params
	 * @return
	 */
	R batchRemoveAgentSysTraposActivityInfo(Map<String, Object> params);


	/**
	 * 系统批量发布和取消系统活动
	 * @param params
	 * @return
	 */
	R batchSysReleaseAgentSysTraposActivityInfo(Map<String, Object> params);

}
	
	

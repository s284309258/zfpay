package com.ruoyi.project.deveagent.activitympos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.activitympos.domain.AgentSysMposActivityInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======线上活动(MPOS)管理
 * @author Administrator
 *
 */
public interface AgentSysMposActivityInfoService {

	
	/**
	 * 查询线上活动(MPOS)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSysMposActivityInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出线上活动(MPOS)信息
	 * @param params
	 * @return
	 */
	List<AgentSysMposActivityInfo> selectAgentSysMposActivityInfoList(Map<String, Object> params);


	/**
	 * 新增线上活动(MPOS)
	 * @param params
	 * @return
	 */
	R addAgentSysMposActivityInfo(Map<String, Object> params);


	/**
	 * 根据id查询线上活动详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentSysMposActivityInfoById(String id);


	/**
	 * 修改保存线上活动（只能待发布未删除的状态才能编辑）
	 * @param params
	 * @return
	 */
	R editAgentSysMposActivityInfo(Map<String, Object> params);


	/**
	 * 批量删除线上活动（MPOS）====>只能删除未发布且未删除的活动
	 * @param params
	 * @return
	 */
	R batchRemoveAgentSysMposActivityInfo(Map<String, Object> params);


	/**
	 * 系统批量发布和取消系统活动
	 * @param params
	 * @return
	 */
	R batchSysReleaseAgentSysMposActivityInfo(Map<String, Object> params);

}
	
	

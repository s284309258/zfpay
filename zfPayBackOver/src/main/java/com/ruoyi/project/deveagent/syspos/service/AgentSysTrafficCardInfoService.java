package com.ruoyi.project.deveagent.syspos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.syspos.domain.AgentSysTrafficCardInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======系统流量卡信息管理
 * @author Administrator
 *
 */
public interface AgentSysTrafficCardInfoService {

	
	/**
	 * 查询系统流量卡信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSysTrafficCardInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出系统流量卡信息
	 * @param params
	 * @return
	 */
	List<AgentSysTrafficCardInfo> selectAgentSysTrafficCardInfoList(Map<String, Object> params);


	/**
	 * 导入系统流量卡信息数据
	 * @param agentSysTrafficCardInfoList
	 * @return
	 */
	R importAgentSysTrafficCardInfoList(List<AgentSysTrafficCardInfo> agentSysTrafficCardInfoList);


	/**
	 * 跳转到新增系统流量卡
	 * @param params
	 * @return
	 */
	R addAgentSysTrafficCardInfo(Map<String, Object> params);


	/**
	 * 根据编号查询系统流量卡信息
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentSysTrafficCardInfoById(String id);


	/**
	 * 批量删除系统流量卡信息====>只能删除未分配的流量卡
	 * @param params
	 * @return
	 */
	R batchRemoveAgentSysTrafficCardInfo(Map<String, Object> params);


	/**
	 * 根据卡号查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentSysTrafficCardInfoDetailByCardNo(String id);

}
	
	

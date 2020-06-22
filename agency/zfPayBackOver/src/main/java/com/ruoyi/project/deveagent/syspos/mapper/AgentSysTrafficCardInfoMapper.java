package com.ruoyi.project.deveagent.syspos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.syspos.domain.AgentSysTrafficCardInfo;
import com.ruoyi.project.deveagent.usertracard.domain.AgentSelectUserTrafficCardInfo;



/**
 * 代理====》系统流量卡信息管理
 * @author Administrator
 *
 */
public interface AgentSysTrafficCardInfoMapper {


	/**
	 * 查询系统流量卡信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSysTrafficCardInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出系统流量卡信息列表
	 * @param params
	 * @return
	 */
	List<AgentSysTrafficCardInfo> selectAgentSysTrafficCardInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 保存系统流量卡信息
	 * @param agentSysTrafficCardInfo
	 * @return
	 */
	int addAgentSysTrafficCardInfo(AgentSysTrafficCardInfo agentSysTrafficCardInfo);


	/**
	 * 根据流量卡号查询系统流量卡信息
	 * @param sn
	 * @return
	 */
	Map<String, Object> getAgentSysTrafficCardInfoByCardNo(@Param("card_no") String card_no);


	/**
	 * 修改系统流量卡的分配状态
	 * @param sysTrafficCardMap
	 * @return
	 */
	int updateAgentSysTrafficCardInfoDisStatus(@Param("map") Map<String, Object> sysTrafficCardMap);


	/**
	 * 导出可分配的流量卡导入模板
	 * @param params
	 * @return
	 */
	List<AgentSelectUserTrafficCardInfo> selectAgentNoDisSysTrafficCardInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 根据编号查询系统流量卡详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentSysTrafficCardInfoById(@Param("card_id") String id);


	/**
	 * 删除系统流量卡机信息
	 * @param sysTrafficCardMap
	 * @return
	 */
	int deleteAgentSysTrafficCardInfo(@Param("map") Map<String, Object> sysTrafficCardMap);


	/**
	 * 根据卡号查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentSysTrafficCardInfoDetailByCardNo(@Param("card_no") String card_no);


}

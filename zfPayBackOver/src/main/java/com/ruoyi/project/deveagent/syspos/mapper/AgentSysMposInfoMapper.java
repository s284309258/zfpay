package com.ruoyi.project.deveagent.syspos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.syspos.domain.AgentSysMposInfo;
import com.ruoyi.project.deveagent.usermpos.domain.AgentSelectUserMposInfo;



/**
 * 代理====》系统MPOS信息管理
 * @author Administrator
 *
 */
public interface AgentSysMposInfoMapper {


	/**
	 * 查询系统MPOS信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSysMposInfoList(@Param("map") Map<String, Object> params);

	/***
	 * 得到代理名下pos add byqh202006
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getOneAgentPosList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出系统MPOS信息列表
	 * @param params
	 * @return
	 */
	List<AgentSysMposInfo> selectAgentSysMposInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 修改系统MPOS刷卡费率
	 * @param applyMap
	 * @return
	 */
	int updateAgentSysMposInfoCreditCardRate(@Param("map") Map<String, Object> applyMap);


	/**
	 * 保存系统MPOS信息
	 * @param agentSysMposInfo
	 * @return
	 */
	int addAgentSysMposInfo(AgentSysMposInfo agentSysMposInfo);


	/**
	 * 根据设备号（机器编号）查询系统MPOS信息
	 * @param sn
	 * @return
	 */
	Map<String, Object> getAgentSysMposInfoBySn(@Param("sn") String sn);


	/**
	 * 修改系统MPOS的分配状态
	 * @param sysMposMap
	 * @return
	 */
	int updateAgentSysMposInfoDisStatus(@Param("map") Map<String, Object> sysMposMap);

	/***
	 * 修改系统MPOS(含已分配和有交易的)分配状态,所属对象,费率byqh
	 * @param sysMposMap
	 * @return
	 */
	int updateSubAgentSysMposInfo(@Param("map") Map<String, Object> sysMposMap);


	/**
	 * 导出可分配的Mpos导入模板
	 * @param params
	 * @return
	 */
	List<AgentSelectUserMposInfo> selectAgentNoDisSysMposInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 根据编号查询系统MPOS详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentSysMposInfoById(@Param("pos_id") String id);


	/**
	 * 修改系统MPOS信息
	 * @param params
	 * @return
	 */
	int updateAgentSysMposInfo(@Param("map") Map<String, Object> params);


	/**
	 * 删除系统MPOS机信息
	 * @param sysMposMap
	 * @return
	 */
	int deleteAgentSysMposInfo(@Param("map") Map<String, Object> sysMposMap);


	/**
	 * 根据编号查询系统MPOS详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentSysMposInfoDetailBySn(@Param("sn") String sn);

	/**
	 * 查询系统一级代理MPOS信息列表byqh
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSubAgentSysMposInfoList(@Param("map") Map<String, Object> params);


}

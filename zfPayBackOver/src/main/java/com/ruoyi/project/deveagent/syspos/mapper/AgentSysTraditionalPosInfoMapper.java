package com.ruoyi.project.deveagent.syspos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.syspos.domain.AgentSysTraditionalPosInfo;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentSelectUserTraditionalPosInfo;
import org.apache.ibatis.annotations.Select;


/**
 * 代理====》系统传统POS信息管理
 * @author Administrator
 *
 */
public interface AgentSysTraditionalPosInfoMapper {


	List<Map<String,Object>> getOneAgentPosList(@Param("map") Map<String, Object> params);

	/**
	 * 查询系统传统POS信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSysTraditionalPosInfoList(@Param("map") Map<String, Object> params);

	/**
	 * 查询系统一级代理传统POS信息列表byqh
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getSubAgentSysTraditionalPosInfoList(@Param("map") Map<String, Object> params);

	/**
	 * 导出系统传统POS信息列表
	 * @param params
	 * @return
	 */
	List<AgentSysTraditionalPosInfo> selectAgentSysTraditionalPosInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 更新传统POS的刷卡费率
	 * @param applyMap
	 * @return
	 */
	int updateSysTraditionalPosInfoCreditCardRate(@Param("map") Map<String, Object> applyMap);

	
	/**
	 * 保存传统POS机信息
	 * @param agentSysTraditionalPosInfo
	 * @return
	 */
	int addAgentTraditionalPosInfo(AgentSysTraditionalPosInfo agentSysTraditionalPosInfo);

	
	/**
	 * 根据设备号（机器编号）查询POS机详情
	 * @param sn
	 * @return
	 */
	Map<String, Object> getAgentSysTraditionalPosInfoBySn(@Param("sn") String sn);


	String getAgentSysTraditionalPosMer_nameInfoBySn(@Param("sn") String sn);

	/**
	 * 根据设备号（机器编号）查询POS机详情
	 * @param sn
	 * @return
	 */
	@Select("select pos_type from t_sys_traditional_pos_info where sn=#{sn}")
	String getAgentSysEposInfoBySn(@Param("sn") String sn);

	
	/**
	 * 根据编号查询传统POS机详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentSysTraditionalPosInfoById(@Param("pos_id") String pos_id);

	
	/**
	 * 更新传统POS机信息
	 * @param params
	 * @return
	 */
	int updateAgentSysTraditionalPosInfo(@Param("map") Map<String, Object> params);

	
	/**
	 * 删除单个系统传统POS信息======》只能删除未分配的
	 * @param sysMposMap
	 * @return
	 */
	int deleteAgentSysTraditionalPosInfo(@Param("map") Map<String, Object> sysMposMap);

	
	/**
	 * 修改系统传统POS的分配状态
	 * @param sysMposMap
	 * @return
	 */
	int updateAgentSysTraditionalPosInfoDisStatus(@Param("map") Map<String, Object> sysMposMap);


	/**
	 * 修改系统MPOS(含已分配和有交易的)分配状态,所属对象,费率byqh
	 * @param sysMposMap
	 * @return
	 */
	int updateSubAgentSysTraditionalPosInfoDisStatus(@Param("map") Map<String, Object> sysMposMap);
			
	
	/**
	 * 导出可分配的传统pos导入模板
	 * @param params
	 * @return
	 */
	List<AgentSelectUserTraditionalPosInfo> selectAgentNoDisSysTraditionalPosInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 更新传统POS的扫码支付状态
	 * @param applyMap
	 * @return
	 */
	int updateAgentSysTraditionalPosInfoScanStatus(@Param("map") Map<String, Object> applyMap);

	
	/**
	 * 根据设备号（机器编号）查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentSysTraditionalPosInfoDetailBySn(@Param("sn") String sn);


}

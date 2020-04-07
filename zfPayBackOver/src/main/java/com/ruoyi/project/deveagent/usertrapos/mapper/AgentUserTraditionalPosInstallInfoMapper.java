package com.ruoyi.project.deveagent.usertrapos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraditionalPosInstallInfo;



/**
 * 代理====》用户传统POS商户进件管理
 * @author Administrator
 *
 */
public interface AgentUserTraditionalPosInstallInfoMapper {


	/**
	 * 查询用户传统POS商户进件列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraditionalPosInstallInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户传统POS商户进件列表
	 * @param params
	 * @return
	 */
	List<AgentUserTraditionalPosInstallInfo> selectAgentUserTraditionalPosInstallInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 保存进件信息
	 * @param params
	 * @return
	 */
	int addAgentUserTraditionalPosInstallInfo(@Param("map") Map<String, Object> params);


	/**
	 * 根据编号查询进件详情
	 * @param id
	 * @return
	 */
	AgentUserTraditionalPosInstallInfo getAgentUserTraditionalPosInstallInfoById(@Param("install_id") String id);


	/**
	 * 修改保存进件信息
	 * @param params
	 * @return
	 */
	int updateAgentUserTraditionalPosInstallInfo(@Param("map") Map<String, Object> params);


	/**
	 * 根据编号删除装机信息
	 * @param installId
	 * @return
	 */
	int deleteAgentUserTraditionalPosInstallInfoById(@Param("install_id") String installId);

}

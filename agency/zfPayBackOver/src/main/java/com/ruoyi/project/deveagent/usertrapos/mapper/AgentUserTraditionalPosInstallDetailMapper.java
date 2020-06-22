package com.ruoyi.project.deveagent.usertrapos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraditionalPosInstallDetail;



/**
 * 代理====》用户传统POS商户进件详情管理
 * @author Administrator
 *
 */
public interface AgentUserTraditionalPosInstallDetailMapper {


	/**
	 * 查询用户传统POS商户进件详情列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraditionalPosInstallDetailList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户传统POS商户进件详情列表
	 * @param params
	 * @return
	 */
	List<AgentUserTraditionalPosInstallDetail> selectAgentUserTraditionalPosInstallDetailList(@Param("map") Map<String, Object> params);


	/**
	 * 根据编号查询装机详情数量
	 * @param installId
	 * @return
	 */
	Integer countAgentUserTraditionalPosInstallDetai(@Param("install_id") String install_id);


	/**
	 * 新增商户进件明细
	 * @param params
	 * @return
	 */
	int addAgentUserTraditionalPosInstallDetail(@Param("map") Map<String, Object> params);

	
	/**
	 * 根据编号查询商户进件详情
	 * @param id
	 * @return
	 */
	AgentUserTraditionalPosInstallDetail getAgentUserTraditionalPosInstallDetailById(@Param("detail_id") String detail_id);


	/**
	 * 更新商户进件明细
	 * @param params
	 * @return
	 */
	int updateAgentUserTraditionalPosInstallDetail(@Param("map") Map<String, Object> params);


	/**
	 * 根据编号删除商户进件明细
	 * @param detailId
	 * @return
	 */
	int deleteAgentUserTraditionalPosInstallDetailById(@Param("detail_id") String detail_id);

}

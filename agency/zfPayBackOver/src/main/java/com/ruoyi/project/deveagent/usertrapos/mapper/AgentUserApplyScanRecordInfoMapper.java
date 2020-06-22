package com.ruoyi.project.deveagent.usertrapos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserApplyScanRecordInfo;



/**
 * 代理====》用户申请扫码支付管理
 * @author Administrator
 *
 */
public interface AgentUserApplyScanRecordInfoMapper {


	/**
	 * 查询用户申请扫码支付列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserApplyScanRecordInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户申请扫码支付列表
	 * @param params
	 * @return
	 */
	List<AgentUserApplyScanRecordInfo> selectAgentUserApplyScanRecordInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 更新申请扫码支付的状态
	 * @param applyMap
	 * @return
	 */
	int updateAgentUserApplyScanRecordInfoStatus(@Param("map") Map<String, Object> applyMap);


	/**
	 * 根据编号查询扫码支付申请详情
	 * @param string
	 * @return
	 */
	Map<String, Object> getAgentUserApplyScanRecordInfoById(@Param("apply_id") String apply_id);


}

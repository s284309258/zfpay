package com.ruoyi.project.deveagent.activitytrapos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.activitytrapos.domain.AgentUserTraposAssessInfo;



/**
 * 代理====》用户传统POS系统考核管理
 * @author Administrator
 *
 */
public interface AgentUserTraposAssessInfoMapper {


	/**
	 * 查询用户传统POS系统考核列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposAssessInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户传统POS系统考核列表
	 * @param params
	 * @return
	 */
	List<AgentUserTraposAssessInfo> selectAgentUserTraposAssessInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 查询该设备号（机器编号）是否已经参与活动
	 * @param params
	 * @return
	 */
	int getAgentHaveJoinSnNum(@Param("map") Map<String, Object> params);


	/**
	 * 新增保存系统考核信息
	 * @param params
	 * @return
	 */
	int addAgentUserTraposAssessInfo(@Param("map") Map<String, Object> params);


	/**
	 * 根据id删除系统考核信息
	 * @param assess_ids
	 * @return
	 */
	int deleteAgentUserTraposAssessInfo(@Param("map") Map<String, Object> assessMap);


	/**
	 * 根据id查询考核活动详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentUserTraposAssessInfoById(@Param("assess_id") String assess_id);


	/**
	 * 更新绑定用户MPOS系统考核的任务编号
	 * @param params
	 * @return
	 */
	int updateAgentUserTraposAssessInfoJobId(@Param("map") Map<String, Object> params);

}

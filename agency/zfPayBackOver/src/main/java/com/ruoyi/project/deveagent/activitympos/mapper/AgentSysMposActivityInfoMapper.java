package com.ruoyi.project.deveagent.activitympos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.activitympos.domain.AgentSysMposActivityInfo;



/**
 * 代理====》线上活动(MPOS)管理
 * @author Administrator
 *
 */
public interface AgentSysMposActivityInfoMapper {


	/**
	 * 查询线上活动(MPOS)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSysMposActivityInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出线上活动(MPOS)列表
	 * @param params
	 * @return
	 */
	List<AgentSysMposActivityInfo> selectAgentSysMposActivityInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 新增线上活动(MPOS)
	 * @param params
	 * @return
	 */
	int addAgentSysMposActivityInfo(@Param("map") Map<String, Object> params);


	/**
	 * 根据id查看线上活动详情
	 * @param params
	 * @return
	 */
	Map<String, Object> getAgentSysMposActivityInfo(@Param("map") Map<String, Object> params);


	/**
	 * 修改保存线上活动（只能待发布未删除的状态才能编辑）
	 * @param params
	 * @return
	 */
	int updateAgentSysMposActivityInfo(@Param("map") Map<String, Object> params);


	/**
	 * 删除单个线上活动（MPOS）=====》只能删除未发布且未删除的活动
	 * @param activityMap
	 * @return
	 */
	int updateAgentSysMposActivityInfoDel(@Param("map") Map<String, Object> activityMap);


	/**
	 * 查询是否存在同类型的正在进行中的未删除的活动信息
	 * @param userMap
	 * @return
	 */
	Map<String, Object> getAgentOnMposActivitySameType(@Param("map") Map<String, Object> userMap);


	/**
	 * 更新线上活动（MPOS）活动状态
	 * @param activityMap
	 * @return
	 */
	int updateAgentSysMposActivityInfoStatus(@Param("map") Map<String, Object> activityMap);


	/**
	 * 根据活动id查询活动详情
	 * @param activity_id
	 * @return
	 */
	Map<String, Object> getAgentSysMposActivityInfoById(@Param("activity_id") String activity_id);

}

package com.ruoyi.project.deveagent.activitytrapos.mapper;

import com.ruoyi.project.deveagent.activitytrapos.domain.AgentSysTraposActivityInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 代理====》线上活动(传统POS)管理
 * @author Administrator
 *
 */
public interface AgentSysEposActivityInfoMapper {


	/**
	 * 查询线上活动(传统POS)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentSysTraposActivityInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出线上活动(传统POS)列表
	 * @param params
	 * @return
	 */
	List<AgentSysTraposActivityInfo> selectAgentSysTraposActivityInfoList(@Param("map") Map<String, Object> params);
	

	/**
	 * 新增线上活动(传统POS)
	 * @param params
	 * @return
	 */
	int addAgentSysTraposActivityInfo(@Param("map") Map<String, Object> params);


	/**
	 * 根据id查看线上活动详情
	 * @param params
	 * @return
	 */
	Map<String, Object> getAgentSysTraposActivityInfo(@Param("map") Map<String, Object> params);


	/**
	 * 修改保存线上活动（只能待发布未删除的状态才能编辑）
	 * @param params
	 * @return
	 */
	int updateAgentSysTraposActivityInfo(@Param("map") Map<String, Object> params);


	/**
	 * 删除单个线上活动（传统POS）=====》只能删除未发布且未删除的活动
	 * @param activityMap
	 * @return
	 */
	int updateAgentSysTraposActivityInfoDel(@Param("map") Map<String, Object> activityMap);


	/**
	 * 查询是否存在同类型的正在进行中的未删除的活动信息
	 * @param userMap
	 * @return
	 */
	Map<String, Object> getAgentOnTraposActivitySameType(@Param("map") Map<String, Object> userMap);


	/**
	 * 更新线上活动（传统POS）活动状态
	 * @param activityMap
	 * @return
	 */
	int updateAgentSysTraposActivityInfoStatus(@Param("map") Map<String, Object> activityMap);


	/**
	 * 根据活动id查询活动详情
	 * @param activity_id
	 * @return
	 */
	Map<String, Object> getAgentSysTraposActivityInfoById(@Param("activity_id") String activity_id);

}

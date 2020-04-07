package com.ruoyi.project.deveagent.activitympos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.activitympos.domain.AgentUserMposActivityApplyInfo;



/**
 * 代理====》用户线上活动(MPOS)申请记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposActivityApplyInfoMapper {

	/**
	 * 查询审核通过的活动申请记录，如果有，则不能取消发布活动
	 * @param activity_id
	 * @return
	 */
	Integer getAgentUserMposActivityApplyNum(@Param("activity_id") String activity_id);

	
	/**
	 * 查询用户线上活动(MPOS)申请记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposActivityApplyInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户线上活动(MPOS)申请记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserMposActivityApplyInfo> selectAgentUserMposActivityApplyInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 查询用户线上活动(MPOS)申请记录详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getAgentUserMposActivityApplyInfoDetailById(@Param("apply_id") String apply_id);


	/**
	 * 更新审核状态
	 * @param activityApplyMap
	 * @return
	 */
	int updateAgentUserMposActivityApplyInfoStatus(@Param("map") Map<String, Object> activityApplyMap);


	/**
	 * 查询申请表对象信息
	 * @param string
	 * @return
	 */
	Map<String, Object> getAgentUserMposActivityApplyInfoMapById(@Param("apply_id") String string);

}

package com.ruoyi.project.deveagent.usermpos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserApplyCardrateMposRecord;



/**
 * 代理====》用户费率申请记录(MPOS)管理
 * @author Administrator
 *
 */
public interface AgentUserApplyCardrateMposRecordMapper {


	/**
	 * 查询用户费率申请记录(MPOS)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserApplyCardrateMposRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户费率申请记录(MPOS)列表
	 * @param params
	 * @return
	 */
	List<AgentUserApplyCardrateMposRecord> selectAgentUserApplyCardrateMposRecordList(@Param("map") Map<String, Object> params);


	/**
	 * 更新刷卡费率申请状态
	 * @param applyMap
	 * @return
	 */
	int updateAgentUserApplyCardrateMposRecordStatus(@Param("map") Map<String, Object> applyMap);


	/**
	 * 根据id查询用户费率申请记录详情
	 * @param string
	 * @return
	 */
	Map<String, Object> getAgentUserApplyCardrateMposRecordById(@Param("apply_id") String apply_id);

}

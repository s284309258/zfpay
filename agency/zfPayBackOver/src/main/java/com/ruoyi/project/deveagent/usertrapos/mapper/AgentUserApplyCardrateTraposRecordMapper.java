package com.ruoyi.project.deveagent.usertrapos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserApplyCardrateTraposRecord;


/**
 * 代理====》用户费率申请记录(传统POS)管理
 * @author Administrator
 *
 */
public interface AgentUserApplyCardrateTraposRecordMapper {


	/**
	 * 查询用户费率申请记录(传统POS)列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserApplyCardrateTraposRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户费率申请记录(传统POS)列表
	 * @param params
	 * @return
	 */
	List<AgentUserApplyCardrateTraposRecord> selectAgentUserApplyCardrateTraposRecordList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 更新刷卡费率申请状态
	 * @param applyMap
	 * @return
	 */
	int updateAgentUserApplyCardrateTraposRecordStatus(@Param("map") Map<String, Object> applyMap);


	/**
	 * 根据id查询用户费率申请记录详情
	 * @param string
	 * @return
	 */
	Map<String, Object> getAgentUserApplyCardrateTraposRecordById(@Param("apply_id") String apply_id);

}

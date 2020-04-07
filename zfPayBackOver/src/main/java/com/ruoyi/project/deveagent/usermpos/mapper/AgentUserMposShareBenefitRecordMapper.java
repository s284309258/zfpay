package com.ruoyi.project.deveagent.usermpos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposShareBenefitRecord;



/**
 * 代理====》用户Mpos分润记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposShareBenefitRecordMapper {


	/**
	 * 查询用户Mpos分润记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposShareBenefitRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户Mpos分润记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserMposShareBenefitRecord> selectAgentUserMposShareBenefitRecordList(@Param("map") Map<String, Object> params);

}

package com.ruoyi.project.deveagent.usertrapos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposShareBenefitRecord;



/**
 * 代理====》用户传统POS分润记录管理
 * @author Administrator
 *
 */
public interface AgentUserTraposShareBenefitRecordMapper {


	/**
	 * 查询用户传统POS分润记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposShareBenefitRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户传统POS分润记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserTraposShareBenefitRecord> selectAgentUserTraposShareBenefitRecordList(@Param("map") Map<String, Object> params);

}

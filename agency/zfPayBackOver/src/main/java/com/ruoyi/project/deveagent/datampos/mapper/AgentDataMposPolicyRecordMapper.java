package com.ruoyi.project.deveagent.datampos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.datampos.domain.AgentDataMposPolicyRecord;



/**
 * 代理======账号政策记录管理
 * @author Administrator
 *
 */
public interface AgentDataMposPolicyRecordMapper {


	/**
	 * 查询账号政策记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentDataMposPolicyRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出账号政策记录
	 * @param params
	 * @return
	 */
	List<AgentDataMposPolicyRecord> selectAgentDataMposPolicyRecordList(@Param("map") Map<String, Object> params);


}

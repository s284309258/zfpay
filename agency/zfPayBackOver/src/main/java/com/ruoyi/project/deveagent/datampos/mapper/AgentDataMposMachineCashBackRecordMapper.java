package com.ruoyi.project.deveagent.datampos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.datampos.domain.AgentDataMposMachineCashBackRecord;



/**
 * 代理======机具返现记录管理
 * @author Administrator
 *
 */
public interface AgentDataMposMachineCashBackRecordMapper {


	/**
	 * 查询机具返现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentDataMposMachineCashBackRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出机具返现记录列表
	 * @param params
	 * @return
	 */
	List<AgentDataMposMachineCashBackRecord> selectAgentDataMposMachineCashBackRecordList(@Param("map") Map<String, Object> params);


}

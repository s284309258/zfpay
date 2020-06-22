package com.ruoyi.project.deveagent.usertrapos.mapper;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposDeductRecord;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposAllotRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 代理====》用户MPOS未达标扣除记录管理
 * @author Administrator
 *
 */
public interface AgentUserEposAllotRecordMapper {


	/**
	 * 查询用户MPOS未达标扣除记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposAllotRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户MPOS未达标扣除记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserTraposAllotRecord> selectAgentUserTraposAllotRecordList(@Param("map") Map<String, Object> params);

}

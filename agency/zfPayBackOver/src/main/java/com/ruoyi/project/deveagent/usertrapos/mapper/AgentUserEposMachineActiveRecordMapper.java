package com.ruoyi.project.deveagent.usertrapos.mapper;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposMachineActiveRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 代理====》用户MPOS未达标扣除记录管理
 * @author Administrator
 *
 */
public interface AgentUserEposMachineActiveRecordMapper {


	/**
	 * 查询用户MPOS未达标扣除记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户MPOS未达标扣除记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserMposMachineActiveRecord> selectAgentUserTraposRecordList(@Param("map") Map<String, Object> params);

}

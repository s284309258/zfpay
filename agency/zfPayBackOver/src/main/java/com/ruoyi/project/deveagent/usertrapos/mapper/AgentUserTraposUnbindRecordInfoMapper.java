package com.ruoyi.project.deveagent.usertrapos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposUnbindRecordInfo;



/**
 * 代理====》用户传统POS解绑记录管理
 * @author Administrator
 *
 */
public interface AgentUserTraposUnbindRecordInfoMapper {


	/**
	 * 查询用户传统POS解绑记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposUnbindRecordInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户传统POS解绑记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserTraposUnbindRecordInfo> selectAgentUserTraposUnbindRecordInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 更新用户传统POS解绑状态
	 * @param unbindMap
	 * @return
	 */
	int updateAgentUserTraposUnbindRecordInfoStatus(@Param("map") Map<String, Object> unbindMap);


	/**
	 * 根据编号查询解绑详情
	 * @param string
	 * @return
	 */
	Map<String, Object> getAgentUserTraposUnbindRecordInfoById(@Param("unbind_id") String unbind_id);

}

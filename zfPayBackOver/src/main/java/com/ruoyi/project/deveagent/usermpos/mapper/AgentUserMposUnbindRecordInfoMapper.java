package com.ruoyi.project.deveagent.usermpos.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposUnbindRecordInfo;



/**
 * 代理====》用户MPOS解绑记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposUnbindRecordInfoMapper {


	/**
	 * 查询用户MPOS解绑记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposUnbindRecordInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 导出用户MPOS解绑记录列表
	 * @param params
	 * @return
	 */
	List<AgentUserMposUnbindRecordInfo> selectAgentUserMposUnbindRecordInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 更新用户MPOS解绑申请状态
	 * @param applyMap
	 * @return
	 */
	int updateAgentUserMposUnbindRecordInfoStatus(@Param("map") Map<String, Object> unbindMap);


	/**
	 * 根据编号查询
	 * @param string
	 * @return
	 */
	Map<String, Object> getAgentUserMposUnbindRecordInfoById(@Param("unbind_id") String unbind_id);

}

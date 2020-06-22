package com.ruoyi.project.deveagent.usermpos.mapper;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposUnbindRecordInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 代理====》用户MPOS解绑记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposUnbindMapper {


	/**
	 * 查询用户MPOS解绑记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposUnbindList(@Param("map") Map<String, Object> params);

	int removePosBind(String ids);

}

package com.ruoyi.project.deveagent.usertrapos.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 代理====》用户MPOS解绑记录管理
 * @author Administrator
 *
 */
public interface AgentUserTraposUnbindMapper {


	/**
	 * 查询用户MPOS解绑记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposUnbindList(@Param("map") Map<String, Object> params);

	int removePosBind(String ids);

}

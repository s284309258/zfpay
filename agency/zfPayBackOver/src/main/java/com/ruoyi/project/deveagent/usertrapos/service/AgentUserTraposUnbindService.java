package com.ruoyi.project.deveagent.usertrapos.service;

import com.ruoyi.project.develop.common.domain.R;

import java.util.List;
import java.util.Map;

/**
 * 代理======用户MPOS解绑记录管理
 * @author Administrator
 *
 */
public interface AgentUserTraposUnbindService {


	/**
	 * 查询用户MPOS解绑记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposUnbindList(Map<String, Object> params);

	/***
	 * 批量解绑操作
	 * @param params
	 * @return
	 */
	R removePosBind(Map<String, Object> params);

}
	
	

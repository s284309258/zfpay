package com.ruoyi.project.deveagent.usertrapos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraposUnbindRecordInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户传统POS解绑记录管理
 * @author Administrator
 *
 */
public interface AgentUserTraposUnbindRecordInfoService {

	
	/**
	 * 查询用户传统POS解绑记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserTraposUnbindRecordInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出用户传统POS解绑记录信息
	 * @param params
	 * @return
	 */
	List<AgentUserTraposUnbindRecordInfo> selectAgentUserTraposUnbindRecordInfoList(Map<String, Object> params);


	/**
	 * 系统批量审核解绑申请
	 * @param params
	 * @return
	 */
	R batchAgentSysAuditUserTraposUnbindRecordInfo(Map<String, Object> params);

}
	
	

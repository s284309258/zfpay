package com.ruoyi.project.deveagent.usermpos.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposUnbindRecordInfo;
import com.ruoyi.project.develop.common.domain.R;

/**
 * 代理======用户MPOS解绑记录管理
 * @author Administrator
 *
 */
public interface AgentUserMposUnbindRecordInfoService {

	
	/**
	 * 查询用户MPOS解绑记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserMposUnbindRecordInfoList(Map<String, Object> params);
	
	
	/**
	 * 导出用户MPOS解绑记录信息
	 * @param params
	 * @return
	 */
	List<AgentUserMposUnbindRecordInfo> selectAgentUserMposUnbindRecordInfoList(Map<String, Object> params);


	/**
	 * 系统批量审核用户MPOS解绑申请
	 * @param params
	 * @return
	 */
	R batchAgentSysAuditUserMposUnbindRecordInfo(Map<String, Object> params);

}
	
	

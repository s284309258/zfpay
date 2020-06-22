package com.ruoyi.project.deveagent.syspos.service;

import com.ruoyi.project.deveagent.syspos.domain.AgentSysTraditionalPosInfo;
import com.ruoyi.project.develop.common.domain.R;

import java.util.List;
import java.util.Map;

/**
 * 代理======系统传统POS信息管理
 * @author Administrator
 *
 */
public interface AgentSysEPOSInfoService {
    /**
     * 查询系统传统POS信息列表
     * @param params
     * @return
     */
    List<Map<String, Object>> getAgentSysTraditionalPosInfoList(Map<String, Object> params);


    /**
     * 导出系统传统POS信息
     * @param params
     * @return
     */
    List<AgentSysTraditionalPosInfo> selectAgentSysTraditionalPosInfoList(Map<String, Object> params);


    /**
     * 查询系统一级代理MPOS信息列表byqh
     * @param params
     * @return
     */
    List<Map<String, Object>> getSubAgentSysTraditionalPosInfoList(Map<String, Object> params);


    /**
     * 导入系统传统POS信息
     * @param agentSysTraditionalPosInfoList
     * @param account_id
     * @return
     */
    R importAgentSysTraditionalPosInfoList(List<AgentSysTraditionalPosInfo> agentSysTraditionalPosInfoList, String account_id);


    /**
     * 新增系统传统POS
     * @param params
     * @return
     */
    R addAgentSysTraditionalPosInfo(Map<String, Object> params);


    /**
     * 根据编号查询传统POS详情
     * @param id
     * @return
     */
    Map<String, Object> getAgentSysTraditionalPosInfoById(String id);


    /**
     * 修改保存系统传统POS信息
     * @param params
     * @return
     */
    R editAgentSysTraditionalPosInfo(Map<String, Object> params);


    /**
     * 批量删除系统传统POS====>只能删除未分配的传统POS
     * @param params
     * @return
     */
    R batchRemoveAgentSysTraditionalPosInfo(Map<String, Object> params);


    /**
     * 根据设备号（机器编号）查询详情
     * @param id
     * @return
     */
    Map<String, Object> getAgentSysTraditionalPosInfoDetailBySn(String id);
}

package com.ruoyi.project.deveagent.syspospolicy.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface SysPosPolicyMapper {

    List<Map<String,Object>> selectUserPOSBelongList(Map<String, Object> params);

    List<Map<String,Object>> selectAgentPosPolicyList(Map<String, Object> params);

    List<Map<String,Object>> selectSysPosPolicyList(Map<String, Object> params);

    int insertPolicyDefine(@Param("map") Map<String, Object> params);

    List<Map<String,Object>> selectSysPosPolicyInfo(Map<String, Object> params);

    int editPolicyInfo(Map<String,Object> params);

    int editPolicySNInfo1(Map<String,Object> params);

    int editPolicySNInfo2(Map<String,Object> params);

    int editPolicySNInfo3(Map<String,Object> params);

    int editPolicySNInfo4(Map<String,Object> params);

    int editPolicySNInfo5(Map<String,Object> params);

    int delPolicySNInfo(@Param("ids") String ids);

    @Update("delete from t_sys_pos_policy_info where id in(${ids})")
    @ResultType(Integer.class)
    int delPolicySNInfoByID(@Param("ids") String ids);

    int delPolicyInfo(@Param("ids") String ids);

    int insertPolicySNInfo1(Map<String,Object> params);

    int insertPolicySNInfo2(Map<String,Object> params);

    int insertPolicySNInfo3(Map<String,Object> params);

    int insertPolicySNInfo4(Map<String,Object> params);

    int insertPolicySNInfo5(Map<String,Object> params);

    @Update("delete from t_sys_pos_policy_info where sn=#{sn}")
    @ResultType(Integer.class)
    int delPolicySNInfoBySN(@Param("sn") String sn);

}

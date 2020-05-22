package com.ruoyi.project.deveagent.syspospolicy.service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.deveagent.syspospolicy.domain.SysPosPolicy;
import com.ruoyi.project.deveagent.syspospolicy.mapper.SysPosPolicyMapper;
import com.ruoyi.project.develop.common.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysPosPolicyServiceImpl {
    @Autowired
    public SysPosPolicyMapper sysPosPolicyMapper;

    public List<Map<String,Object>> selectSysPosPolicyList(Map<String, Object> params){
        params.put("manager_id", ShiroUtils.getUserId());
        return sysPosPolicyMapper.selectSysPosPolicyList(params);
    }

    public List<Map<String,Object>> selectAgentPosPolicyList(Map<String, Object> params){
        params.put("manager_id", ShiroUtils.getUserId());
        return sysPosPolicyMapper.selectAgentPosPolicyList(params);
    }


    public List<Map<String,Object>> selectUserPOSBelongList(Map<String, Object> params){
        params.put("manager_id", ShiroUtils.getUserId());
        return sysPosPolicyMapper.selectUserPOSBelongList(params);
    }


    public R addPolicyInfo(Map<String,Object> params) {
        params.put("manager_id", ShiroUtils.getUserId());//代理编号
        try{
            sysPosPolicyMapper.insertPolicyDefine(params);
        }catch (Exception e){
            return R.error("操作失败");
        }
        return R.ok("操作成功");
    }

    public void insertPolicySNInfo(String policyIds,String sns,String user_id,String pos_type){
        String[] ids = Convert.toStrArray(policyIds);
        for(String id : ids){
            Map<String,Object> map = new HashMap<>();
            map.put("id",id);
            List<Map<String,Object>> list = sysPosPolicyMapper.selectSysPosPolicyInfo(map);
            if(list.size()>0){
                Map<String,Object> mm = list.get(0);
                String[] ss = sns.split(";");
                if(ss.length>0){
                    for(String sn : ss){
                        if(sn.contains("#")){
                            user_id = sn.split("#")[0];
                            sn = sn.split("#")[1];
                        }

                        if("1".equals(String.valueOf(mm.get("policy_type")))){
                            HashMap<String,Object> hashMap = new HashMap<>();
                            hashMap.put("policy_name",mm.get("policy_name"));
                            hashMap.put("sn",sn);
                            hashMap.put("module1_pickup_date",mm.get("begin_date"));
                            hashMap.put("module1_end_date",mm.get("end_date"));
                            hashMap.put("module1_deduct",mm.get("policy_amount"));
                            hashMap.put("policy_id",mm.get("id"));
                            hashMap.put("user_id",user_id);
                            sysPosPolicyMapper.insertPolicySNInfo1(hashMap);
                        }else if("2".equals(String.valueOf(mm.get("policy_type")))){
                            HashMap<String,Object> hashMap = new HashMap<>();
                            hashMap.put("policy_name",mm.get("policy_name"));
                            hashMap.put("sn",sn);
                            hashMap.put("module2_reward",mm.get("policy_amount"));
                            hashMap.put("module2_quantity",mm.get("policy_quantity"));
                            hashMap.put("module2_active_end_day",mm.get("policy_end_day"));
                            hashMap.put("module2_active_begin_day",mm.get("policy_begin_day"));
                            hashMap.put("policy_id",mm.get("id"));
                            hashMap.put("user_id",user_id);
                            sysPosPolicyMapper.insertPolicySNInfo2(hashMap);
                        }else if("3".equals(String.valueOf(mm.get("policy_type")))){
                            HashMap<String,Object> hashMap = new HashMap<>();
                            hashMap.put("policy_name",mm.get("policy_name"));
                            hashMap.put("sn",sn);
                            hashMap.put("module3_reward",mm.get("policy_amount"));
                            hashMap.put("module3_quantity",mm.get("policy_quantity"));
                            hashMap.put("module3_active_end_day",mm.get("policy_end_day"));
                            hashMap.put("policy_id",mm.get("id"));
                            hashMap.put("user_id",user_id);
                            sysPosPolicyMapper.insertPolicySNInfo3(hashMap);
                        }else if("4".equals(String.valueOf(mm.get("policy_type")))){
                            HashMap<String,Object> hashMap = new HashMap<>();
                            hashMap.put("policy_name",mm.get("policy_name"));
                            hashMap.put("sn",sn);
                            hashMap.put("module4_deduct",mm.get("policy_amount"));
                            hashMap.put("module4_quantity",mm.get("policy_quantity"));
                            hashMap.put("module4_active_end_day",mm.get("policy_end_day"));
                            hashMap.put("policy_id",mm.get("id"));
                            hashMap.put("user_id",user_id);
                            sysPosPolicyMapper.insertPolicySNInfo4(hashMap);
                        }else if("5".equals(String.valueOf(mm.get("policy_type")))){
                            HashMap<String,Object> hashMap = new HashMap<>();
                            hashMap.put("policy_name",mm.get("policy_name"));
                            hashMap.put("sn",sn);
                            hashMap.put("module5_reward",mm.get("policy_amount"));
                            hashMap.put("module5_quantity",mm.get("policy_income"));
                            hashMap.put("policy_id",mm.get("id"));
                            hashMap.put("user_id",user_id);
                            sysPosPolicyMapper.insertPolicySNInfo5(hashMap);
                        }
                    }
                }
            }
        }
    }

    @Transactional
    public R removePolicyInfo(Map<String,Object> params){
        sysPosPolicyMapper.delPolicySNInfo(params.get("ids").toString());
        sysPosPolicyMapper.delPolicyInfo(params.get("ids").toString());
        return R.ok("操作成功");
    }

    @Transactional
    public R removePolicySNInfo(Map<String,Object> params){
        sysPosPolicyMapper.delPolicySNInfoByID(params.get("ids").toString());
        return R.ok("操作成功");
    }

    public Map<String,Object> selectSysPosPolicyInfo(Map<String,Object> params){
       List<Map<String,Object>> list = sysPosPolicyMapper.selectSysPosPolicyInfo(params);
       return list.get(0);
    }

    public R editPolicyInfo(Map<String,Object> params){
        params.put("upd_by",ShiroUtils.getLoginName());
        try{
            int cnt = 0;
            cnt = sysPosPolicyMapper.editPolicyInfo(params);
            if(cnt!=1){
                return R.error("操作失败");
            }
            String policy_type = params.get("policy_type").toString();
            if("1".equals(policy_type)){
                sysPosPolicyMapper.editPolicySNInfo1(params);
            }else if("2".equals(policy_type)){
                sysPosPolicyMapper.editPolicySNInfo2(params);
            }else if("3".equals(policy_type)){
                sysPosPolicyMapper.editPolicySNInfo3(params);
            }else if("4".equals(policy_type)){
                sysPosPolicyMapper.editPolicySNInfo4(params);
            }else if("5".equals(policy_type)){
                sysPosPolicyMapper.editPolicySNInfo5(params);
            }

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return R.error("操作异常");
        }
        return R.ok("操作成功");
    }
}

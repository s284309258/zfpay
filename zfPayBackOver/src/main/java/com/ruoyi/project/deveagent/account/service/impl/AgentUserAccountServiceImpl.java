package com.ruoyi.project.deveagent.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.SysTableNameConstant;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.json.NetJsonUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.account.domain.AgentUserAccount;
import com.ruoyi.project.deveagent.account.mapper.AgentUserAccountMapper;
import com.ruoyi.project.deveagent.account.service.AgentUserAccountService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.param.mapper.ManaSysEditMapper;


/**
 * 代理====》中付账号管理
 * @author Administrator
 *
 */
@Service
public class AgentUserAccountServiceImpl implements AgentUserAccountService {
	
	
	@Autowired
	private AgentUserAccountMapper agentUserAccountMapper;
	@Autowired
	private ManaSysEditMapper manaSysEditMapper;


	
	/**
	 * 查询中付账号列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserAccountList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserAccountMapper.getAgentUserAccountList(params);
	}
	
	
	/**
	 * 导出中付账号列表
	 */
	@Override
	public List<AgentUserAccount> selectAgentUserAccountList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserAccountMapper.selectAgentUserAccountList(params);
	}


	/**
	 * 根据id查询中付账号详情
	 */
	@Override
	public Map<String, Object> getAgentUserAccountById(String id) {
		return agentUserAccountMapper.getAgentUserAccountById(id);
	}


	/**
	 * 新增保存中付账号
	 */
	@Override
	public R addAgentUserAccount(Map<String, Object> params) {
		try {
			if(!ShiroUtils.getSysUser().isAuth()) {
				return R.error(Type.WARN, "身份信息未认证，不能操作");
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			//（1）交易时间判断
			String tran_time = TimeUtil.get_format4(params.get("tran_time").toString());//交易时间
			String cre_date = TimeUtil.getDayString();//创建日期
			String cre_time = TimeUtil.getTimeString();//创建时间
			String his_date = TimeUtil.getCountDay(cre_date,-60,TimeUtil.format1,TimeUtil.format1);
			System.out.println("交易时间====="+tran_time);
			System.out.println("当前时间====="+cre_date+cre_time);
			//如果交易时间大于等于当前时间
			if(tran_time.compareTo(cre_date+cre_time)>=0) {
				return R.error(Type.WARN, "交易时间不能大于当前时间");
			}
			//如果交易时间小于前60天
			if(tran_time.compareTo(his_date+cre_time)<0) {
				return R.error(Type.WARN, "交易时间最多跨60天");
			}
			//（2）保存账号信息
			params.put("manager_id", ShiroUtils.getUserId());
			params.put("tran_time", tran_time);
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			int i = agentUserAccountMapper.addAgentUserAccount(params);
			if(i != 1) {
				return R.error(Type.WARN, "中付账号新增失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "操作异常");
		}
	}


	/**
	 * 修改保存中付账号
	 */
	@Override
	@Transactional
	public R editAgentUserAccount(Map<String, Object> params) {
		try {
			if(!ShiroUtils.getSysUser().isAuth()) {
				return R.error(Type.WARN, "身份信息未认证，不能操作");
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			//（1）交易时间判断
			String tran_time = TimeUtil.get_format4(params.get("tran_time").toString());//交易时间
			String cre_date = TimeUtil.getDayString();//创建日期
			String cre_time = TimeUtil.getTimeString();//创建时间
			String his_date = TimeUtil.getCountDay(cre_date,-60,TimeUtil.format1,TimeUtil.format1);
			System.out.println("交易时间====="+tran_time);
			System.out.println("当前时间====="+cre_date+cre_time);
			//如果交易时间大于等于当前时间
			if(tran_time.compareTo(cre_date+cre_time)>=0) {
				return R.error(Type.WARN, "交易时间不能大于当前时间");
			}
			//如果交易时间小于前60天
			if(tran_time.compareTo(his_date+cre_time)<0) {
				return R.error(Type.WARN, "交易时间最多跨60天");
			}
			//（2）查询old参数信息
			Map<String, Object> oldValue = agentUserAccountMapper.getAgentUserAccountById(params.get("account_id").toString());
			//（3）更新参数信息
			params.put("manager_id", ShiroUtils.getUserId());
			params.put("tran_time", tran_time);
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = agentUserAccountMapper.updateAgentUserAccount(params);
			if(i != 1) {
				return R.error(Type.WARN, "中付账号更新失败");
			}
			//（4）查询new参数信息
			Map<String, Object> newValue = agentUserAccountMapper.getAgentUserAccountById(params.get("account_id").toString());
			//（5）记录修改记录
			params.put("table_name", SysTableNameConstant.sys_user_account);
			params.put("old_value", NetJsonUtils.mapToJson1(oldValue));
			params.put("new_value", NetJsonUtils.mapToJson1(newValue));
			i = manaSysEditMapper.addManaSysEdit(params);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "修改记录记录失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "中付账号更新异常");
		}
	}


	/**
	 * 批量删除中付账号
	 */
	@Override
	public R batchRemoveAgentUserAccount(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
			return R.error(Type.WARN, "操作备注不能为空");
		}
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] account_ids = Convert.toStrArray(StringUtil.getMapValue(params, "account_ids"));
        for(int i=0;i<account_ids.length;i++) {
        	Map<String, Object> accountMap = new HashMap<>();
        	accountMap.put("remark", params.get("remark"));
        	accountMap.put("account_id", account_ids[i]);
        	accountMap.put("manager_id", ShiroUtils.getUserId());
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).removeAgentUserAccount(accountMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "删除结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "删除结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 删除单个中付账号
	 * @param accountMap
	 * @return
	 */
	@Transactional
	public R removeAgentUserAccount(Map<String, Object> accountMap) {
		try {
			int i=0;
			//（1）查询old参数信息
			Map<String, Object> oldValue = agentUserAccountMapper.getAgentUserAccountById(accountMap.get("account_id").toString());
			//（2）删除活动信息
			i = agentUserAccountMapper.delAgentUserAccountById(accountMap);
			if(i != 1) {
				return R.error(Type.WARN, "编号"+accountMap.get("account_id").toString()+"：删除失败");
			}
			//（3）记录修改记录
			accountMap.put("update_by", ShiroUtils.getSysUser().getLoginName());
			accountMap.put("up_date", TimeUtil.getDayString());
			accountMap.put("up_time", TimeUtil.getTimeString());
			accountMap.put("table_name", SysTableNameConstant.sys_user_account);
			accountMap.put("old_value", NetJsonUtils.mapToJson1(oldValue));
			accountMap.put("new_value", "");
			i = manaSysEditMapper.addManaSysEdit(accountMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "编号"+accountMap.get("account_id").toString()+"：修改记录记录失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "编号"+accountMap.get("account_id").toString()+"：删除异常");
		}
	}


	/**
	 * 批量开启关闭中付账号
	 */
	@Override
	public R batchSysOpenAgentUserAccount(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
        	return R.error(Type.WARN, "请输入操作备注");
        }
        if(TypeStatusConstant.sys_user_account_is_start_0.equals(StringUtil.getMapValue(params, "is_start"))) {
        	//启用账号
        	return this.batchOpenAgentUserAccountOper(params);
        }else if(TypeStatusConstant.sys_user_account_is_start_1.equals(StringUtil.getMapValue(params, "is_start"))){
        	//停用账号
        	return this.batchNoOpenAgentUserAccountOper(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}

	
	/**
	 * 批量启用中付账号
	 * @param params
	 * @return
	 */
	private R batchOpenAgentUserAccountOper(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] account_ids = Convert.toStrArray(StringUtil.getMapValue(params, "account_ids"));
        for(int i=0;i<account_ids.length;i++) {
        	Map<String, Object> accountMap = new HashMap<>();
        	accountMap.put("remark", params.get("remark"));
        	accountMap.put("account_id", account_ids[i]);
        	accountMap.put("manager_id", ShiroUtils.getUserId());
        	//依次启用每一个账号
        	R result = SpringUtils.getAopProxy(this).openAgentUserAccountOper(accountMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "启用结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "启用结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 启用单个中付账号
	 * @param accountMap
	 * @return
	 */
	@Transactional
	public R openAgentUserAccountOper(Map<String, Object> accountMap) {
		try {
			int i=0;
			//（1）更新账号启用状态
			accountMap.put("old_is_start", TypeStatusConstant.sys_user_account_is_start_1);//旧状态：停用
			accountMap.put("new_is_start", TypeStatusConstant.sys_user_account_is_start_0);//新状态：启用
			accountMap.put("manager_id", ShiroUtils.getUserId());
			accountMap.put("up_time", TimeUtil.getTimeString());
			accountMap.put("up_date", TimeUtil.getDayString());
			i = agentUserAccountMapper.updateAgentUserAccountStart(accountMap);
			if(i != 1) {
				return R.error(Type.WARN, "编号"+accountMap.get("account_id").toString()+"：状态更新失败");
			}
			return R.ok("启用成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.WARN, "编号"+accountMap.get("account_id").toString()+"：启用异常");
		}
	}
	
	
	/**
	 * 批量关闭中付账号
	 * @param params
	 * @return
	 */
	private R batchNoOpenAgentUserAccountOper(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] account_ids = Convert.toStrArray(StringUtil.getMapValue(params, "account_ids"));
        for(int i=0;i<account_ids.length;i++) {
        	Map<String, Object> accountMap = new HashMap<>();
        	accountMap.put("remark", params.get("remark"));
        	accountMap.put("account_id", account_ids[i]);
        	accountMap.put("manager_id", ShiroUtils.getUserId());
        	//依次启用每一个账号
        	R result = SpringUtils.getAopProxy(this).noOpenAgentUserAccountOper(accountMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "关闭结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "关闭结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 关闭单个中付账号
	 * @param accountMap
	 * @return
	 */
	@Transactional
	public R noOpenAgentUserAccountOper(Map<String, Object> accountMap) {
		try {
			int i=0;
			//（1）更新账号启用状态
			accountMap.put("old_is_start", TypeStatusConstant.sys_user_account_is_start_0);//旧状态：启用
			accountMap.put("new_is_start", TypeStatusConstant.sys_user_account_is_start_1);//新状态：停用
			accountMap.put("manager_id", ShiroUtils.getUserId());
			accountMap.put("up_time", TimeUtil.getTimeString());
			accountMap.put("up_date", TimeUtil.getDayString());
			i = agentUserAccountMapper.updateAgentUserAccountStart(accountMap);
			if(i != 1) {
				return R.error(Type.WARN, "编号"+accountMap.get("account_id").toString()+"：状态更新失败");
			}
			return R.ok("启用成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.WARN, "编号"+accountMap.get("account_id").toString()+"：关闭异常");
		}
	}


	/**
	 * 查询代理有效的中付账号列表
	 */
	@Override
	public List<AgentUserAccount> getAgentValidUserAccountList() {
		Map<String, Object> params = new HashMap<>();
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserAccountMapper.getAgentValidUserAccountList(params);
	}
	
}

package com.ruoyi.project.deveagent.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.SysParamConstant;
import com.ruoyi.common.constant.SysTableNameConstant;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.encryption.MD5Utils;
import com.ruoyi.common.utils.json.NetJsonUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.RegexUtil;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.deveagent.user.domain.AgentUserInfo;
import com.ruoyi.project.deveagent.user.mapper.AgentUserInfoMapper;
import com.ruoyi.project.deveagent.user.service.AgentUserInfoService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.param.mapper.ManaSysEditMapper;


/**
 * 管理员====》用户信息管理
 * @author Administrator
 *
 */
@Service
public class AgentUserInfoServiceImpl implements AgentUserInfoService {
	
	@Autowired
	private RedisUtils redisUtils;
	
	
	@Autowired
	private AgentUserInfoMapper agentUserInfoMapper;
	@Autowired
	private ManaSysEditMapper manaSysEditMapper;
	
	
	/**
	 * 查询用户列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserInfoMapper.getAgentUserInfoList(params);
	}
	
	
	/**
	 * 统计用户信息
	 */
	@Override
	public Map<String, Object> summaryAgentUserInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserInfoMapper.summaryAgentUserInfoList(params);
	}

	
	/**
	 * 导出用户列表
	 */
	@Override
	public List<AgentUserInfo> selectAgentUserInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserInfoMapper.selectAgentUserInfoList(params);
	}


	/**
	 * 批量系统冻结解冻用户账户
	 */
	@Override
	public R batchSysFreezeAgentUser(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
        	return R.error(Type.WARN, "请输入操作备注");
        }
        if(TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(params, "status"))) {
        	//解冻账号
        	return this.batchNoFreezeAgentUser(params);
        }else if(TypeStatusConstant.user_info_status_1.equals(StringUtil.getMapValue(params, "status"))){
        	//冻结账号
        	return this.batchFreezeAgentUser(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}


	/**
	 * 批量冻结账号
	 * @param params
	 * @return
	 */
	private R batchFreezeAgentUser(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] user_ids = Convert.toStrArray(StringUtil.getMapValue(params, "user_ids"));
        for(int i=0;i<user_ids.length;i++) {
        	Map<String, Object> userMap = new HashMap<>();
        	userMap.put("remark", params.get("remark"));
        	userMap.put("user_id", user_ids[i]);
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).freezeAgentUser(userMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "冻结结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "冻结结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 单个账号冻结
	 * @param userMap
	 * @return
	 */
	@Transactional
	public R freezeAgentUser(Map<String, Object> userMap) {
		try {
			int i=0;
			//（1）更新用户状态
			userMap.put("old_status", TypeStatusConstant.user_info_status_0);
			userMap.put("new_status", TypeStatusConstant.user_info_status_1);
			userMap.put("manager_id", ShiroUtils.getUserId());
			userMap.put("cre_date", TimeUtil.getDayString());
			userMap.put("cre_time", TimeUtil.getTimeString());
			i = agentUserInfoMapper.updateAgentUserStatus(userMap);
			if(i != 1) {
				return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：状态更新失败");
			}
			//（2）记录冻结账号
			i = agentUserInfoMapper.addFrozeAgentUser(userMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：冻结记录记录失败");
			}
			
			//（3）缓存清理
			redisUtils.remove(RedisNameConstants.zfpay_user_freeze+userMap.get("user_id").toString());
			redisUtils.remove(RedisNameConstants.zfpay_user_info_id+userMap.get("user_id").toString());
			
			return R.ok("冻结成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：冻结异常");
		}
	}


	/**
	 * 批量解冻账号
	 * @param params
	 * @return
	 */
	private R batchNoFreezeAgentUser(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] user_ids = Convert.toStrArray(StringUtil.getMapValue(params, "user_ids"));
        for(int i=0;i<user_ids.length;i++) {
        	Map<String, Object> userMap = new HashMap<>();
        	userMap.put("remark", params.get("remark"));
        	userMap.put("user_id", user_ids[i]);
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).noFreezeAgentUser(userMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "解冻结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "解冻结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 单个账号解冻
	 * @param userMap
	 * @return
	 */
	@Transactional
	public R noFreezeAgentUser(Map<String, Object> userMap) {
		try {
			int i=0;
			//（1）更新用户状态
			userMap.put("old_status", TypeStatusConstant.user_info_status_1);
			userMap.put("new_status", TypeStatusConstant.user_info_status_0);
			userMap.put("manager_id", ShiroUtils.getUserId());
			userMap.put("cre_time", TimeUtil.getTimeString());
			userMap.put("cre_date", TimeUtil.getDayString());
			i = agentUserInfoMapper.updateAgentUserStatus(userMap);
			if(i != 1) {
				return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：状态更新失败");
			}
			//（2）删除冻结账号
			i = agentUserInfoMapper.deleteFrozeAgentUser(userMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：冻结记录删除失败");
			}
			//（3）缓存清理
			redisUtils.remove(RedisNameConstants.zfpay_user_freeze+userMap.get("user_id").toString());
			redisUtils.remove(RedisNameConstants.zfpay_user_info_id+userMap.get("user_id").toString());
			
			return R.ok("解冻成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：解冻异常");
		}
	}
	
	
	/**
	 * 批量实名认证审核用户账号
	 */
	@Override
	public R batchSysAuthAuditAgentUser(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
        	return R.error(Type.WARN, "请输入操作备注");
        }
        if(TypeStatusConstant.user_info_auth_status_08.equals(StringUtil.getMapValue(params, "auth_status")) || TypeStatusConstant.user_info_auth_status_09.equals(StringUtil.getMapValue(params, "auth_status"))) {
        	return this.batchSysAuthAuditAgentUserOper(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}


	/**
	 * 批量实名认证操作
	 * @param params
	 * @return
	 */
	private R batchSysAuthAuditAgentUserOper(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] user_ids = Convert.toStrArray(StringUtil.getMapValue(params, "user_ids"));
        for(int i=0;i<user_ids.length;i++) {
        	Map<String, Object> userMap = new HashMap<>();
        	userMap.put("remark", params.get("remark"));
        	userMap.put("user_id", user_ids[i]);
        	userMap.put("new_auth_status", params.get("auth_status").toString());
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).sysAuthAuditAgentUser(userMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "审核结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "审核结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 单个实名认证审核
	 * @param userMap
	 * @return
	 */
	@Transactional
	public R sysAuthAuditAgentUser(Map<String, Object> userMap) {
		try {
			int i=0;
			//（1）更新用户实名认证状态
			userMap.put("old_auth_status", TypeStatusConstant.user_info_auth_status_04);
			userMap.put("manager_id", ShiroUtils.getUserId());
			i = agentUserInfoMapper.updateAgentUserAuthStatus(userMap);
			if(i != 1) {
				return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：认证状态更新失败");
			}
			//（2）缓存清理
			redisUtils.remove(RedisNameConstants.zfpay_user_freeze+userMap.get("user_id").toString());
			redisUtils.remove(RedisNameConstants.zfpay_user_info_id+userMap.get("user_id").toString());
			
			return R.ok("审核成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "用户编号"+userMap.get("user_id").toString()+"：审核异常");
		}
	}


	/**
	 * 根据用户id查询用户详情
	 */
	@Override
	public AgentUserInfo getAgentUserInfoById(String id) {
		return agentUserInfoMapper.getAgentUserInfoById(id);
	}


	/**
	 * 修改保存用户信息
	 */
	@Override
	@Transactional
	public R editAgentUserInfo(Map<String, Object> params) {
		try {
			if(!ShiroUtils.getSysUser().isAuth()) {
				return R.error(Type.WARN, "身份信息未认证，不能操作");
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			//校验手机格式
			if(!RegexUtil.isValidTel(StringUtil.getMapValue(params, "user_tel"))) {
				return R.error(Type.WARN, "手机号码格式有误");
			}
			//用户账号信息校验
			Map<String, Object> userAccountMap = agentUserInfoMapper.checkSysAgentUserTel(params);
			if(userAccountMap!=null) {
				return R.error(Type.WARN, "该手机号已存在");
			}
			//（1）查询old参数信息
			Map<String, Object> oldValue = agentUserInfoMapper.getAgentUserMapById(params.get("user_id").toString());
			//处理登录密码信息
			if(!StringUtils.isEmpty(StringUtil.getMapValue(params, "login_password"))) {
				if(!RegexUtil.isValidPass(params.get("login_password").toString())) {
					return R.error(Type.WARN, "密码格式不对");
				}
				params.put("login_password", new Md5Hash(oldValue.get("cre_date").toString()
						+oldValue.get("cre_time").toString(), 
						MD5Utils.MD5Encode(params.get("login_password").toString()).toUpperCase(), 
						SysParamConstant.passNum).toString());
			}
			//处理支付密码信息
			if(!StringUtils.isEmpty(StringUtil.getMapValue(params, "pay_password"))) {
				params.put("pay_password", new Md5Hash(oldValue.get("cre_date").toString()
						+oldValue.get("cre_time").toString(), 
						MD5Utils.MD5Encode(params.get("pay_password").toString()).toUpperCase(), 
						SysParamConstant.passNum).toString());
			}
			//（2）更新用户信息
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			params.put("manager_id", ShiroUtils.getUserId());
			int i = agentUserInfoMapper.updateAgentUserInfo(params);
			if(i != 1) {
				return R.error(Type.WARN, "用户信息更新失败");
			}
			//（3）查询new参数信息
			Map<String, Object> newValue = agentUserInfoMapper.getAgentUserMapById(params.get("user_id").toString());
			//（4）记录修改记录
			params.put("table_name", SysTableNameConstant.t_user_info);
			params.put("old_value", NetJsonUtils.mapToJson1(oldValue));
			params.put("new_value", NetJsonUtils.mapToJson1(newValue));
			i = manaSysEditMapper.addManaSysEdit(params);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "修改记录记录失败");
			}
			//（4）清理缓存
			redisUtils.remove(RedisNameConstants.zfpay_user_info_id+params.get("user_id").toString());
			
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "用户信息更新异常");
		}
	}


	/**
	 * 查询父级团队成员列表
	 */
	@Override
	public List<Map<String, Object>> getParentAgentUserInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserInfoMapper.getParentAgentUserInfoList(params);
	}


	/**
	 * 导出父级成员信息列表
	 */
	@Override
	public List<AgentUserInfo> selectParentAgentUserInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserInfoMapper.selectParentAgentUserInfoList(params);
	}


	/**
	 * 查询子级团队成员列表
	 */
	@Override
	public List<Map<String, Object>> getChildrenAgentUserList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserInfoMapper.getChildrenAgentUserList(params);
	}


	/**
	 * 导出子级成员信息列表
	 */
	@Override
	public List<AgentUserInfo> selectChildrenAgentUserInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserInfoMapper.selectChildrenAgentUserInfoList(params);
	}


	/**
	 * 批量删除用户信息====>只能删除未实名的并且下面没有人的
	 */
	@Override
	public R batchRemoveAgentUserInfo(Map<String, Object> params) {
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
        String[] user_ids = Convert.toStrArray(StringUtil.getMapValue(params, "user_ids"));
        for(int i=0;i<user_ids.length;i++) {
        	Map<String, Object> userMap = new HashMap<>();
        	userMap.put("user_id", user_ids[i]);
        	userMap.put("remark", params.get("remark").toString());
        	R result = SpringUtils.getAopProxy(this).removeAgentUserInfo(userMap);
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
	 * 删除单个用户信息
	 * @param sysMposMap
	 * @return
	 */
	@Transactional
	public R removeAgentUserInfo(Map<String, Object> userMap) {
		try {
			//（1）根据用户编号查询用户详情
			Map<String, Object> oldValue =	agentUserInfoMapper.getAgentUserMapById(userMap.get("user_id").toString());
			//（2）直推人数大于0，说明有推荐他人，不能删除
			if(Integer.parseInt(oldValue.get("referer_num").toString())>0) {
				return R.error(Type.WARN, "编号"+userMap.get("user_id").toString()+"：推荐了人，不能删除");
			}
			//（3）实名认证了，不能删除
			if(TypeStatusConstant.user_info_auth_status_09.equals(oldValue.get("auth_status").toString())) {
				return R.error(Type.WARN, "编号"+userMap.get("user_id").toString()+"：已实名认证，不能删除");
			}
			String up_date = TimeUtil.getDayString();
			String up_time = TimeUtil.getTimeString();
			//（4）处理更新父级链信息
			if(!StringUtils.isEmpty(StringUtil.getMapValue(oldValue, "parent_chain"))) {
				int parentLength = oldValue.get("parent_chain").toString().split(",").length;
				//父级链长度大于0，说明存在父级
				if(parentLength>0) {
					userMap.put("up_date", up_date);//更新日期
					userMap.put("up_time", up_time);//更新时间
					//（1）更新父级链的伞下人数，伞下人数减1
					int updateLength = agentUserInfoMapper.updateAgentParentUnderNum(oldValue);
					if(updateLength != parentLength) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return R.error(Type.WARN, "编号"+userMap.get("user_id").toString()+"：父级链伞下人数更新数量不对");
					}
					//（2）更新父级的直推人数，直推人数减1
					int m = agentUserInfoMapper.updateAgentUserReferNum(oldValue);
					if(m != 1) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return R.error(Type.WARN, "编号"+userMap.get("user_id").toString()+"：父级直推人数更新失败");
					}
				}
			}
			int i=0;
			//（5）删除用户
			userMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			i = agentUserInfoMapper.deleteAgentUserInfo(userMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "编号"+userMap.get("user_id").toString()+"：用户删除失败");
			}
			//（6）记录修改记录
			userMap.put("table_name", SysTableNameConstant.t_user_info);
			userMap.put("old_value", NetJsonUtils.mapToJson1(oldValue));
			userMap.put("new_value", null);
			userMap.put("up_date", up_date);//更新日期
			userMap.put("up_time", up_time);//更新时间
			userMap.put("update_by", ShiroUtils.getLoginName());//创建人
			i = manaSysEditMapper.addManaSysEdit(userMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "编号"+userMap.get("user_id").toString()+"：修改记录记录失败");
			}
			//（7）删除缓存
			redisUtils.remove(RedisNameConstants.zfpay_user_info_id+userMap.get("user_id").toString());
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "编号"+userMap.get("user_id").toString()+"：操作异常");
		} 
	}
	
}

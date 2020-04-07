package com.example.longecological.service.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import com.example.longecological.constant.*;
import com.example.longecological.service.common.SysParamService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.UserInfoCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.common.VerifyRecordService;
import com.example.longecological.service.common.VerifyTokenService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.service.user.UserLoginService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.ip.IpUtils;
import com.example.longecological.utils.string.ParamValidUtil;
import com.example.longecological.utils.string.RegexUtil;
import com.example.longecological.utils.string.StringUtil;
import com.example.longecological.utils.time.TimeUtil;


/**
 * 用户登录注册相关
 * @author Administrator
 *
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginServiceImpl.class);
	
	@Autowired
	RedisUtils redisUtils;
	
	
	@Autowired
	VerifyTokenService verifyTokenService;
	@Autowired
	VerifyRecordService verifyRecordService;
	@Autowired
	UserInfoCacheService userInfoCacheService;
	
	@Autowired
	UserInfoMapper userInfoMapper;

	@Autowired
	SysParamService sysParamService;

	
	/**
	 * 用户登录
	 */
	@Override
	public R userLogin(Map<String, Object> map) {
		//解密成功与否验证
		if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
			return (R) map.get("result");
		}
		//登陆操作
		return this.userLoginOper((Map<String, Object>)map.get("data"));
	}

	@Override
	public R updateUserDeviceToken(Map<String,Object> map){
		userInfoMapper.updateUserDeviceToken(map);
		return R.ok(CommonCodeConstant.COMMON_CODE_999999);
	}


	/**
	 * 用户登录操作
	 * @param dataMap
	 * @return
	 */
	private R userLoginOper(Map<String, Object> dataMap) {
		try {
			//（1）校验登录类型
			R checkParamResult = ParamValidUtil.checkSpecifyParam(dataMap, "login_type", OperParamConstant.USER_LOGIN_TYPE);
			if(!Boolean.valueOf(checkParamResult.get(R.SUCCESS_TAG).toString())) {
				return checkParamResult;
			}
			//用户信息对象
			Map<String, Object> userMap = new HashMap<>();
			//账号登录
			if(OperParamConstant.USER_LOGIN_TYPE_ACCOUNT.equals(dataMap.get("login_type").toString())) {
				//（1）账号信息校验
				userMap = userInfoMapper.getUserInfoByUserAccount(StringUtil.getMapValue(dataMap, "sys_user_account"));
				if(userMap==null) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999798,UserInfoCodeConstant.USER_INFO_MSG_999798);
				}
				//（2）判断密码信息
				String login_password = new Md5Hash(StringUtil.getMapValue(userMap, "cre_date")+StringUtil.getMapValue(userMap, "cre_time"), StringUtil.getMapValue(dataMap, "login_password"), SysParamConstant.passNum).toString();
				if(!login_password.equals(StringUtil.getMapValue(userMap, "login_password"))) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999798,UserInfoCodeConstant.USER_INFO_MSG_999798);
				}
				//(3)账号是否冻结
				if(!TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(userMap, "status"))) {
					return R.error(CommonCodeConstant.COMMON_CODE_999988,CommonCodeConstant.COMMON_MSG_999988);
				}
				//（4）生成新的token
				userMap.put("token", verifyTokenService.setToken(userMap.get("id").toString()));
				//（5）更新数据
				dataMap.put("sys_user_id", userMap.get("id"));
				dataMap.put("last_login_date", TimeUtil.getDayString());
				dataMap.put("last_login_time", TimeUtil.getTimeString());
				dataMap.put("last_login_ip", IpUtils.getIpAddress());
				int i = userInfoMapper.updateUserLoginInfo(dataMap);
				if(i != 1) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999797,UserInfoCodeConstant.USER_INFO_MSG_999797);
				}
			}else {
				//token登录
				//（1）校验token
				R checkTokenResult = verifyTokenService.isToken(dataMap.get("token").toString());
				if(!Boolean.valueOf(checkTokenResult.get(R.SUCCESS_TAG).toString())) {
					return checkTokenResult;
				}
				//（2）校验账户信息（ID是否存在，账户是否冻结）
				userMap = userInfoMapper.getUserInfoById(dataMap.get("token").toString().split("\\|")[0]);
				if (userMap==null) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999798,UserInfoCodeConstant.USER_INFO_MSG_999798);
				}
				//(3)账号是否冻结
				if(!TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(userMap, "status"))) {
					return R.error(CommonCodeConstant.COMMON_CODE_999988,CommonCodeConstant.COMMON_MSG_999988);
				}
				userMap.put("token", dataMap.get("token").toString());
			}
			//校验通过，更新数据和返回结果
			//根据代理编号查询代理信息
			Map<String, Object> agentMap = userInfoMapper.getAgentUserById(userMap.get("manager_id").toString());
			//（1）返回数据
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("sys_time", new Date().getTime());//系统时间戳
			respondMap.put("user_id", StringUtil.getMapValue(userMap, "id"));//用户id
			respondMap.put("real_name", StringUtil.getMapValue(userMap, "real_name"));//用户姓名
			respondMap.put("auth_status", StringUtil.getMapValue(userMap, "auth_status"));//认证状态
			respondMap.put("qiniu_domain", SysParamConstant.qiniu_domain);//七牛域名
			respondMap.put("qr_code_url", SysParamConstant.web_register_link+userMap.get("user_tel").toString());//WEB端注册链接
			respondMap.put("pay_password", StringUtils.isEmpty(StringUtil.getMapValue(userMap, "pay_password"))?"":"99999999999999");//支付密码数据返回处理，不返回正确密码
			respondMap.put("user_tel",StringUtil.getMapValue(userMap, "user_tel"));//手机号
			respondMap.put("head_photo",StringUtil.getMapValue(userMap, "head_photo"));//头像
			respondMap.put("token",StringUtil.getMapValue(userMap, "token"));//token
//			respondMap.put("algebra",StringUtil.getMapValue(userMap, "algebra"));//代数
			respondMap.put("algebra","1");//代数,针对手机端N级代理都可以查看自己未达标的考核任务做的修改update byqh
			respondMap.put("app_id", StringUtil.getMapValue(agentMap, "app_id"));//appid
			respondMap.put("open_distribution", sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_openThreeTierDistribution));//三级分销开放参数
			respondMap.put("open_threetier_distribution",sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_openThreeTierDistribution));
            respondMap.put("appstore_test_distribution", sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_openThreeTierDistribution));//三级分销开放参数
//            respondMap.put("appstore_online_distribution",sysParamService.getParamByCode(SysParamCodeConstants.sys_param_code_openThreeTierDistribution));
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999, respondMap);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- userLoginOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}

	
	/**
	 * 用户注册
	 */
	@Override
	@Transactional
	public R userRegister(Map<String, Object> map) {
		//解密成功与否验证
		if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
			return (R) map.get("result");
		}
		Map<String, Object> dataMap = (Map<String, Object>)map.get("data");
		//注册信息校验
		R checkRegisterResult = this.checkUserRegister(dataMap);
		if(!Boolean.valueOf(checkRegisterResult.get(R.SUCCESS_TAG).toString())) {
			return checkRegisterResult;
		}
		//注册操作
		return this.userRegisterOper(dataMap);
	}


	/**
	 * 用户注册操作
	 * @param dataMap
	 * @return
	 */
	private R userRegisterOper(Map<String, Object> dataMap) {
		int i=0;
		try {
			if(!StringUtils.isEmpty(StringUtil.getMapValue(dataMap, "referer_id"))) {
				//（1）更新所有父级的伞下人数
				i = userInfoMapper.updateParentUnderNum(dataMap);
				if(i < 1){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999787,UserInfoCodeConstant.USER_INFO_MSG_999787);
				}
				//（2）更新父级直推人数
				i = userInfoMapper.updateUserReferNum(dataMap);
				if(i != 1){
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999786,UserInfoCodeConstant.USER_INFO_MSG_999786);
				}
			}
			//（3）保存用户信息
			dataMap.put("id",null);//可用来记录用户返回主键位置
			i = userInfoMapper.saveUserInfo(dataMap);
			if(i != 1){
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999786,UserInfoCodeConstant.USER_INFO_MSG_999786);
			}
			
			//用户信息返回
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("token", verifyTokenService.setToken(dataMap.get("id").toString()));
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999,respondMap);
			
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- userRegisterOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	/**
	 * 校验用户注册信息
	 * @param dataMap
	 * @return
	 */
	private R checkUserRegister(Map<String, Object> dataMap) {
		try {
			//（1）手机号格式校验
			if(!RegexUtil.isValidTel(StringUtil.getMapValue(dataMap, "sys_user_account"))) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999778,UserInfoCodeConstant.USER_INFO_MSG_999778);
			}
			//（2）账号是否已存在
			Map<String, Object> userMap = userInfoMapper.getUserInfoByUserAccount(dataMap.get("sys_user_account").toString());
			if(userMap!=null) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999777,UserInfoCodeConstant.USER_INFO_MSG_999777);
			}
			//（3）邀请码校验（邀请码规则：手机号为普通用户注册，非代理人，否则为代理）
			//如果邀请码为11位，则为普通用户注册，去前台用户表查询用户信息：需要记录推荐人和所对应的代理商ID
			if(RegexUtil.isValidTel(dataMap.get("invite_code").toString())) {
				//（4）邀请人信息是否存在
				Map<String, Object> referUserInfo = userInfoMapper.getUserInfoByUserAccount(dataMap.get("invite_code").toString());
				if (referUserInfo == null) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999790,UserInfoCodeConstant.USER_INFO_MSG_999790);
				} 
				//(5)邀请人是否被冻结
				if(!TypeStatusConstant.user_info_status_0.equals(referUserInfo.get("status").toString())) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999789,UserInfoCodeConstant.USER_INFO_MSG_999789);
				}
				dataMap.put("manager_id", referUserInfo.get("manager_id").toString());//代理编号
				dataMap.put("referer_id", referUserInfo.get("id").toString());//存的是前台用户的ID
				dataMap.put("algebra", Integer.parseInt(referUserInfo.get("algebra").toString())+1);//代数
				if(!StringUtil.isEmpty(StringUtil.getMapValue(referUserInfo, "parent_chain"))) {
					dataMap.put("parent_chain", referUserInfo.get("parent_chain").toString()+","+referUserInfo.get("id").toString());//父级链
				}else {
					dataMap.put("parent_chain", referUserInfo.get("id").toString());//父级链
				}
			}else {
				//否则为代理注册，去后台用户表查询供应商信息：需要记录代理商ID，无推荐人
				Map<String, Object> agentUserMap = userInfoMapper.getAgentUserByLoginName(dataMap.get("invite_code").toString());
				if (agentUserMap == null) {
					return R.error(UserInfoCodeConstant.USER_INFO_CODE_999765,UserInfoCodeConstant.USER_INFO_MSG_999765);
				} 
				dataMap.put("manager_id", agentUserMap.get("manager_id").toString());//代理编号
				dataMap.put("referer_id", null);//推荐人
				dataMap.put("algebra", 1);//代数
				dataMap.put("parent_chain", null);//父级链
			}
			
			//==================校验短信验证码===============
			R checkSmsResult = verifyRecordService.compare(null, null, VerifyConstant.BusType_FrontRegister, VerifyConstant.MobileAccType, dataMap.get("sys_user_account").toString(), dataMap.get("sms_code").toString(), VerifyConstant.SystemFront);
			if(!Boolean.valueOf(checkSmsResult.get(R.SUCCESS_TAG).toString())) {
				return checkSmsResult;
			}
				
			String cre_date = TimeUtil.getDayString();
			String cre_time = TimeUtil.getTimeString();
			dataMap.put("cre_date", cre_date);//注册日期
			dataMap.put("cre_time", cre_time);//注册时间
			dataMap.put("login_password", new Md5Hash(cre_date+cre_time, dataMap.get("login_password").toString(), SysParamConstant.passNum).toString());//登录密码
			dataMap.put("pay_password", new Md5Hash(cre_date+cre_time, dataMap.get("pay_password").toString(), SysParamConstant.passNum).toString());//支付密码
			dataMap.put("head_photo", SysParamConstant.user_head_photo);//用户头像
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- checkUserRegister方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(UserInfoCodeConstant.USER_INFO_CODE_999788,UserInfoCodeConstant.USER_INFO_MSG_999788);
		}
	}


	/**
	 * 用户退出登录
	 */
	@Override
	public R userLogOut(Map<String, Object> map) {
		//解密成功与否验证
		if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
			return (R) map.get("result");
		}
		//退出登陆操作
		return this.userLogOutOper(map);
	}


	/**
	 * 用户退出登录操作
	 * @param map
	 * @return
	 */
	private R userLogOutOper(Map<String, Object> map) {
		try {
			R checkTokenResult = verifyTokenService.isToken(map.get("token").toString());
			if(Boolean.valueOf(checkTokenResult.get(R.SUCCESS_TAG).toString())) {
				verifyTokenService.deleteToken(map.get("token").toString());
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- userLogOutOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	/**
	 * 用户忘记密码
	 */
	@Override
	@Transactional
	public R userForgetPass(Map<String, Object> map) {
		//解密成功与否验证
		if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
			return (R) map.get("result");
		}
		return this.userForgetPassOper((Map<String, Object>)map.get("data"));
	}


	/**
	 * 用户忘记密码操作
	 * @param dataMap
	 * @return
	 */
	private R userForgetPassOper(Map<String, Object> dataMap) {
		try {
			//（1）账号类型校验
			dataMap.put("bus_type", VerifyConstant.BusType_FrontForgetPass);
			Map<String, Object> userMap = userInfoMapper.getUserInfoByUserAccount(dataMap.get("sys_user_account").toString());
			if(userMap==null) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999799,UserInfoCodeConstant.USER_INFO_MSG_999799);
			}
			//（2）校验短信验证码
			R checkSmsResult = verifyRecordService.compare(userMap.get("id").toString(), userMap.get("user_tel").toString(), VerifyConstant.BusType_FrontForgetPass, VerifyConstant.MobileAccType, dataMap.get("sys_user_account").toString(), dataMap.get("sms_code").toString(), VerifyConstant.SystemFront);
			if(!Boolean.valueOf(checkSmsResult.get(R.SUCCESS_TAG).toString())) {
				return checkSmsResult;
			}
			//（3）重置登录密码
			dataMap.put("sys_user_id", userMap.get("id"));
			dataMap.put("login_password", new Md5Hash(userMap.get("cre_date").toString()+userMap.get("cre_time").toString(), dataMap.get("login_password").toString(), SysParamConstant.passNum).toString());//密码
			//（4）修改登录密码
			int i = userInfoMapper.updateUserLoginPass(dataMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999784,UserInfoCodeConstant.USER_INFO_MSG_999784);
			}
			//（5）删除缓存
			redisUtils.remove(RedisNameConstants.zfpay_user_info_id+dataMap.get("sys_user_id").toString());
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- userForgetPassOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}
	
	
}

package com.example.longecological.service.user.impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.VerifyConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.MsgImgCodeConstant;
import com.example.longecological.constant.msgcode.UserInfoCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.common.SmsCodeService;
import com.example.longecological.service.common.VerifyRecordService;
import com.example.longecological.service.common.VerifyTokenService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.service.user.UserInfoService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.RegexUtil;
import com.example.longecological.utils.string.StringUtil;


/**
 * 用户信息相关
 * @author Administrator
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	@Autowired
	RedisUtils redisUtils;
	
	@Autowired
	SmsCodeService smsCodeService;
	@Autowired
	VerifyTokenService verifyTokenService;
	@Autowired
	VerifyRecordService verifyRecordService;
	@Autowired
	UserInfoCacheService userInfoCacheService;
	@Autowired
	UserInfoMapper userInfoMapper;

	
	/**
	 * 修改用户交易密码
	 */
	@Override
	@Transactional
	public R modifyPayPass(Map<String, Object> map) {
		//解密成功与否验证
		if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
			return (R) map.get("result");
		}
		return this.modifyPayPassOper(map);
	}


	/**
	 * 修改用户交易密码操作
	 * @param map
	 * @return
	 */
	private R modifyPayPassOper(Map<String, Object> map) {
		try {
			Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
			//（2）校验短信验证码
			dataMap.put("bus_type", VerifyConstant.BusType_FrontModifyPayPass);
			R checkSmsResult = smsCodeService.checkSmsCode(dataMap);
			if(!Boolean.valueOf(checkSmsResult.get(R.SUCCESS_TAG).toString())) {
				return checkSmsResult;
			}
			//（3）重置交易密码
			dataMap.put("pay_password", new Md5Hash(dataMap.get("cre_date").toString()+dataMap.get("cre_time").toString(), dataMap.get("pay_password").toString(), SysParamConstant.passNum).toString());//密码
			int i = userInfoMapper.updateUserPayPass(dataMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999783,UserInfoCodeConstant.USER_INFO_MSG_999783);
			}
			//（4）删除缓存
			redisUtils.remove(RedisNameConstants.zfpay_user_info_id+dataMap.get("sys_user_id").toString());
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- modifyPayPassOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	
	/**
	 * 修改登录密码
	 */
	@Override
	@Transactional
	public R modifyLoginPass(Map<String, Object> map) {
		//解密成功与否验证
		if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
			return (R) map.get("result");
		}
		return this.modifyLoginPassOper(map);
	}


	/**
	 * 修改登录密码操作
	 * @param map
	 * @return
	 */
	private R modifyLoginPassOper(Map<String, Object> map) {
		try {
			Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
			//（2）校验短信验证码
			dataMap.put("bus_type", VerifyConstant.BusType_FrontModifyLoginPass);
			R checkSmsResult = smsCodeService.checkSmsCode(dataMap);
			if(!Boolean.valueOf(checkSmsResult.get(R.SUCCESS_TAG).toString())) {
				return checkSmsResult;
			}
			//（3）重置交易密码
			dataMap.put("login_password", new Md5Hash(dataMap.get("cre_date").toString()+dataMap.get("cre_time").toString(), dataMap.get("login_password").toString(), SysParamConstant.passNum).toString());//密码
			int i = userInfoMapper.updateUserLoginPass(dataMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999784,UserInfoCodeConstant.USER_INFO_MSG_999784);
			}
			//（4）删除缓存
			redisUtils.remove(RedisNameConstants.zfpay_user_info_id+dataMap.get("sys_user_id").toString());
			
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- modifyLoginPassOper方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	/**
	 * 修改用户资料（头像）
	 */
	@Override
	public R modifyUserInfo(Map<String, Object> map) {
		//验签
		if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
			return (R) map.get("result");
		}
		return this.modifyUserInfoOper(map);
	}


	/**
	 * 修改用户资料操作
	 * @param map
	 * @return
	 */
	private R modifyUserInfoOper(Map<String, Object> dataMap) {
		//（1）更新用户资料
		int i = userInfoMapper.updateUserInfo(dataMap);
		if(i != 1) {
			return R.error(UserInfoCodeConstant.USER_INFO_CODE_999793, UserInfoCodeConstant.USER_INFO_MSG_999793);
		}
		//（2）删除缓存
		redisUtils.remove(RedisNameConstants.zfpay_user_info_id+dataMap.get("sys_user_id").toString());
		
		return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
	}


	/**
	 * 修改手机号第一步：校验旧手机号
	 */
	@Override
	@Transactional
	public R modifyTelFirst(Map<String, Object> map) {
		//解密成功与否验证
		if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
			return (R) map.get("result");
		}
		return this.modifyTelFirstOper(map);
	}


	/**
	 * 修改手机号第一步操作
	 * @param map
	 * @return
	 */
	private R modifyTelFirstOper(Map<String, Object> map) {
		try {
			Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
			//（1）校验短信验证码
			dataMap.put("bus_type", VerifyConstant.BusType_FrontModifyTelFirst);
			R checkSmsResult = smsCodeService.checkSmsCode(dataMap);
			if(!Boolean.valueOf(checkSmsResult.get(R.SUCCESS_TAG).toString())) {
				return checkSmsResult;
			}
			//（2）返回校验通过的唯一标识
			String valid_flag = StringUtil.getDateTimeAndRandomForID();//随机数ID（字母数字混合）
			String redis_valid_flag_key = RedisNameConstants.zfpay_modify_tel_valid_flag+dataMap.get("sys_user_id").toString();//修改手机号校验通过的唯一标识
			redisUtils.set(redis_valid_flag_key, valid_flag, SysParamConstant.verification_code_seconds);//存入redis
			
			 //（3）返回验证通过的信息
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("valid_flag", valid_flag);
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- modifyTelFirstOper方法处理异常：" +  ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	/**
	 * 修改手机号第一步：设置新的手机号
	 */
	@Override
	@Transactional
	public R modifyTelSecond(Map<String, Object> map) {
		//解密成功与否验证
		if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
			return (R) map.get("result");
		}
		return this.modifyTelSecondOper(map);
	}


	/**
	 * 设置新手机号操作
	 * @param map
	 * @return
	 */
	private R modifyTelSecondOper(Map<String, Object> map) {
		try {
			int i=0;
			Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
			//（1）新的手机号信息校验
			Map<String, Object> userMap = userInfoMapper.getUserInfoByUserAccount(dataMap.get("sys_user_account").toString());
			if(userMap!=null) {
				if(dataMap.get("sys_user_id").toString().equals(userMap.get("id").toString())) {
					return R.error(MsgImgCodeConstant.MESSAGE_CODE_999869,MsgImgCodeConstant.MESSAGE_MSG_999869);
				}else {
					return R.error(MsgImgCodeConstant.MESSAGE_CODE_999870,MsgImgCodeConstant.MESSAGE_MSG_999870);
				}
			}
			//（2）校验短信验证码
			R checkSmsResult = verifyRecordService.compare(dataMap.get("sys_user_id").toString(),dataMap.get("sys_user_account").toString(), VerifyConstant.BusType_FrontModifyTelSecond, VerifyConstant.MobileAccType, dataMap.get("sys_user_account").toString(), dataMap.get("sms_code").toString(), VerifyConstant.SystemFront);
			if(!Boolean.valueOf(checkSmsResult.get(R.SUCCESS_TAG).toString())) {
				return checkSmsResult;
			}
			//（3）返回的标识信息校验
			//从redis中拿出校验通过的标识
			String redis_valid_flag_key = RedisNameConstants.zfpay_modify_tel_valid_flag+dataMap.get("sys_user_id").toString();
			Object valid_flag = redisUtils.get(redis_valid_flag_key);
			if(valid_flag == null){
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999776,UserInfoCodeConstant.USER_INFO_MSG_999776);
			}
			if(!valid_flag.toString().toUpperCase().equals(StringUtil.getMapValue(dataMap, "valid_flag").toUpperCase())){
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999775,UserInfoCodeConstant.USER_INFO_MSG_999775);
			}
			//（4）修改用户手机号
			i = userInfoMapper.updateUserTel(dataMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999764, UserInfoCodeConstant.USER_INFO_MSG_999764);
			}
			//（5）删除缓存
			redisUtils.remove(redis_valid_flag_key);//校验通过，删除
			redisUtils.remove(RedisNameConstants.zfpay_user_info_id+dataMap.get("sys_user_id").toString());
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- modifyTelSecondOper方法处理异常：" +  ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997,CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	/**
	 * 查询用户实名认证状态信息 update byqh 201912
	 */
	@Override
	public R getUserAuthStatus(Map<String, Object> map) {
		try {
			//（1）验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//查询结果返回处理
			Map<String, Object> respondMap=new HashMap<>();
			Map<String, Object> userAuthStatus = userInfoMapper.getUserAuthStatus(map.get("sys_user_id").toString());

			String tradeAmountDay = userInfoMapper.selectTransAmountByDay(map.get("sys_user_id").toString());
			String tradeAmountAll = userInfoMapper.selectTransAmountByAll(map.get("sys_user_id").toString());
			userAuthStatus.put("tradeAmountDay",tradeAmountDay);
			userAuthStatus.put("tradeAmountAll",tradeAmountAll);
			respondMap.put("userAuthStatus", userAuthStatus);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983,respondMap);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- getUserAuthStatus方法处理异常：" +  ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);

		}
	}


	/**
	 * 用户提交实名认证资料信息
	 */
	@Override
	@Transactional
	public R submitUserAuthInfo(Map<String, Object> map) {
		try {
			//（1）验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			//（2）身份证号信息校验
			if(!RegexUtil.isValidIdCard(map.get("id_card").toString())) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999771,UserInfoCodeConstant.USER_INFO_MSG_999771);
			}
			//（3）证件照信息校验
			if(map.get("card_photo").toString().split(",").length!=3) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999774,UserInfoCodeConstant.USER_INFO_MSG_999774);
			}
			//（4）查询用户实名认证是否需要审核（与代理有关）
			Map<String, Object> agentMap = userInfoMapper.getUserAgentInfo(map.get("sys_user_id").toString());
			if(agentMap==null) {
				return R.error(UserInfoCodeConstant.USER_INFO_CODE_999770,UserInfoCodeConstant.USER_INFO_MSG_999770);
			}
			//（3）更新用户实名认证状态
			map.put("old_auth_status", "("+TypeStatusConstant.user_info_auth_status_00+","+TypeStatusConstant.user_info_auth_status_08+")");
			//不需要审核
			if(TypeStatusConstant.user_auth_status_auth_isaudit_0.equals(agentMap.get("auth_isaudit").toString())) {
				map.put("new_auth_status", TypeStatusConstant.user_info_auth_status_09);//认证成功
			}else {
				map.put("new_auth_status", TypeStatusConstant.user_info_auth_status_04);//待审核
			}
			int i = userInfoMapper.updateUserAuthStatus(map);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(CommonCodeConstant.COMMON_CODE_999998, CommonCodeConstant.COMMON_MSG_999998);
			}
			//（4）删除缓存
			redisUtils.remove(RedisNameConstants.zfpay_user_info_id+map.get("sys_user_id").toString());
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999);
		} catch (Exception e) {
			LOGGER.error("UserInfoServiceImpl -- submitUserAuthInfo方法处理异常：" +  ExceptionUtil.getExceptionAllinformation(e));
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	/**
	 * 查询用户最新信息
	 */
	@Override
	public R getUserNewInfo(Map<String, Object> map) {
		try {
			//（1）验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> userNewInfo = userInfoMapper.getUserPurseById(map.get("sys_user_id").toString());
			//（3）返回信息
			Map<String, Object> respondMap = new HashMap<>();
			respondMap.put("userNewInfo", userNewInfo);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}

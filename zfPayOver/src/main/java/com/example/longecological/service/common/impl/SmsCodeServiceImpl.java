package com.example.longecological.service.common.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.MsgTemplateConstants;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.VerifyConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.MsgImgCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.common.ImgCodeService;
import com.example.longecological.service.common.SmsCodeService;
import com.example.longecological.service.common.VerifyRecordService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.StringUtil;


/**
 * 发送和比较短信验证码
 * @author Administrator
 *
 */
@Service
public class SmsCodeServiceImpl implements SmsCodeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmsCodeServiceImpl.class);
	
	@Autowired
	ImgCodeService imgCodeService;
	@Autowired
	VerifyRecordService verifyRecordService;
	
	@Autowired
	UserInfoCacheService userInfoCacheService;

	@Autowired
	UserInfoMapper userInfoMapper;
	
	
	@Autowired
	RedisUtils redisUtils;
	

	/**
	 * 短信验证码发送=====》整体仅仅rsa加密（适用于注册和忘记密码）
	 */
	@Override
	public R sendSmsCodeOnly(Map<String, Object> map) {
		//解密成功与否验证
		if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
			return (R) map.get("result");
		}
		Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
		//基本参数信息校验
		R checkParamResult = this.checkSendSmsCodeOnlyParam(dataMap);
		if(!Boolean.valueOf(checkParamResult.get(R.SUCCESS_TAG).toString())) {
			return checkParamResult;
		}
		//发送短信验证码
		if(StringUtils.isEmpty(StringUtil.getMapValue(dataMap, "sys_user_id"))) {
			return verifyRecordService.transmit(null,null,dataMap.get("bus_type").toString(), 
					VerifyConstant.MobileAccType, dataMap.get("sys_user_account").toString(), VerifyConstant.SystemFront,MsgTemplateConstants.COMMON_TIP);
		}else {
			return verifyRecordService.transmit(dataMap.get("sys_user_id").toString(),dataMap.get("sys_user_account").toString(),dataMap.get("bus_type").toString(), 
					VerifyConstant.MobileAccType, dataMap.get("sys_user_account").toString(), VerifyConstant.SystemFront,MsgTemplateConstants.COMMON_TIP);
		}
	}


	/**
	 * 校验短信验证码发送的参数====》适用于注册和忘记密码
	 * @param map
	 * @return
	 */
	private R checkSendSmsCodeOnlyParam(Map<String, Object> dataMap) {
		if(VerifyConstant.BusType_FrontRegister.equals(dataMap.get("bus_type").toString())) {
			Map<String, Object> userMap = userInfoMapper.getUserInfoByUserAccount(dataMap.get("sys_user_account").toString());
			if(userMap != null){
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999870,MsgImgCodeConstant.MESSAGE_MSG_999870);
			}
		}else if(VerifyConstant.BusType_FrontForgetPass.equals(dataMap.get("bus_type").toString())){
			//校验手机号信息
			Map<String, Object> userMap = userInfoMapper.getUserInfoByUserAccount(dataMap.get("sys_user_account").toString());
			//忘记密码类型：账号必须存在
			if(userMap==null) {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999875,MsgImgCodeConstant.MESSAGE_MSG_999875);
			}
			//账号存在，但已被冻结
			if(!TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(userMap, "status"))){
				return R.error(CommonCodeConstant.COMMON_CODE_999988,CommonCodeConstant.COMMON_MSG_999988);
			}
			dataMap.put("sys_user_id", userMap.get("id"));
			dataMap.put("sys_user_account", userMap.get("user_tel"));
		}else {
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999872,MsgImgCodeConstant.MESSAGE_MSG_999872);
		}
		//校验图形验证码
		R checkResult = imgCodeService.checkImgCode(dataMap);
		if(!Boolean.valueOf(checkResult.get(R.SUCCESS_TAG).toString())) {
			return checkResult;
		}
		return R.ok(CommonCodeConstant.COMMON_CODE_999999);
	}


	/**
	 * 短信验证码发送=====》整体rsa加密+token验证（适用于修改登录密码和交易密码和修改手机号等等）
	 */
	@Override
	public R sendSmsCodeToken(Map<String, Object> map) {
		//解密成功与否验证
		if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
			return (R) map.get("result");
		}
		Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
		//基本参数信息校验
		R checkParamResult = this.checkSendSmsCodeTokenParam(dataMap);
		if(!Boolean.valueOf(checkParamResult.get(R.SUCCESS_TAG).toString())) {
			return checkParamResult;
		}
		return verifyRecordService.transmit(dataMap.get("sys_user_id").toString(),dataMap.get("sys_user_account").toString(),dataMap.get("bus_type").toString(), 
				VerifyConstant.MobileAccType, dataMap.get("sys_user_account").toString(), VerifyConstant.SystemFront,MsgTemplateConstants.COMMON_TIP);
	}


	/**
	 * 短信验证码发送基本参数校验
	 * @param dataMap
	 * @return
	 */
	private R checkSendSmsCodeTokenParam(Map<String, Object> dataMap) {
		try {
			//（1）业务类型校验
			if(StringUtils.isEmpty(StringUtil.getMapValue(dataMap, "bus_type"))) {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999874,MsgImgCodeConstant.MESSAGE_MSG_999874);
			}
			//（2）校验图形验证码
			R checkResult = imgCodeService.checkImgCode(dataMap);
			if(!Boolean.valueOf(checkResult.get(R.SUCCESS_TAG).toString())) {
				return checkResult;
			}
			
			//（3）业务类型为设置新手机号类型的，手机号必须不存在
			if(VerifyConstant.BusType_FrontModifyTelSecond.equals(dataMap.get("bus_type").toString())){
				Map<String, Object> userMap = userInfoMapper.getUserInfoByUserAccount(dataMap.get("sys_user_account").toString());
				if(userMap!=null) {
					if(dataMap.get("sys_user_id").toString().equals(userMap.get("id").toString())) {
						return R.error(MsgImgCodeConstant.MESSAGE_CODE_999869,MsgImgCodeConstant.MESSAGE_MSG_999869);
					}else {
						return R.error(MsgImgCodeConstant.MESSAGE_CODE_999870,MsgImgCodeConstant.MESSAGE_MSG_999870);
					}
				}
			}else {
				//（4）根据用户id查询详情
				Map<String, Object> userMap = userInfoCacheService.getUserInfoCacheById(dataMap.get("sys_user_id").toString());
				if(userMap==null) {
					return R.error(MsgImgCodeConstant.MESSAGE_CODE_999871,MsgImgCodeConstant.MESSAGE_MSG_999871);
				}
				//账号存在，但已被冻结
				if(!TypeStatusConstant.user_info_status_0.equals(StringUtil.getMapValue(userMap, "status"))){
					return R.error(CommonCodeConstant.COMMON_CODE_999988,CommonCodeConstant.COMMON_MSG_999988);
				}
				dataMap.put("sys_user_account", userMap.get("user_tel").toString());
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999);
		} catch (Exception e) {
			LOGGER.error("SmsCodeServiceImpl -- checkSendSmsCodeTokenParam方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999997, CommonCodeConstant.COMMON_MSG_999997);
		}
	}


	/**
	 * 校验短信验证码（验证码接收的必须是当前登录绑定账号）
	 */
	@Override
	public R checkSmsCode(Map<String, Object> dataMap) {
		//（1）查询用户信息
		Map<String, Object> userMap = userInfoCacheService.getUserInfoCacheById(dataMap.get("sys_user_id").toString());
		if(userMap==null) {
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999871,MsgImgCodeConstant.MESSAGE_MSG_999871);
		}
		dataMap.put("cre_date", userMap.get("cre_date"));
		dataMap.put("cre_time", userMap.get("cre_time"));
		//（2）校验短信验证码
		return verifyRecordService.compare(userMap.get("id").toString(),userMap.get("user_tel").toString(),
				dataMap.get("bus_type").toString(), VerifyConstant.MobileAccType, userMap.get("user_tel").toString(),
				dataMap.get("sms_code").toString(), VerifyConstant.SystemFront);
	}
	

	/**
	 * 校验短信验证码和支付密码
	 */
	@Override
	public R checkSmsCodePayPass(Map<String, Object> dataMap) {
		//（1）查询用户信息
		Map<String, Object> userMap = userInfoCacheService.getUserInfoCacheById(dataMap.get("sys_user_id").toString());
		if(userMap==null) {
			return R.error(MsgImgCodeConstant.MESSAGE_CODE_999871,MsgImgCodeConstant.MESSAGE_MSG_999871);
		}
		//（2）校验用户交易密码
		if(StringUtils.isEmpty(StringUtil.getMapValue(userMap, "pay_password"))) {
			return R.error(CommonCodeConstant.COMMON_CODE_999986,CommonCodeConstant.COMMON_MSG_999986);
		}
		String pay_password = new Md5Hash(userMap.get("cre_date").toString()+userMap.get("cre_time").toString(), dataMap.get("pay_password").toString(), SysParamConstant.passNum).toString();
		if(!pay_password.equals(userMap.get("pay_password").toString())) {
			return R.error(CommonCodeConstant.COMMON_CODE_999985,CommonCodeConstant.COMMON_MSG_999985);
		}
		//（3）校验短信验证码
		return verifyRecordService.compare(userMap.get("id").toString(), userMap.get("user_tel").toString(), dataMap.get("bus_type").toString(), VerifyConstant.MobileAccType, userMap.get("user_tel").toString(), dataMap.get("sms_code").toString(), VerifyConstant.SystemFront);
	}
}

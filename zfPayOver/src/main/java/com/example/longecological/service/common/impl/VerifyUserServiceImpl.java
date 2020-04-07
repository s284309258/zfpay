package com.example.longecological.service.common.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.TypeStatusConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.constant.msgcode.MsgImgCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.common.VerifyUserService;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.string.StringUtil;

@Service
public class VerifyUserServiceImpl implements VerifyUserService {
	
	private static final  Logger LOGGER=LoggerFactory.getLogger(VerifyUserServiceImpl.class);
	
	@Autowired
	UserInfoMapper userInfoMapper;
	@Autowired
	UserInfoCacheService userInfoCacheService;
	
	
	/**
	 * 校验用户支付密码
	 */
	@Override
	public R checUserPayPass(Map<String, Object> dataMap) {
		try {
			Map<String, Object> userMap = userInfoCacheService.getUserInfoCacheById(dataMap.get("sys_user_id").toString());
			if(userMap==null) {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999871,MsgImgCodeConstant.MESSAGE_MSG_999871);
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(userMap, "pay_password"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999986,CommonCodeConstant.COMMON_MSG_999986);
			}
			String pay_password = new Md5Hash(userMap.get("cre_date").toString()+userMap.get("cre_time").toString(), dataMap.get("pay_password").toString(), SysParamConstant.passNum).toString();//密码
			if(!pay_password.equals(userMap.get("pay_password").toString())) {
				return R.error(CommonCodeConstant.COMMON_CODE_999985,CommonCodeConstant.COMMON_MSG_999985);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999);
		} catch (Exception e) {
			LOGGER.error("VerifyUserServiceImpl -- checUserPayPass方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999984,CommonCodeConstant.COMMON_MSG_999984);
		}
	}


	/**
	 * 校验用户实名认证和交易密码
	 */
	@Override
	public R checkAuthAndPayPass(Map<String, Object> dataMap) {
		try {
			//（1）查询用户基本信息
			Map<String, Object> userMap = userInfoCacheService.getUserInfoCacheById(dataMap.get("sys_user_id").toString());
			if(userMap==null) {
				return R.error(MsgImgCodeConstant.MESSAGE_CODE_999871,MsgImgCodeConstant.MESSAGE_MSG_999871);
			}
			//（2）实名认证信息校验
			if(!TypeStatusConstant.user_info_auth_status_09.equals(userMap.get("auth_status").toString())) {
				return R.error(CommonCodeConstant.COMMON_CODE_999980,CommonCodeConstant.COMMON_MSG_999980);
			}
			//（3）支付密码校验
			if(StringUtils.isEmpty(StringUtil.getMapValue(userMap, "pay_password"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999986,CommonCodeConstant.COMMON_MSG_999986);
			}
			String pay_password = new Md5Hash(userMap.get("cre_date").toString()+userMap.get("cre_time").toString(), dataMap.get("pay_password").toString(), SysParamConstant.passNum).toString();//密码
			if(!pay_password.equals(userMap.get("pay_password").toString())) {
				return R.error(CommonCodeConstant.COMMON_CODE_999985,CommonCodeConstant.COMMON_MSG_999985);
			}
			return R.ok(CommonCodeConstant.COMMON_CODE_999999,CommonCodeConstant.COMMON_MSG_999999,userMap);
		} catch (Exception e) {
			LOGGER.error("VerifyUserServiceImpl -- checkAuthAndPayPass方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999984,CommonCodeConstant.COMMON_MSG_999984);
		}
	}
	
}

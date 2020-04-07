package com.example.longecological.service.common.impl;

import com.example.longecological.mapper.user.UserInfoMapper;
import org.apache.shiro.crypto.hash.Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.common.VerifyTokenService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.string.StringUtil;

import java.util.HashMap;
import java.util.Map;

@Service
public class VerifyTokenServiceImpl implements VerifyTokenService {
	
	private static final  Logger LOGGER=LoggerFactory.getLogger(VerifyTokenServiceImpl.class);
	
	@Autowired
	public RedisUtils redisUtils;

	@Autowired
	private UserInfoMapper userInfoMapper;

	
	/**
	 * 校验token值
	 * @param token
	 * @param ajaxJson
	 * @return
	 */
	@Override
	public R isToken(String token) {
		try{
			LOGGER.info("token值======="+token);
			String userId = token.split("\\|")[0];
			String key = RedisNameConstants.zfpay_user_token + userId;
			Object user_token = redisUtils.get(key);
			if(user_token == null || !token.equals(user_token.toString())){
				return R.error(CommonCodeConstant.COMMON_CODE_999990,CommonCodeConstant.COMMON_MSG_999990);
			}
			redisUtils.set(key, token, SysParamConstant.past_seconds);
			return R.ok(CommonCodeConstant.COMMON_CODE_999999);
		}catch(Exception e){
			LOGGER.error("VerifyTokenServiceImpl -- isToken方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999989,CommonCodeConstant.COMMON_MSG_999989);
		}
	}

	
	/**
	 * 设置token
	 */
	@Override
	public String setToken(String userId) {
		//获取key值
		String key = RedisNameConstants.zfpay_user_token + userId;
		//删除失效的token值
		redisUtils.remove(key);
		//生成Token
		String token = StringUtil.getTokenRandom(userId);
		//保存Token
		redisUtils.set(key, token, SysParamConstant.past_seconds);
		return token;
	}

	
	/**
	 * 删除token值
	 */
	@Override
	public void deleteToken(String token) {
		String userId = token.split("\\|")[0];
		String key = RedisNameConstants.zfpay_user_token + userId;
		redisUtils.remove(key);
		Map<String,Object> map = new HashMap<>();
		map.put("user_id",userId);
		userInfoMapper.updateUserDeviceToken(map);
	}

}

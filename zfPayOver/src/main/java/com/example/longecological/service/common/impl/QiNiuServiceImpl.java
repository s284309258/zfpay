package com.example.longecological.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.common.QiNiuService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.qiniu.util.Auth;

@Service
public class QiNiuServiceImpl implements QiNiuService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QiNiuServiceImpl.class);
	
	@Autowired
	private RedisUtils redisUtils;

	
	/**
	 * 获取七牛云token值
	 */
	@Override
	public R getQiNiuToken(Map<String, Object> map) {
		 try{
			//解密成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> param = new HashMap<>();
			map.put("token", "token");
			param.put("qiniu_token", createToken(map));
			param.put("qiniu_domain", SysParamConstant.qiniu_domain);
			return R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999, param);
		 }catch(Exception e){
			 LOGGER.error("QiNiuServiceImpl -- getQiNiuToken方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			 return R.error(CommonCodeConstant.COMMON_CODE_999991, CommonCodeConstant.COMMON_MSG_999991);
		 }
	}
	
	
	/**
	 * 创建七牛云token值
	 * @param map
	 * @return
	 */
	private String createToken(Map<String, Object> map){
		if(redisUtils.exists(RedisNameConstants.zfpay_qiniu_token_jjst)){
			return redisUtils.get(RedisNameConstants.zfpay_qiniu_token_jjst).toString();
		}
		System.out.println("生成秘钥");
		//生成秘钥
		Auth auth = Auth.create(SysParamConstant.qiniu_accessKey, SysParamConstant.qiniu_secretKey);
		String token = auth.uploadToken(SysParamConstant.qiniu_bucket);
		redisUtils.set(RedisNameConstants.zfpay_qiniu_token_jjst, token, SysParamConstant.verification_code_seconds);
		return token;
	}

	
}

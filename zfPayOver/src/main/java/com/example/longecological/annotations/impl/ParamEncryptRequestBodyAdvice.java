package com.example.longecological.annotations.impl;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.alibaba.fastjson.JSONObject;
import com.example.longecological.annotations.RSAOnly;
import com.example.longecological.annotations.RSAOnlyWeb;
import com.example.longecological.annotations.RSAToken;
import com.example.longecological.annotations.SIGNOnly;
import com.example.longecological.annotations.SIGNOnlyWeb;
import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.config.redis.RedisUtils;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.SysSecurityKeyConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.user.UserInfoCacheService;
import com.example.longecological.utils.encryption.md5.SignUtil;
import com.example.longecological.utils.encryption.rsa.Base64;
import com.example.longecological.utils.encryption.rsa.RSAUtilApp;
import com.example.longecological.utils.string.StringUtil;

/** 
 * 解密数据 
 * 
 * @author huan.fu 
 * @date 2018/9/28 - 16:09 
 */  
@RestControllerAdvice  
public class ParamEncryptRequestBodyAdvice implements RequestBodyAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParamEncryptRequestBodyAdvice.class);
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private UserInfoCacheService userInfoCacheService;

	@Override
	public boolean supports(MethodParameter methodParameter, java.lang.reflect.Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return methodParameter.hasParameterAnnotation(RSAToken.class) 
			|| methodParameter.hasParameterAnnotation(RSAOnly.class)
			|| methodParameter.hasParameterAnnotation(RSAOnlyWeb.class)
			|| methodParameter.hasParameterAnnotation(SIGNToken.class)
			|| methodParameter.hasParameterAnnotation(SIGNOnly.class)
			|| methodParameter.hasParameterAnnotation(SIGNOnlyWeb.class);
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter,
			java.lang.reflect.Type targetType, Class<? extends HttpMessageConverter<?>> converterType)
			throws IOException {
		return inputMessage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			java.lang.reflect.Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		LOGGER.info("此处进行解密+验签+身份验证");
		Map<String, Object> data = (Map<String, Object>) body; 
		try{
			if(parameter.hasParameterAnnotation(RSAToken.class)){
				/*参数通过RSA解密*/
//				byte[] hexStringToBytes = RSAUtilApp.hexStringToBytes(StringUtil.getMapValue(data, "data"));
				byte[] hexStringToBytes = Base64.decode(StringUtil.getMapValue(data, "data"));
				byte[] decryptByPrivateKey = RSAUtilApp.decryptByPrivateKey(hexStringToBytes,SysSecurityKeyConstant.privateKey_app);
				LOGGER.info("解密："+ new String(decryptByPrivateKey, "utf-8"));
				JSONObject param = (JSONObject) JSONObject.parse(new String(decryptByPrivateKey, "utf-8"));
				/*获取用户ID*/
				String user_id = param.getString("token").split("\\|")[0];
				
				/*if(!("9".equals(user_id)) && !("6206".equals(user_id)) && !("23259".equals(user_id)) && !("25471".equals(user_id))) {
					data.put("result", R.error(UserInfoCodeConstant.USER_LOGIN_CODE_999799,UserInfoCodeConstant.USER_LOGIN_MSG_999799));
					return data;
				}*/
				
				/*用户冻结校验*/
				Map<String, Object> freezeUser = userInfoCacheService.getUserFreezeCacheById(user_id);
				if(freezeUser != null){
					data.put("result", R.error(CommonCodeConstant.COMMON_MSG_999988,CommonCodeConstant.COMMON_MSG_999988));
					return data;
				}
				/*用户TOKEN校验*/
				String user_token_key = RedisNameConstants.zfpay_user_token + user_id;
				Object user = redisUtils.get(user_token_key);
				if(user == null || !param.getString("token").equals(user.toString())){
					data.put("result", R.error(CommonCodeConstant.COMMON_CODE_999990,CommonCodeConstant.COMMON_MSG_999990));
					return data;
				}
				/*校验通过*/
				param.put("sys_user_id", user_id);
				data.put("data", param);
				data.put("result", R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999));
			}else if(parameter.hasParameterAnnotation(RSAOnly.class)){
				/*参数通过RSA解密*/
//				byte[] hexStringToBytes = RSAUtilApp.hexStringToBytes(StringUtil.getMapValue(data, "data"));
				byte[] hexStringToBytes = Base64.decode(StringUtil.getMapValue(data, "data"));
				byte[] decryptByPrivateKey = RSAUtilApp.decryptByPrivateKey(hexStringToBytes,SysSecurityKeyConstant.privateKey_app);
				LOGGER.info("解密："+ new String(decryptByPrivateKey, "utf-8"));
				JSONObject param = (JSONObject) JSONObject.parse(new String(decryptByPrivateKey, "utf-8"));
				data.put("data", param);
				/*校验通过*/
				data.put("result", R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999));
			}else if(parameter.hasParameterAnnotation(RSAOnlyWeb.class)){
				/*参数通过RSA解密*/
//				byte[] hexStringToBytes = RSAUtilApp.hexStringToBytes(StringUtil.getMapValue(data, "data"));
				byte[] hexStringToBytes = Base64.decode(StringUtil.getMapValue(data, "data"));
				byte[] decryptByPrivateKey = RSAUtilApp.decryptByPrivateKey(hexStringToBytes,SysSecurityKeyConstant.privateKey_web);
				LOGGER.info("解密："+ new String(decryptByPrivateKey, "utf-8"));
				JSONObject param = (JSONObject) JSONObject.parse(new String(decryptByPrivateKey, "utf-8"));
				data.put("data", param);
				/*校验通过*/
				data.put("result", R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999));
			}else if(parameter.hasParameterAnnotation(SIGNToken.class)){
				/*校验验签*/
				R check = SignUtil.checkSign(data, SysSecurityKeyConstant.md5Key_app);
				if(!Boolean.valueOf(check.get(R.SUCCESS_TAG).toString())) {
				   data.put("result", check);
				   return data;
				}
				/*获取用户ID*/
				String user_id = StringUtil.getMapValue(data, "token").split("\\|")[0];
				
				/*if(!("9".equals(user_id)) && !("6206".equals(user_id)) && !("23259".equals(user_id)) && !("25471".equals(user_id))) {
					data.put("result", R.error(UserInfoCodeConstant.USER_LOGIN_CODE_999799,UserInfoCodeConstant.USER_LOGIN_MSG_999799));
					return data;
				}*/
				
				/*用户冻结校验*/
				Map<String, Object> freezeUser = userInfoCacheService.getUserFreezeCacheById(user_id);
				if(freezeUser != null){
					data.put("result", R.error(CommonCodeConstant.COMMON_MSG_999988,CommonCodeConstant.COMMON_MSG_999988));
					return data;
				}
				/*用户TOKEN校验*/
				String user_token_key = RedisNameConstants.zfpay_user_token + user_id;
				Object user = redisUtils.get(user_token_key);
				if(user == null || !StringUtil.getMapValue(data, "token").equals(user.toString())){
					data.put("result", R.error(CommonCodeConstant.COMMON_CODE_999990,CommonCodeConstant.COMMON_MSG_999990));
					return data;
				}
				/*校验通过*/
				data.put("sys_user_id", user_id);
				data.put("result", R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999));
			}else if(parameter.hasParameterAnnotation(SIGNOnly.class)){
				/*校验验签*/
				R check = SignUtil.checkSign(data, SysSecurityKeyConstant.md5Key_app);
				if(!Boolean.valueOf(check.get(R.SUCCESS_TAG).toString())) {
				   data.put("result", check);
				   return data;
				}
				/*校验通过*/
				data.put("result", R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999));
			}else if(parameter.hasParameterAnnotation(SIGNOnlyWeb.class)){
				/*校验验签*/
				R check = SignUtil.checkSign(data, SysSecurityKeyConstant.md5Key_web);
				if(!Boolean.valueOf(check.get(R.SUCCESS_TAG).toString())) {
				   data.put("result", check);
				   return data;
				}
				/*校验通过*/
				data.put("result", R.ok(CommonCodeConstant.COMMON_CODE_999999, CommonCodeConstant.COMMON_MSG_999999));
			}else{
				data.put("result", R.error(CommonCodeConstant.COMMON_CODE_999987, CommonCodeConstant.COMMON_MSG_999987));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error("解密异常："+body.toString());
			data.put("result", R.error(CommonCodeConstant.COMMON_CODE_999987, CommonCodeConstant.COMMON_MSG_999987));
		}
		return data;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			java.lang.reflect.Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}  
  
}  
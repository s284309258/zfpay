package com.example.longecological.controller.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.RSAOnly;
import com.example.longecological.annotations.RSAOnlyWeb;
import com.example.longecological.annotations.RSAToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.common.SmsCodeService;


/**
 * 短信，邮箱验证码相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/common/smsCode")
public class SmsCodeController {
	
	@Autowired
	private SmsCodeService smsCodeService;

	
	/**
	 * 短信验证码发送=====》整体仅仅rsa加密（适用于注册和忘记密码）
	 * @param map
	 * @return
	 */
	@ResponseBody
	@PostMapping("/sendSmsCodeOnly")
	public R sendSmsCode(@RSAOnly @RequestBody Map<String, Object> map) {
		return smsCodeService.sendSmsCodeOnly(map);
	}
	
	/**
	 * WEB端注册短信验证码发送=====》整体仅仅rsa加密（适用于注册和忘记密码）
	 * @param map
	 * @return
	 */
	@ResponseBody
	@PostMapping("/sendSmsRegisterCodeWeb")
	public R sendSmsRegisterCodeWeb(@RSAOnlyWeb @RequestBody Map<String, Object> map) {
		return smsCodeService.sendSmsCodeOnly(map);
	}
	
	
	/**
	 * 短信验证码发送=====》整体rsa加密+token验证（适用于修改登录密码和交易密码）
	 * @param map
	 * @return
	 */
	@ResponseBody
	@PostMapping("/sendSmsCodeToken")
	public R sendSmsCodeToken(@RSAToken @RequestBody Map<String, Object> map) {
		return smsCodeService.sendSmsCodeToken(map);
	}
	
}

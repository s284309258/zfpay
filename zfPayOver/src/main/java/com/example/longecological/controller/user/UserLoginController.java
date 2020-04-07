package com.example.longecological.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.RSAOnly;
import com.example.longecological.annotations.RSAOnlyWeb;
import com.example.longecological.annotations.SIGNOnly;
import com.example.longecological.entity.R;
import com.example.longecological.service.user.UserLoginService;


/**
 * 用户登录注册相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/user/login")
public class UserLoginController {
	
	@Autowired
	private UserLoginService userLoginService;

	
	/**
	 * 用户登录======》整体仅仅rsa加密
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userLogin")
	public R userLogin(@RSAOnly @RequestBody Map<String, Object> map) {
		return userLoginService.userLogin(map);
	}

	/***
	 *add byqh202003
	 * @param map(user_id,device_token)
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateUserDeviceToken")
	public R updateUserDeviceToken(@RSAOnly @RequestBody Map<String, Object> map){
		return userLoginService.updateUserDeviceToken(map);
	}
	
	
	/**
	 * 用户注册======》整体仅仅rsa加密
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userRegister")
	public R userRegister(@RSAOnly @RequestBody Map<String, Object> map) {
		return userLoginService.userRegister(map);
	}
	
	/**
	 * WEB端用户注册======》整体仅仅rsa加密
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userRegisterWeb")
	public R userRegisterWeb(@RSAOnlyWeb @RequestBody Map<String, Object> map) {
		return userLoginService.userRegister(map);
	}
	
	
	/**
	 * 用户退出登录======》整体rsa加密
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userLogOut")
	public R userLogOut(@SIGNOnly @RequestBody Map<String, Object> map) {
		return userLoginService.userLogOut(map);
	}
	
	
	/**
	 * 找回登录密码（忘记密码）======》整体仅仅rsa加密
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userForgetPass")
	public R userForgetPass(@RSAOnly @RequestBody Map<String, Object> map) {
		return userLoginService.userForgetPass(map);
	}
	
}

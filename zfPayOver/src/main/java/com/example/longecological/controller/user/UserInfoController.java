package com.example.longecological.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.RSAToken;
import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.user.UserInfoService;


/**
 * 用户信息相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/user/info")
public class UserInfoController {
	
	@Autowired
	private UserInfoService userInfoService;

	
	/**
	 * 修改交易密码======》整体rsa加密+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyPayPass")
	public R modifyPayPass(@RSAToken @RequestBody Map<String, Object> map) {
		return userInfoService.modifyPayPass(map);
	}
	
	
	/**
	 * 修改登录密码======》整体rsa加密+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyLoginPass")
	public R modifyLoginPass(@RSAToken @RequestBody Map<String, Object> map) {
		return userInfoService.modifyLoginPass(map);
	}
	
	
	/**
	 * 修改用户资料（头像）======》整体MD5验签+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyUserInfo")
	public R modifyUserInfo(@SIGNToken @RequestBody Map<String, Object> map) {
		return userInfoService.modifyUserInfo(map);
	}
	
	
	/**
	 * 修改手机号第一步：校验旧手机号======》整体rsa加密+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyTelFirst")
	public R modifyTelFirst(@RSAToken @RequestBody Map<String, Object> map) {
		return userInfoService.modifyTelFirst(map);
	}
	
	
	/**
	 * 修改手机号第一步：设置新的手机号======》整体rsa加密+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/modifyTelSecond")
	public R modifyTelSecond(@RSAToken @RequestBody Map<String, Object> map) {
		return userInfoService.modifyTelSecond(map);
	}
	
	
	/**
	 * 查询用户实名认证状态======》整体MD5验签+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserAuthStatus")
	public R getUserAuthStatus(@SIGNToken @RequestBody Map<String, Object> map) {
		return userInfoService.getUserAuthStatus(map);
	}
	
	
	/**
	 * 提交用户实名认证资料信息======》整体MD5验签+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/submitUserAuthInfo")
	public R submitUserAuthInfo(@SIGNToken @RequestBody Map<String, Object> map) {
		return userInfoService.submitUserAuthInfo(map);
	}
	
	
	/**
	 * 查询用户最新信息
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserNewInfo")
	public R getPurseInfo(@SIGNToken @RequestBody Map<String, Object> map) {
		return userInfoService.getUserNewInfo(map);
	}
	
}

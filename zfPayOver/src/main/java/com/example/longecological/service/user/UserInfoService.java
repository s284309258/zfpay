package com.example.longecological.service.user;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 用户信息相关
 * @author Administrator
 *
 */
public interface UserInfoService {

	
	/**
	 * 修改支付密码
	 * @param map
	 * @return
	 */
	R modifyPayPass(Map<String, Object> map);

	
	/**
	 * 修改登录密码
	 * @param map
	 * @return
	 */
	R modifyLoginPass(Map<String, Object> map);


	/**
	 * 修改用户资料
	 * @param map
	 * @return
	 */
	R modifyUserInfo(Map<String, Object> map);


	/**
	 * 修改手机号第一步：校验旧手机号
	 * @param map
	 * @return
	 */
	R modifyTelFirst(Map<String, Object> map);


	/**
	 * 修改手机号第一步：设置新的手机号
	 * @param map
	 * @return
	 */
	R modifyTelSecond(Map<String, Object> map);


	/**
	 * 查询用户实名认证状态信息
	 * @param map
	 * @return
	 */
	R getUserAuthStatus(Map<String, Object> map);


	/**
	 * 提交用户实名认证资料信息
	 * @param map
	 * @return
	 */
	R submitUserAuthInfo(Map<String, Object> map);


	/**
	 * 查询用户最新信息
	 * @param map
	 * @return
	 */
	R getUserNewInfo(Map<String, Object> map);

}

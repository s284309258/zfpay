package com.example.longecological.service.user;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 用户结算卡相关
 * @author Administrator
 *
 */
public interface UserCardInfoService {

	/**
	 * 查询用户结算卡列表
	 * @param map
	 * @return
	 */
	R getUserCardList(Map<String, Object> map);

	
	/**
	 * 设置用户结算卡
	 * @param map
	 * @return
	 */
	R updateUserCard(Map<String, Object> map);


	/**
	 * 查询用户有效的结算卡列表
	 * @param map
	 * @return
	 */
	R getUserValidCardList(Map<String, Object> map);

}

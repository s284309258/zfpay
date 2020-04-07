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
import com.example.longecological.service.user.UserCardInfoService;


/**
 * 用户结算卡相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/user/cardInfo")
public class UserCardInfoController {
	
	@Autowired
	private UserCardInfoService userCardInfoService;

	
	/**
	 * 查询用户结算卡列表 ======》整体MD5验签+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserCardList")
	public R getUserCardList(@SIGNToken @RequestBody Map<String, Object> map) {
		return userCardInfoService.getUserCardList(map);
	}
	
	
	/**
	 * 查询用户有效的结算卡列表 ======》整体MD5验签+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserValidCardList")
	public R getUserValidCardList(@SIGNToken @RequestBody Map<String, Object> map) {
		return userCardInfoService.getUserValidCardList(map);
	}
	
	
	/**
	 * 设置用户结算卡 ======》整体MD5验签+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateUserCard")
	public R updateUserCard(@RSAToken @RequestBody Map<String, Object> map) {
		return userCardInfoService.updateUserCard(map);
	}
	
}

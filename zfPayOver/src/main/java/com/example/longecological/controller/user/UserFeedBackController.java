package com.example.longecological.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.user.UserFeedBackService;


/**
 * 用户意见反馈相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/user/feedBack")
public class UserFeedBackController {
	
	@Autowired
	private UserFeedBackService userFeedBackService;

	
	/**
	 * 添加意见反馈 ======》整体MD5验签+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addUserFeedBack")
	public R modifyUserInfo(@SIGNToken @RequestBody Map<String, Object> map) {
		return userFeedBackService.addUserFeedBack(map);
	}
	
	
	/**
	 * 查询意见反馈列表 ======》整体MD5验签+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserFeedBackList")
	public R getUserFeedBackList(@SIGNToken @RequestBody Map<String, Object> map) {
		return userFeedBackService.getUserFeedBackList(map);
	}
	
}

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
import com.example.longecological.service.user.UserCashRecordService;


/**
 * 用户提现相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/user/cashRecord")
public class UserCashRecordController {
	
	@Autowired
	private UserCashRecordService userCashRecordService;

	
	/**
	 * 查询提现信息 ======》整体MD5验签+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCashInfo")
	public R getCashInfo(@SIGNToken @RequestBody Map<String, Object> map) {
		return userCashRecordService.getCashInfo(map);
	}
	
	
	/**
	 * 用户申请提现 ======》整体MD5验签+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/applyCash")
	public R applyCash(@RSAToken @RequestBody Map<String, Object> map) {
		return userCashRecordService.applyCash(map);
	}
	
	
	/**
	 * 查询提现记录列表 ======》整体MD5验签+token验证
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCashRecordList")
	public R getCashRecordList(@SIGNToken @RequestBody Map<String, Object> map) {
		return userCashRecordService.getCashRecordList(map);
	}
	
	
}

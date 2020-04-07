package com.example.longecological.controller.moneylocker;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.moneylocker.MoneyLockerCollegeService;

/**
 * 首页机具管理 控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/moneylockercollege")
public class MoneyLockerCollegeController {

	@Autowired
	private MoneyLockerCollegeService moneyLockerCollegeService;
	
	/**
	 * 查询钱柜学院列表 ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMoneyLockerCollegeList")
	public R getMoneyLockerCollegeList(@SIGNToken @RequestBody Map<String, Object> map) {
		return moneyLockerCollegeService.getMoneyLockerCollegeList(map);
	}
	
	/**
	 * 查询钱柜学院详情 ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMoneyLockerCollegeDetail")
	public R getMoneyLockerCollegeDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return moneyLockerCollegeService.getMoneyLockerCollegeDetail(map);
	}
}

package com.example.longecological.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysBankInfoService;


/**
 * 系统银行相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/bankInfo")
public class SysBankInfoController {
	
	@Autowired
	private SysBankInfoService sysBankInfoService;

	
	/**
	 * 模糊搜索银行列表======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/searchLikeBank")
	public R searchLikeBank(@SIGNToken @RequestBody Map<String, Object> map) {
		return sysBankInfoService.searchLikeBank(map);
	}
	
}

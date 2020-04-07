package com.example.longecological.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysExchangeUrlService;


/**
 * 交易所行情相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/exchangeUrl")
public class SysExchangeUrlController {
	
	@Autowired
	private SysExchangeUrlService sysExchangeUrlService;

	
	/**
	 * 查询系统URL版本信息列表======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getExchangeUrlList")
	public R getExchangeUrlList(@SIGNToken @RequestBody Map<String, Object> map) {
		return sysExchangeUrlService.getExchangeUrlList(map);
	}
	
}

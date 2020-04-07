package com.example.longecological.controller.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNOnly;
import com.example.longecological.entity.R;
import com.example.longecological.service.common.QiNiuService;

/**
 * 七牛controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/common/qiniu")
public class QiNiuController {
	
	@Autowired
	private QiNiuService qiNiuReadService;

	
	/**
	 * 生成七牛云Token
	 * @param map
	 * @return
	 */
	@RequestMapping("getQiNiuToken")
	@ResponseBody
	public R getToken(@SIGNOnly @RequestBody Map<String, Object> map){
		return qiNiuReadService.getQiNiuToken(map);
	}
}

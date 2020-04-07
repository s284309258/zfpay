package com.example.longecological.controller.test;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.RSAToken;
import com.example.longecological.entity.R;

@Controller
@RequestMapping("/api/test")
public class TestController {
	

	@ResponseBody
	@RequestMapping("/test")
	public R test(@RSAToken @RequestBody Map<String, Object> map) {
		System.out.println(map.toString());
		return R.ok("访问成功");
	}
}

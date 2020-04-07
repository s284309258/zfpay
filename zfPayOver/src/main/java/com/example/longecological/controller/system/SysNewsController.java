package com.example.longecological.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysNewsService;


/**
 * 新闻资讯相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/news")
public class SysNewsController {
	
	@Autowired
	private SysNewsService sysNewsService;
	
	/**
	 * 查询系统新闻列表======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getNewNews")
	public R getNewNews(@SIGNToken @RequestBody Map<String, Object> map) {
		return sysNewsService.getNewNews(map);
	}
	
	/**
	 * 查询系统新闻列表======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getNewsList")
	public R getNewsList(@SIGNToken @RequestBody Map<String, Object> map) {
		return sysNewsService.getNewsList(map);
	}
	
	/**
	 * 查询系统新闻详情======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getNewsDetail")
	public R getNewsDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return sysNewsService.getNewsDetail(map);
	}
	
}

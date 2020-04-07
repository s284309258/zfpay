package com.example.longecological.controller.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysNoticeService;


/**
 * 公告通知相关控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/notice")
public class SysNoticeController {
	
	@Autowired
	private SysNoticeService sysNoticeService;

	/**
	 * 得到未读消息add byqh 201912
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUnReadNews")
	public R getUnReadNews(@SIGNToken @RequestBody Map<String, Object> map){
		return sysNoticeService.getUnReadNews(map);
	}

	/***
	 * 更新已读未读标志 add byqh 2010912
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateNewsReadFlag")
	public R updateNewsReadFlag(@SIGNToken @RequestBody Map<String, Object> map){
		return sysNoticeService.updateNewsReadFlag(map);
	}

	
	/**
	 * 查询系统最新公告======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getNewNotice")
	public R getNewNotice(@SIGNToken @RequestBody Map<String, Object> map) {
		return sysNoticeService.getNewNotice(map);
	}
	
	
	/**
	 * 查询系统公告列表======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getNoticeList")
	public R getNoticeList(@SIGNToken @RequestBody Map<String, Object> map) {
		return sysNoticeService.getNoticeList(map);
	}
	
	/**
	 * 查询系统公告详情======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getNoticeDetail")
	public R getNoticeDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return sysNoticeService.getNoticeDetail(map);
	}
	
}

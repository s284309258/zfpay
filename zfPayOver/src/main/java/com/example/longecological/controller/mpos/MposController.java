package com.example.longecological.controller.mpos;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.RSAToken;
import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.mpos.MposService;

/**
 * 首页MPOS功能 控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/mpos")
public class MposController {
	
	@Autowired
	private MposService mposService;

	/**
	 * 根据SN码查询POS机列表 -- 分页======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMposList")
	public R getMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return mposService.getMposList(map);
	}
	
	/**
	 * 修改MPOS商户信息 -- 分页======》RSA验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editMposMerInfo")
	public R editMposMerInfo(@SIGNToken @RequestBody Map<String, Object> map) {
		return mposService.editMposMerInfo(map);	
	}
}

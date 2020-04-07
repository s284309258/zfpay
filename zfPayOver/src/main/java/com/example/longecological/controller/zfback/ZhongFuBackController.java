package com.example.longecological.controller.zfback;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.zfback.ZhongFuBackService;

/**
 * 中付进件接口回调
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/interface/zfback")
public class ZhongFuBackController {
	
	@Autowired
	private ZhongFuBackService zhongFuBackService;

	/**
	 * 进件接口回调
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/merchantIntoCallback")
	public String merchantIntoCallback(@RequestParam Map<String, Object> map){
		return zhongFuBackService.merchantIntoCallback(map);
	}
	
	/**
	 * 进件列表查询
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTraditionalPosInstallList")
	public R getTraditionalPosInstallList(@SIGNToken @RequestBody Map<String, Object> map){
		return zhongFuBackService.getTraditionalPosInstallList(map);
	}
	
	/**
	 * 进件详情
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTraditionalPosInstallDetail")
	public R getTraditionalPosInstallDetail(@SIGNToken @RequestBody Map<String, Object> map){
		return zhongFuBackService.getTraditionalPosInstallDetail(map);
	}
}

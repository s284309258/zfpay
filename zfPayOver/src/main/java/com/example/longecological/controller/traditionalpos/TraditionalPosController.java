package com.example.longecological.controller.traditionalpos;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.traditionalpos.TraditionalPosService;

/**
 * 首页传统POS管理 控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/traditionalpos")
public class TraditionalPosController {

	@Autowired
	private TraditionalPosService traditionalPosService;
	
	/**
	 * 查询需要申请扫码支付的传统POS机列表======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getScanTraditionalPosList")
	public R getScanTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return traditionalPosService.getScanTraditionalPosList(map);
	}
	
	/**
	 * 提交申请记录======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addApplyScanRecord")
	public R addApplyScanRecord(@SIGNToken @RequestBody Map<String, Object> map) {
		return traditionalPosService.addApplyScanRecord(map);
	}
	
	/**
	 * 查询扫码支付申请记录======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getApplyScanRecordList")
	public R getApplyScanRecordList(@SIGNToken @RequestBody Map<String, Object> map) {
		return traditionalPosService.getApplyScanRecordList(map);
	}
}

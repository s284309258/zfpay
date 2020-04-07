package com.example.longecological.controller.ratesmanage;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.ratesmanage.CreditCardRatesApplyService;

/**
 * 首页刷卡费率申请 控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/creditcardratesapply")
public class CreditCardRatesApplyController {

	@Autowired
	private CreditCardRatesApplyService creditCardRatesApplyService;
	
	/**
	 * 申请费率POS机列表（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getApplyRateTraditionalPosList")
	public R getApplyRateTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return creditCardRatesApplyService.getApplyRateTraditionalPosList(map);
	}
	
	/**
	 * 申请费率POS机列表（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getApplyRateMposList")
	public R getApplyRateMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return creditCardRatesApplyService.getApplyRateMposList(map);
	}
	
	/**
	 * 查询刷卡费率列表======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCreditCardRateList")
	public R getCreditCardRateList(@SIGNToken @RequestBody Map<String, Object> map) {
		return creditCardRatesApplyService.getCreditCardRateList(map);
	}
	
	/**
	 * 提交费率申请记录（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addApplyRateTraditionalPos")
	public R addApplyRateTraditionalPos(@SIGNToken @RequestBody Map<String, Object> map) {
		return creditCardRatesApplyService.addApplyRateTraditionalPos(map);
	}
	
	/**
	 * 提交费率申请记录（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addApplyRateMpos")
	public R addApplyRateMpos(@SIGNToken @RequestBody Map<String, Object> map) {
		return creditCardRatesApplyService.addApplyRateMpos(map);
	}
	
	/**
	 * 查询费率申请记录（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getApplyRateTraditionalPosRecordList")
	public R getApplyRateTraditionalPosRecordList(@SIGNToken @RequestBody Map<String, Object> map) {
		return creditCardRatesApplyService.getApplyRateTraditionalPosRecordList(map);
	}
	
	/**
	 * 查询费率申请记录（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getApplyRateMposRecordList")
	public R getApplyRateMposRecordList(@SIGNToken @RequestBody Map<String, Object> map) {
		return creditCardRatesApplyService.getApplyRateMposRecordList(map);
	}
}

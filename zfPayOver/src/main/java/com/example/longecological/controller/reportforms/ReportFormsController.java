package com.example.longecological.controller.reportforms;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.reportforms.ReportFormsService;

/**
 * 报表中心 控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/reportforms")
public class ReportFormsController {

	@Autowired
	private ReportFormsService reportFormsService;

	/**
	 * 首页统计信息======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getHomePageInfo")
	public R getHomePageInfo(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getHomePageInfo(map);
	}
	
	/**
	 * 代理每天详情（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDayAgencyTraditionalPosDetail")
	public R getDayAgencyTraditionalPosDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getDayAgencyTraditionalPosDetail(map);
	}

	/**
	 * 代理每天详情（传统POS）======》MD5验签方式 add byqh202003
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDayAgencyEposDetail")
	public R getDayAgencyEposDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getDayAgencyEposDetail(map);
	}
	
	/**
	 * 代理每月详情（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMonthAgencyTraditionalPosDetail")
	public R getMonthAgencyTraditionalPosDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getMonthAgencyTraditionalPosDetail(map);
	}

	/**
	 * 代理每月详情（传统POS）======》MD5验签方式 add byqh202003
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMonthAgencyEposDetail")
	public R getMonthAgencyEposDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getMonthAgencyEposDetail(map);
	}
	
	/**
	 * 商户每天详情（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDayMerchantTraditionalPosDetail")
	public R getDayMerchantTraditionalPosDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getDayMerchantTraditionalPosDetail(map);
	}

	/**
	 * 商户每天详情（传统POS）======》MD5验签方式add byqh202003
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDayMerchantEposDetail")
	public R getDayMerchantEposDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getDayMerchantEposDetail(map);
	}
	
	/**
	 * 商户每月详情（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMonthMerchantTraditionalPosDetail")
	public R getMonthMerchantTraditionalPosDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getMonthMerchantTraditionalPosDetail(map);
	}

	/**
	 * 商户每月详情（传统POS）======》MD5验签方式add byqh202003
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMonthMerchantEposDetail")
	public R getMonthMerchantEposDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getMonthMerchantEposDetail(map);
	}
	
	/**
	 * 代理每天走势列表（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDayAgencyTraditionalPosList")
	public R getDayAgencyTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getDayAgencyTraditionalPosList(map);
	}
	
	/**
	 * 代理每月走势列表（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMonthAgencyTraditionalPosList")
	public R getMonthAgencyTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getMonthAgencyTraditionalPosList(map);
	}
	
	/**
	 * 商户每天走势列表（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDayMerchantTraditionalPosList")
	public R getDayMerchantTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getDayMerchantTraditionalPosList(map);
	}
	
	/**
	 * 商户每月走势列表（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMonthMerchantTraditionalPosList")
	public R getMonthMerchantTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getMonthMerchantTraditionalPosList(map);
	}
	
	/**
	 * 代理每天详情（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDayAgencyMposDetail")
	public R getDayAgencyMposDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getDayAgencyMposDetail(map);
	}
	
	/**
	 * 代理每月详情（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMonthAgencyMposDetail")
	public R getMonthAgencyMposDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getMonthAgencyMposDetail(map);
	}
	
	/**
	 * 商户每天详情（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDayMerchantMposDetail")
	public R getDayMerchantMposDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getDayMerchantMposDetail(map);
	}
	
	/**
	 * 商户每月详情（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMonthMerchantMposDetail")
	public R getMonthMerchantMposDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getMonthMerchantMposDetail(map);
	}
	
	/**
	 * 代理每天走势列表（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDayAgencyMposList")
	public R getDayAgencyMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getDayAgencyMposList(map);
	}
	
	/**
	 * 代理每月走势列表（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMonthAgencyMposList")
	public R getMonthAgencyMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getMonthAgencyMposList(map);
	}
	
	/**
	 * 商户每天走势列表（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDayMerchantMposList")
	public R getDayMerchantMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getDayMerchantMposList(map);
	}
	
	/**
	 * 商户每月走势列表（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMonthMerchantMposList")
	public R getMonthMerchantMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return reportFormsService.getMonthMerchantMposList(map);
	}
	
}

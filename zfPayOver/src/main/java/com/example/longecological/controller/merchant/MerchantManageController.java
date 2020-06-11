package com.example.longecological.controller.merchant;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.merchant.MerchantManageService;

/**
 * 首页商户管理 控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/merchant")
public class MerchantManageController {

	@Autowired
	private MerchantManageService merchantManageService;
	
	/**
	 * 商户管理汇总列表(传统POS)======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSummaryTraditionalPosList")
	public R getSummaryTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getSummaryTraditionalPosList(map);
	}
	
	/**
	 * 商户管理汇总列表(MPOS)======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSummaryMposList")
	public R getSummaryMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getSummaryMposList(map);
	}
	
	/**
	 * 全部商户列表查询（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllMerchantTraditionalPosList")
	public R getAllMerchantTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getAllMerchantTraditionalPosList(map);
	}

	/***
	 * 更新商户姓名和电话 sn,name,tel add byqh 201912
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateMerchantNameAndTel")
	public R updateMerchantNameAndTel(@SIGNToken @RequestBody Map<String, Object> map){
		return merchantManageService.updateMerchantNameAndTel(map);
	}
	
	/**
	 * 优质商户列表查询（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getExcellentMerchantTraditionalPosList")
	public R getExcellentMerchantTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getExcellentMerchantTraditionalPosList(map);
	}
	
	/**
	 * 活跃商户列表查询（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getActiveMerchantTraditionalPosList")
	public R getActiveMerchantTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getActiveMerchantTraditionalPosList(map);
	}
	
	/**
	 * 休眠商户列表查询（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDormantMerchantTraditionalPosList")
	public R getDormantMerchantTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getDormantMerchantTraditionalPosList(map);
	}
	
	/**
	 * 全部商户列表查询（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllMerchantMposList")
	public R getAllMerchantMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getAllMerchantMposList(map);
	}
	
	/**
	 * 优质商户列表查询（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getExcellentMerchantMposList")
	public R getExcellentMerchantMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getExcellentMerchantMposList(map);
	}
	
	/**
	 * 活跃商户列表查询（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getActiveMerchantMposList")
	public R getActiveMerchantMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getActiveMerchantMposList(map);
	}
	
	/**
	 * 休眠商户列表查询（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDormantMerchantMposList")
	public R getDormantMerchantMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getDormantMerchantMposList(map);
	}
	
	/**
	 * 查询商户详情（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTraditionalPosDetail")
	public R getTraditionalPosDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getTraditionalPosDetail(map);
	}

	/**
	 * 查询商户详情（传统POS）======》MD5验签方式 add byqh202003
	 * @param map(sn)
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTraditionalPosTradeDetail")
	public R getTraditionalPosTradeDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getTraditionalPosTradeDetail(map);
	}




	//查询商户排名10 byqh202006
	@ResponseBody
	@RequestMapping("/getTradeVolumeRankByMonth")
	public R getTradeVolumeRankByMonth(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getTradeVolumeRankByMonth(map);
	}
	@ResponseBody
	@RequestMapping("/getTradeVolumeRankByDay")
	public R getTradeVolumeRankByDay(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getTradeVolumeRankByDay(map);
	}









	@ResponseBody
	@RequestMapping("/getMposTradeVolumeRankByMonth")
	public R getMposTradeVolumeRankByMonth(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getMposTradeVolumeRankByMonth(map);
	}

	@ResponseBody
	@RequestMapping("/getMposTradeVolumeRankByDay")
	public R getMposTradeVolumeRankByDay(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getMposTradeVolumeRankByDay(map);
	}

	@ResponseBody
	@RequestMapping("/getTraposTradeVolumeRankByMonth")
	public R getTraposTradeVolumeRankByMonth(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getTraposTradeVolumeRankByMonth(map);
	}

	@ResponseBody
	@RequestMapping("/getTraposTradeVolumeRankByDay")
	public R getTraposTradeVolumeRankByDay(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getTraposTradeVolumeRankByDay(map);
	}

	//查询商户排名10 byqh202006
	
	/**
	 * 查询商户详情（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMposDetail")
	public R getMposDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getMposDetail(map);
	}
	
	/**
	 * 查询直推人数 ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getReferAgencyNum")
	public R getReferAgencyNum(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getReferAgencyNum(map);
	}
	
	/**
	 * 查询直推代理 ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getReferAgencyList")
	public R getReferAgencyList(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getReferAgencyList(map);
	}

	/***
	 * add byqh 201912
	 * 完成政策达标考核的商户======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCompletePolicyMerchantTraditionalPos")
	public R getCompletePolicyMerchantTraditionalPos(@SIGNToken @RequestBody Map<String, Object> map){
		return merchantManageService.getCompletePolicyMerchantTraditionalPos(map);
	}

	/***
	 * add byqh 201912
	 * 完成政策达标考核的商户======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCompletePolicyMerchantMPos")
	public R getCompletePolicyMerchantMPos(@SIGNToken @RequestBody Map<String, Object> map){
		return merchantManageService.getCompletePolicyMerchantMPos(map);
	}
	
	/**
	 * 查询代理头部信息（传统POS） ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getReferAgencyHeadTraditionalPosInfo")
	public R getReferAgencyHeadTraditionalPosInfo(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getReferAgencyHeadTraditionalPosInfo(map);
	}

	/***
	 * 得到传统POS交易额和月均交易额 add byqh 201912
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getReferAgencyTraditionalPosTradeAmountAvg")
	public R getReferAgencyTraditionalPosTradeAmountAvg(@SIGNToken @RequestBody Map<String, Object> map){
		return merchantManageService.getReferAgencyTraditionalPosTradeAmountAvg(map);
	}

	/***
	 * 得到传统POS交易额和月均交易额 add byqh 201912 add byqh202003,多余接口，不用
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getReferAgencyEposTradeAmountAvg")
	public R getReferAgencyEposTradeAmountAvg(@SIGNToken @RequestBody Map<String, Object> map){
		return merchantManageService.getReferAgencyEposTradeAmountAvg(map);
	}

	/***
	 * 得到MPOS交易额和月均交易额 add byqh 201912
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getReferAgencyMPosTradeAmountAvg")
	public R getReferAgencyMPosTradeAmountAvg(@SIGNToken @RequestBody Map<String, Object> map){
		return merchantManageService.getReferAgencyMPosTradeAmountAvg(map);
	}
	
	/**
	 * 查询代理商户列表（传统POS） ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getReferAgencyTraditionalPosList")
	public R getReferAgencyTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getReferAgencyTraditionalPosList(map);
	}
	
	/**
	 * 查询代理头部信息（MPOS） ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getReferAgencyHeadMposInfo")
	public R getReferAgencyHeadMposInfo(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getReferAgencyHeadMposInfo(map);
	}
	
	/**
	 * 查询代理商户列表（MPOS） ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getReferAgencyMposList")
	public R getReferAgencyMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return merchantManageService.getReferAgencyMposList(map);
	}
	
}

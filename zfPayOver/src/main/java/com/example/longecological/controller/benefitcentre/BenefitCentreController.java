package com.example.longecological.controller.benefitcentre;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.benefitcentre.BenefitCentreService;

/**
 * 收益中心 控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/benefitcentre")
public class BenefitCentreController {

	@Autowired
	private BenefitCentreService benefitCentreService;
	
	/**
	 * 收益中心头部信息======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getHeaderInformation")
	public R getHeaderInformation(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getHeaderInformation(map);
	}
	
	/**
	 * 收益中心详情（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getBenefitCentreTraditionalPosDetail")
	public R getBenefitCentreTraditionalPosDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getBenefitCentreTraditionalPosDetail(map);
	}

	/**
	 * 收益中心详情（传统POS）======》MD5验签方式add byqh202003
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getBenefitCentreEposDetail")
	public R getBenefitCentreEposDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getBenefitCentreEposDetail(map);
	}
	
	/**
	 * 收益中心详情（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getBenefitCentreMposDetail")
	public R getBenefitCentreMposDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getBenefitCentreMposDetail(map);
	}
	
	/**
	 * 分润记录列表（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getShareBenefitTraditionalPosList")
	public R getShareBenefitTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getShareBenefitTraditionalPosList(map);
	}

	/**
	 * 分润记录列表（传统POS）======》MD5验签方式add byqh202003
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getShareBenefitEposList")
	public R getShareBenefitEposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getShareBenefitEposList(map);
	}
	
	/**
	 * 机器返现列表（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMachineBackTraditionalPosList")
	public R getMachineBackTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getMachineBackTraditionalPosList(map);
	}

	/**
	 * 机器返现列表（传统POS）======》MD5验签方式add byqh202003
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMachineBackEposList")
	public R getMachineBackEposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getMachineBackEposList(map);
	}
	
	/**
	 * 活动奖励列表（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getActivityRewardTraditionalPosList")
	public R getActivityRewardTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getActivityRewardTraditionalPosList(map);
	}

	/**
	 * 活动奖励列表（传统POS）======》MD5验签方式add byqh202003
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getActivityRewardEposList")
	public R getActivityRewardEposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getActivityRewardEposList(map);
	}
	
	/**
	 * 扣除列表（传统POS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDeductTraditionalPosList")
	public R getDeductTraditionalPosList(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getDeductTraditionalPosList(map);
	}

	/**
	 * 扣除列表（传统POS）======》MD5验签方式add byqh202003
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDeductEposList")
	public R getDeductEposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getDeductEposList(map);
	}
	
	/**
	 * 分润记录列表（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getShareBenefitMposList")
	public R getShareBenefitMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getShareBenefitMposList(map);
	}
	
	/**
	 * 机器返现列表（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMachineBackMposList")
	public R getMachineBackMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getMachineBackMposList(map);
	}
	
	/**
	 * 活动奖励列表（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getActivityRewardMposList")
	public R getActivityRewardMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getActivityRewardMposList(map);
	}
	
	/**
	 * 扣除列表（MPOS）======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDeductMposList")
	public R getDeductMposList(@SIGNToken @RequestBody Map<String, Object> map) {
		return benefitCentreService.getDeductMposList(map);
	}
}

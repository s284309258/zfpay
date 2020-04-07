package com.example.longecological.controller.activity;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.longecological.annotations.SIGNToken;
import com.example.longecological.entity.R;
import com.example.longecological.service.activity.OnlineActivityService;

/**
 * 首页线上活动 控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/sys/onlineactivity")
public class OnlineActivityController {

	@Autowired
	private OnlineActivityService onlineActivityService;
	
	/**
	 * 查询活动列表（传统POS） ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTraditionalPosOnlineActivityList")
	public R getTraditionalPosOnlineActivityList(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.getTraditionalPosOnlineActivityList(map);
	}

	/***
	 * add byqh 201912
	 * 查询线上可以参与的政策
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getPosOnlinePolicyList")
	public R getPosOnlinePolicyList(@SIGNToken @RequestBody Map<String, Object> map){
		return onlineActivityService.getPosOnlinePolicyList(map);
	}

	/***
	 * add byqh 201912
	 * 参加线上可以参与的政策
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/joinPosOnlinePolicy")
	public R joinPosOnlinePolicy(@SIGNToken @RequestBody Map<String, Object> map){
		return onlineActivityService.joinPosOnlinePolicy(map);
	}
	
	/**
	 * 查询活动列表（MPOS） ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMposOnlineActivityList")
	public R getMposOnlineActivityList(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.getMposOnlineActivityList(map);
	}
	
	/**
	 * 查询活动奖励记录列表(传统POS) ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTraditionalPosRewardRecordList")
	public R getTraditionalPosRewardRecordList(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.getTraditionalPosRewardRecordList(map);
	}
	
	/**
	 * 查询活动奖励记录列表(MPOS) ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMposRewardRecordList")
	public R getMposRewardRecordList(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.getMposRewardRecordList(map);
	}
	
	/**
	 * 查询用户活动申请列表(传统POS) ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTraditionalPosActivityApplyList")
	public R getTraditionalPosActivityApplyList(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.getTraditionalPosActivityApplyList(map);
	}
	
	/**
	 * 查询用户活动申请列表(MPOS) ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMposActivityApplyList")
	public R getMposActivityApplyList(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.getMposActivityApplyList(map);
	}
	
	/**
	 * 查询用户活动申请详情(传统POS) ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTraditionalPosActivityApplyDetail")
	public R getTraditionalPosActivityApplyDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.getTraditionalPosActivityApplyDetail(map);
	}
	
	/**
	 * 查询用户活动申请详情(MPOS) ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMposActivityApplyDetail")
	public R getMposActivityApplyDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.getMposActivityApplyDetail(map);
	}
	
	/**
	 * 查询活动详情(传统POS) ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTraditionalPosOnlineActivityDetail")
	public R getTraditionalPosOnlineActivityDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.getTraditionalPosOnlineActivityDetail(map);
	}
	
	/**
	 * 查询活动详情(MPOS) ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMposOnlineActivityDetail")
	public R getMposOnlineActivityDetail(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.getMposOnlineActivityDetail(map);
	}
	
	/**
	 * 查询参与活动信息(传统POS) ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getTraditionalPosPartActivityInfo")
	public R getTraditionalPosPartActivityInfo(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.getTraditionalPosPartActivityInfo(map);
	}
	
	/**
	 * 查询参与活动信息(MPOS) ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMposPartActivityInfo")
	public R getMposPartActivityInfo(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.getMposPartActivityInfo(map);
	}
	
	/**
	 * 提交申请信息(传统POS) ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/submitTraditionalPosActivityApply")
	public R submitTraditionalPosActivityApply(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.submitTraditionalPosActivityApply(map);
	}
	
	/**
	 * 提交申请信息(MPOS) ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/submitMposActivityApply")
	public R submitMposActivityApply(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.submitMposActivityApply(map);
	}
	
	/**
	 * 取消申请信息(传统POS) ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cancelTraditionalPosActivityApply")
	public R cancelTraditionalPosActivityApply(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.cancelTraditionalPosActivityApply(map);
	}
	
	/**
	 * 取消申请信息(MPOS) ======》MD5验签方式
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cancelMposActivityApply")
	public R cancelMposActivityApply(@SIGNToken @RequestBody Map<String, Object> map) {
		return onlineActivityService.cancelMposActivityApply(map);
	}
	
}

package com.example.longecological.service.activity;

import java.util.Map;

import com.example.longecological.entity.R;

public interface OnlineActivityService {
	
	/**
	 * 查询活动列表(传统POS)
	 * @param map
	 * @return
	 */
	R getTraditionalPosOnlineActivityList(Map<String, Object> map);

	/***
	 * add byqh 201912
	 * 查询线上可以参与的政策
	 * @param map
	 * @return
	 */
	R getPosOnlinePolicyList(Map<String, Object> map);


	/***
	 * add byqh 201912
	 * 参加线上可以参与的政策
	 * @param map
	 * @return
	 */
	R joinPosOnlinePolicy(Map<String, Object> map);
	
	/**
	 * 查询活动列表(MPOS)
	 * @param map
	 * @return
	 */
	R getMposOnlineActivityList(Map<String, Object> map); 
	
	/**
	 * 查询活动奖励记录列表(传统POS)
	 * @param map
	 * @return
	 */
	R getTraditionalPosRewardRecordList(Map<String, Object> map); 
	
	/**
	 * 查询活动奖励记录列表(MPOS)
	 * @param map
	 * @return
	 */
	R getMposRewardRecordList(Map<String, Object> map);
	
	/**
	 * 查询用户申请列表(传统POS)
	 * @param map
	 * @return
	 */
	R getTraditionalPosActivityApplyList(Map<String, Object> map); 
	
	/**
	 * 查询用户申请列表(MPOS)
	 * @param map
	 * @return
	 */
	R getMposActivityApplyList(Map<String, Object> map); 
	
	/**
	 * 查询用户申请详情(传统POS)
	 * @param map
	 * @return
	 */
	R getTraditionalPosActivityApplyDetail(Map<String, Object> map); 
	
	/**
	 * 查询用户申请详情(MPOS)
	 * @param map
	 * @return
	 */
	R getMposActivityApplyDetail(Map<String, Object> map); 
	
	/**
	 * 查询线上活动详情(传统POS)
	 * @param map
	 * @return
	 */
	R getTraditionalPosOnlineActivityDetail(Map<String, Object> map); 
	
	/**
	 * 查询线上活动详情(MPOS)
	 * @param map
	 * @return
	 */
	R getMposOnlineActivityDetail(Map<String, Object> map);
	
	/**
	 * 查询参与活动信息(传统POS)
	 * @param map
	 * @return
	 */
	R getTraditionalPosPartActivityInfo(Map<String, Object> map); 
	
	/**
	 * 查询参与活动信息(MPOS)
	 * @param map
	 * @return
	 */
	R getMposPartActivityInfo(Map<String, Object> map); 
	
	/**
	 * 提交活动申请信息(传统POS)
	 * @param map
	 * @return
	 */
	R submitTraditionalPosActivityApply(Map<String, Object> map); 
	
	/**
	 * 提交活动申请信息(MPOS)
	 * @param map
	 * @return
	 */
	R submitMposActivityApply(Map<String, Object> map); 
	
	/**
	 * 取消活动申请(传统POS)
	 * @param map
	 * @return
	 */
	R cancelTraditionalPosActivityApply(Map<String, Object> map); 
	
	/**
	 * 取消活动申请(MPOS)
	 * @param map
	 * @return
	 */
	R cancelMposActivityApply(Map<String, Object> map); 

}

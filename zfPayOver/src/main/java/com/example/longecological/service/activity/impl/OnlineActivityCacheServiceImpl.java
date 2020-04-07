package com.example.longecological.service.activity.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheService.CacheOperation;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.mapper.activity.OnlineActivityMapper;
import com.example.longecological.service.activity.OnlineActivityCacheService;

@Service
public class OnlineActivityCacheServiceImpl implements OnlineActivityCacheService {
	
	@Autowired
	private OnlineActivityMapper onlineActivityMapper;

	/**
	 * 线上活动列表(传统POS)
	 */
//	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_activity_list_01,
//			fieldKey="#map['key']", cacheOperation=CacheOperation.QUERY)
	@Override
	public List<Map<String, Object>> getTraditionalPosOnlineActivityList(Map<String, Object> map) {
		return onlineActivityMapper.getTraditionalPosOnlineActivityList(map);
	}
	
	/**
	 * 线上活动列表(MPOS)
	 */
//	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_activity_list_02,
//			fieldKey="#map['key']", cacheOperation=CacheOperation.QUERY)
	@Override
	public List<Map<String, Object>> getMposOnlineActivityList(Map<String, Object> map) {
		return onlineActivityMapper.getMposOnlineActivityList(map);
	}

	/**
	 * 线上活动详情(传统POS)
	 */
//	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_activity_01,
//			fieldKey="#map['activity_id']", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getTraditionalPosOnlineActivityDetail(Map<String, Object> map) {
		return onlineActivityMapper.getTraditionalPosOnlineActivityDetail(map);
	}
	
	/**
	 * 线上活动详情(MPOS)
	 */
//	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_activity_02,
//			fieldKey="#map['activity_id']", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getMposOnlineActivityDetail(Map<String, Object> map) {
		return onlineActivityMapper.getMposOnlineActivityDetail(map);
	}

	/**
	 * 查询活动奖励列表（传统POS）
	 */
//	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_activity_reward_list_01,
//			fieldKey="#map['activity_id']", cacheOperation=CacheOperation.QUERY)
	@Override
	public List<Map<String, Object>> getTraditionalPosActivityRewardList(Map<String, Object> map) {
		return onlineActivityMapper.getTraditionalPosActivityRewardList(map);
	}
	
	/**
	 * 查询活动奖励列表（MPOS）
	 */
//	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_activity_reward_list_02,
//			fieldKey="#map['activity_id']", cacheOperation=CacheOperation.QUERY)
	@Override
	public List<Map<String, Object>> getMposActivityRewardList(Map<String, Object> map) {
		return onlineActivityMapper.getMposActivityRewardList(map);
	}

	/**
	 * 查询活动奖励详情
	 */
//	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_activity_reward_01,
//			fieldKey="#map['activity_reward_id']", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getTraditionalPosActivityRewardDetail(Map<String, Object> map) {
		return onlineActivityMapper.getTraditionalPosActivityRewardDetail(map);
	}
	
	/**
	 * 查询活动奖励详情
	 */
//	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_activity_reward_02,
//			fieldKey="#map['activity_reward_id']", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getMposActivityRewardDetail(Map<String, Object> map) {
		return onlineActivityMapper.getMposActivityRewardDetail(map);
	}

}

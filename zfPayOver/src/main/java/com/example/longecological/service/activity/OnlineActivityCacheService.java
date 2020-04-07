package com.example.longecological.service.activity;

import java.util.List;
import java.util.Map;

public interface OnlineActivityCacheService {
	
	/**
	 * 线上活动列表（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraditionalPosOnlineActivityList(Map<String, Object> map);
	
	/**
	 * 线上活动列表（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposOnlineActivityList(Map<String, Object> map);
	
	/**
	 * 线上活动详情(传统POS)
	 * @param map
	 * @return
	 */
	Map<String, Object> getTraditionalPosOnlineActivityDetail(Map<String, Object> map);
	
	/**
	 * 线上活动详情(MPOS)
	 * @param map
	 * @return
	 */
	Map<String, Object> getMposOnlineActivityDetail(Map<String, Object> map);
	
	/**
	 * 查询活动奖励列表(传统POS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraditionalPosActivityRewardList(Map<String, Object> map);
	
	/**
	 * 查询活动奖励列表(MPOS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposActivityRewardList(Map<String, Object> map);
	
	/**
	 * 查询活动奖励详情(传统POS)
	 * @param map
	 * @return
	 */
	Map<String, Object> getTraditionalPosActivityRewardDetail(Map<String, Object> map);
	
	/**
	 * 查询活动奖励详情(传统POS)
	 * @param map
	 * @return
	 */
	Map<String, Object> getMposActivityRewardDetail(Map<String, Object> map);

}

package com.example.longecological.mapper.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OnlineActivityMapper {

	/**
	 * 查询线上活动列表(传统POS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraditionalPosOnlineActivityList(@Param("map") Map<String, Object> map);


	/***
	 * add byqh 201912
	 * 查询线上可以参与的政策
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getPosOnlinePolicyList(@Param("map") Map<String, Object> map);

	/***
	 * add byqh 201912
	 * 参加线上可以参与的政策
	 * @param map
	 * @return
	 */
	int joinPosOnlinePolicy(@Param("map") Map<String, Object> map);

	/***
	 * add byqh 201912
	 * 查询线上可以参与的政策BYID
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getPosOnlinePolicyByID(@Param("map") Map<String, Object> map);



	/**
	 * 查询线上活动列表(MPOS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposOnlineActivityList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询用户活动奖励记录(传统POS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraditionalPosRewardRecordList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询用户活动奖励记录(MPOS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposRewardRecordList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询活动申请列表(传统POS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraditionalPosActivityApplyList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询活动申请列表(MPOS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposActivityApplyList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询活动申请详情(传统POS)
	 * @param map
	 * @return
	 */
	Map<String, Object> getTraditionalPosActivityApplyDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询活动申请详情(MPOS)
	 * @param map
	 * @return
	 */
	Map<String, Object> getMposActivityApplyDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询活动详情(传统POS)
	 * @param map
	 * @return
	 */
	Map<String, Object> getTraditionalPosOnlineActivityDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询活动详情(MPOS)
	 * @param map
	 * @return
	 */
	Map<String, Object> getMposOnlineActivityDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询未激活的MPOS列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询未激活的传统POS列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserTraditionalPosList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询活动奖励列表(传统POS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraditionalPosActivityRewardList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询活动奖励列表(MPOS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposActivityRewardList(@Param("map") Map<String, Object> map);
	
	/**
	 * 判断是否已申请活动(传统POS)
	 * @param map
	 * @return
	 */
	int getTraditionalPosProcessingActivityApply(@Param("map") Map<String, Object> map);
	
	/**
	 * 判断是否已申请活动(MPOS)
	 * @param map
	 * @return
	 */
	int getMposProcessingActivityApply(@Param("map") Map<String, Object> map);
	
	/**
	 * 获取活动奖励详情(传统POS)
	 * @param map
	 * @return
	 */
	Map<String, Object> getTraditionalPosActivityRewardDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 获取活动奖励详情(MPOS)
	 * @param map
	 * @return
	 */
	Map<String, Object> getMposActivityRewardDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 保存申请记录(传统POS)
	 * @param map
	 * @return
	 */
	int addTraditionalPosActivityApplyInfo(@Param("map") Map<String, Object> map);
	
	/**
	 * 保存申请记录(MPOS)
	 * @param map
	 * @return
	 */
	int addMposActivityApplyInfo(@Param("map") Map<String, Object> map);
	
	/**
	 * 修改传统POS机的活动状态
	 * @param map
	 * @return
	 */
	int updateUserTraditionalPosActivityStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 修改Mpos机的活动状态
	 * @param map
	 * @return
	 */
	int updateUserMposActivityStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询申请记录(传统POS)
	 * @param map
	 * @return
	 */
	Map<String, Object> getTraditionalPosActivityApply(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新申请记录状态（传统POS）
	 * @param map
	 * @return
	 */
	int updateTraditionalPosActivityApplyStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 取消POS机参与状态（传统POS）
	 * @param map
	 * @return
	 */
	int updateUserTraditionalPosCancelActivityStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询申请记录(MPOS)
	 * @param map
	 * @return
	 */
	Map<String, Object> getMposActivityApply(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新申请记录状态（MPOS）
	 * @param map
	 * @return
	 */
	int updateMposActivityApplyStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 取消POS机参与状态（MPOS）
	 * @param map
	 * @return
	 */
	int updateUserMposCancelActivityStatus(@Param("map") Map<String, Object> map);
 }

package com.example.longecological.mapper.benefitcentre;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BenefitCentreMapper {
	
	/**
	 * 查询收益中心头部信息
	 * @param map
	 * @return
	 */
	Map<String, Object> getHeaderInformation(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询每月的传统POS详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getMonthTraditionalPosBenefitDeatil(@Param("map") Map<String, Object> map);

	/**
	 * 查询每月的传统POS详情add byqh202003
	 * @param map
	 * @return
	 */
	Map<String, Object> getMonthEposBenefitDeatil(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询每月的MPOS详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getMonthMposBenefitDeatil(@Param("map") Map<String, Object> map);
	
	/**
	 * 分润记录列表（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getShareBenefitTraditionalPosList(@Param("map") Map<String, Object> map);

	/**
	 * 分润记录列表（EPOS）add byqh202003
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getShareBenefitEposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 机器返现记录列表（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMachineBackTraditionalPosList(@Param("map") Map<String, Object> map);

	/**
	 * 机器返现记录列表（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMachineBackEposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 活动奖励记录列表（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getActivityRewardTraditionalPosList(@Param("map") Map<String, Object> map);

	/**
	 * 活动奖励记录列表（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getActivityRewardEposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 扣除列表（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getDeductTraditionalPosList(@Param("map") Map<String, Object> map);

	/**
	 * 扣除列表（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getDeductEposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 分润记录列表（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getShareBenefitMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 机器返现记录列表（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMachineBackMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 活动奖励记录列表（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getActivityRewardMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 扣除列表（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getDeductMposList(@Param("map") Map<String, Object> map);

}

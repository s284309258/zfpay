package com.example.longecological.service.benefitcentre;


import java.util.Map;

import com.example.longecological.entity.R;

public interface BenefitCentreService {

	/**
	 * 收益中心头部信息
	 * @param map
	 * @return
	 */
	R getHeaderInformation(Map<String, Object> map);
	
	/**
	 * 收益中心详情（传统POS）
	 * @param map
	 * @return
	 */
	R getBenefitCentreTraditionalPosDetail(Map<String, Object> map);

	/**
	 * 收益中心详情（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	R getBenefitCentreEposDetail(Map<String, Object> map);
	
	/**
	 * 收益中心详情（MPOS）
	 * @param map
	 * @return
	 */
	R getBenefitCentreMposDetail(Map<String, Object> map);
	
	/**
	 * 分润记录列表（传统POS）
	 * @param map
	 * @return
	 */
	R getShareBenefitTraditionalPosList(Map<String, Object> map);

	/**
	 * 分润记录列表（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	R getShareBenefitEposList(Map<String, Object> map);
	
	/**
	 * 机器返现记录列表（传统POS）
	 * @param map
	 * @return
	 */
	R getMachineBackTraditionalPosList(Map<String, Object> map);

	/**
	 * 机器返现记录列表（传统POS）
	 * @param map
	 * @return
	 */
	R getMachineBackEposList(Map<String, Object> map);
	
	/**
	 * 活动奖励记录列表（传统POS）
	 * @param map
	 * @return
	 */
	R getActivityRewardTraditionalPosList(Map<String, Object> map);

	/**
	 * 活动奖励记录列表（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	R getActivityRewardEposList(Map<String, Object> map);
	
	/**
	 * 扣除列表（传统POS）
	 * @param map
	 * @return
	 */
	R getDeductTraditionalPosList(Map<String, Object> map);

	/**
	 * 扣除列表（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	R getDeductEposList(Map<String, Object> map);
	
	/**
	 * 分润记录列表（MPOS）
	 * @param map
	 * @return
	 */
	R getShareBenefitMposList(Map<String, Object> map);
	
	/**
	 * 机器返现记录列表（MPOS）
	 * @param map
	 * @return
	 */
	R getMachineBackMposList(Map<String, Object> map);
	
	/**
	 * 活动奖励记录列表（MPOS）
	 * @param map
	 * @return
	 */
	R getActivityRewardMposList(Map<String, Object> map);
	
	/**
	 * 扣除列表（MPOS）
	 * @param map
	 * @return
	 */
	R getDeductMposList(Map<String, Object> map);
}

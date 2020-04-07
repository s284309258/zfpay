package com.example.longecological.service.reportforms;

import java.util.Map;

import com.example.longecological.entity.R;

public interface ReportFormsService {

	/**
	 * 报表中心首页
	 * @param map
	 * @return
	 */
	R getHomePageInfo(Map<String, Object> map);
	
	/**
	 * 代理每天详情（传统POS）
	 * @param map
	 * @return
	 */
	R getDayAgencyTraditionalPosDetail(Map<String, Object> map);

	/**
	 * 代理每天详情（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	R getDayAgencyEposDetail(Map<String, Object> map);
	
	/**
	 * 代理每月详情（传统POS）
	 * @param map
	 * @return
	 */
	R getMonthAgencyTraditionalPosDetail(Map<String, Object> map);

	/**
	 * 代理每月详情（传统POS）
	 * @param map
	 * @return
	 */
	R getMonthAgencyEposDetail(Map<String, Object> map);
	
	/**
	 * 商户每天详情（传统POS）
	 * @param map
	 * @return
	 */
	R getDayMerchantTraditionalPosDetail(Map<String, Object> map);

	/**
	 * 商户每天详情（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	R getDayMerchantEposDetail(Map<String, Object> map);
	
	/**
	 * 商户每月详情（传统POS）
	 * @param map
	 * @return
	 */
	R getMonthMerchantTraditionalPosDetail(Map<String, Object> map);

	/**
	 * 商户每月详情（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	R getMonthMerchantEposDetail(Map<String, Object> map);
	
	/**
	 * 代理每天走势列表（传统POS）
	 * @param map
	 * @return
	 */
	R getDayAgencyTraditionalPosList(Map<String, Object> map);
	
	/**
	 * 代理每月走势列表（传统POS）
	 * @param map
	 * @return
	 */
	R getMonthAgencyTraditionalPosList(Map<String, Object> map);
	
	/**
	 * 商户每天走势列表（传统POS）
	 * @param map
	 * @return
	 */
	R getDayMerchantTraditionalPosList(Map<String, Object> map);
	
	/**
	 * 商户每月走势列表（传统POS）
	 * @param map
	 * @return
	 */
	R getMonthMerchantTraditionalPosList(Map<String, Object> map);
	
	
	
	/**
	 * 代理每天详情（MPOS）
	 * @param map
	 * @return
	 */
	R getDayAgencyMposDetail(Map<String, Object> map);
	
	/**
	 * 代理每月详情（MPOS）
	 * @param map
	 * @return
	 */
	R getMonthAgencyMposDetail(Map<String, Object> map);
	
	/**
	 * 商户每天详情（MPOS）
	 * @param map
	 * @return
	 */
	R getDayMerchantMposDetail(Map<String, Object> map);
	
	/**
	 * 商户每月详情（MPOS）
	 * @param map
	 * @return
	 */
	R getMonthMerchantMposDetail(Map<String, Object> map);
	
	/**
	 * 代理每天走势列表（MPOS）
	 * @param map
	 * @return
	 */
	R getDayAgencyMposList(Map<String, Object> map);
	
	/**
	 * 代理每月走势列表（MPOS）
	 * @param map
	 * @return
	 */
	R getMonthAgencyMposList(Map<String, Object> map);
	
	/**
	 * 商户每天走势列表（MPOS）
	 * @param map
	 * @return
	 */
	R getDayMerchantMposList(Map<String, Object> map);
	
	/**
	 * 商户每月走势列表（MPOS）
	 * @param map
	 * @return
	 */
	R getMonthMerchantMposList(Map<String, Object> map);
	
}

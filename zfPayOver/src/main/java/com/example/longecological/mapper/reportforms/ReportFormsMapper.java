package com.example.longecological.mapper.reportforms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ReportFormsMapper {

	/***
	 * 得到本日新增商户人数 add byqh 201912
	 * @param user_id
	 * @return
	 */
	@Select("select IFNULL(cast(merchant_trade_num+agency_trade_num as char),'0') as act_num_day from t_summary_user_traditional_pos_benefit_everyday where user_id=#{user_id} and pos_type is null and cre_date=CURRENT_DATE")
	String getHomePageInfoTraditionalActNumDay(@Param("user_id") String user_id);

	/***
	 * 得到本日新增商户人数 add byqh 201912
	 * @param user_id
	 * @return
	 */
	@Select("select IFNULL(cast(merchant_trade_num+agency_trade_num as char),'0') as act_num_day from t_summary_user_traditional_pos_benefit_everyday where user_id=#{user_id} and pos_type='epos' and cre_date=CURRENT_DATE")
	String getHomePageInfoEposActNumDay(@Param("user_id") String user_id);

	/***
	 * 得到本日新增商户人数 add byqh 201912
	 * @param user_id
	 * @return
	 */
	@Select("select IFNULL(cast(merchant_trade_num+agency_trade_num as char),'0') as act_num_day from t_summary_user_mpos_benefit_everyday where user_id=#{user_id} and cre_date=CURRENT_DATE")
	String getHomePageInfoMPOSActNumDay(@Param("user_id") String user_id);
	
	/**
	 * 报表中心-首页传统POS
	 * @param map
	 * @return
	 */
	Map<String, Object> getHomePageInfoByTraditionalPos(@Param("map") Map<String, Object> map);

	/**
	 * 报表中心-首页传统POS
	 * @param map
	 * @return
	 */
	Map<String, Object> getHomePageInfoByEpos(@Param("map") Map<String, Object> map);
	
	/**
	 * 报表中心-首页MPOS
	 * @param map
	 * @return
	 */
	Map<String, Object> getHomePageInfoByMpos(@Param("map") Map<String, Object> map);
	
	/**
	 * 代理每天详情（传统POS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getDayAgencyTraditionalPosDetail(@Param("map") Map<String, Object> map);

	/**
	 * 代理每天详情（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	Map<String, Object> getDayAgencyEposDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 代理每月详情（传统POS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getMonthAgencyTraditionalPosDetail(@Param("map") Map<String, Object> map);

	/**
	 * 代理每月详情（传统POS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getMonthAgencyEposDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 商户每天详情（传统POS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getDayMerchantTraditionalPosDetail(@Param("map") Map<String, Object> map);

	/**
	 * 商户每天详情（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	Map<String, Object> getDayMerchantEposDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 商户每月详情（传统POS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getMonthMerchantTraditionalPosDetail(@Param("map") Map<String, Object> map);

	/**
	 * 商户每月详情（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	Map<String, Object> getMonthMerchantEposDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 代理每天走势列表（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getDayAgencyTraditionalPosList(@Param("map") Map<String, Object> map);
	
	/**
	 * 代理每月走势列表（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMonthAgencyTraditionalPosList(@Param("map") Map<String, Object> map);
	
	/**
	 * 商户每天走势列表（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getDayMerchantTraditionalPosList(@Param("map") Map<String, Object> map);
	
	/**
	 * 商户每月走势列表（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMonthMerchantTraditionalPosList(@Param("map") Map<String, Object> map);
	
	/**
	 * 代理每天详情（MPOS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getDayAgencyMposDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 代理每月详情（MPOS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getMonthAgencyMposDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 商户每天详情（MPOS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getDayMerchantMposDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 商户每月详情（MPOS）
	 * @param map
	 * @return
	 */
	Map<String, Object> getMonthMerchantMposDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 代理每天走势列表（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getDayAgencyMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 代理每月走势列表（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMonthAgencyMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 商户每天走势列表（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getDayMerchantMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 商户每月走势列表（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMonthMerchantMposList(@Param("map") Map<String, Object> map);

}

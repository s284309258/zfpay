package com.example.longecological.service.merchant;

import java.util.Map;

import com.example.longecological.entity.R;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Update;

public interface MerchantManageService {

	/**
	 * 商户管理汇总列表（传统POS）
	 * @return
	 */
	R getSummaryTraditionalPosList(Map<String, Object> map);
	
	/**
	 * 商户管理汇总列表（MPOS）
	 * @return
	 */
	R getSummaryMposList(Map<String, Object> map);
	
	/**
	 * 全部商户列表查询（传统POS）
	 * @param map
	 * @return
	 */
	R getAllMerchantTraditionalPosList(Map<String, Object> map);


	/***
	 * 更新商户姓名和电话 add byqh 201912
	 * @param map
	 * @return
	 */
	R updateMerchantNameAndTel(Map<String,Object> map);
	
	/**
	 * 优质商户列表查询（传统POS）
	 * @param map
	 * @return
	 */
	R getExcellentMerchantTraditionalPosList(Map<String, Object> map);
	
	/**
	 * 活跃商户列表查询（传统POS）
	 * @param map
	 * @return
	 */
	R getActiveMerchantTraditionalPosList(Map<String, Object> map);
	
	/**
	 * 休眠商户列表查询（传统POS）
	 * @param map
	 * @return
	 */
	R getDormantMerchantTraditionalPosList(Map<String, Object> map);
	
	/**
	 * 全部商户列表查询（MPOS）
	 * @param map
	 * @return
	 */
	R getAllMerchantMposList(Map<String, Object> map);
	
	/**
	 * 优质商户列表查询（MPOS）
	 * @param map
	 * @return
	 */
	R getExcellentMerchantMposList(Map<String, Object> map);
	
	/**
	 * 活跃商户列表查询（MPOS）
	 * @param map
	 * @return
	 */
	R getActiveMerchantMposList(Map<String, Object> map);
	
	/**
	 * 休眠商户列表查询（MPOS）
	 * @param map
	 * @return
	 */
	R getDormantMerchantMposList(Map<String, Object> map);
	
	/**
	 * 查询商户详情（传统POS）
	 * @param map
	 * @return
	 */
	R getTraditionalPosDetail(Map<String, Object> map);

	/**
	 * 查询商户详情（传统POS）add byqh202003
	 * @param map
	 * @return
	 */
	R getTraditionalPosTradeDetail(Map<String, Object> map);




	//查询商户排名10 byqh202006
	R getTradeVolumeRankByMonth(Map<String, Object> map);
	R getTradeVolumeRankByDay(Map<String, Object> map);




	R getMposTradeVolumeRankByMonth(Map<String, Object> map);
	R getMposTradeVolumeRankByDay(Map<String, Object> map);
	R getTraposTradeVolumeRankByMonth(Map<String, Object> map);
	R getTraposTradeVolumeRankByDay(Map<String, Object> map);
	//查询商户排名10 byqh202006



	/**
	 * 查询商户详情（MPOS）
	 * @param map
	 * @return
	 */
	R getMposDetail(Map<String, Object> map);
	
	/**
	 * 查询直推代理
	 * @param map
	 * @return
	 */
	R getReferAgencyList(Map<String, Object> map);

	/***
	 * add byqh 201912
	 * 查询完成政策考核的商户
	 * @param map
	 * @return
	 */
	R getCompletePolicyMerchantTraditionalPos(Map<String, Object> map);

	/***
	 * add byqh 201912
	 * 查询完成政策考核的商户
	 * @param map
	 * @return
	 */
	R getCompletePolicyMerchantMPos(Map<String, Object> map);

	
	/**
	 * 查询代理头部信息（传统POS）
	 * @param map
	 * @return
	 */
	R getReferAgencyHeadTraditionalPosInfo(Map<String, Object> map);
	
	/**
	 * 查询代理商户列表（传统POS）
	 * @param map
	 * @return
	 */
	R getReferAgencyTraditionalPosList(Map<String, Object> map);
	
	/**
	 * 查询代理头部信息（MPOS）
	 * @param map
	 * @return
	 */
	R getReferAgencyHeadMposInfo(Map<String, Object> map);
	
	/**
	 * 查询代理商户列表（MPOS）
	 * @param map
	 * @return
	 */
	R getReferAgencyMposList(Map<String, Object> map);
	
	/**
	 * 查询直推人数
	 * @param map
	 * @return
	 */
	R getReferAgencyNum(Map<String, Object> map);


	/***
	 * 得到传统POS交易额和月均交易额 add byqh 201912
	 * @param map
	 * @return
	 */
	R getReferAgencyTraditionalPosTradeAmountAvg(Map<String, Object> map);

	/***
	 * 得到传统POS交易额和月均交易额 add byqh 201912
	 * @param map
	 * @return
	 */
	R getReferAgencyEposTradeAmountAvg(Map<String, Object> map);


	/***
	 * 得到MPOS交易额和月均交易额 add byqh 201912
	 * @param map
	 * @return
	 */
	R getReferAgencyMPosTradeAmountAvg(Map<String, Object> map);
}

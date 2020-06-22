package com.ruoyi.project.develop.task.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TransactionDataProcessingMapper {
	
	/**
	 * 查询传统POS机待处理数据
	 * @return
	 */
	List<Map<String, Object>> getTraposTradeList();
	
	/**
	 * 更新待处理交易数据状态(传统POS)
	 * @param map
	 * @return
	 */
	int updateTraposTransactionRecordDealStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询POS系统参数(传统POS)
	 * @param sn
	 * @return
	 */
	Map<String, Object> getTraposDetailInfo(@Param("sn") String sn);
	
	/**
	 * 查询直属会员分润信息(传统POS)
	 * @param sn
	 * @return
	 */
	Map<String, Object> getTraposMerchantInfo(@Param("sn") String sn);
	
	/**
	 * 查询代理分润信息(传统POS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraposAgencyList(@Param("map") Map<String, Object> map);

	/**
	 * 记录传统POS分润记录
	 * @param map
	 * @return
	 */
	int insertUserTraposShareBenefitRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 记录收益通知
	 * @param map
	 * @return
	 */
	int insertUserMessageInfo(@Param("map") Map<String, Object> map);
	
	/**
	 * 交易状态更新(传统POS)
	 * @param map
	 * @return
	 */
	int updateTraposTradeStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 汇总直属商户交易额(传统POS)
	 * @param map
	 * @return
	 */
	int updateSummaryTraditionalPosMerchantPerformance(@Param("map") Map<String, Object> map);

	/**
	 * 汇总直属商户交易额(传统POS)
	 * @param map
	 * @return
	 */
	int updateSummaryEposMerchantPerformance(@Param("map") Map<String, Object> map);
	
	/**
	 * 汇总直属会员交易额(传统POS)
	 * @param map
	 * @return
	 */
	int updateSummaryTraposUserMerchantPerformance(@Param("map") Map<String, Object> map);

	/**
	 * 汇总直属会员交易额(传统POS)add byqh202003
	 * @param map
	 * @return
	 */
	int updateSummaryEposUserMerchantPerformance(@Param("map") Map<String, Object> map);
	
	/**
	 * 汇总代理商户交易额(传统POS)
	 * @param map
	 * @return
	 */
	int updateSummaryTraditionalPosAgencyPerformance(@Param("map") Map<String, Object> map);

	/**
	 * 汇总代理商户交易额(传统POS)
	 * @param map
	 * @return
	 */
	int updateSummaryEposAgencyPerformance(@Param("map") Map<String, Object> map);
	
	/**
	 * 汇总代理会员交易额(传统POS)
	 * @param map
	 * @return
	 */
	int updateSummaryTraposUserAgencyPerformance(@Param("map") Map<String, Object> map);

	/**
	 * 汇总代理会员交易额(传统POS)add byqh202003
	 * @param map
	 * @return
	 */
	int updateSummaryEposUserAgencyPerformance(@Param("map") Map<String, Object> map);

	
	/**
	 * 移入正式表(传统POS)
	 * @param map
	 * @return
	 */
	int backupTraposTransactionRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 删除待处理表交易数据(传统POS)
	 * @param map
	 * @return
	 */
	int deleteTraposTransactionRecordDeal(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询MPOS机待处理数据
	 * @return
	 */
	List<Map<String, Object>> getMposTradeList();
	
	/**
	 * 更新待处理交易数据状态(MPOS)
	 * @param map
	 * @return
	 */
	int updateMposTransactionRecordDealStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询POS系统参数(MPOS)
	 * @param sn
	 * @return
	 */
	Map<String, Object> getMposDetailInfo(@Param("sn") String sn);
	
	/**
	 * 查询直属会员分润信息(MPOS)
	 * @param sn
	 * @return
	 */
	Map<String, Object> getMposMerchantInfo(@Param("sn") String sn);
	
	/**
	 * 查询代理分润信息(MPOS)
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposAgencyList(@Param("map") Map<String, Object> map);
	
	/**
	 * 记录MPOS分润记录
	 * @param map
	 * @return
	 */
	int insertUserMposShareBenefitRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 交易状态更新(MPOS)
	 * @param map
	 * @return
	 */
	int updateMposTradeStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 汇总直属商户交易额(MPOS)
	 * @param map
	 * @return
	 */
	int updateSummaryMposMerchantPerformance(@Param("map") Map<String, Object> map);
	
	/**
	 * 汇总直属会员交易额(MPOS)
	 * @param map
	 * @return
	 */
	int updateSummaryMposUserMerchantPerformance(@Param("map") Map<String, Object> map);
	
	/**
	 * 汇总代理商户交易额(MPOS)
	 * @param map
	 * @return
	 */
	int updateSummaryMposAgencyPerformance(@Param("map") Map<String, Object> map);
	
	/**
	 * 汇总代理会员交易额(MPOS)
	 * @param map
	 * @return
	 */
	int updateSummaryMposUserAgencyPerformance(@Param("map") Map<String, Object> map);
	
	/**
	 * 移入正式表(MPOS)
	 * @param map
	 * @return
	 */
	int backupMposTransactionRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 删除待处理表交易数据(MPOS)
	 * @param map
	 * @return
	 */
	int deleteMposTransactionRecordDeal(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询传统POS机待处理返现数据
	 * @return
	 */
	List<Map<String, Object>> getTraposMachineBackList();
	
	/**
	 * 更新待处理返现数据状态(传统POS)
	 * @param map
	 * @return
	 */
	int updateTraposMachineBackDealStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新返现状态（传统POS）
	 * @param map
	 * @return
	 */
	int updateTraposCashBackStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 记录传统POS返现记录
	 * @param map
	 * @return
	 */
	int insertUserTraposMachineBackRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 移入返现至正式表(传统POS)
	 * @param map
	 * @return
	 */
	int backupTraposMachineBackRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 删除待处理表返现记录（传统POS）
	 * @param map
	 * @return
	 */
	int deleteTraposMachineBackRecordDeal(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询MPOS机待处理返现数据
	 * @return
	 */
	List<Map<String, Object>> getMposMachineBackList();
	
	/**
	 * 更新待处理返现数据状态(MPOS)
	 * @param map
	 * @return
	 */
	int updateMposMachineBackDealStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新返现状态（MPOS）
	 * @param map
	 * @return
	 */
	int updateMposCashBackStatus(@Param("map") Map<String, Object> map);
	
	/**
	 * 移入返现至正式表(MPOS)
	 * @param map
	 * @return
	 */
	int insertUserMposMachineBackRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 移入返现至正式表(MPOS)
	 * @param map
	 * @return
	 */
	int backupMposMachineBackRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 删除待处理表返现记录（MPOS）
	 * @param map
	 * @return
	 */
	int deleteMposMachineBackRecordDeal(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新商户名称(传统POS)
	 * @param map
	 * @return
	 */
	int updateTraposMerName(@Param("map") Map<String, Object> map);

}

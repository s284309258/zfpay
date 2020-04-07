package com.ruoyi.project.develop.task.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface ZhongFuDataAcquireMapper {

	/**
	 * 查询待处理的账号列表
	 * @return
	 */
	List<Map<String, Object>> getManagerAccountList();
	
	/**
	 * 判断传统POS机是否导入系统
	 * @param map
	 * @return
	 */
	int checkTransactionPosExists(@Param("map")Map<String, Object> map);


	/***
	 * 增加被扫入库byqh
	 * @param merId
	 * @return
	 */
	@Select("select max(sn) from t_sys_traditional_pos_info where mer_id=#{merId}")
	@ResultType(String.class)
	String getTranscationPosSnByMerID(@Param("merId") String merId);
	
	/**
	 * 将传统POS交易记录插入待处理表
	 * @param map
	 * @return
	 */
	int insertDataTraposTransactionRecordDeal(@Param("map")Map<String, Object> map);
	
	/**
	 * 判断MPOS机是否导入系统
	 * @param map
	 * @return
	 */
	int checkMposExists(@Param("map")Map<String, Object> map);
	
	/**
	 * 将MPOS交易记录插入待处理表
	 * @param map
	 * @return
	 */
	int insertDataMposTransactionRecordDeal(@Param("map")Map<String, Object> map);
	
	/**
	 * 更新账号交易时间
	 * @param map
	 * @return
	 */
	int updateSysUserAccountTranTime(@Param("map")Map<String, Object> map);
	
	/**
	 * 查询账号详情
	 * @param account_id
	 * @return
	 */
	Map<String, Object> getManagerAccountDetail(@Param("account_id")String account_id);
	
	/**
	 * 删除传统POS政策信息
	 * @param account_id
	 * @return
	 */
	int deleteDataTraposPolicyRecord(@Param("account_id")String account_id);
	
	/**
	 * 删除MPOS政策信息
	 * @param account_id
	 * @return
	 */
	int deleteDataMposPolicyRecord(@Param("account_id")String account_id);
	
	/**
	 * 保存传统POS政策信息
	 * @param map
	 * @return
	 */
	int insertDataTraposPolicyRecord(@Param("map")Map<String, Object> map);
	
	/**
	 * 保存MPOS政策信息
	 * @param map
	 * @return
	 */
	int insertDataMposPolicyRecord(@Param("map")Map<String, Object> map);
	
	/**
	 * 获取传统POS政策信息列表
	 * @return
	 */
	List<Map<String, Object>> getTraposPolicyList();
	
	/**
	 * 获取MPOS政策信息列表
	 * @return
	 */
	List<Map<String, Object>> getMposPolicyList();
	
	/**
	 * 保存传统POS返现记录
	 * @param map
	 * @return
	 */
	int insertTransposDataMachineCashbackRecordDeal(@Param("map")Map<String, Object> map);
	
	/**
	 * 保存MPOS返现记录
	 * @param map
	 * @return
	 */
	int insertMposDataMachineCashbackRecordDeal(@Param("map")Map<String, Object> map);
	
	/**
	 * 未激活POS机列表（传统POS）
	 * @return
	 */
	List<Map<String, Object>> getTraposUnactivatedStateList();
	
	/**
	 * 未激活POS机列表（MPOS）
	 * @return
	 */
	List<Map<String, Object>> getMposUnactivatedStateList();
	
	/**
	 * 更新POS机激活状态（传统POS）
	 * @param map
	 * @return
	 */
	int updateTraposActivatedState(@Param("map")Map<String, Object> map);
	
	/**
	 * 更新POS机激活状态（MPOS）
	 * @param map
	 * @return
	 */
	int updateMposActivatedState(@Param("map")Map<String, Object> map);
	
	/**
	 * 更新商户激活数量(传统POS)
	 * @param map
	 * @return
	 */
	int updateSummaryUserTraditionalPosMerchantActNum(@Param("map")Map<String, Object> map);

	/**
	 * 更新商户激活数量(传统POS)
	 * @param map
	 * @return
	 */
	int updateSummaryUserEposMerchantActNum(@Param("map")Map<String, Object> map);
	
	/**
	 * 更新代理激活数量(传统POS)
	 * @param map
	 * @return
	 */
	int updateSummaryUserTraditionalPosAgencyActNum(@Param("map")Map<String, Object> map);

	/**
	 * 更新代理激活数量(传统POS)
	 * @param map
	 * @return
	 */
	int updateSummaryUserEposAgencyActNum(@Param("map")Map<String, Object> map);
	
	/**
	 * 更新商户激活数量(MPOS)
	 * @param map
	 * @return
	 */
	int updateSummaryUserMposMerchantActNum(@Param("map")Map<String, Object> map);
	
	/**
	 * 更新代理激活数量(MPOS)
	 * @param map
	 * @return
	 */
	int updateSummaryUserMposAgencyActNum(@Param("map")Map<String, Object> map);
	
	/**
	 * 查询POS机用户列表（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraposUserList(@Param("map")Map<String, Object> map);
	
	/**
	 * 查询POS机用户列表（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposUserList(@Param("map")Map<String, Object> map);
}

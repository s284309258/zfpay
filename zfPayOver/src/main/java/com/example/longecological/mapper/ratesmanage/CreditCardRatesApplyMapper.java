package com.example.longecological.mapper.ratesmanage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CreditCardRatesApplyMapper {

	/**
	 * 申请传统POS机列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getApplyRateTraditionalPosList(@Param("map") Map<String, Object> map);
	
	/**
	 * 申请MPOS机列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getApplyRateMposList(@Param("map") Map<String, Object> map);

	/**
	 * add byqh202003
	 * @param map
	 * @return
	 */
	@Select("select sum(cnt) as cnt from (" +
			" select count(*) as cnt from t_user_apply_cardrate_trapos_record_info where find_in_set(sn,#{map.sn_list}) and `status`='00'" +
			" union all" +
			" select count(*) as cnt from t_user_apply_cardrate_mpos_record_info where find_in_set(sn,#{map.sn_list}) and `status`='00'" +
			" ) tb")
	int getApplyRateRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 新增费率申请记录（传统POS）
	 * @param map
	 * @return
	 */
	int addApplyRateTraditionalPos(@Param("map") Map<String, Object> map);
	
	/**
	 * 新增费率申请记录（MPOS）
	 * @param map
	 * @return
	 */
	int addApplyRateMpos(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询费率申请记录（传统POS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getApplyRateTraditionalPosRecordList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询费率申请记录（MPOS）
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getApplyRateMposRecordList(@Param("map") Map<String, Object> map);
}

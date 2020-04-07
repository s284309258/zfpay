package com.example.longecological.mapper.traditionalpos;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TraditionalPosMapper {

	/**
	 * 查询需要申请扫码支付的传统POS机列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getScanTraditionalPosList(@Param("map") Map<String, Object> map);
	
	/**
	 * 新增申请扫码支付记录
	 * @param map
	 * @return
	 */
	int addApplyScanRecord(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询扫码支付申请记录
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getApplyScanRecordList(@Param("map") Map<String, Object> map);

	/***
	 * 询代理商的结算价格等信息byqh
	 * @param map
	 * @return
	 */
	Map<String,Object> getUserTraditionalPosInfoAll(@Param("map") Map<String,Object> map);


	/***
	 * 得到SN直属用户 add byqh 201912
	 * @param sn
	 * @return
	 */
	List<Map<String,Object>> getUserTraditionalPosBelongBySN(@Param("sn_list") String sn);
}

package com.example.longecological.mapper.mpos;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MposMapper {

	/**
	 * 查询POS机列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMposList(@Param("map") Map<String, Object> map);
	
	/**
	 * 获取用户MPOS信息
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserMpos(@Param("map") Map<String, Object> map);
	
	/**
	 * 修改商户信息
	 * @param map
	 * @return
	 */
	int editMposMerInfo(@Param("map") Map<String, Object> map);

	/***
	 * 询代理商的结算价格等信息byqh
	 * @param map
	 * @return
	 */
	Map<String,Object> getUserMposInfoAll(@Param("map") Map<String,Object> map);

	/***
	 * 得到SN直属用户 add byqh 201912
	 * @param sn
	 * @return
	 */
	List<Map<String,Object>> getUserMposBelongBySN(@Param("sn_list") String sn);
}

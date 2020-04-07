package com.example.longecological.mapper.moneylocker;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MoneyLockerCollegeMapper {
	
	/**
	 * 查询钱柜列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMoneyLockerCollegeList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询钱柜详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getMoneyLockerCollegeDetail(@Param("map") Map<String, Object> map);
 
}

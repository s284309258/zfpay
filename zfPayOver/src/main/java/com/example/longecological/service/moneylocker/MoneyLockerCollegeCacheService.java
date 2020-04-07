package com.example.longecological.service.moneylocker;

import java.util.List;
import java.util.Map;

public interface MoneyLockerCollegeCacheService {

	/**
	 * 查询钱柜学院列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMoneyLockerCollegeList(Map<String, Object> map);
	
	/**
	 * 查询钱柜学院详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getMoneyLockerCollegeDetail(Map<String, Object> map);
}

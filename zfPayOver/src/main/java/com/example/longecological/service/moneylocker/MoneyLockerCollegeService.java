package com.example.longecological.service.moneylocker;

import java.util.Map;

import com.example.longecological.entity.R;

public interface MoneyLockerCollegeService {

	/**
	 * 查询钱柜学院列表
	 * @param map
	 * @return
	 */
	R getMoneyLockerCollegeList(Map<String, Object> map);
	
	/**
	 * 查询钱柜学院详情
	 * @param map
	 * @return
	 */
	R getMoneyLockerCollegeDetail(Map<String, Object> map);
}

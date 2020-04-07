package com.example.longecological.service.moneylocker.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheService.CacheOperation;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.mapper.moneylocker.MoneyLockerCollegeMapper;
import com.example.longecological.service.moneylocker.MoneyLockerCollegeCacheService;

@Service
public class MoneyLockerCollegeCacheServiceImpl implements MoneyLockerCollegeCacheService {
	
	@Autowired
	private MoneyLockerCollegeMapper moneyLockerCollegeMapper;

	/**
	 * 钱柜学院列表
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_money_locker_college_list,
			fieldKey="#map['last_id']", cacheOperation=CacheOperation.QUERY)
	@Override
	public List<Map<String, Object>> getMoneyLockerCollegeList(Map<String, Object> map) {
		return moneyLockerCollegeMapper.getMoneyLockerCollegeList(map);
	}

	/**
	 * 钱柜学院详情
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_money_locker_college,
			fieldKey="#map['money_locker_id']", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getMoneyLockerCollegeDetail(Map<String, Object> map) {
		return moneyLockerCollegeMapper.getMoneyLockerCollegeDetail(map);
	}

}

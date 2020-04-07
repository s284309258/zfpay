package com.example.longecological.service.moneylocker.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.moneylocker.MoneyLockerCollegeCacheService;
import com.example.longecological.service.moneylocker.MoneyLockerCollegeService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.StringUtil;

@Service
public class MoneyLockerCollegeServiceImpl implements MoneyLockerCollegeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MoneyLockerCollegeServiceImpl.class);
	
	@Autowired
	private MoneyLockerCollegeCacheService moneyLockerCollegeCacheService;
	
	/**
	 * 查询钱柜学院列表
	 */
	@Override
	public R getMoneyLockerCollegeList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//默认查询第一页
			if(StringUtils.isEmpty(StringUtil.getMapValue(map, "last_id"))) {
				map.put("last_id", "0");
			}
			List<Map<String, Object>> moneyLockerCollegeList = moneyLockerCollegeCacheService.getMoneyLockerCollegeList(map);
			respondMap.put("moneyLockerCollegeList", moneyLockerCollegeList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MoneyLockerCollegeServiceImpl -- getMoneyLockerCollegeList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询钱柜学院详情
	 */
	@Override
	public R getMoneyLockerCollegeDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> moneyLockerCollege = moneyLockerCollegeCacheService.getMoneyLockerCollegeDetail(map);
			respondMap.put("moneyLockerCollege", moneyLockerCollege);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("MoneyLockerCollegeServiceImpl -- getMoneyLockerCollegeDetail方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.error(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

}

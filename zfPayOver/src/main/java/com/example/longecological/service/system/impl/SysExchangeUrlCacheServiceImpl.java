package com.example.longecological.service.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheService.CacheOperation;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.mapper.system.SysExchangeUrlMapper;
import com.example.longecological.service.system.SysExchangeUrlCacheService;


/**
 * 交易所行情相关
 * @author Administrator
 *
 */
@Service
public class SysExchangeUrlCacheServiceImpl implements SysExchangeUrlCacheService {
	
	@Autowired
	SysExchangeUrlMapper sysExchangeUrlMapper;

	
	/**
	 * 查询行情URL信息列表
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_exchange_url,
			fieldKey="", cacheOperation=CacheOperation.QUERY)
	@Override
	public List<Map<String, Object>> getExchangeUrlList(Map<String, Object> map) {
		return sysExchangeUrlMapper.getExchangeUrlList(map);
	}
	

}

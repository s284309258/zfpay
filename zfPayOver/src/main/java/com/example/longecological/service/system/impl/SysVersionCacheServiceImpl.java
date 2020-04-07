package com.example.longecological.service.system.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheService.CacheOperation;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.system.SysVersionMapper;
import com.example.longecological.service.system.SysVersionCacheService;


/**
 * 系统版本相关
 * @author Administrator
 *
 */
@Service
public class SysVersionCacheServiceImpl implements SysVersionCacheService {
	
	@Autowired
	SysVersionMapper sysVersionMapper;

	
	/**
	 * 查询最新版本
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_version_info_new,
			fieldKey="#map['device_type']", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getNewVersion(Map<String, Object> map) {
		return sysVersionMapper.getNewVersion(map);
	}

	

}

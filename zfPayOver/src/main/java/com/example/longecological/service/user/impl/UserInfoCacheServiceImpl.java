package com.example.longecological.service.user.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheService.CacheOperation;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.mapper.user.UserInfoMapper;
import com.example.longecological.service.user.UserInfoCacheService;

@Service
public class UserInfoCacheServiceImpl implements UserInfoCacheService {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	

	
	/**
	 * 通过用户ID查询用户缓存信息
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_user_info_id,
			fieldKey="#user_id", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getUserInfoCacheById(String user_id) {
		return userInfoMapper.getUserInfoCacheById(user_id);
	}
	
	/**
	 * 通过用户ID查询冻结用户信息
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_user_freeze,
			fieldKey="#user_id", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getUserFreezeCacheById(String user_id){
		return userInfoMapper.getUserFreezeCacheById(user_id);
	}
	

}

package com.example.longecological.service.user;

import java.util.List;
import java.util.Map;

public interface UserInfoCacheService {

	/**
	 * 通过用户ID查询用户缓存信息
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserInfoCacheById(String user_id);
	
	/**
	 * 通过用户ID查询冻结信息
	 * @param map
	 * @return
	 */
	Map<String, Object> getUserFreezeCacheById(String user_id);
	

}

package com.example.longecological.service.system;

import java.util.Map;


/**
 * 系统版本相关
 * @author Administrator
 *
 */
public interface SysVersionCacheService {

	/**
	 * 查询系统最新版本
	 * @param map
	 * @return
	 */
	Map<String, Object> getNewVersion(Map<String, Object> map);

}

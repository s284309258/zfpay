package com.example.longecological.service.system;

import java.util.List;
import java.util.Map;


/**
 * 系统公告缓存相关
 * @author Administrator
 *
 */
public interface SysNoticeCacheService {


	/**
	 * 查询系统最新公告
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getNewNotice(Map<String, Object> map);

	
	/**
	 * 查询系统公告列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getNoticeList(Map<String, Object> map);

}

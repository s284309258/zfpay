package com.example.longecological.service.system;

import java.util.List;
import java.util.Map;


/**
 * 新闻资讯缓存相关
 * @author Administrator
 *
 */
public interface SysNewsCacheService {


	/**
	 * 查询新闻资讯列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getNewsList(Map<String, Object> map);

}

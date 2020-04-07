package com.example.longecological.service.system;

import java.util.List;
import java.util.Map;


/**
 * app图片相关
 * @author Administrator
 *
 */
public interface SysAppImgCacheService {


	/**
	 * 查询app图片列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getAppImgList(Map<String, Object> map);

}

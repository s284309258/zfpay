package com.example.longecological.service.system;

import java.util.List;
import java.util.Map;


/**
 * 交易所行情相关
 * @author Administrator
 *
 */
public interface SysExchangeUrlCacheService {


	/**
	 * 查询行情URL信息列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getExchangeUrlList(Map<String, Object> map);

}

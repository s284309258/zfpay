package com.example.longecological.service.system;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 交易所行情相关
 * @author Administrator
 *
 */
public interface SysExchangeUrlService {

	/**
	 * 查询行情URL信息列表
	 * @param map
	 * @return
	 */
	R getExchangeUrlList(Map<String, Object> map);

}

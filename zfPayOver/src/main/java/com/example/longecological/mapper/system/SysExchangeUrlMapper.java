package com.example.longecological.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 交易所行情相关
 * @author Administrator
 *
 */
public interface SysExchangeUrlMapper {

	
	/**
	 * 查询行情URL信息列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getExchangeUrlList(@Param("map") Map<String, Object> map);

	
}

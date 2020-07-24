package com.example.longecological.mapper.common;

import java.util.List;
import java.util.Map;

public interface SysParamRateMapper {

	/**
	 * 费率列表
	 * @param type
	 * @return
	 */
	List<Map<String, Object>> getRateListByType(String type,String pos_type);
	
	/**
	 * 费率表所有值
	 * @return
	 */
	List<Map<String, Object>> getSysParamRateAll();
}

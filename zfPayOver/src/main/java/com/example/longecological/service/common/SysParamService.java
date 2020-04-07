package com.example.longecological.service.common;

import java.util.Map;

/**
 * 参数管理
 * @author Administrator
 *
 */
public interface SysParamService {
	
	
	/**
	 * 根据参数代码查询参数值
	 * @param code
	 * @return
	 */
	String getParamByCode(String code);
	
	/**
	 * 
	 * @param order
	 * @return
	 */
	Map<String, Object> getSendEmailAccount(String order);
}

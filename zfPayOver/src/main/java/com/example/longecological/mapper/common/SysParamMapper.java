package com.example.longecological.mapper.common;

import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * 参数管理
 * @author Administrator
 *
 */
public interface SysParamMapper {

	/**
	 * 根据参数代码查询参数值
	 * @param code
	 * @return
	 */
	String getParamByCode(@Param("code") String code);
	
	/**
	 * 获取发送邮箱账号
	 * @param order
	 * @return
	 */
	Map<String, Object> getSendEmailAccount(@Param("order") String order);
	
}

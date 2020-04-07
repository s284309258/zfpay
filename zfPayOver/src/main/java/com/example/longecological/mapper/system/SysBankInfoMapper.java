package com.example.longecological.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 系统银行相关
 * @author Administrator
 *
 */
public interface SysBankInfoMapper {

	
	/**
	 * 模糊搜索银行列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> searchLikeBank(@Param("map") Map<String, Object> map);
	
}

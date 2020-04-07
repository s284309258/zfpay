package com.example.longecological.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 新闻资讯管理
 * @author Administrator
 *
 */
public interface SysNewsMapper {

	
	/**
	 * 查询新闻资讯列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getNewsList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询最新新闻列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getNewNews(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询资讯详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getNewsDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新浏览次数
	 * @param map
	 * @return
	 */
	int updateNewsBrowseNum(@Param("map") Map<String, Object> map);

	
}

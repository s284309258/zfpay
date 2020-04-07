package com.example.longecological.service.system;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 新闻资讯相关
 * @author Administrator
 *
 */
public interface SysNewsService {

	/**
	 * 查询新闻资讯列表
	 * @param map
	 * @return
	 */
	R getNewsList(Map<String, Object> map);
	
	/**
	 * 查询最新新闻资讯
	 * @param map
	 * @return
	 */
	R getNewNews(Map<String, Object> map);
	
	/**
	 * 查询最新新闻详情
	 * @param map
	 * @return
	 */
	R getNewsDetail(Map<String, Object> map);

}

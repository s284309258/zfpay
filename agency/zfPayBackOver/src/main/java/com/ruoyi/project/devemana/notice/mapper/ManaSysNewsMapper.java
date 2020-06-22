package com.ruoyi.project.devemana.notice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.notice.domain.ManaSysNews;

/**
 * 管理员=====新闻资讯 数据层
 * 
 * @author ruoyi
 */
public interface ManaSysNewsMapper
{

	/**
	 * 查询新闻资讯列表
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getManaSysNewsList(@Param("map") Map<String, Object> params);

	
	/**
	 * 查询新闻资讯详情
	 * @param id
	 * @return
	 */
	public ManaSysNews getManaNewsById(@Param("news_id") String id);

	
	/**
	 * 新增新闻资讯
	 * @param params
	 * @return
	 */
	public int addManaSysNews(@Param("map") Map<String, Object> params);

	
	/**
	 * 更新新闻资讯
	 * @param params
	 * @return
	 */
	public int updateManaSysNews(@Param("map") Map<String, Object> params);

	
	/**
	 * 删除新闻资讯
	 * @param notice_id
	 * @return
	 */
	public int deleteManaSysNews(@Param("news_id") String notice_id);


	/**
	 * 获取当前队列长度
	 * @return
	 */
	public int getManaSysNewsSize();
}
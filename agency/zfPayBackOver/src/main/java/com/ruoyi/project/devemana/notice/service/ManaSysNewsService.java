package com.ruoyi.project.devemana.notice.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.devemana.notice.domain.ManaSysNews;


/**
 * 管理员=====新闻资讯 服务层
 * @author Administrator
 *
 */
public interface ManaSysNewsService
{
   
    
    /**
     * 查询新闻资讯列表
     * @param params
     * @return
     */
    public List<Map<String, Object>> getManaSysNewsList(Map<String, Object> params);
    
    
    /**
     * 根据id查询新闻资讯详情
     * @param id
     * @return
     */
    public ManaSysNews getManaNewsById(String id);


    /**
     * 添加系统新闻资讯
     * @param params
     * @return
     */
	public R addManaSysNews(Map<String, Object> params);


	/**
	 * 修改保存新闻资讯
	 * @param params
	 * @return
	 */
	public R editManaSysNews(Map<String, Object> params);


	/**
	 * 批量删除系统新闻资讯
	 * @param ids
	 * @return
	 */
	public R batchRemoveManaSysNews(String ids);
}

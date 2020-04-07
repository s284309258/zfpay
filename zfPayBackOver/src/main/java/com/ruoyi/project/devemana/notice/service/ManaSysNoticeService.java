package com.ruoyi.project.devemana.notice.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.devemana.notice.domain.ManaSysNotice;


/**
 * 管理员====系统公告 服务层
 * @author Administrator
 *
 */
public interface ManaSysNoticeService
{
   
    
    /**
     * 查询公告列表
     * @param params
     * @return
     */
    public List<Map<String, Object>> getManaSysNoticeList(Map<String, Object> params);
    
    
    /**
     * 根据公告id查询公告详情
     * @param id
     * @return
     */
    public ManaSysNotice getManaNoticeById(String id);


    /**
     * 添加系统公告
     * @param params
     * @return
     */
	public R addManaSysNotice(Map<String, Object> params);


	/**
	 * 修改保存公告
	 * @param params
	 * @return
	 */
	public R editManaSysNotice(Map<String, Object> params);


	/**
	 * 批量删除系统公告
	 * @param ids
	 * @return
	 */
	public R batchRemoveManaSysNotice(String ids);
}

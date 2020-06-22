package com.ruoyi.project.devemana.notice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.notice.domain.ManaSysNotice;
import org.apache.ibatis.annotations.Update;

/**
 * 管理员====系统公告 数据层
 * 
 * @author ruoyi
 */
public interface ManaSysNoticeMapper
{

	/***
	 * 更新已读未读标志 add byqh 2010912
	 * @param map
	 * @return
	 */
	@Update("update t_news_read_state set read_flag=#{map.read_flag} where user_id=#{map.sys_user_id} and news_type=#{map.news_type}")
	int updateNewsReadFlag(@Param("map") Map<String,Object> map);

	/***
	 * 更新已读未读标志 add byqh 2010912
	 * @param map
	 * @return
	 */
	@Update("update t_news_read_state set read_flag=#{map.read_flag} where manager_id=#{map.manager_id} and news_type=#{map.news_type}")
	int updateNewsReadFlagManagerID(@Param("map") Map<String,Object> map);


	/**
	 * 查询公告列表
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getManaSysNoticeList(@Param("map") Map<String, Object> params);

	
	/**
	 * 查询公告详情
	 * @param id
	 * @return
	 */
	public ManaSysNotice getManaNoticeById(@Param("notice_id") String id);

	
	/**
	 * 新增公告
	 * @param params
	 * @return
	 */
	public int addManaSysNotice(@Param("map") Map<String, Object> params);

	
	/**
	 * 更新公告
	 * @param params
	 * @return
	 */
	public int updateManaSysNotice(@Param("map") Map<String, Object> params);

	
	/**
	 * 删除公告
	 * @param notice_id
	 * @return
	 */
	public int deleteManaSysNotice(@Param("notice_id") String notice_id);


	/**
	 * 获取当前队列长度
	 * @return
	 */
	public int getManaSysNoticeSize();
}
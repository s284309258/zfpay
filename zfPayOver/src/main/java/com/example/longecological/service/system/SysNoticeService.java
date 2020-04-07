package com.example.longecological.service.system;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 系统公告相关
 * @author Administrator
 *
 */
public interface SysNoticeService {

	/***
	 *得到未读消息add byqh 201912
	 * @param map
	 * @return
	 */
	R getUnReadNews(Map<String, Object> map);

	/***
	 * 更新已读未读标志 add byqh 201912
	 * @param map
	 * @return
	 */
	R updateNewsReadFlag(Map<String, Object> map);

	/**
	 * 查询系统最新公告
	 * @param map
	 * @return
	 */
	R getNewNotice(Map<String, Object> map);

	
	/**
	 * 查询系统公告列表
	 * @param map
	 * @return
	 */
	R getNoticeList(Map<String, Object> map);
	
	/**
	 * 查询系统公告详情
	 * @param map
	 * @return
	 */
	R getNoticeDetail(Map<String, Object> map);

}

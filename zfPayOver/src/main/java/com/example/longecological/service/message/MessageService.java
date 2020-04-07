package com.example.longecological.service.message;

import java.util.Map;

import com.example.longecological.entity.R;

public interface MessageService {

	/**
	 * 获取用户通知列表
	 * @param map
	 * @return
	 */
	R getMessageRecordList(Map<String, Object> map);
	
	/**
	 * 获取用户通知详情
	 * @param map
	 * @return
	 */
	R getMessageRecordDetail(Map<String, Object> map);
}

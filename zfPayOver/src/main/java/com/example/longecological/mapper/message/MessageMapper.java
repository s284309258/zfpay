package com.example.longecological.mapper.message;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MessageMapper {

	/**
	 * 用户通知记录列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getMessageRecordList(@Param("map") Map<String, Object> map);
	
	/**
	 * 用户通知记录详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getMessageRecordDetail(@Param("map") Map<String, Object> map);
}

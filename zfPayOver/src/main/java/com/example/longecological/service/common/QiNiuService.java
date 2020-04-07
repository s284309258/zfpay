package com.example.longecological.service.common;

import java.util.Map;

import com.example.longecological.entity.R;


public interface QiNiuService {

	
	/**
	 * 获取七牛云token值
	 * @param map
	 * @return
	 */
	R getQiNiuToken(Map<String, Object> map);
}

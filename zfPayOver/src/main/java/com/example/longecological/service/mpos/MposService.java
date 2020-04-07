package com.example.longecological.service.mpos;

import java.util.Map;

import com.example.longecological.entity.R;

public interface MposService {

	/**
	 * 获取MPOS列表
	 * @param map
	 * @return
	 */
	R getMposList(Map<String, Object> map);
	
	/**
	 * 修改商户信息
	 * @param map
	 * @return
	 */
	R editMposMerInfo(Map<String, Object> map);
}

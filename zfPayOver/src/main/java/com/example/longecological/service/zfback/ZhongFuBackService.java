package com.example.longecological.service.zfback;

import java.util.List;
import java.util.Map;

import com.example.longecological.entity.R;

public interface ZhongFuBackService {

	/**
	 * 处理进件中付回调
	 * @param map
	 * @return
	 */
	String merchantIntoCallback(Map<String, Object> map);
	
	/**
	 * 查询装机记录
	 * @param map
	 * @return
	 */
	R getTraditionalPosInstallList(Map<String, Object> map);
	
	/**
	 * 进件详情
	 * @param map
	 * @return
	 */
	R getTraditionalPosInstallDetail(Map<String, Object> map);
}

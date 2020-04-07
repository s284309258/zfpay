package com.example.longecological.service.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheService.CacheOperation;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.mapper.system.SysAppImgMapper;
import com.example.longecological.service.system.SysAppImgCacheService;


/**
 * app图片相关
 * @author Administrator
 *
 */
@Service
public class SysAppImgCacheServiceImpl implements SysAppImgCacheService {
	
	@Autowired
	SysAppImgMapper sysAppImgMapper;

	
	/**
	 * 查询app图片列表列表
	 */
//	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_app_img,
//			fieldKey="#map['img_type']", cacheOperation=CacheOperation.QUERY)
	@Override
	public List<Map<String, Object>> getAppImgList(Map<String, Object> map) {
		return sysAppImgMapper.getAppImgList(map);
	}
	

}

package com.example.longecological.service.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheService.CacheOperation;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.mapper.system.SysNoticeMapper;
import com.example.longecological.service.system.SysNoticeCacheService;
import com.example.longecological.utils.string.StringUtil;


/**
 * 系统公告缓存相关
 * @author Administrator
 *
 */
@Service
public class SysNoticeCacheServiceImpl implements SysNoticeCacheService {
	
	@Autowired
	SysNoticeMapper sysNoticeMapper;

	
	/**
	 * 查询最新公告
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_notice_info_new,
			fieldKey="", cacheOperation=CacheOperation.QUERY)
	@Override
	public List<Map<String, Object>> getNewNotice(Map<String, Object> map) {
		return sysNoticeMapper.getNewNotice(map);
	}

	
	/**
	 * 查询系统公告列表
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_notice_info_list,
			fieldKey="#map['pageNum']", cacheOperation=CacheOperation.QUERY)
	@Override
	public List<Map<String, Object>> getNoticeList(Map<String, Object> map) {
		map.put("start", (Integer.parseInt(StringUtil.getMapValue(map, "pageNum"))-1) * SysParamConstant.page_size);
		map.put("end", SysParamConstant.page_size);
		return sysNoticeMapper.getNoticeList(map);
	}

}

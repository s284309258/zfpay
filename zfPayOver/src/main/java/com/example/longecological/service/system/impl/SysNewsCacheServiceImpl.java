package com.example.longecological.service.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheService.CacheOperation;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.mapper.system.SysNewsMapper;
import com.example.longecological.service.system.SysNewsCacheService;
import com.example.longecological.utils.string.StringUtil;


/**
 * 系统新闻资讯相关
 * @author Administrator
 *
 */
@Service
public class SysNewsCacheServiceImpl implements SysNewsCacheService {
	
	@Autowired
	SysNewsMapper sysNewsMapper;

	
	/**
	 * 查询新闻资讯列表
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_news_info_list,
	fieldKey="#map['pageNum']", cacheOperation=CacheOperation.QUERY)
	@Override
	public List<Map<String, Object>> getNewsList(Map<String, Object> map) {
		map.put("start", (Integer.parseInt(StringUtil.getMapValue(map, "pageNum"))-1) * SysParamConstant.page_size);
		map.put("end", SysParamConstant.page_size);
		return sysNewsMapper.getNewsList(map);
	}

}

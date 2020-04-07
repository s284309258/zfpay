package com.example.longecological.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.SysParamConstant;
import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.mapper.system.SysNewsMapper;
import com.example.longecological.mapper.system.SysVersionMapper;
import com.example.longecological.service.system.SysNewsCacheService;
import com.example.longecological.service.system.SysNewsService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.StringUtil;


/**
 * 新闻资讯相关
 * @author Administrator
 *
 */
@Service
public class SysNewsServiceImpl implements SysNewsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysNewsServiceImpl.class);
	
	@Autowired
	SysNewsCacheService sysNewsCacheService;
	
	@Autowired
	SysNewsMapper sysNewsMapper;

	
	/**
	 * 查询新闻资讯列表
	 */
	@Override
	public R getNewsList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			//默认查询第一页
			if(StringUtils.isEmpty(StringUtil.getMapValue(map, "pageNum"))) {
				map.put("pageNum", "1");
			}
			map.put("start", (Integer.parseInt(StringUtil.getMapValue(map, "pageNum"))-1) * SysParamConstant.page_size);
			map.put("end", SysParamConstant.page_size);
			List<Map<String, Object>> newsInfoList = sysNewsMapper.getNewsList(map);
			respondMap.put("newsInfoList", newsInfoList);
			respondMap.put("pageNum", map.get("pageNum"));
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("SysNewsServiceImpl -- getNewsList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询最新新闻资讯
	 */
	@Override
	public R getNewNews(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> newsInfoList = sysNewsMapper.getNewNews(map);
			respondMap.put("newsInfoList", newsInfoList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("SysNewsServiceImpl -- getNewsList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}

	/**
	 * 查询最新新闻详情
	 */
	@Override
	public R getNewsDetail(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			Map<String, Object> newsInfo = sysNewsMapper.getNewsDetail(map);
			sysNewsMapper.updateNewsBrowseNum(map);
			respondMap.put("newsInfo", newsInfo);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("SysNewsServiceImpl -- getNewsList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


}

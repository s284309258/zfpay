package com.example.longecological.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysExchangeUrlCacheService;
import com.example.longecological.service.system.SysExchangeUrlService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;


/**
 * 交易所行情相关
 * @author Administrator
 *
 */
@Service
public class SysExchangeUrlServiceImpl implements SysExchangeUrlService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysExchangeUrlServiceImpl.class);
	
	@Autowired
	SysExchangeUrlCacheService sysExchangeUrlCacheService;
	
	
	/**
	 * 查询行情URL信息列表
	 */
	@Override
	public R getExchangeUrlList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> exchangeUrlListMap = sysExchangeUrlCacheService.getExchangeUrlList(map);
			respondMap.put("exchangeUrlLis", exchangeUrlListMap);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("SysExchangeUrlServiceImpl -- getExchangeUrlList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


}

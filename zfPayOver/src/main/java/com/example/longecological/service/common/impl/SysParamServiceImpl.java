package com.example.longecological.service.common.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.annotations.EnableCacheService;
import com.example.longecological.annotations.EnableCacheService.CacheOperation;
import com.example.longecological.constant.RedisNameConstants;
import com.example.longecological.mapper.common.SysParamMapper;
import com.example.longecological.service.common.SysParamService;



/**
 * 参数管理
 * @author Administrator
 *
 */
@Service
public class SysParamServiceImpl implements SysParamService {
	
	@Autowired
	private SysParamMapper sysParamMapper;

	
	/**
	 * 根据参数代码查询参数值
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_param,
			fieldKey="#code", cacheOperation=CacheOperation.QUERY)
	@Override
	public String getParamByCode(String code) {
		return sysParamMapper.getParamByCode(code);
	}


	/**
	 * 根据编号获取发送邮箱账号
	 */
	@EnableCacheService(keyPrefix=RedisNameConstants.zfpay_sys_send_email_account,
			fieldKey="#order", cacheOperation=CacheOperation.QUERY)
	@Override
	public Map<String, Object> getSendEmailAccount(String order) {
		return sysParamMapper.getSendEmailAccount(order);
	}

}

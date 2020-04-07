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
import com.example.longecological.mapper.system.SysBankInfoMapper;
import com.example.longecological.service.system.SysBankInfoService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;


/**
 * 系统银行相关
 * @author Administrator
 *
 */
@Service
public class SysBankInfoServiceImpl implements SysBankInfoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysBankInfoServiceImpl.class);
	
	@Autowired
	SysBankInfoMapper sysBankInfoMapper;

	
	/**
	 * 模糊搜索银行列表
	 */
	@Override
	public R searchLikeBank(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> sysBankList = sysBankInfoMapper.searchLikeBank(map);
			respondMap.put("sysBankList", sysBankList);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("SysBankInfoServiceImpl -- searchLikeBank方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


}

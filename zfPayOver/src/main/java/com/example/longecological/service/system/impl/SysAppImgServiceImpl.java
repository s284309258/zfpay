package com.example.longecological.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.longecological.constant.msgcode.CommonCodeConstant;
import com.example.longecological.entity.R;
import com.example.longecological.service.system.SysAppImgCacheService;
import com.example.longecological.service.system.SysAppImgService;
import com.example.longecological.utils.ExceptionUtil;
import com.example.longecological.utils.TokenUtil;
import com.example.longecological.utils.string.StringUtil;


/**
 * app图片相关
 * @author Administrator
 *
 */
@Service
public class SysAppImgServiceImpl implements SysAppImgService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysAppImgServiceImpl.class);
	
	@Autowired
	SysAppImgCacheService sysAppImgCacheService;

	
	/**
	 * 查询app图片列表
	 */
	@Override
	public R getAppImgList(Map<String, Object> map) {
		try {
			//验签成功与否验证
			if(!TokenUtil.checkRSAdecrypt((Map<String, Object>)map.get("result"))) {
				return (R) map.get("result");
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(map, "img_type"))) {
				return R.error(CommonCodeConstant.COMMON_CODE_999995, CommonCodeConstant.COMMON_MSG_999995);
			}
			Map<String, Object> respondMap = new HashMap<>();
			List<Map<String, Object>> appImgListMap = sysAppImgCacheService.getAppImgList(map);
			respondMap.put("appImgList", appImgListMap);
			return R.ok(CommonCodeConstant.COMMON_CODE_999983, CommonCodeConstant.COMMON_MSG_999983, respondMap);
		} catch (Exception e) {
			LOGGER.error("SysAppImgServiceImpl -- getAppImgList方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
			return R.ok(CommonCodeConstant.COMMON_CODE_999996, CommonCodeConstant.COMMON_MSG_999996);
		}
	}


}

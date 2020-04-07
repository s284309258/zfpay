package com.example.longecological.service.system;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 系统银行相关
 * @author Administrator
 *
 */
public interface SysBankInfoService {

	
	/**
	 * 模糊搜索银行
	 * @param map
	 * @return
	 */
	R searchLikeBank(Map<String, Object> map);

}

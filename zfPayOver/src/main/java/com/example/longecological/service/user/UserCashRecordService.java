package com.example.longecological.service.user;

import java.util.Map;

import com.example.longecological.entity.R;


/**
 * 用户提现相关
 * @author Administrator
 *
 */
public interface UserCashRecordService {


	/**
	 * 查询提现信息
	 * @param map
	 * @return
	 */
	R getCashInfo(Map<String, Object> map);

	
	/**
	 * 用户申请提现
	 * @param map
	 * @return
	 */
	R applyCash(Map<String, Object> map);


	/**
	 * 查询提现记录列表
	 * @param map
	 * @return
	 */
	R getCashRecordList(Map<String, Object> map);

}

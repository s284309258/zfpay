package com.example.longecological.service.common;

import java.util.List;
import java.util.Map;

public interface SysParamRateService {

	/**
	 * 根据Type类型查询费率列表
	 * @param type
	 * @return
	 */
	List<Map<String, Object>> getRateListByType(String type);
	
	/**
	 * 传统POS-系统分配参数
	 * @return
	 */
	Map<String, Object> getSysParamRateByTraditionalPos();
	
	/**
	 * Mpos-系统分配参数
	 * @return
	 */
	Map<String, Object> getSysParamRateByMpos();

	/**
	 * Mpos-系统分配参数不能低于user_id的结算低价 byqh
	 * @return
	 */
	Map<String, Object> getSysParamRateByMposAgent(Map<String,Object> map);

	/**
	 * 传统POS-系统分配参数 byqh
	 * 类型（1-刷卡费率  2-刷卡结算价 3-云闪付结算价 4-云闪付费率  5-微信结算价 6-微信费率 7-支付宝结算价 8-支付宝费率 9-单笔分润 10-机器返现 ）
	 * @return
	 */
	Map<String, Object> getSysParamRateByTraditionalPosAgent(Map<String,Object> map);
}

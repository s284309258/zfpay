package com.example.longecological.service.traditionalpos;

import java.util.Map;

import com.example.longecological.entity.R;

public interface TraditionalPosService {

	/**
	 * 查询需要申请扫码支付的传统POS机列表
	 * @param map
	 * @return
	 */
	R getScanTraditionalPosList(Map<String, Object> map);
	
	/**
	 * 提交申请扫码支付记录
	 * @param map
	 * @return
	 */
	R addApplyScanRecord(Map<String, Object> map);
	
	/**
	 * 查询扫码支付申请记录
	 * @param map
	 * @return
	 */
	R getApplyScanRecordList(Map<String, Object> map);
}

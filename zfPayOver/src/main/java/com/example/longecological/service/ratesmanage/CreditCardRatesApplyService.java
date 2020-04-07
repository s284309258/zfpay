package com.example.longecological.service.ratesmanage;

import java.util.Map;

import com.example.longecological.entity.R;

public interface CreditCardRatesApplyService {

	/**
	 * 查询申请费率修改的POS机列表
	 * @param map
	 * @return
	 */
	R getApplyRateTraditionalPosList(Map<String, Object> map);
	
	/**
	 * 查询申请费率修改的MPOS机列表
	 * @param map
	 * @return
	 */
	R getApplyRateMposList(Map<String, Object> map);
	
	/**
	 * 查询刷卡费率列表
	 * @param map
	 * @return
	 */
	R getCreditCardRateList(Map<String, Object> map);
	
	/**
	 * 提交费率申请记录（传统POS）
	 * @param map
	 * @return
	 */
	R addApplyRateTraditionalPos(Map<String, Object> map);
	
	/**
	 * 提交费率申请记录（MPOS）
	 * @param map
	 * @return
	 */
	R addApplyRateMpos(Map<String, Object> map);
	
	/**
	 * 查询费率申请记录（传统POS）
	 * @param map
	 * @return
	 */
	R getApplyRateTraditionalPosRecordList(Map<String, Object> map);
	
	/**
	 * 查询费率申请记录（MPOS）
	 * @param map
	 * @return
	 */
	R getApplyRateMposRecordList(Map<String, Object> map);
}

package com.ruoyi.project.devemana.user.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.devemana.user.domain.ManaUserCashRecord;

/**
 * 管理员======用户取现记录管理
 * @author Administrator
 *
 */
public interface ManaUserCashRecordService {

	
	/**
	 * 查询用户取现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaUserCashRecordList(Map<String, Object> params);
	
	
	/**
	 * 统计取现信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryManaUserCashRecordList(Map<String, Object> params);
	
	
	/**
	 * 导出用户取现记录信息
	 * @param params
	 * @return
	 */
	List<ManaUserCashRecord> selectManaUserCashRecordList(Map<String, Object> params);


	/**
	 * 查询取现记录详情列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaUserCashRecordDetailList(Map<String, Object> params);
	

}
	
	

package com.ruoyi.project.develop.task.service;

import com.ruoyi.project.develop.common.domain.R;

public interface ZhongFuDataAcquireService {

	/***
	 * 根据查询时间获取交易数据add byqh20200103
	 * @param startTime
	 * @param endTime
	 */
	void getDataTransactionRecordHHMMSS(String startTime,String endTime);

	/**
	 * 获取中付交易数据，插入到待处理表中
	 */
	void getDataTransactionRecordDeal();
	
	/**
	 * 获取中付前一天数据
	 */
	void getDataTransactionRecordYesterday();
	
	/**
	 * 获取中付返现数据，插入到待处理表中
	 */
	void getDataMachineCashbackRecordDeal(String query_date);
	
	/**
	 * 获取中付政策信息，插入到系统政策信息表中(传统POS)
	 */
	R getTransposDataPolicyRecord(String account_id);
	
	/**
	 * 获取中付政策信息，插入到系统政策信息表中(MPOS)
	 */
	R getMposDataPolicyRecord(String account_id);
	
	/**
	 * 获取中付激活信息
	 */
	void getDataActivatedState();

	void getDataActivatedStateSN(String sn);
}

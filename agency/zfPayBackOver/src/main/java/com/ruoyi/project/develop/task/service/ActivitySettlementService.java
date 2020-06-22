package com.ruoyi.project.develop.task.service;

public interface ActivitySettlementService {

	/**
	 * 高返现活动结算
	 * @param activity_id
	 */
	void highCashbackActivitySettlement();
	
	/**
	 * 交易量活动
	 */
	void volumeActivitySettlement();
	
	/**
	 * 达标任务处理（传统POS）
	 * @param activity_id
	 */
	void traposAssessTaskSettlement(String assess_id);
	
	/**
	 * 达标任务处理（MPOS）
	 */
	void mposAssessTaskSettlement(String assess_id);

	/***
	 * pos政策每天执行一次,政策跟机器走
	 * add byqh 201912
	 */
	void posPolicyTaskSettlement();

}

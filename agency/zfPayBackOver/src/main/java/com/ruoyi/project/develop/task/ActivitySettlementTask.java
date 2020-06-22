package com.ruoyi.project.develop.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.project.develop.task.service.ActivitySettlementService;

@Component("activitySettlementTask")
public class ActivitySettlementTask {
	
	@Autowired
	private ActivitySettlementService activitySettlementService;

	/**
	 * 高返现活动结算--每天执行一次
	 */
	public void highCashbackActivitySettlement(){
		activitySettlementService.highCashbackActivitySettlement();
	}
	
	/**
	 * 交易量活动--每月执行一次
	 */
	public void volumeActivitySettlement(){
		activitySettlementService.volumeActivitySettlement();
	}
	
	/**
	 * 达标任务处理（传统POS）
	 * @param assess_id
	 */
	public void traposAssessTaskSettlement(String assess_id){
		activitySettlementService.traposAssessTaskSettlement(assess_id);
	}
	
	/**
	 * 达标任务处理（MPOS）
	 */
	public void mposAssessTaskSettlement(String assess_id){
		activitySettlementService.mposAssessTaskSettlement(assess_id);
	}


	/***
	 * pos政策每天执行一次,政策跟机器走
	 * add byqh 201912
	 */
	public void posPolicyTaskSettlement(){
		activitySettlementService.posPolicyTaskSettlement();
	}
}

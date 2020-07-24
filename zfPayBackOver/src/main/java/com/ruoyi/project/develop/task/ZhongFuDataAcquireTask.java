package com.ruoyi.project.develop.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.project.develop.task.service.ZhongFuDataAcquireService;

@Component("zhongFuDataAcquireTask")
public class ZhongFuDataAcquireTask {

	@Autowired
	private ZhongFuDataAcquireService zhongFuDataAcquireService;

	/***
	 * 根据查询时间获取交易数据add byqh20200103
	 * @param startTime
	 * @param endTime
	 */
	public void getDataTransactionRecordHHMMSS(String startTime,String endTime){
		zhongFuDataAcquireService.getDataTransactionRecordHHMMSS(startTime,endTime);
	}

	/**
	 * 获取中付交易数据，插入到待处理表中 --每2分钟执行一次
	 */
	public void getDataTransactionRecordDeal(){
		zhongFuDataAcquireService.getDataTransactionRecordDeal();
	}
	
	/**
	 * 获取中付前天交易数据
	 */
	public void getDataTransactionRecordYesterday(){
		zhongFuDataAcquireService.getDataTransactionRecordYesterday();
	}
	
	/**
	 * 获取中付返现数据，插入到待处理表中--每天执行一次
	 */
	public void getDataMachineCashbackRecordDeal(){
		zhongFuDataAcquireService.getDataMachineCashbackRecordDeal(null);
	}
	
	/**
	 * 获取中付激活数据--一个小时执行一次
	 */
	public void getDataActivatedState(){
		zhongFuDataAcquireService.getDataActivatedState();
	}

	public void getDataActivatedStateSN(String sn){
		zhongFuDataAcquireService.getDataActivatedStateSN(sn);
	}
}

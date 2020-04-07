package com.ruoyi.project.develop.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.project.develop.task.service.TransactionDataProcessingService;

@Component("transactionDataProcessingTask")
public class TransactionDataProcessingTask {

	@Autowired
	private TransactionDataProcessingService transactionDataProcessingService;
	
	/**
	 * 处理交易数据 -- 每2分钟执行一次
	 */
	public void processingTrades(){
		transactionDataProcessingService.processingTrades();
	}
	
	/**
	 * 处理机器返现-- 每天执行一次
	 */
	public void processingMachineBack(){
		transactionDataProcessingService.processingMachineBack();
	}
}

package com.ruoyi.project.develop.task.service;

public interface TransactionDataProcessingService {

	/**
	 * 处理交易数据
	 */
	void processingTrades();
	
	/**
	 * 处理机器返现
	 */
	void processingMachineBack();
}

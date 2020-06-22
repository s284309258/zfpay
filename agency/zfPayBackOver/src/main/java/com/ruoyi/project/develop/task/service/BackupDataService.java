package com.ruoyi.project.develop.task.service;

public interface BackupDataService {

	/**
	 * 备份验证码信息 -- 每天4点开始执行
	 */
	void backUpVerifyRecord();

	
	/**
	 * 用户今日收益清零 -- 每天零点开始执行
	 */
	void updateUserTodayBenefitToZero();

}

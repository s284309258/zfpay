package com.ruoyi.project.develop.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.project.develop.task.service.BackupDataService;


/**
 * 数据备份的任务
 * @author Administrator
 *
 */
@Component("backupDataTask")
public class BackupDataTask {
	
	
	@Autowired
	private BackupDataService backupDataService;
	
	
	/**
	 * 备份验证码信息 -- 每天4点开始执行
	 */
	public void backUpVerifyRecord(){
		backupDataService.backUpVerifyRecord();
	}
	
	
	/**
	 * 用户今日收益清零 -- 每天零点开始执行
	 */
	public void updateUserTodayBenefitToZero(){
		backupDataService.updateUserTodayBenefitToZero();
	}
}

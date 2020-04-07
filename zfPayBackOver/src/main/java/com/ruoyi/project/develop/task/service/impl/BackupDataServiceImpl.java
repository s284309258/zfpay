package com.ruoyi.project.develop.task.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.task.mapper.BackupDataMapper;
import com.ruoyi.project.develop.task.service.BackupDataService;

/**
 * 活动结算业务层
 * @author i
 *
 */
@Service
public class BackupDataServiceImpl implements BackupDataService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BackupDataServiceImpl.class);
	
	@Autowired
	private BackupDataMapper backupDataMapper;

	
	/**
	 * 备份短信验证码
	 */
	@Override
	public void backUpVerifyRecord() {
		LOGGER.info("开始备份验证码");
		String date = TimeUtil.dayStart();
		backupDataMapper.backUpVerifyRecord(date);
		backupDataMapper.delVerifyRecord(date);
		LOGGER.info("验证码备份结束");
	}


	/**
	 * 用户今日收益清零 -- 每天零点开始执行
	 */
	@Override
	public void updateUserTodayBenefitToZero() {
		LOGGER.info("开始用户今日收益清零");
		backupDataMapper.updateUserTodayBenefitToZero();
		LOGGER.info("用户今日收益清零结束");
	}
	
}

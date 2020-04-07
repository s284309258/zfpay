package com.ruoyi.project.develop.task.mapper;

import org.apache.ibatis.annotations.Param;

public interface BackupDataMapper {

	/**
	 * 备份验证码
	 * @return
	 */
	int backUpVerifyRecord(@Param("date") String date);
	
	
	/**
	 * 删除主表数据
	 * @return
	 */
	int delVerifyRecord(@Param("date") String date);


	/**
	 * 用户今日收益清零 -- 每天零点开始执行
	 */
	void updateUserTodayBenefitToZero();
}

package com.example.longecological.mapper.quartz;

import java.util.List;

import com.example.longecological.entity.ScheduleJob;

/**
 * 计划任务相关mapper
 * @author Administrator
 *
 */
public interface ScheduleJobMapper {

	
	/**
	 * 查询所有的调度任务
	 * @return
	 */
	List<ScheduleJob> selectAllScheduleJob();

	
}

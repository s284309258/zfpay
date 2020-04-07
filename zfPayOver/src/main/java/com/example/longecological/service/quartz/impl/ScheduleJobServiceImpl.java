package com.example.longecological.service.quartz.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.longecological.config.quartz.SchedulerFactory;
import com.example.longecological.entity.ScheduleJob;
import com.example.longecological.mapper.quartz.ScheduleJobMapper;
import com.example.longecological.service.quartz.ScheduleJobService;
import com.example.longecological.utils.ExceptionUtil;



/**
 * 计划任务相关service
 * @author Administrator
 *
 */
@Service
@Transactional
public class ScheduleJobServiceImpl implements ScheduleJobService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);

	@Autowired
	private SchedulerFactory schedulerFactory;
	
	@Autowired
	private ScheduleJobMapper scheduleJobMapper;
	
	
	/**
	 * 初始化加载数据库中的延迟任务
	 * @throws Exception
	 */
	/*@PostConstruct
	public void init(){
		try {
			// 这里获取任务信息数据
			List<ScheduleJob> lists = this.scheduleJobMapper.selectAllScheduleJob();
			if (lists != null && lists.size() > 0){
				for (ScheduleJob job : lists){
					this.addJob(job);
				}
			}
		} catch (Exception e) {
			LOGGER.error("ScheduleJobServiceImpl -- init方法处理异常：" + ExceptionUtil.getExceptionAllinformation(e));
		}
	}*/


	/**
	 * 添加任务
	 * @param job
	 * @throws Exception
	 */
	private void addJob(ScheduleJob job) throws Exception {
		schedulerFactory.addJob(job);
	}
	

}

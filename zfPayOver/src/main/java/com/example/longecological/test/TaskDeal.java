package com.example.longecological.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.longecological.config.redis.RedisUtils;



/**
 * @Scheduled定时任务测试
 * @author Administrator
 *
 */
@Component
public class TaskDeal {
	
	 @Autowired
	 private RedisUtils redisUtils;
	
	
	//  每分钟启动 （测试）
	//@Scheduled(cron = "0 0/1 * * * ?")
	public void timerToNow(){
		redisUtils.set("user_account_16616053.122@qq.com", "1661605307@qq.com");
		System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		System.out.println(redisUtils.get("user_account_16616053.122@qq.com"));
	}

}

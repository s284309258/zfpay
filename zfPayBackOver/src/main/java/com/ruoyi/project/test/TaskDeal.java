package com.ruoyi.project.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.time.DateUtils;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.task.service.ZhongFuInterfaceService;
import com.ruoyi.project.develop.task.service.impl.ZhongFuInterfaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.encryption.MD5Utils;
import com.ruoyi.common.utils.interfaces.HttpUtils;
import com.ruoyi.framework.redis.RedisUtils;


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
//	public void timerToNow(){
//		redisUtils.set("ceshi", "123456789");
//		System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//		System.out.println(redisUtils.get("ceshi"));
//		System.out.println(MessageUtils.message("login.html_tip1"));
//		
//		/*FrontUser user = new FrontUser();
//		user.setUser_tel("111111111111");
//		user.setUser_name("22222222222");
//		redisUtils.set("user111", user);
//		FrontUser userString =  (FrontUser) redisUtils.get("user111");
//		System.out.println(userString);*/
//	}
	 
	 public static void main(String[] args){
		 
		 /************7002接口（交易数据接口）***************/
//		 String appId = "csdlo";
//		 String requestType = "7002";
//		 JSONObject requestData = new JSONObject();
//		 requestData.put("agentAccount", "csdlo");
//		 requestData.put("transType", "1");
//		 requestData.put("deviceType", "0");
//		 requestData.put("startTime", "20190828000000");
//		 requestData.put("endTime", "20190829000000");
//		 requestData.put("pageNum", "1");
//		 String dataSign = MD5Utils.MD5Encode("d1560229-06c2-45fc-aa4c-34e5c3690cd8"+requestData.toJSONString());
//		 JSONObject param = new JSONObject();
//		 param.put("appId", appId);
//		 param.put("requestType", requestType);
//		 param.put("requestData", requestData);
//		 param.put("dataSign", dataSign);
		 
		 
		 
		 /************7004接口(获取查询返现所需政策编码)***************/
//		 String appId = "csdlo";
//		 String requestType = "7004";
//		 JSONObject requestData = new JSONObject();
//		 requestData.put("agentAccount", "csdlo");
//		 requestData.put("machineType", "01");
//		 requestData.put("pageNum", "1");
//		 String dataSign = MD5Utils.MD5Encode("d1560229-06c2-45fc-aa4c-34e5c3690cd8"+requestData.toJSONString());
//		 JSONObject param = new JSONObject();
//		 param.put("appId", appId);
//		 param.put("requestType", requestType);
//		 param.put("requestData", requestData);
//		 param.put("dataSign", dataSign);
		 
		 
		 /************7005接口（返现信息接口）***************/
//		 String appId = "csdlo";
//		 String requestType = "7005";
//		 JSONObject requestData = new JSONObject();
//		 requestData.put("agentAccount", "csdlo");
//		 requestData.put("queryDate", "2019-08-28");
//		 requestData.put("machineType", "01");
//		 requestData.put("policyCode", "POS-19-02");
//		 requestData.put("pageNum", "1");
//		 String dataSign = MD5Utils.MD5Encode("d1560229-06c2-45fc-aa4c-34e5c3690cd8"+requestData.toJSONString());
//		 JSONObject param = new JSONObject();
//		 param.put("appId", appId);
//		 param.put("requestType", requestType);
//		 param.put("requestData", requestData);
//		 param.put("dataSign", dataSign);
		 
		 
		 
		 /************7006接口（激活信息接口）***************/
//		 String appId = "csdlo";
//		 String requestType = "7006";
//		 JSONObject requestData = new JSONObject();
//		 requestData.put("agentAccount", "csdlo");
//		 requestData.put("startTime", "20190828000000");
//		 requestData.put("endTime", "20190829000000");
//		 requestData.put("machineType", "01");
//		 requestData.put("pageNum", "1");
//		 String dataSign = MD5Utils.MD5Encode("d1560229-06c2-45fc-aa4c-34e5c3690cd8"+requestData.toJSONString());
//		 JSONObject param = new JSONObject();
//		 param.put("appId", appId);
//		 param.put("requestType", requestType);
//		 param.put("requestData", requestData);
//		 param.put("dataSign", dataSign);
//
//		 String url = "http://test59.qtopay.cn/gateway/exterfaceInvoke.json";
//
//		 try {
//			String result = HttpUtils.sendHttpPostRequestJson(url, param, false);
//			System.out.println("返回结果：" + result);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}


		 //通过7002接口取交易数据
		 ZhongFuInterfaceService zhongFuInterfaceService = new ZhongFuInterfaceServiceImpl();
		 R transposData = zhongFuInterfaceService.requestType7006("beiye", "000006026221521100", "20180101000000", "20200325000000", "02", String.valueOf(1), "a6d9fc6d-16e0-485d-89d9-a388678ee6cf");
		 System.out.println(transposData);

		 System.out.println("iOS*".matches("iOS11.33"));

		 int module2_uint_time = 60;
		 Date act_date_date =  DateUtils.parseDate("20191001");
		 Date dataTime_date = DateUtils.parseDate("20191231");

		 int diffDay = DateUtils.getDatePoor(act_date_date,dataTime_date,0);
		 System.out.println(diffDay);
		 System.out.println(diffDay%module2_uint_time);
		 System.out.println(diffDay/module2_uint_time);
		 System.out.println(33/30);


		 try
		 {
			 String endTime = TimeUtil.getDayFormat4();
			 String startTime = TimeUtil.getBeforeTwoDayBankCutDate(endTime);
			 System.out.println(endTime);
			 System.out.println(startTime);
			 System.out.println(DateUtils.addDays(DateUtils.parseDate(startTime),-2));
		 }catch (Exception e){

		 }



	 }
	
	

}

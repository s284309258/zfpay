package com.ruoyi.common.utils.sms2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruoyi.common.utils.sms2.yun.SmsSingleSender;
import com.ruoyi.common.utils.sms2.yun.SmsSingleSenderResult;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;

public class SmsSDKDemo {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmsSDKDemo.class);
	
	public static R sendMesg(String account, String code) {
        try {
        	//请根据实际 accesskey 和 secretkey 进行开发，以下只作为演示 sdk 使用
    		String accesskey = "5aa61ef90cf26e4137553782";
    		String secretkey = "f7d1639ee3984dff9ada24006299047a";
    		//手机号码
//    		String phoneNumber = "136252412xx";
    		 //初始化单发
	    	SmsSingleSender singleSender = new SmsSingleSender(accesskey, secretkey);
	    	SmsSingleSenderResult singleSenderResult;
	
	    	 //普通单发,注意前面必须为【】符号包含，置于头或者尾部。
	    	singleSenderResult = singleSender.send(0, "86", account, "【RETC】您的验证码是"+code+"。如非本人操作，请忽略本短信。", "", "");
	    	LOGGER.info("当前手机号"+account+"用户接收结果为："+singleSenderResult.toString());
	    	
            if(singleSenderResult.result==0){
            	return R.ok("发送成功");
            }else{
            	return R.error(Type.ERROR, "发送失败");
            }
        } catch (Exception e) {
        	LOGGER.info("当前手机号"+account+"用户发送异常："+e.getMessage());
            return R.error(Type.ERROR, "发送异常");
        }
    }
}

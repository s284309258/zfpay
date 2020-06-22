package com.ruoyi.project.test;

import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.PushNotificationResponse;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import com.turo.pushy.apns.util.TokenUtil;

import java.util.concurrent.Future;

public class IOSPushy 
{
	public final static String topic = "com.zfqgco.zfqg";
	
	public static String PushyMessage(String title,String message,String deviceToken) throws Exception
	{
		ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
		payloadBuilder.setAlertTitle(title);
		payloadBuilder.setAlertBody(message);
		payloadBuilder.setSoundFileName(ApnsPayloadBuilder.DEFAULT_SOUND_FILENAME);
		//payloadBuilder.addCustomProperty("image", "https://onevcat.com/assets/images/background-cover.jpg");
		payloadBuilder.setMutableContent(true);
		payloadBuilder.setBadgeNumber(1);
		PushyMessageBASE(payloadBuilder,deviceToken);
		return "success";
	}
	
	public static String PushyMessage(String protocol,String deviceToken) throws Exception
	{
		PushyMessageBASE(protocol,deviceToken);
		return "success";
	}
	
	private static String PushyMessageBASE(ApnsPayloadBuilder payloadBuilder, String deviceToken) throws Exception
	{
		ApnsClient apnsClient = GenerateApnsClient.getApnsClient();
		
		String token = TokenUtil.sanitizeTokenString(deviceToken);
		
		String payload = payloadBuilder.buildWithDefaultMaximumLength();
		
		SimpleApnsPushNotification pushNotification = new SimpleApnsPushNotification(token, topic, payload);
		
		Future<PushNotificationResponse<SimpleApnsPushNotification>> future = apnsClient.sendNotification(pushNotification);

		PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse = future.get();
			
		return "success";
	}
	
	private static String PushyMessageBASE(String protocol,String deviceToken) throws Exception
	{
		ApnsClient apnsClient = GenerateApnsClient.getApnsClient();
		
		String token = TokenUtil.sanitizeTokenString(deviceToken);
		
		SimpleApnsPushNotification pushNotification = new SimpleApnsPushNotification(token, topic, protocol);
		
		Future<PushNotificationResponse<SimpleApnsPushNotification>> future = apnsClient.sendNotification(pushNotification);
		
//		PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse = future.get();
			
		return "success";
	}
	
}

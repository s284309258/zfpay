package com.ruoyi.project.test;

import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.ApnsClientBuilder;
import com.turo.pushy.apns.PushNotificationResponse;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import com.turo.pushy.apns.util.TokenUtil;
import com.turo.pushy.apns.util.concurrent.PushNotificationFuture;

import javax.net.ssl.SSLException;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.Future;

public class TuroPushy 
{
	public static void main(String[] args) 
	{
		//Tu135813
		try {
//			final ApnsClient apnsClient = new ApnsClientBuilder()
//			        .setApnsServer(ApnsClientBuilder.DEVELOPMENT_APNS_HOST)
//			        .setClientCredentials(new File("D:\\apns_dev_cert.p12"),"111111")
//			        .build();

			InputStream apnsstream = TuroPushy.class.getClassLoader().getResourceAsStream("zfqg_push_development_sandbox.p12");
			ApnsClient apnsClient = new ApnsClientBuilder().setApnsServer(ApnsClientBuilder.DEVELOPMENT_APNS_HOST).setClientCredentials(apnsstream,"111111").build();
			
			final SimpleApnsPushNotification pushNotification;

			{
			    final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
			    payloadBuilder.setAlertTitle("");
			    payloadBuilder.setAlertBody("【中付钱柜】你收到一笔[闪付]收益1.00元");
			    payloadBuilder.setSoundFileName(ApnsPayloadBuilder.DEFAULT_SOUND_FILENAME);
			    payloadBuilder.setMutableContent(true);
				payloadBuilder.setBadgeNumber(1);

			    final String payload = payloadBuilder.buildWithDefaultMaximumLength();
			    //a3bb9dcf47d465541920a3abd9f98cf8ce852515222f34c0c878e1e215543910
				//c1539cf78e14f3fa7f317399dc2f8d1e667783b0023b7e9540be64295b0300f3
			    final String token = TokenUtil.sanitizeTokenString("937fcb6d20f384786e2e1343983cc91c55741257e79ef9c734847a600ce27852");
				//com.hwactive.huaWangCloud2017
			    pushNotification = new SimpleApnsPushNotification(token, "com.zfqgco.zfqg", payload);
			}
			
			Future<PushNotificationResponse<SimpleApnsPushNotification>> future = apnsClient.sendNotification(pushNotification);
			
			PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse = future.get();
//			((PushNotificationFuture) future).getNow();

			
//			System.out.println(pushNotificationResponse.getApnsId());
//			System.out.println(pushNotificationResponse.getPushNotification().getToken());
//			System.out.println(pushNotificationResponse.getPushNotification().getTopic());
//
//			System.out.println(pushNotificationResponse.getPushNotification().toString());
//
//			System.out.println(pushNotificationResponse.isAccepted());
//
//			System.out.println(pushNotificationResponse.getRejectionReason());
			
//			final PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>>
//		    sendNotificationFuture = apnsClient.sendNotification(pushNotification);
		} catch (SSLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

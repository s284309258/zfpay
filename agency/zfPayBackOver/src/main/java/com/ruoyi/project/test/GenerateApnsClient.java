package com.ruoyi.project.test;

import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.ApnsClientBuilder;

import java.io.InputStream;

public class GenerateApnsClient 
{
	private static ApnsClient apnsClient = null;
	
	public synchronized static ApnsClient getApnsClient() throws Exception
	{
		if(apnsClient==null) 
		{
			InputStream apnsstream = GenerateApnsClient.class.getClassLoader().getResourceAsStream("zfqg_push_product.p12");//Tu135813
			apnsClient = new ApnsClientBuilder().setApnsServer(ApnsClientBuilder.PRODUCTION_APNS_HOST).setClientCredentials(apnsstream,"111111").build();
		}
		
		return apnsClient;
	}
	
//	public static ApnsClient getApnsClient(int ConcurrentConnections,int EventLoopGroup) throws Exception
//	{
//		if(apnsClient==null) 
//		{
//			InputStream apnsstream = TuroPushyBase.class.getClassLoader().getResourceAsStream("apns_dev_cert.p12");
//			apnsClient = new ApnsClientBuilder().setApnsServer(ApnsClientBuilder.DEVELOPMENT_APNS_HOST).setClientCredentials(apnsstream,"tu123456")
//					.setConcurrentConnections(ConcurrentConnections).setEventLoopGroup(new NioEventLoopGroup(EventLoopGroup)).build();
//		}
//		
//		return apnsClient;
//	}
}

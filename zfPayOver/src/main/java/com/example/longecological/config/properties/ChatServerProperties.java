package com.example.longecological.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/chatserver.properties")
public class ChatServerProperties {

	/**
	 * http好友和群通知推送路径
	 */
	@Value("${http_url}")
	private String http_url;
	
	/**
	 * 好友红包通知
	 */
	@Value("${red_packet_notice}")
	private String red_packet_notice;
	
	/**
	 * 红包接收通知
	 */
	@Value("${red_packet_receive_notice}")
	private String red_packet_receive_notice;
	
	
	/**
	 * 登录或者退出请求聊天接口通知
	 */
	@Value("${chat_login_or_logout}")
	private String chat_login_or_logout;
	
	/**
	 * 聊天节点
	 */
	@Value("${webscoket_path}")
	private String webscoket_path;
	
	/**
	 * 朋友圈互动通知
	 */
	@Value("${circle_notice}")
	private String circle_notice;


	public String getHttp_url() {
		return http_url;
	}


	public void setHttp_url(String http_url) {
		this.http_url = http_url;
	}


	public String getRed_packet_notice() {
		return red_packet_notice;
	}


	public void setRed_packet_notice(String red_packet_notice) {
		this.red_packet_notice = red_packet_notice;
	}


	public String getRed_packet_receive_notice() {
		return red_packet_receive_notice;
	}


	public void setRed_packet_receive_notice(String red_packet_receive_notice) {
		this.red_packet_receive_notice = red_packet_receive_notice;
	}


	public String getChat_login_or_logout() {
		return chat_login_or_logout;
	}


	public void setChat_login_or_logout(String chat_login_or_logout) {
		this.chat_login_or_logout = chat_login_or_logout;
	}


	public String getWebscoket_path() {
		return webscoket_path;
	}


	public void setWebscoket_path(String webscoket_path) {
		this.webscoket_path = webscoket_path;
	}


	public String getCircle_notice() {
		return circle_notice;
	}


	public void setCircle_notice(String circle_notice) {
		this.circle_notice = circle_notice;
	}
	
	
}

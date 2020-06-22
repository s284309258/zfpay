package com.ruoyi.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/chatserver.properties")
public class FtpServerProperties {

	/**
	 * FTP的IP
	 */
	@Value("${ftp.ip}")
	private String ftp_ip;
	
	/**
	 * FTP用户名
	 */
	@Value("${ftp.userName}")
	private String ftp_userName;
	
	/**
	 * FTP用户密码
	 */
	@Value("${ftp.userPwd}")
	private String ftp_userPwd;
	
	
	/**
	 * FTP端口
	 */
	@Value("${ftp.port}")
	private int ftp_port;
	
	/**
	 * FTP路径
	 */
	@Value("${ftp.path}")
	private String ftp_path;
	
	/**
	 * FTP备份路径
	 */
	@Value("${ftp.pathBack}")
	private String ftp_pathBack;

	public String getFtp_ip() {
		return ftp_ip;
	}

	public void setFtp_ip(String ftp_ip) {
		this.ftp_ip = ftp_ip;
	}

	public String getFtp_userName() {
		return ftp_userName;
	}

	public void setFtp_userName(String ftp_userName) {
		this.ftp_userName = ftp_userName;
	}

	public String getFtp_userPwd() {
		return ftp_userPwd;
	}

	public void setFtp_userPwd(String ftp_userPwd) {
		this.ftp_userPwd = ftp_userPwd;
	}

	public int getFtp_port() {
		return ftp_port;
	}

	public void setFtp_port(int ftp_port) {
		this.ftp_port = ftp_port;
	}

	public String getFtp_path() {
		return ftp_path;
	}

	public void setFtp_path(String ftp_path) {
		this.ftp_path = ftp_path;
	}

	public String getFtp_pathBack() {
		return ftp_pathBack;
	}

	public void setFtp_pathBack(String ftp_pathBack) {
		this.ftp_pathBack = ftp_pathBack;
	}
}

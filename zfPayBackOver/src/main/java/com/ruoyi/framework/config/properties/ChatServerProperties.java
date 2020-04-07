package com.ruoyi.framework.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/chatserver.properties")
public class ChatServerProperties {

	/**
	 * 获取七牛token值
	 */
	@Value("${get_qiniu_token_url}")
	public String get_qiniu_token_url;

	public String getGet_qiniu_token_url() {
		return get_qiniu_token_url;
	}

	public void setGet_qiniu_token_url(String get_qiniu_token_url) {
		this.get_qiniu_token_url = get_qiniu_token_url;
	}

	@Override
	public String toString() {
		return "ChatServerProperties [get_qiniu_token_url=" + get_qiniu_token_url + "]";
	}
	
}

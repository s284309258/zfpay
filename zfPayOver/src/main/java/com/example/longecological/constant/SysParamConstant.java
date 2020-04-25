package com.example.longecological.constant;

import java.math.BigDecimal;

public class SysParamConstant {

	/*
	 * token过期时间
	 */	
	public static final Long past_seconds = 2592000L;
	
	/*
	 * 提现过期时间
	 */	
	public static final Long cash_past_seconds = 3600L;
	
	
	/**
	 * 页面数量（分页）
	 */
	public static final int page_size = 10;
	
	/**
	 * 缓存中分页类型：下一页
	 */
	public static final String page_type1 = "1";
	
	/**
	 * 缓存中分页类型：上一页
	 */
	public static final String page_type2 = "2";

	/**
	 * 用户银行卡总数量
	 */
	public static final Integer user_card_max_num = 10;


	/**
	 * 小数精度
	 */
	public static final int decimal_precision = 4;
	

	/**
	 * 加密次数
	 */
	public static final Integer passNum = 10;

	/**
	 * 取反参数
	 */
	public static final BigDecimal lose = new BigDecimal(-1);
	
	
	/**
	 * 七牛公钥
	 */
	public static final String qiniu_accessKey = "yF0b1F4ED38qfQP-igOcaZPuulcfUVr4o6Sontce";

	
	/**
	 * 七牛私钥
	 */
	public static final String qiniu_secretKey = "YOM7jGDa-hRY1Mdg0YF1Id74Op_SeBNDkgaMRRPa";
	
	
	/**
	 * 七牛域名
	 */
//	public static final String qiniu_domain = "http://cdn.yhswl.com";

	public static final String qiniu_domain = "http://cdn.szbypos.com";
	
	
	/**
	 * 七牛对象名
	 */
	public static final String qiniu_bucket = "zfqg";
	
	
	/**
	 * 系统域名======》测试环境
	 *//*
	public static final String sys_domain = "http://jjst.han-yu-china.com";*/
	
	
	/**
	 * 系统域名======》线上环境
	 */
	public static final String sys_domain = "http://www.proecosystem.com/";
	
	
	/**
	 * 用户头像
	 */
	public static final String user_head_photo = "defaultHeadPhoto.png";
	
	
	
	/**
	 * 图形验证码过期时间
	 */
	public static final Long verification_code_seconds = 3600L;
	
	
	/**
	 * web端注册链接============》测试环境
	 *//*
	public static final String web_register_link = "http://jjst.han-yu-china.com/html/rst.html?account=";*/
	
	
	
	/**
	 * web端注册链接============》线上环境
	 */
	public static final String web_register_link = "https://new.ck-pay.com/html/register.html?account=";
	
	
	/**
	 * 访问端口 ： WEB浏览器
	 */
	public static final String terminal_web = "web";
}

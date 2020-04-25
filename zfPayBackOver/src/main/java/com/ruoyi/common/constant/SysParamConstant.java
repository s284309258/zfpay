package com.ruoyi.common.constant;


/**
 * 系统参数
 * @author Administrator
 *
 */
public class SysParamConstant {
	
	
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
	 * 图形验证码过期时间
	 */
	public static final Long verification_code_seconds = 3600L;
	
	
	/**
	 * 一天对应的秒数
	 */
	public static final Long day_seconds = 86400L;
	
	
	/**
	 * 加密次数
	 */
	public static final Integer passNum = 10;
	
	
	/**
	 * 页面数量（分页）
	 */
	public static int page_size = 10;

	/**
	 * 交易数据缓存时间
	 */
	public static final Long two_day_seconds = 172800L;
	
	/**
	 * 金额保留位数
	 */
	public static final int money_decimal_digits = 2;
}

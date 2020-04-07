package com.ruoyi.common.constant;

public class ZhongFuInterfaceCodeConstant {

	/**
	 * 代理报备接口 7001
	 */
	public static String request_type_7001 = "7001";
	
	/**
	 * 交易状态查询接口 7002
	 */
	public static String request_type_7002 = "7002";
	
	/**
	 * 代理信息参考号查询结算状态接口 7003
	 */
	public static String request_type_7003 = "7003";
	
	/**
	 * 代理信息查询政策信息接口 7004
	 */
	public static String request_type_7004 = "7004";
	
	/**
	 * 代理信息查询返现信息接口 7005
	 */
	public static String request_type_7005 = "7005";
	
	/**
	 * 代理信息查询设备激活信息接口 7006
	 */
	public static String request_type_7006 = "7006";
	
	/**
	 * 中付数据请求链接
	 */
	//public static String request_url = "http://test59.qtopay.cn/gateway/exterfaceInvoke.json";

	public static String request_url = "https://openapi.qtopay.cn:8818/gateway/exterfaceInvoke.json";
	
	/**
	 * 7002 接口 设备类型 1-传统POS
	 */
	public static String device_type_1 = "1";
	
	/**
	 * 7002接口 设备类型 0-MPOS
	 */
	public static String device_type_0 = "0";
	
	/**
	 * 7004接口 设备类型 02-传统POS
	 */
	public static String machine_type_02 = "02";
	
	/**
	 * 7004接口 设备类型 01-MPOS
	 */
	public static String machine_type_01 = "01";

	/**
	 * 交易类型 1-刷卡
	 */
	public static String trans_type_1 = "1";
	
	/**
	 * 交易类型 2-快捷支付
	 */
	public static String trans_type_2 = "2";
	
	/**
	 * 交易类型 3-微信
	 */
	public static String trans_type_3 = "3";
	
	/**
	 * 交易类型 4-支付宝
	 */
	public static String trans_type_4 = "4";
	
	/**
	 * 交易类型 5-银联二维码
	 */
	public static String trans_type_5 = "5";

	/**
	 * 卡类型 0-贷记卡
	 */
//	public static String card_type_0 = "0";
	
	/**
	 * 卡类型 1-借记卡
	 */
//	public static String card_type_1 = "1";
	
	/**
	 * 卡类型 2-境外卡
	 */
//	public static String card_type_2 = "2";
	
	/**
	 * 卡类型 3-云闪付
	 */
//	public static String card_type_3 = "3";
	
	
	/**
	 * 卡类型1-借记卡
	 */
	public static String card_type_new_1 = "1";
	
	/**
	 * 卡类型 2-贷记卡
	 */
	public static String card_type_new_2 = "2";
	
	/**
	 * 卡类型3-境外卡
	 */
	public static String card_type_new_3 = "3";
	
	/**
	 * 卡类型 4-云闪付
	 */
	public static String card_type_new_4 = "4";
	
	/**
	 * 报备信息标识：同时报备代理信息与设备信息
	 */
	public static final String user_report_record_detail_is_report_0 = "0";
	/**
	 * 报备信息标识：只报备代理信息
	 */
	public static final String user_report_record_detail_is_report_1 = "1";
	/**
	 * 报备信息标识：只报备设备信息
	 */
	public static final String user_report_record_detail_is_report_2 = "2";

	/**
	 * 付款秒到(非商圈),byqh 201912
	 */
	public static final String transProduct_1="1";

	/**
	 * VIP秒到(商圈),byqh 201912
	 */
	public static final String transProduct_2="2";

	/**
	 * 星券秒到,byqh 201912
	 */
	public static final String transProduct_3="3";
	
}

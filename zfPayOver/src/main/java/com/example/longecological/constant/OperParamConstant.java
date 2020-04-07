package com.example.longecological.constant;

public class OperParamConstant {

	//用户登录类型类型（选取====》账号登录、token登录）
	public static final String[] USER_LOGIN_TYPE = {"account","token"};
	
	/**
	 * 用户登录类型：账号登录
	 */
	public static final String USER_LOGIN_TYPE_ACCOUNT = "account";
	
	/**
	 * 用户登录类型：token登录
	 */
	public static final String USER_LOGIN_TYPE_TOKEN = "token";
	
	
	//用户设备类型（选取====》android、iOS）
	public static final String[] USER_DEVICE_TYPE = {"android","iOS"};
	
	
	//用户结算卡操作类型（选取====》新增、删除、设置默认）
	public static final String[] USER_CARD_OPER_UPDATE_TYPE = {"user_card_add","user_card_del","user_card_set"};
	
	/**
	 * 用户结算卡操作类型：新增
	 */
	public static final String USER_CARD_OPER_UPDATE_TYPE_ADD = "user_card_add";
	
	/**
	 * 用户结算卡操作类型：删除
	 */
	public static final String USER_CARD_OPER_UPDATE_TYPE_DEL = "user_card_del";
	
	/**
	 * 用户结算卡操作类型：设置默认
	 */
	public static final String USER_CARD_OPER_UPDATE_TYPE_SET = "user_card_set";
	
	
	//结算卡必备参数（账号、银行代码、银行名称、银行卡照片）
	public static final String[] USER_CARD_MUST_PARAM = {"account","bank_code","bank_name","card_photo"};
	
	
}

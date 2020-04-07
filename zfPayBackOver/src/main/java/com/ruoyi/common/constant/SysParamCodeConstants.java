package com.ruoyi.common.constant;

public class SysParamCodeConstants {

	
	/**
	 * 验证码失效时长(单位/分)<发了之后不验证多久失效>
	 */
	public static final String sys_param_code_verifyInvalidDuration = "verifyInvalidDuration";
	
	/**
	 * 验证码发送时长(单位/分)<发了之后过多久再次发>
	 */
	public static final String sys_param_code_verifySendDuration = "verifySendDuration";
	
	/**
	 * 验证码每小时发送次数限制
	 */
	public static final String sys_param_code_verifySendHourLimit = "verifySendHourLimit";
	
	/**
	 * 验证码每天发送次数限制
	 */
	public static final String sys_param_code_verifySendDayLimit = "verifySendDayLimit";
	
	/**
	 * 提现单笔固定手续费
	 */
	public static final String sys_param_code_cashSingleFeet = "cashSingleFeet";
	
	/**
	 * 最低提现额度
	 */
	public static final String sys_param_code_cashMinNum = "cashMinNum";
	
	/**
	 * 提现手续费率
	 */
	public static final String sys_param_code_cashFeetRate = "cashFeetRate";
	
	/**
	 * 系统发送邮件账号数量
	 */
	public static final String sys_param_code_sysEmailAccountNum = "sysEmailAccountNum";
	
	/**
	 * 云闪付交易额临界值条件（超过当前数量将使用刷卡费率）
	 */
	public static final String sys_param_code_cloudFlushCriticalValue = "cloudFlushCriticalValue";
	
}

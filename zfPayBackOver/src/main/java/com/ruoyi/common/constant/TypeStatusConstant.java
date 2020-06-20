package com.ruoyi.common.constant;


/**
 * 类型状态常量管理
 * @author Administrator
 *
 */
public class TypeStatusConstant {
	
	/**
	 * 是否允许并发执行：允许
	 */
	public static final String sys_task_concurrent1 = "0";
	/**
	 * 是否允许并发执行：禁止
	 */
	public static final String sys_task_concurrent2 = "1";
	
	/**
	 * 用户账号状态：可用（未冻结）
	 */
	public static final String user_info_status_0 = "0";
	/**
	 * 用户账号状态：不可用（已冻结）
	 */
	public static final String user_info_status_1 = "1";
	
	/**
	 * 用户实名认证状态：待认证
	 */
	public static final String user_info_auth_status_00 = "00";
	/**
	 * 用户实名认证状态：待审核
	 */
	public static final String user_info_auth_status_04 = "04";
	/**
	 * 用户实名认证状态：认证失败
	 */
	public static final String user_info_auth_status_08 = "08";
	/**
	 * 用户实名认证状态：认证成功
	 */
	public static final String user_info_auth_status_09 = "09";

	/**
	 * 用户报备状态：未报备
	 */
	public static final String user_eport_status_00 = "0";

	/**
	 * 用户报备状态：未报备
	 */
	public static final String user_eport_status_01 = "1";

	/**
	 * 用户结算卡状态：待审核
	 */
	public static final String user_card_info_status_00 = "00";
	/**
	 * 用户结算卡状态：审核不通过
	 */
	public static final String user_card_info_status_08 = "08";
	/**
	 * 用户结算卡状态：审核通过
	 */
	public static final String user_card_info_status_09 = "09";
	
	/**
	 * 用户提现状态：待处理
	 */
	public static final String user_cash_record_status_00 = "00";
	/**
	 * 用户提现状态：处理中
	 */
	public static final String user_cash_record_status_02 = "02";
	/**
	 * 用户提现状态：已撤销
	 */
	public static final String user_cash_record_status_04 = "04";
	/**
	 * 用户提现状态：处理失败
	 */
	public static final String user_cash_record_status_08 = "08";
	/**
	 * 用户提现状态：处理成功
	 */
	public static final String user_cash_record_status_09 = "09";
	
	/**
	 * 线上活动状态：待发布
	 */
	public static final String sys_activity_info_status_00 = "00";
	/**
	 * 线上活动状态：已发布
	 */
	public static final String sys_activity_info_status_09 = "09";
	/**
	 * 线上活动状态：已结束
	 */
	public static final String sys_activity_info_status_10 = "10";
	
	/**
	 * 线上活动类型：活动A 高返现
	 */
	public static final String sys_activity_info_activity_type_01 = "01";
	/**
	 * 线上活动类型：活动B 交易量
	 */
	public static final String sys_activity_info_activity_type_02 = "02";
	
	/**
	 * 线上活动删除状态：未删除
	 */
	public static final String sys_activity_info_del_0 = "0";
	/**
	 * 线上活动删除状态：已删除
	 */
	public static final String sys_activity_info_del_1 = "1";
	
	/**
	 * 线上活动进行状态：未开始
	 */
	public static final String sys_activity_info_open_status_0 = "0";
	/**
	 * 线上活动进行状态：进行中
	 */
	public static final String sys_activity_info_open_status_1 = "1";
	/**
	 * 线上活动进行状态：已结束
	 */
	public static final String sys_activity_info_open_status_2 = "2";
	
	/**
	 * 线上活动奖励类型删除状态：未删除
	 */
	public static final String sys_activity_reward_info_del_0 = "0";
	/**
	 * 线上活动奖励类型删除状态：已删除
	 */
	public static final String sys_activity_reward_info_del_1 = "1";
	
	/**
	 * 用户活动申请记录审核状态：待审核
	 */
	public static final String user_pos_activity_info_status_00 = "00";
	/**
	 * 用户活动申请记录审核状态：取消活动
	 */
	public static final String user_pos_activity_info_status_04 = "04";
	/**
	 * 用户活动申请记录审核状态：审核失败
	 */
	public static final String user_pos_activity_info_status_08 = "08";
	/**
	 * 用户活动申请记录审核状态：审核成功
	 */
	public static final String user_pos_activity_info_status_09 = "09";
	
	/**
	 * 用户POS机是否参与线上活动：否
	 */
	public static final String user_pos_info_activity_status_0 = "0";
	/**
	 * 用户POS机是否参与线上活动：是
	 */
	public static final String user_pos_info_activity_status_1 = "1";
	
	
	/**
	 * 用户申请刷卡费率状态：待处理
	 */
	public static final String user_apply_cardrate_record_status_00 = "00";
	/**
	 * 用户申请刷卡费率状态：拒绝
	 */
	public static final String user_apply_cardrate_record_status_08 = "08";
	/**
	 * 用户申请刷卡费率状态：通过
	 */
	public static final String user_apply_cardrate_record_status_09 = "09";
	
	/**
	 * 系统费率参数类型：刷卡费率
	 */
	public static final String sys_param_rate_type_1 = "1";
	/**
	 * 系统费率参数类型：刷卡结算价
	 */
	public static final String sys_param_rate_type_2 = "2";
	/**
	 * 系统费率参数类型：云闪付结算价
	 */
	public static final String sys_param_rate_type_3 = "3";
	/**
	 * 系统费率参数类型：云闪付费率
	 */
	public static final String sys_param_rate_type_4 = "4";
	/**
	 * 系统费率参数类型：微信结算价
	 */
	public static final String sys_param_rate_type_5 = "5";
	/**
	 * 系统费率参数类型：微信费率
	 */
	public static final String sys_param_rate_type_6 = "6";
	/**
	 * 系统费率参数类型：支付宝结算价
	 */
	public static final String sys_param_rate_type_7 = "7";
	/**
	 * 系统费率参数类型：支付宝费率
	 */
	public static final String sys_param_rate_type_8 = "8";
	/**
	 * 系统费率参数类型：单笔分润
	 */
	public static final String sys_param_rate_type_9 = "9";
	/**
	 * 系统费率参数类型：机器返现
	 */
	public static final String sys_param_rate_type_10 = "10";
	/**
	 * 系统费率参数类型：封顶费
	 */
	public static final String sys_param_rate_type_11 = "11";
	
	
	
	/**
	 * 系统POS机分配状态：未分配
	 */
	public static final String sys_pos_info_dis_status_0 = "0";
	/**
	 * 系统POS机分配状态：已分配
	 */
	public static final String sys_pos_info_dis_status_1 = "1";
	
	/**
	 * 系统POS机是否申请扫码支付：否
	 */
	public static final String sys_pos_info_scan_status_0 = "0";
	/**
	 * 系统POS机是否申请扫码支付：是
	 */
	public static final String sys_pos_info_scan_status_1 = "1";
	
	/**
	 * 用户POS机归属状态：上级
	 */
	public static final String user_pos_info_state_status_0 = "0";
	/**
	 * 用户POS机归属状态：直属
	 */
	public static final String user_pos_info_state_status_1 = "1";
	
	/**
	 * 用户POS机删除状态：未删除
	 */
	public static final String user_pos_info_del_0 = "0";
	/**
	 * 用户POS机删除状态：已删除
	 */
	public static final String user_pos_info_del_1 = "1";
	
	/**
	 * 用户POS机是否有交易：否、无交易
	 */
	public static final String user_pos_info_trade_status_0 = "0";
	/**
	 * 用户POS机是否有交易：是、有交易
	 */
	public static final String user_pos_info_trade_status_1 = "1";
	
	/**
	 * 系统新增类型：批量
	 */
	public static final String sys_add_type_1 = "1";
	/**
	 * 系统新增类型：自增
	 */
	public static final String sys_add_type_2 = "2";
	
	
	
	/**
	 * 用户申请扫码支付状态：待处理
	 */
	public static final String user_apply_scan_record_info_status_00 = "00";
	/**
	 * 用户申请扫码支付状态：拒绝
	 */
	public static final String user_apply_scan_record_info_status_08 = "08";
	/**
	 * 用户申请扫码支付状态：通过
	 */
	public static final String user_apply_scan_record_info_status_09 = "09";
	
	
	/**
	 * 用户POS解绑申请状态：待处理
	 */
	public static final String user_trapos_unbind_record_info_status_00 = "00";
	/**
	 * 用户POS解绑申请状态：拒绝
	 */
	public static final String user_trapos_unbind_record_info_status_08 = "08";
	/**
	 * 用户POS解绑申请状态：同意
	 */
	public static final String user_trapos_unbind_record_info_status_09 = "09";
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 平台流水汇总类型：总汇总
	 */
	public static final String summary_user_benefit_everyday_type_1 = "1";
	
	/**
	 * 平台流水汇总类型：明细汇总
	 */
	public static final String summary_user_benefit_everyday_type_2 = "2";
	
	
	/**
	 * APP图片类型：首页轮播图
	 */
	public static final String sys_app_img_type_01 = "01";
	
	
	/**
	 * 设备类型：iOS
	 */
	public static final String user_info_device_type_iOS = "iOS";
	
	/**
	 * 设备类型：android
	 */
	public static final String user_info_device_type_android = "android";
	
	
	/**
	 * 查询流水类型：RET钱包
	 */
	public static final String BENEFIT_TYPE_01 = "01";

	/**
	 * 查询流水类型：定存钱包
	 */
	public static final String BENEFIT_TYPE_02 = "02";
	
	/**
	 * 查询流水类型：奖励钱包
	 */
	public static final String BENEFIT_TYPE_03 = "03";
	
	/**
	 * 查询流水类型：杠杆钱包
	 */
	public static final String BENEFIT_TYPE_04 = "04";
	
	
	
	/**
	 * 流水变化类型：RET钱包
	 */
	public static final String sys_benefit_name_change_type_01 = "01";
	
	/**
	 * 流水变化类型：定存钱包
	 */
	public static final String sys_benefit_name_change_type_02 = "02";
	
	/**
	 * 流水变化类型：奖励钱包
	 */
	public static final String sys_benefit_name_change_type_03 = "03";
	
	/**
	 * 流水变化类型：杠杆钱包
	 */
	public static final String sys_benefit_name_change_type_04 = "04";
	
	/**
	 * 流水变化类型：RET钱包和定存钱包
	 */
	public static final String sys_benefit_name_change_type_05 = "05";
	
	/**
	 * 流水变化类型：奖励钱包和定存钱包
	 */
	public static final String sys_benefit_name_change_type_06 = "06";
	
	/**
	 * 流水变化类型：奖励钱包和RET钱包
	 */
	public static final String sys_benefit_name_change_type_07 = "07";
	
	/**
	 * 流水变化类型：奖励钱包和杠杆钱包
	 */
	public static final String sys_benefit_name_change_type_08 = "08";
	
	/**
	 * 流水变化类型：RET钱包、定存钱包、奖励钱包和杠杆钱包
	 */
	public static final String sys_benefit_name_change_type_09 = "09";
	
	
	/**
	 * 系统操作标识：修改邮箱
	 */
	public static final String sys_oper_flag_modify_email_account = "modify_email_account";
	
	
	/**
	 * 调度任务类型：普通轮循类任务
	 */
	public static final String sys_job_job_type_01 = "01";
	
	/**
	 * 调度任务类型：只执行一次的任务
	 */
	public static final String sys_job_job_type_02 = "02";

	
	/**
	 * 中付账号启用状态：启用
	 */
	public static final String sys_user_account_is_start_0 = "0";
	/**
	 * 中付账号启用状态：停用
	 */
	public static final String sys_user_account_is_start_1 = "1";
	

	
}

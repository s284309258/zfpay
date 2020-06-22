package com.ruoyi.common.constant;


/***
 * 验证码业务类型常量
 * @author zenghuayo
 *
 */
public class VerifyConstant {
	
	/**
	 * 短信验证码接收类型：手机
	 */
	public static final String MobileAccType = "1";
	
	
	/**
	 * 短信验证码接收类型：邮箱
	 */
	public static final String EmailAccType = "2";
	
	
	/**
	 * 验证码验证状态：已被验证
	 */
	public static final String verifyStatus_1 = "1";
	
	/**
	 * 验证码验证状态：未验证
	 */
	public static final String verifyStatus_0 = "0";


	/**
	 * 业务类型：前端注册
	 */
	public static final String BusType_FrontRegister = "FrontRegister";


	/**
	 * 前端平台
	 */
	public static final String SystemFront = "SystemFront";
	
	
	/**
	 * 后端平台
	 */
	public static final String SystemBack = "SystemBack";
	
	
	
	//======================================代理模块==================================
	/**
	 * 业务类型：冻结解冻用户账号
	 */
	public static final String AuthVerfiy_AgentSysFreezeUserInfo = "AuthVerfiy_AgentSysFreezeUserInfo";
	/**
	 * 业务类型：单个审核实名认证
	 */
	public static final String AuthVerfiy_AgentSysAuthAuditUserInfo = "AuthVerfiy_AgentSysAuthAuditUserInfo";
	/**
	 * 业务类型：批量审核实名认证
	 */
	public static final String AuthVerfiy_AgentBathSysAuthAuditUserInfo = "AuthVerfiy_AgentBathSysAuthAuditUserInfo";
	/**
	 * 业务类型：编辑用户信息
	 */
	public static final String AuthVerfiy_AgentEditUserInfo = "AuthVerfiy_AgentEditUserInfo";
	/**
	 * 业务类型：删除用户信息
	 */
	public static final String AuthVerfiy_AgentDelUserInfo = "AuthVerfiy_AgentDelUserInfo";
	
	
	
	/**
	 * 业务类型：单个审核用户结算卡
	 */
	public static final String AuthVerfiy_AgentSysAuditCardInfo = "AuthVerfiy_AgentSysAuditCardInfo";
	/**
	 * 业务类型：批量审核用户结算卡
	 */
	public static final String AuthVerfiy_AgentBatchSysAuditCardInfo = "AuthVerfiy_AgentBatchSysAuditCardInfo";
	
	
	/**
	 * 业务类型：新增中付账号
	 */
	public static final String AuthVerfiy_AgentAddUserAccount = "AuthVerfiy_AgentAddUserAccount";
	/**
	 * 业务类型：编辑中付账号
	 */
	public static final String AuthVerfiy_AgentEditUserAccount = "AuthVerfiy_AgentEditUserAccount";
	/**
	 * 业务类型：删除中付账号
	 */
	public static final String AuthVerfiy_AgentDelUserAccount = "AuthVerfiy_AgentDelUserAccount";
	/**
	 * 业务类型：开启关闭中付账号
	 */
	public static final String AuthVerfiy_AgentSysOpenUserAccount = "AuthVerfiy_AgentSysOpenUserAccount";
	
	
	/**
	 * 业务类型：处理提现申请
	 */
	public static final String AuthVerfiy_AgentSysAuditUserCash = "AuthVerfiy_AgentSysAuditUserCash";
	
	
	/**
	 * 业务类型：新增线上活动（MPOS）
	 */
	public static final String AuthVerfiy_AgentAddSysMposActivityInfo = "AuthVerfiy_AgentAddSysMposActivityInfo";
	/**
	 * 业务类型：编辑线上活动（MPOS）
	 */
	public static final String AuthVerfiy_AgentEditSysMposActivityInfo = "AuthVerfiy_AgentEditSysMposActivityInfo";
	/**
	 * 业务类型：删除线上活动（MPOS）
	 */
	public static final String AuthVerfiy_AgentDelSysMposActivityInfo = "AuthVerfiy_AgentDelSysMposActivityInfo";
	/**
	 * 业务类型：发布和删除线上活动（MPOS）
	 */
	public static final String AuthVerfiy_AgentSysReleaseSysMposActivityInfo = "AuthVerfiy_AgentSysReleaseSysMposActivityInfo";


	/**
	 * 业务类型：新增线上活动（传统POS）
	 */
	public static final String AuthVerfiy_AgentAddSysEposActivityInfo = "AuthVerfiy_AgentAddSysEposActivityInfo";
	/**
	 * 业务类型：编辑线上活动（传统POS）
	 */
	public static final String AuthVerfiy_AgentEditSysEposActivityInfo = "AuthVerfiy_AgentEditSysEposActivityInfo";
	/**
	 * 业务类型：删除线上活动（传统POS）
	 */
	public static final String AuthVerfiy_AgentDelSysEposActivityInfo = "AuthVerfiy_AgentDelSysEposActivityInfo";
	/**
	 * 业务类型：发布和删除线上活动（传统POS）
	 */
	public static final String AuthVerfiy_AgentSysReleaseSysEposActivityInfo = "AuthVerfiy_AgentSysReleaseSysEposActivityInfo";

	
	/**
	 * 业务类型：新增线上活动（传统POS）
	 */
	public static final String AuthVerfiy_AgentAddSysTraposActivityInfo = "AuthVerfiy_AgentAddSysTraposActivityInfo";
	/**
	 * 业务类型：编辑线上活动（传统POS）
	 */
	public static final String AuthVerfiy_AgentEditSysTraposActivityInfo = "AuthVerfiy_AgentEditSysTraposActivityInfo";
	/**
	 * 业务类型：删除线上活动（传统POS）
	 */
	public static final String AuthVerfiy_AgentDelSysTraposActivityInfo = "AuthVerfiy_AgentDelSysTraposActivityInfo";
	/**
	 * 业务类型：发布和删除线上活动（传统POS）
	 */
	public static final String AuthVerfiy_AgentSysReleaseSysTraposActivityInfo = "AuthVerfiy_AgentSysReleaseSysTraposActivityInfo";
	
	
	/**
	 * 业务类型：新增线上活动（MPOS）奖励类型信息
	 */
	public static final String AuthVerfiy_AgentAddSysMposActivityRewardInfo = "AuthVerfiy_AgentAddSysMposActivityRewardInfo";
	/**
	 * 业务类型：编辑线上活动（MPOS）奖励类型信息
	 */
	public static final String AuthVerfiy_AgentEditSysMposActivityRewardInfo = "AuthVerfiy_AgentEditSysMposActivityRewardInfo";
	/**
	 * 业务类型：删除线上活动（MPOS）奖励类型信息
	 */
	public static final String AuthVerfiy_AgentDelSysMposActivityRewardInfo = "AuthVerfiy_AgentDelSysMposActivityRewardInfo";
	
	
	/**
	 * 业务类型：新增线上活动（传统POS）奖励类型信息
	 */
	public static final String AuthVerfiy_AgentAddSysTraposActivityRewardInfo = "AuthVerfiy_AgentAddSysTraposActivityRewardInfo";
	/**
	 * 业务类型：编辑线上活动（传统POS）奖励类型信息
	 */
	public static final String AuthVerfiy_AgentEditSysTraposActivityRewardInfo = "AuthVerfiy_AgentEditSysTraposActivityRewardInfo";
	/**
	 * 业务类型：删除线上活动（传统POS）奖励类型信息
	 */
	public static final String AuthVerfiy_AgentDelSysTraposActivityRewardInfo = "AuthVerfiy_AgentDelSysTraposActivityRewardInfo";
	
	
	/**
	 * 业务类型：审核线上活动（MPOS）申请
	 */
	public static final String AuthVerfiy_AgentSysAuditUserMposActivityApplyInfo = "AuthVerfiy_AgentSysAuditUserMposActivityApplyInfo";
	
	/**
	 * 业务类型：审核线上活动（传统POS）申请
	 */
	public static final String AuthVerfiy_AgentSysAuditUserTraposActivityApplyInfo = "AuthVerfiy_AgentSysAuditUserTraposActivityApplyInfo";
	
	
	/**
	 * 业务类型：新增MPOS系统考核
	 */
	public static final String AuthVerfiy_AgentAddUserMposAssessInfo = "AuthVerfiy_AgentAddUserMposAssessInfo";
	/**
	 * 业务类型：删除MPOS系统考核
	 */
	public static final String AuthVerfiy_AgentDelUserMposAssessInfo = "AuthVerfiy_AgentDelUserMposAssessInfo";
	
	
	/**
	 * 业务类型：新增传统POS系统考核
	 */
	public static final String AuthVerfiy_AgentAddUserTraposAssessInfo = "AuthVerfiy_AgentAddUserTraposAssessInfo";
	/**
	 * 业务类型：删除传统POS系统考核
	 */
	public static final String AuthVerfiy_AgentDelUserTraposAssessInfo = "AuthVerfiy_AgentDelUserTraposAssessInfo";
	
	
	/**
	 * 业务类型：审核刷卡费率（MPOS）申请
	 */
	public static final String AuthVerfiy_AgentSysAuditUserApplyCardrateMposRecord = "AuthVerfiy_AgentSysAuditUserApplyCardrateMposRecord";
	/**
	 * 业务类型：审核刷卡费率（传统POS）申请
	 */
	public static final String AuthVerfiy_AgentSysAuditUserApplyCardrateTraposRecord = "AuthVerfiy_AgentSysAuditUserApplyCardrateTraposRecord";

	/**
	 * 业务类型：修改系统MPOS
	 */
	public static final String AuthVerfiy_AgentEditSysMposInfo = "AuthVerfiy_AgentEditSysMposInfo";
	/**
	 * 业务类型：删除系统MPOS
	 */
	public static final String AuthVerfiy_AgentDelSysMposInfo = "AuthVerfiy_AgentDelSysMposInfo";
	
	/**
	 * 业务类型：修改系统传统POS
	 */
	public static final String AuthVerfiy_AgentEditSysTraditionalPosInfo = "AuthVerfiy_AgentEditSysTraditionalPosInfo";
	/**
	 * 业务类型：删除系统MPOS
	 */
	public static final String AuthVerfiy_AgentDelSysTraditionalPosInfo = "AuthVerfiy_AgentDelSysTraditionalPosInfo";
	
	/**
	 * 业务类型：删除系统流量卡
	 */
	public static final String AuthVerfiy_AgentDelSysTrafficCardInfo = "AuthVerfiy_AgentDelSysTrafficCardInfo";
	
	/**
	 * 业务类型：新增用户MPOS信息
	 */
	public static final String AuthVerfiy_AgentAddUserMposInfo = "AuthVerfiy_AgentAddUserMposInfo";

	/**
	 * 业务类型：新增用户MPOS信息byqh
	 */
	public static final String AuthVerfiy_AgentAddUserAgentMposInfo = "AuthVerfiy_AgentAddUserAgentMposInfo";

	/**
	 * 业务类型：修改用户MPOS
	 */
	public static final String AuthVerfiy_AgentEditUserMposInfo = "AuthVerfiy_AgentEditUserMposInfo";
	
	/**
	 * 业务类型：新增用户传统POS
	 */
	public static final String AuthVerfiy_AgentAddUserTraditionalPosInfo = "AuthVerfiy_AgentAddUserTraditionalPosInfo";

	public static final String AuthVerfiy_AgentAddUserEposInfo="AuthVerfiy_AgentAddUserEposInfo";

	public static final String AuthVerfiy_AgentAddUserAgentEposInfo="AuthVerfiy_AgentAddUserAgentEposInfo";

	/**
	 * 业务类型：新增用户传统POS代理byqh
	 */
	public static final String AuthVerfiy_AgentAddUserAgentTraditionalPosInfo = "AuthVerfiy_AgentAddUserAgentTraditionalPosInfo";

	/**
	 * 业务类型：修改用户传统POS
	 */
	public static final String AuthVerfiy_AgentEditUserTraditionalPosInfo = "AuthVerfiy_AgentEditUserTraditionalPosInfo";
	
	/**
	 * 业务类型：新增用户流量卡信息
	 */
	public static final String AuthVerfiy_AgentAddUserTrafficCardInfo = "AuthVerfiy_AgentAddUserTrafficCardInfo";
	
	
	/**
	 * 业务类型：审核用户扫码支付申请
	 */
	public static final String AuthVerfiy_AgentSysAuditUserApplyScanRecordInfo = "AuthVerfiy_AgentSysAuditUserApplyScanRecordInfo";
	
	
	/**
	 * 业务类型：审核用户MPOS解绑申请
	 */
	public static final String AuthVerfiy_AgentSysAuditUserMposUnbindRecordInfo = "AuthVerfiy_AgentSysAuditUserMposUnbindRecordInfo";
	
	/**
	 * 业务类型：审核用户传统POS解绑申请
	 */
	public static final String AuthVerfiy_AgentSysAuditUserTraposUnbindRecordInfo = "AuthVerfiy_AgentSysAuditUserTraposUnbindRecordInfo";
	
	//=====================================管理员模块===========================
	
	/**
	 * 业务类型：修改系统参数
	 */
	public static final String AuthVerfiy_ManaEditSysParam = "AuthVerfiy_ManaEditSysParam";
	
	/**
	 * 业务类型：新增系统费率参数
	 */
	public static final String AuthVerfiy_ManaAddSysParamRate = "AuthVerfiy_ManaAddSysParamRate";
	/**
	 * 业务类型：编辑系统费率参数
	 */
	public static final String AuthVerfiy_ManaEditSysParamRate = "AuthVerfiy_ManaEditSysParamRate";
	/**
	 * 业务类型：删除系统费率参数
	 */
	public static final String AuthVerfiy_ManaDelSysParamRate = "AuthVerfiy_ManaDelSysParamRate";
	
	


	/**
	 * 业务类型：重置后台账户密码
	 */
	public static final String AuthVerfiy_ResetUserPwd = "AuthVerfiy_ResetUserPwd";


	
	
	
	/**
	 * 业务类型：公司拨款页面
	 */
	public static final String AuthVerfiy_AddUserSysReward = "AuthVerfiy_AddUserSysReward";
	
	
	/**
	 * 业务类型：公司扣款页面
	 */
	public static final String AuthVerfiy_AddUserSysRecycle = "AuthVerfiy_AddUserSysRecycle";
	

	/**
	 * 业务类型：修改密码
	 */
	public static final String BackModifyPass = "BackModifyPass";


	
}

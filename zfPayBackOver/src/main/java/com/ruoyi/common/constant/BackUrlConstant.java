package com.ruoyi.common.constant;

import java.util.HashMap;

public class BackUrlConstant {
	
	/**
	 * 后台URL跳转
	 */
	public static final HashMap<String, String> BackUrlMap = new HashMap<String, String>();  
	static {  
		//================================代理模块============================
		//冻结解冻用户信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysFreezeUserInfo, "deveagent/userInfo/sysFreeze");
		//单个实名认证用户信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysAuthAuditUserInfo, "deveagent/userInfo/sysAuthAudit");
		//批量实名认证用户信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentBathSysAuthAuditUserInfo, "deveagent/userInfo/batchSysAuthAudit");
		//修改用户信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentEditUserInfo, "deveagent/userInfo/edit");
		//删除用户信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentDelUserInfo, "deveagent/userInfo/del");
		//修改用户报备状态
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentbatchsysBatchFilingUserInfo,"/deveagent/userInfo/batchsysBatchFiling");
		//单个审核用户结算卡
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysAuditCardInfo, "deveagent/userCard/sysCardAudit");
		//批量审核用户结算卡
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentBatchSysAuditCardInfo, "deveagent/userCard/batchSysCardAudit");
		
		//新增中付账号
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddUserAccount, "deveagent/userAccount/add");
		//修改中付账号
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentEditUserAccount, "deveagent/userAccount/edit");
		//删除中付账号
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentDelUserAccount, "deveagent/userAccount/del");
		//开启关闭中付账号
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysOpenUserAccount, "deveagent/userAccount/sysOpen");
		
		//处理提现申请
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysAuditUserCash, "deveagent/userCashRecord/sysAudit");
		
		//新增线上活动（MPOS）
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddSysMposActivityInfo, "deveagent/sysMposActivityInfo/add");
		//编辑线上活动（MPOS）
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentEditSysMposActivityInfo, "deveagent/sysMposActivityInfo/edit");
		//删除线上活动（MPOS）
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentDelSysMposActivityInfo, "deveagent/sysMposActivityInfo/del");
		//发布和取消线上活动（MPOS）
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysReleaseSysMposActivityInfo, "deveagent/sysMposActivityInfo/sysRelease");


		//新增线上活动（EPOS）
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddSysEposActivityInfo, "deveagent/sysEposActivityInfo/add");
		//编辑线上活动（EPOS）
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentEditSysEposActivityInfo, "deveagent/sysEposActivityInfo/edit");
		//删除线上活动（EPOS）
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentDelSysEposActivityInfo, "deveagent/sysEposActivityInfo/del");
		//发布和取消线上活动（EPOS）
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysReleaseSysEposActivityInfo, "deveagent/sysEposActivityInfo/sysRelease");


		//新增线上活动（传统POS）
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddSysTraposActivityInfo, "deveagent/sysTraposActivityInfo/add");
		//编辑线上活动（传统POS）
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentEditSysTraposActivityInfo, "deveagent/sysTraposActivityInfo/edit");
		//删除线上活动（传统POS）
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentDelSysTraposActivityInfo, "deveagent/sysTraposActivityInfo/del");
		//发布和取消线上活动（传统POS）
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysReleaseSysTraposActivityInfo, "deveagent/sysTraposActivityInfo/sysRelease");
		
		//新增线上活动（MPOS）奖励类型信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddSysMposActivityRewardInfo, "deveagent/sysMposActivityRewardInfo/addReward");
		//编辑线上活动（MPOS）奖励类型信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentEditSysMposActivityRewardInfo, "deveagent/sysMposActivityRewardInfo/edit");
		//删除线上活动（MPOS）奖励类型信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentDelSysMposActivityRewardInfo, "deveagent/sysMposActivityRewardInfo/del");
		
		//新增线上活动（MPOS）奖励类型信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddSysTraposActivityRewardInfo, "deveagent/sysTraposActivityRewardInfo/addReward");
		//编辑线上活动（MPOS）奖励类型信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentEditSysTraposActivityRewardInfo, "deveagent/sysTraposActivityRewardInfo/edit");
		//删除线上活动（MPOS）奖励类型信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentDelSysTraposActivityRewardInfo, "deveagent/sysTraposActivityRewardInfo/del");
		
		//审核线上活动（MPOS）申请
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysAuditUserMposActivityApplyInfo, "deveagent/userMposActivityApplyInfo/sysAudit");
		
		//审核线上活动（传统POS）申请
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysAuditUserTraposActivityApplyInfo, "deveagent/userTraposActivityApplyInfo/sysAudit");
		
		//新增MPOS系统考核
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddUserMposAssessInfo, "deveagent/userMposAssessInfo/selectUser");
		//删除MPOS系统考核
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentDelUserMposAssessInfo, "deveagent/userMposAssessInfo/del");
		
		//新增传统POS系统考核
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddUserTraposAssessInfo, "deveagent/userTraposAssessInfo/selectUser");
		//删除传统POS系统考核
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentDelUserTraposAssessInfo, "deveagent/userTraposAssessInfo/del");
		
		//MPOS刷卡费率申请审核
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysAuditUserApplyCardrateMposRecord, "deveagent/userApplyCardrateMposRecord/sysAudit");
		//传统POS刷卡费率申请审核
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysAuditUserApplyCardrateTraposRecord, "deveagent/userApplyCardrateTraposRecord/sysAudit");
		
		//修改系统MPOS
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentEditSysMposInfo, "deveagent/sysMposInfo/edit");
		//删除系统MPOS
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentDelSysMposInfo, "deveagent/sysMposInfo/del");
		
		//修改系统传统POS
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentEditSysTraditionalPosInfo, "deveagent/sysTraditionalPosInfo/edit");
		//删除系统传统POS
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentDelSysTraditionalPosInfo, "deveagent/sysTraditionalPosInfo/del");
		
		//删除系统流量卡
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentDelSysTrafficCardInfo, "deveagent/sysTrafficCardInfo/del");
		
		//新增用户MPOS信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddUserMposInfo, "deveagent/userMposInfo/selectUser");
		//修改用户MPOS
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentEditUserMposInfo, "deveagent/userMposInfo/edit");

		//批量修改用户结算低价add byqh202006
		BackUrlMap.put(VerifyConstant.AuthVerfiy_SelectOneAgentPos, "deveagent/userMposInfo/selectOneAgentPos");
		//批量修改用户结算低价add byqh202006
		BackUrlMap.put(VerifyConstant.AuthVerfiy_SelectOneAgentTrapos, "deveagent/userTraditionalPosInfo/selectOneAgentPos");
		//批量修改用户结算低价add byqh202006
		BackUrlMap.put(VerifyConstant.AuthVerfiy_SelectOneAgentEpos, "deveagent/userEposInfo/selectOneAgentPos");

		//新增一级代理商MPOS分配给他的二级代理商byqh
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddUserAgentMposInfo, "deveagent/userMposInfo/selectAgentUser");
		
		//新增用户传统POS
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddUserTraditionalPosInfo, "deveagent/userTraditionalPosInfo/selectUser");

		//新增用户传统POS add byqh202003
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddUserEposInfo, "deveagent/userEposInfo/selectUser");

		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddUserAgentEposInfo,"deveagent/userEposInfo/selectAgentUser");

		//新增一级代理商TradePOS分配给他的二级代理商byqh
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddUserAgentTraditionalPosInfo, "deveagent/userTraditionalPosInfo/selectAgentUser");

		//修改用户传统POS
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentEditUserTraditionalPosInfo, "deveagent/userTraditionalPosInfo/edit");
		
		//新增用户流量卡信息
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentAddUserTrafficCardInfo, "deveagent/userTrafficCardInfo/selectUser");
		
		//审核用户扫码支付申请
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysAuditUserApplyScanRecordInfo, "deveagent/userApplyScanRecordInfo/sysAudit");
		
		//审核用户MPOS解绑申请
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysAuditUserMposUnbindRecordInfo, "deveagent/userMposUnbindRecordInfo/sysAudit");
		
		//审核用户传统POS解绑申请
		BackUrlMap.put(VerifyConstant.AuthVerfiy_AgentSysAuditUserTraposUnbindRecordInfo, "deveagent/userTraposUnbindRecordInfo/sysAudit");

				
		
		//================================管理员模块============================
		//修改系统参数
		BackUrlMap.put(VerifyConstant.AuthVerfiy_ManaEditSysParam, "devemana/sysParam/edit");
		
		//新增中付账号
		BackUrlMap.put(VerifyConstant.AuthVerfiy_ManaAddSysParamRate, "devemana/sysParamRate/add");
		//修改中付账号
		BackUrlMap.put(VerifyConstant.AuthVerfiy_ManaEditSysParamRate, "devemana/sysParamRate/edit");
		//删除中付账号
		BackUrlMap.put(VerifyConstant.AuthVerfiy_ManaDelSysParamRate, "devemana/sysParamRate/del");
		
		
		
		
		
		//===============================系统模块============================
		//重置后台账户密码
		BackUrlMap.put(VerifyConstant.AuthVerfiy_ResetUserPwd, "system/user/resetPwd");
		
	}
	
}

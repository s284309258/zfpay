package com.ruoyi.project.deveagent.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.SysParamConstant;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.constant.ZhongFuInterfaceCodeConstant;
import com.ruoyi.common.utils.Base64Utils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.account.domain.AgentUserReportRecord;
import com.ruoyi.project.deveagent.account.mapper.AgentUserAccountMapper;
import com.ruoyi.project.deveagent.account.mapper.AgentUserReportRecordDetailMapper;
import com.ruoyi.project.deveagent.account.mapper.AgentUserReportRecordMapper;
import com.ruoyi.project.deveagent.account.service.AgentUserReportRecordService;
import com.ruoyi.project.deveagent.user.mapper.AgentUserCardMapper;
import com.ruoyi.project.deveagent.user.mapper.AgentUserInfoMapper;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.develop.task.service.ZhongFuInterfaceService;

import net.sf.json.JSONArray;


/**
 * 代理====》代理报备管理
 * @author Administrator
 *
 */
@Service
public class AgentUserReportRecordServiceImpl implements AgentUserReportRecordService {
	
	@Autowired
	private ZhongFuInterfaceService zhongFuInterfaceService;
	
	@Autowired
	private AgentUserInfoMapper agentUserInfoMapper;
	@Autowired
	private AgentUserAccountMapper agentUserAccountMapper;
	@Autowired
	private AgentUserCardMapper agentUserCardMapper;
	@Autowired
	private AgentUserReportRecordMapper agentUserReportRecordMapper;
	@Autowired
	private AgentUserReportRecordDetailMapper agentUserReportRecordDetailMapper;


	
	/**
	 * 查询代理报备列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserReportRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserReportRecordMapper.getAgentUserReportRecordList(params);
	}
	
	
	/**
	 * 导出代理报备列表
	 */
	@Override
	public List<AgentUserReportRecord> selectAgentUserReportRecordList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserReportRecordMapper.selectAgentUserReportRecordList(params);
	}


	/**
	 * 代理报备
	 */
	@Override
	@Transactional
	public R sysReport(Map<String, Object> params) {
		try {
			if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "请输入操作备注");
			}
			int i=0;
			//（1）代理账号信息校验
			params.put("manager_id", ShiroUtils.getUserId());//代理编号
			Map<String, Object> userAccountMap = agentUserAccountMapper.getAgentUserAccount(params);
			if(userAccountMap==null) {
				return R.error(Type.WARN, "该代理账号无效，请重新选择设置");
			}
			//（2）用户信息校验
			Map<String, Object> userInfoMap = agentUserInfoMapper.getAgentUserMapInfo(params);
			if(userInfoMap==null) {
				return R.error(Type.WARN, "该用户信息无效");
			}
			if(!TypeStatusConstant.user_info_auth_status_09.equals(userInfoMap.get("auth_status").toString())) {
				return R.error(Type.WARN, "该用户未实名认证成功，不能报备");
			}
			//（3）结算账号信息校验
			Map<String, Object> userCardMap = agentUserCardMapper.getAgentUserCard(params);
			if(userCardMap==null) {
				return R.error(Type.WARN, "该结算账号信息无效");
			}
			//（4）子级代理身份证图片
			Integer card_photo_num = Integer.parseInt(params.get("card_photo_num").toString());//身份证图片第几张
			String card_photo_url = SysParamConstant.qiniu_domain+"/"+userInfoMap.get("card_photo").toString().split(",")[card_photo_num];//线上图片路径URL
			//七牛线上图片BASE64位流
			String subAgentIdImg = Base64Utils.ImageToBase64ByOnline(card_photo_url+"?imageView2/1/w/1080/h/1920");
			subAgentIdImg = subAgentIdImg.replaceAll("\r","").replaceAll("\n","");
			//（5）请求数据对象
			Map<String, Object> detailsMap = new HashMap<String, Object>();
			detailsMap.put("subAgentAccount", userInfoMap.get("user_tel").toString());//子级代理账户
			detailsMap.put("subAgentName", userInfoMap.get("real_name").toString());//子级代理名称
			detailsMap.put("subAgentIdNum", userInfoMap.get("id_card").toString());//子级代理身份证号
			detailsMap.put("subAgentIdImg", subAgentIdImg);//子级代理身份证图片
			detailsMap.put("subAgentSettAccount", userCardMap.get("account").toString());//子级代理结算账号
			detailsMap.put("isReport", ZhongFuInterfaceCodeConstant.user_report_record_detail_is_report_1);//报备信息标识：只报备代理信息
			R reportResult = zhongFuInterfaceService.requestType7001(userAccountMap.get("app_id").toString(),JSONArray.fromObject(detailsMap),userAccountMap.get("app_key").toString());
			if(!R.Type.SUCCESS.value.equals(reportResult.get("code").toString())) {
				return reportResult;
			}
			//（6）保存代理报备信息
			Map<String, Object> userReportRecordMap = new HashMap<>();
			userReportRecordMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			userReportRecordMap.put("account_id", params.get("account_id").toString());//代理账号id
			userReportRecordMap.put("agent_account", userAccountMap.get("app_id").toString());//代理账号
			userReportRecordMap.put("cre_date", TimeUtil.getDayString());//创建日期
			userReportRecordMap.put("cre_time", TimeUtil.getTimeString());//创建时间
			userReportRecordMap.put("remark", params.get("remark").toString());//备注
			userReportRecordMap.put("create_by", ShiroUtils.getLoginName());//创建人
			userReportRecordMap.put("id", null);
			i = agentUserReportRecordMapper.addAgentUserReportRecord(userReportRecordMap);
			if(i != 1) {
				return R.error(Type.WARN, "代理报备信息记录失败");
			}
			//（7）保存代理报备详情
			userReportRecordMap.put("user_id", params.get("user_id").toString());//用户编号
			userReportRecordMap.put("record_id", userReportRecordMap.get("id").toString());//记录编号
			userReportRecordMap.put("sub_agent_account", userInfoMap.get("user_tel").toString());//子级代理账户
			userReportRecordMap.put("sub_agent_name", userInfoMap.get("real_name").toString());//子级代理名称
			userReportRecordMap.put("sub_agent_id_num", userInfoMap.get("id_card").toString());//子级代理身份证号
			userReportRecordMap.put("sub_agent_id_img", userInfoMap.get("card_photo").toString().split(",")[card_photo_num]);//子级代理身份证图片Base64
			userReportRecordMap.put("sub_agent_sett_account", userCardMap.get("account").toString());//子级代理结算账号
			userReportRecordMap.put("is_report", ZhongFuInterfaceCodeConstant.user_report_record_detail_is_report_1);//报备信息标识：只报备代理信息
			i = agentUserReportRecordDetailMapper.addAgentUserReportRecordDetail(userReportRecordMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "代理报备信息详情记录失败");
			}
			//（8）更新用户报备状态
			i = agentUserInfoMapper.updateAgentUserReportStatus(userReportRecordMap);
			return R.ok("报备成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "报备异常");
		}
	}

}

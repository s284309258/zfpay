package com.ruoyi.project.deveagent.usertrapos.service.impl;

import com.ruoyi.common.constant.SysTableNameConstant;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.BasicSerivce;
import com.ruoyi.common.utils.json.NetJsonUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.string.TrimUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.syspos.mapper.AgentSysTraditionalPosInfoMapper;
import com.ruoyi.project.deveagent.syspospolicy.service.SysPosPolicyServiceImpl;
import com.ruoyi.project.deveagent.user.mapper.AgentUserInfoMapper;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentSelectUserTraditionalPosInfo;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraditionalPosInfo;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserEPosInfoMapper;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraditionalPosInfoMapper;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserEPosInfoService;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraditionalPosInfoService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.param.mapper.ManaSysEditMapper;
import com.ruoyi.project.devemana.param.mapper.ManaSysParamRateMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 代理====》用户传统POS信息管理
 * @author Administrator
 *
 */
@Service
public class AgentUserEPosInfoServiceImpl extends BasicSerivce implements AgentUserEPosInfoService {

	@Autowired
	private ManaSysEditMapper manaSysEditMapper;
	@Autowired
	private AgentUserEPosInfoMapper agentUserEPosInfoMapper;

	@Autowired
	private AgentUserTraditionalPosInfoMapper agentUserTraditionalPosInfoMapper;

	@Autowired
	private AgentUserInfoMapper agentUserInfoMapper;
	@Autowired
	private ManaSysParamRateMapper manaSysParamRateMapper;
	@Autowired
	private AgentSysTraditionalPosInfoMapper agentSysTraditionalPosInfoMapper;

	@Autowired
	private SysPosPolicyServiceImpl sysPosPolicyService;



	/**
	 * 查询用户传统POS信息列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTraditionalPosInfoList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserEPosInfoMapper.getAgentUserTraditionalPosInfoList(params);
	}


	/**
	 * 导出用户传统POS信息列表
	 */
	@Override
	public List<AgentUserTraditionalPosInfo> selectAgentUserTraditionalPosInfoList(Map<String, Object> params) {
		MapChainParams(params);
		return agentUserEPosInfoMapper.selectAgentUserTraditionalPosInfoList(params);
	}


	/**
	 * 导入用户传统POS数据
	 */
	@Override
	public R importAgentUserTraditionalPosInfoList(List<AgentUserTraditionalPosInfo> agentuserTraditionalPosInfoList,
			String user_id) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if (StringUtil.isNull(agentuserTraditionalPosInfoList) || agentuserTraditionalPosInfoList.size() == 0){
			return R.error(Type.WARN, "导入用户传统POS数据不能为空！");
        }
		//校验是否是一级代理用户并且实名认证
		Map<String, Object> params = new HashMap<>();
		params.put("user_id", user_id);//用户编号
		params.put("manager_id", ShiroUtils.getUserId());//代理编号
		Map<String, Object> userMap = agentUserInfoMapper.getAgentFirstAgentUserInfo(params);
		if(userMap==null) {
			return R.error(Type.WARN, "只能给属于自己的一级代理分配POS机");
		}
		if(!TypeStatusConstant.user_info_auth_status_09.equals(userMap.get("auth_status").toString())) {
			return R.error(Type.WARN, "该用户未实名认证，不能分配POS机");
		}

		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        for(int i=0;i<agentuserTraditionalPosInfoList.size();i++) {
        	//依次导入每一个传统POS机信息
        	agentuserTraditionalPosInfoList.get(i).setUser_id(user_id);//用户编号
        	R result = SpringUtils.getAopProxy(this).importAgentUserTraditionalPosInfo(agentuserTraditionalPosInfoList.get(i),i);
        	if(Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "导入结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "导入结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 导入单条数据
	 * @param agentUserTraditionalPosInfo
	 * @param m
	 * @return
	 */
	public R importAgentUserTraditionalPosInfo(AgentUserTraditionalPosInfo agentUserTraditionalPosInfo, int m) {
		try {
			agentUserTraditionalPosInfo = (AgentUserTraditionalPosInfo) TrimUtil.trimObject(agentUserTraditionalPosInfo);
			if(StringUtils.isEmpty(agentUserTraditionalPosInfo.getSn())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：设备号（机器编号）必填");
			}
			if(StringUtils.isEmpty(agentUserTraditionalPosInfo.getCard_settle_price())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：刷卡结算底价必填");
			}
			if(StringUtils.isEmpty(agentUserTraditionalPosInfo.getCard_settle_price_vip())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：VIP刷卡结算底价必填");
			}
			if(StringUtils.isEmpty(agentUserTraditionalPosInfo.getCloud_settle_price())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：云闪付结算底价必填 ");
			}
			if(StringUtils.isEmpty(agentUserTraditionalPosInfo.getWeixin_settle_price())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：微信结算底价必填");
			}
			if(StringUtils.isEmpty(agentUserTraditionalPosInfo.getZhifubao_settle_price())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：支付宝结算底价必填 ");
			}
			if(StringUtils.isEmpty(agentUserTraditionalPosInfo.getSingle_profit_rate())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：单笔分润比例（%）必填");
			}
			if(StringUtils.isEmpty(agentUserTraditionalPosInfo.getCash_back_rate())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：返现比例（%）必填");
			}
			if(StringUtils.isEmpty(agentUserTraditionalPosInfo.getMer_cap_fee())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：封顶费必填 ");
			}
			//（1）查询刷卡结算底价参数是否有效
			List<Map<String, Object>> sysParamRateList1 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_2,agentUserTraditionalPosInfo.getCard_settle_price());
			if(sysParamRateList1.size()<=0) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：刷卡结算底价数值异常");
			}
			//（2）查询云闪付结算底价参数是否有效
			List<Map<String, Object>> sysParamRateList2 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_3,agentUserTraditionalPosInfo.getCloud_settle_price());
			if(sysParamRateList2.size()<=0) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：云闪付结算底价数值异常");
			}
			//（3）查询微信结算底价参数是否有效
			List<Map<String, Object>> sysParamRateList3 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_5,agentUserTraditionalPosInfo.getWeixin_settle_price());
			if(sysParamRateList3.size()<=0) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：微信结算底价数值异常");
			}
			//（4）查询支付宝结算底价参数是否有效
			List<Map<String, Object>> sysParamRateList4 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_7,agentUserTraditionalPosInfo.getZhifubao_settle_price());
			if(sysParamRateList4.size()<=0) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：支付宝结算底价数值异常");
			}
			//（5）查询单笔分润比例参数是否有效
			List<Map<String, Object>> sysParamRateList5 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_9,agentUserTraditionalPosInfo.getSingle_profit_rate());
			if(sysParamRateList5.size()<=0) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：单笔分润比例（%）数值异常");
			}
			//（6）查询返现比例参数是否有效
			List<Map<String, Object>> sysParamRateList6 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_10,agentUserTraditionalPosInfo.getCash_back_rate());
			if(sysParamRateList6.size()<=0) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：返现比例（%）数值异常");
			}
			//（7）查询封顶费参数是否有效
			List<Map<String, Object>> sysParamRateList7 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_11,agentUserTraditionalPosInfo.getMer_cap_fee());
			if(sysParamRateList7.size()<=0) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：封顶费数值异常");
			}
			int i=0;
			String cre_date = TimeUtil.getDayString();//创建日期
			String cre_time = TimeUtil.getTimeString();//创建时间
			//（8）更新该系统POS机的分配状态
			Map<String, Object> sysTraditionalPosMap = new HashMap<String, Object>();
			sysTraditionalPosMap.put("old_dis_status", TypeStatusConstant.sys_pos_info_dis_status_0);//旧状态：未分配
			sysTraditionalPosMap.put("new_dis_status", TypeStatusConstant.sys_pos_info_dis_status_1);//旧状态：已分配
			sysTraditionalPosMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			sysTraditionalPosMap.put("sn", agentUserTraditionalPosInfo.getSn());//设备号（机器编号）
			sysTraditionalPosMap.put("update_by", ShiroUtils.getLoginName());//代理编号
			sysTraditionalPosMap.put("up_date", cre_date);//更新日期
			sysTraditionalPosMap.put("up_time", cre_time);//更新时间
			i = agentSysTraditionalPosInfoMapper.updateAgentSysTraditionalPosInfoDisStatus(sysTraditionalPosMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：系统传统POS分配状态更新失败");
			}
			//（9）查询是否已经存在该用户和传统POS的关系
			Map<String, Object> userTraditionalPosMap = agentUserEPosInfoMapper.getAgentUserTraditionalPosInfo(agentUserTraditionalPosInfo);
			//（10）处理关系
			agentUserTraditionalPosInfo.setCre_date(cre_date);//更新日期
			agentUserTraditionalPosInfo.setCre_time(TimeUtil.getTimeString());//更新时间
			agentUserTraditionalPosInfo.setCreate_by(ShiroUtils.getLoginName());//创建人
			agentUserTraditionalPosInfo.setState_status(TypeStatusConstant.user_pos_info_state_status_1);//归属状态：直属
			agentUserTraditionalPosInfo.setDel(TypeStatusConstant.user_pos_info_del_0);//删除状态：未删除
			if(userTraditionalPosMap!=null) {
				//已存在，更新该关系的信息
				agentUserTraditionalPosInfo.setId(Integer.parseInt(userTraditionalPosMap.get("id").toString()));
				i = agentUserEPosInfoMapper.updateAgentUserTraditionalPosInfoByDis(agentUserTraditionalPosInfo);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：用户传统POS关系更新失败");
				}
			}else {
				i = agentUserEPosInfoMapper.addAgentUserTraditionalPosInfoByDis(agentUserTraditionalPosInfo);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：用户传统POS关系建立失败");
				}
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "第"+(m+1)+"条数据导入失败：数据导入异常");
		}
	}


	/**
	 * 导出可分配的传统pos导入模板
	 */
	@Override
	public List<AgentSelectUserTraditionalPosInfo> selectAgentNoDisSysTraditionalPosInfoList(
			Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSysTraditionalPosInfoMapper.selectAgentNoDisSysTraditionalPosInfoList(params);
	}


	/**
	 * 新增保存用户传统POS机信息
	 */
	@Override
	public R addAgentUserTraditionalPosInfo(Map<String, Object> params) {
//		if(!ShiroUtils.getSysUser().isAuth()) {
//			return R.error(Type.WARN, "身份信息未认证，不能操作");
//		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
			return R.error(Type.WARN, "操作备注不能为空");
		}
		//校验是否是一级代理用户并且实名认证
		params.put("manager_id", getShiroUserInfo().getManager_id());//代理编号
		Map<String, Object> userMap = agentUserInfoMapper.getAgentFirstAgentUserInfo(params);
		if(userMap==null) {
			return R.error(Type.WARN, "只能给属于自己的一级代理分配POS机");
		}
		if(!TypeStatusConstant.user_info_auth_status_09.equals(userMap.get("auth_status").toString())) {
			return R.error(Type.WARN, "该用户未实名认证，不能分配POS机");
		}

		//（1）查询刷卡结算底价参数是否有效
		List<Map<String, Object>> sysParamRateList1 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_2,StringUtil.getMapValue(params, "card_settle_price"));
		if(sysParamRateList1.size()<=0) {
			return R.error(Type.WARN, "刷卡结算底价数值异常");
		}
		//（2）查询云闪付结算底价参数是否有效
		List<Map<String, Object>> sysParamRateList2 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_3,StringUtil.getMapValue(params, "cloud_settle_price"));
		if(sysParamRateList2.size()<=0) {
			return R.error(Type.WARN, "云闪付结算底价数值异常");
		}
		//（3）查询微信结算底价参数是否有效
		List<Map<String, Object>> sysParamRateList3 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_5,StringUtil.getMapValue(params, "weixin_settle_price"));
		if(sysParamRateList3.size()<=0) {
			return R.error(Type.WARN, "微信结算底价数值异常");
		}
		//（4）查询支付宝结算底价参数是否有效
		List<Map<String, Object>> sysParamRateList4 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_7,StringUtil.getMapValue(params, "zhifubao_settle_price"));
		if(sysParamRateList4.size()<=0) {
			return R.error(Type.WARN, "支付宝结算底价数值异常");
		}
		//（5）查询单笔分润比例参数是否有效
		List<Map<String, Object>> sysParamRateList5 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_9,StringUtil.getMapValue(params, "single_profit_rate"));
		if(sysParamRateList5.size()<=0) {
			return R.error(Type.WARN, "单笔分润比例（%）数值异常");
		}
		//（6）查询返现比例参数是否有效
		List<Map<String, Object>> sysParamRateList6 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_10,StringUtil.getMapValue(params, "cash_back_rate"));
		if(sysParamRateList6.size()<=0) {
			return R.error(Type.WARN, "返现比例（%）数值异常");
		}
		//（7）查询封顶费参数是否有效
		List<Map<String, Object>> sysParamRateList7 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_11,StringUtil.getMapValue(params, "mer_cap_fee"));
		if(sysParamRateList7.size()<=0) {
			return R.error(Type.WARN, "封顶费数值异常");
		}
		//（8）查询刷卡结算底价参数是否有效
		List<Map<String, Object>> sysParamRateList8 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_2,StringUtil.getMapValue(params, "card_settle_price_vip"));
		if(sysParamRateList8.size()<=0) {
			return R.error(Type.WARN, "VIP刷卡结算底价数值异常");
		}

		//用户传统POS机对象
		AgentUserTraditionalPosInfo agentUserTraditionalPosInfo = new AgentUserTraditionalPosInfo();
		agentUserTraditionalPosInfo.setUser_id(params.get("user_id").toString());//用编号
		agentUserTraditionalPosInfo.setCard_settle_price(params.get("card_settle_price").toString());//刷卡结算底价
		agentUserTraditionalPosInfo.setCloud_settle_price(params.get("cloud_settle_price").toString());//云闪付结算底价
		agentUserTraditionalPosInfo.setWeixin_settle_price(params.get("weixin_settle_price").toString());//微信结算底价
		agentUserTraditionalPosInfo.setZhifubao_settle_price(params.get("zhifubao_settle_price").toString());//支付宝结算底价
		agentUserTraditionalPosInfo.setSingle_profit_rate(params.get("single_profit_rate").toString());//单笔分润比例
		agentUserTraditionalPosInfo.setCash_back_rate(params.get("cash_back_rate").toString());//返现比例
		agentUserTraditionalPosInfo.setMer_cap_fee(params.get("mer_cap_fee").toString());//封顶费
		agentUserTraditionalPosInfo.setRemark(params.get("remark").toString());//备注

		//add begin byqh 201912
		agentUserTraditionalPosInfo.setCard_settle_price_vip(params.get("card_settle_price_vip").toString());
		agentUserTraditionalPosInfo.setCloud_settle_price_vip(params.get("cloud_settle_price_vip").toString());
		agentUserTraditionalPosInfo.setWeixin_settle_price_vip(params.get("weixin_settle_price_vip").toString());
		agentUserTraditionalPosInfo.setZhifubao_settle_price_vip(params.get("zhifubao_settle_price_vip").toString());
        agentUserTraditionalPosInfo.setIs_reward(params.get("is_reward").toString());
		agentUserTraditionalPosInfo.setIs_reward1(params.get("is_reward1").toString());
		//add end byqh 201912
		if(TypeStatusConstant.sys_add_type_1.equals(StringUtil.getMapValue(params, "add_type"))) {
			//add byqh 201912
//			sysPosPolicyService.insertPolicySNInfo(String.valueOf(params.get("policy")),params.get("pos_sns").toString(),params.get("user_id").toString(),"epos");
			//批量处理
			return this.batchAddAgentUserTraditionalPosInfo(agentUserTraditionalPosInfo, params);
		}else if(TypeStatusConstant.sys_add_type_2.equals(StringUtil.getMapValue(params, "add_type"))) {
			//自增处理
			return this.autoAddAgentUserTraditionalPosInfo(agentUserTraditionalPosInfo, params);
		}else {
			return R.error(Type.WARN, "新增类型异常");
		}
	}

	/***
	 *选中POS分配给代理保存操作byqh update byqh 201912
	 * @param params
	 * @return
	 */
	@Override
	@Transactional
	public R addSubAgentUserTraditionalPosInfo(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
			return R.error(Type.WARN, "操作备注不能为空");
		}
		//校验是否是一级代理用户并且实名认证
		params.put("manager_id", ShiroUtils.getUserId());//代理编号
		Map<String, Object> userMap = agentUserInfoMapper.getAgentFirstAgentUserInfo(params);
		if(userMap==null) {
			return R.error(Type.WARN, "只能给属于自己的一级代理分配POS机");
		}
		if(!TypeStatusConstant.user_info_auth_status_09.equals(userMap.get("auth_status").toString())) {
			return R.error(Type.WARN, "该用户未实名认证，不能分配POS机");
		}

		//（1）查询刷卡结算底价参数是否有效
		List<Map<String, Object>> sysParamRateList1 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_2,StringUtil.getMapValue(params, "card_settle_price"));
		if(sysParamRateList1.size()<=0) {
			return R.error(Type.WARN, "刷卡结算底价数值异常");
		}
		//（2）查询云闪付结算底价参数是否有效
		List<Map<String, Object>> sysParamRateList2 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_3,StringUtil.getMapValue(params, "cloud_settle_price"));
		if(sysParamRateList2.size()<=0) {
			return R.error(Type.WARN, "云闪付结算底价数值异常");
		}
		//（3）查询微信结算底价参数是否有效
		List<Map<String, Object>> sysParamRateList3 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_5,StringUtil.getMapValue(params, "weixin_settle_price"));
		if(sysParamRateList3.size()<=0) {
			return R.error(Type.WARN, "微信结算底价数值异常");
		}
		//（4）查询支付宝结算底价参数是否有效
		List<Map<String, Object>> sysParamRateList4 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_7,StringUtil.getMapValue(params, "zhifubao_settle_price"));
		if(sysParamRateList4.size()<=0) {
			return R.error(Type.WARN, "支付宝结算底价数值异常");
		}
		//（5）查询单笔分润比例参数是否有效
		List<Map<String, Object>> sysParamRateList5 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_9,StringUtil.getMapValue(params, "single_profit_rate"));
		if(sysParamRateList5.size()<=0) {
			return R.error(Type.WARN, "单笔分润比例（%）数值异常");
		}
		//（6）查询返现比例参数是否有效
		List<Map<String, Object>> sysParamRateList6 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_10,StringUtil.getMapValue(params, "cash_back_rate"));
		if(sysParamRateList6.size()<=0) {
			return R.error(Type.WARN, "返现比例（%）数值异常");
		}
		//（7）查询封顶费参数是否有效
		List<Map<String, Object>> sysParamRateList7 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_11,StringUtil.getMapValue(params, "mer_cap_fee"));
		if(sysParamRateList7.size()<=0) {
			return R.error(Type.WARN, "封顶费数值异常");
		}

		//（8）查询刷卡结算底价参数是否有效
		List<Map<String, Object>> sysParamRateList8 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_2,StringUtil.getMapValue(params, "card_settle_price_vip"));
		if(sysParamRateList8.size()<=0) {
			return R.error(Type.WARN, "VIP刷卡结算底价数值异常");
		}


		Map<String, Object> baseMposInfo = agentUserEPosInfoMapper.getAgentUserTraditionalPosInfoById(params.get("user_pos_id").toString());
		Double original_card_settle_price = Double.parseDouble(baseMposInfo.get("card_settle_price").toString());
		Double original_cloud_settle_price = Double.parseDouble(baseMposInfo.get("cloud_settle_price").toString());
		Double current_card_settle_price = Double.parseDouble(StringUtil.getMapValue(params, "card_settle_price"));
		Double current_cloud_settle_price = Double.parseDouble(StringUtil.getMapValue(params, "cloud_settle_price"));

		Double original_weixin_settle_price = Double.parseDouble(baseMposInfo.get("weixin_settle_price").toString());
		Double original_zhifubao_settle_price = Double.parseDouble(baseMposInfo.get("zhifubao_settle_price").toString());
		Double current_weixin_settle_price = Double.parseDouble(StringUtil.getMapValue(params, "weixin_settle_price"));
		Double current_zhifubao_settle_price = Double.parseDouble(StringUtil.getMapValue(params, "zhifubao_settle_price"));

		Double original_single_profit_rate = Double.parseDouble(baseMposInfo.get("single_profit_rate").toString());
		Double original_cash_back_rate = Double.parseDouble(baseMposInfo.get("cash_back_rate").toString());
		Double current_single_profit_rate = Double.parseDouble(StringUtil.getMapValue(params, "single_profit_rate"));
		Double current_cash_back_rate = Double.parseDouble(StringUtil.getMapValue(params, "cash_back_rate"));

		//add begin byqh 201912
		Double original_card_settle_price_vip = Double.parseDouble(baseMposInfo.get("card_settle_price_vip").toString());
		Double original_cloud_settle_price_vip = Double.parseDouble(baseMposInfo.get("cloud_settle_price_vip").toString());
		Double current_card_settle_price_vip = Double.parseDouble(StringUtil.getMapValue(params, "card_settle_price_vip"));
		Double current_cloud_settle_price_vip = Double.parseDouble(StringUtil.getMapValue(params, "cloud_settle_price_vip"));

		Double original_weixin_settle_price_vip = Double.parseDouble(baseMposInfo.get("weixin_settle_price_vip").toString());
		Double original_zhifubao_settle_price_vip = Double.parseDouble(baseMposInfo.get("zhifubao_settle_price_vip").toString());
		Double current_weixin_settle_price_vip = Double.parseDouble(StringUtil.getMapValue(params, "weixin_settle_price_vip"));
		Double current_zhifubao_settle_price_vip = Double.parseDouble(StringUtil.getMapValue(params, "zhifubao_settle_price_vip"));
		//add end byqh 201912

		Double original_mer_cap_fee = Double.parseDouble(baseMposInfo.get("mer_cap_fee").toString());
		Double current_mer_cap_fee = Double.parseDouble(StringUtil.getMapValue(params, "mer_cap_fee"));

		if(current_card_settle_price<original_card_settle_price){
			return R.error(Type.WARN, "刷卡结算底价不能低于"+original_card_settle_price);
		}

		if(current_cloud_settle_price<original_cloud_settle_price){
			return R.error(Type.WARN, "云闪付结算底价不能低于"+original_cloud_settle_price);
		}

		if(current_weixin_settle_price<original_weixin_settle_price){
			return R.error(Type.WARN, "微信结算底价不能低于"+original_weixin_settle_price);
		}

		if(current_zhifubao_settle_price<original_zhifubao_settle_price){
			return R.error(Type.WARN, "支付宝结算不能低于"+original_zhifubao_settle_price);
		}

		//add begin byqh 201912
		if(current_card_settle_price_vip<original_card_settle_price_vip){
			return R.error(Type.WARN, "刷卡结算底价不能低于"+original_card_settle_price_vip);
		}

		if(current_cloud_settle_price_vip<original_cloud_settle_price_vip){
			return R.error(Type.WARN, "云闪付结算底价不能低于"+original_cloud_settle_price_vip);
		}

		if(current_weixin_settle_price_vip<original_weixin_settle_price_vip){
			return R.error(Type.WARN, "微信结算底价不能低于"+original_weixin_settle_price_vip);
		}

		if(current_zhifubao_settle_price_vip<original_zhifubao_settle_price_vip){
			return R.error(Type.WARN, "支付宝结算不能低于"+original_zhifubao_settle_price_vip);
		}
		//add end byqh 201912

		if(current_single_profit_rate>original_single_profit_rate){
			return R.error(Type.WARN, "单笔分润比例不能高于"+original_single_profit_rate);
		}

		if(current_cash_back_rate>original_cash_back_rate){
			return R.error(Type.WARN, "返现比例不能高于"+original_cash_back_rate);
		}

		if(current_mer_cap_fee<original_mer_cap_fee){
			return R.error(Type.WARN, "封顶费不能低于"+original_mer_cap_fee);
		}

		String cre_date = TimeUtil.getDayString();//创建日期
		String cre_time = TimeUtil.getTimeString();//创建时间
		//1.更新该系统POS机的分配状态
		Map<String, Object> sysTraditionalMap = new HashMap<String, Object>();
		sysTraditionalMap.put("new_dis_status", TypeStatusConstant.sys_pos_info_dis_status_1);//旧状态：已分配
		sysTraditionalMap.put("manager_id", getShiroUserInfo().getManager_id());//代理编号
		sysTraditionalMap.put("sn", params.get("pos_sns").toString());//设备号（机器编号）
		sysTraditionalMap.put("update_by", ShiroUtils.getLoginName());//代理编号
		sysTraditionalMap.put("up_date", cre_date);//更新日期
		sysTraditionalMap.put("up_time", cre_time);//更新时间
		sysTraditionalMap.put("remark", params.get("remark").toString());//操作备注
		int i = agentSysTraditionalPosInfoMapper.updateSubAgentSysTraditionalPosInfoDisStatus(sysTraditionalMap);
		if(i != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "设备号（机器编号）"+params.get("pos_sns").toString()+"新增失败：系统传统POS分配状态更新失败");
		}
		//3.更新直属关系和上下级关系
		Map<String, Object> userTraditionalMap = new HashMap<String, Object>();
		userTraditionalMap.put("user_pos_id",params.get("user_pos_id").toString());
		userTraditionalMap.put("state_status",TypeStatusConstant.user_pos_info_state_status_0);
		userTraditionalMap.put("update_by",ShiroUtils.getLoginName());
		userTraditionalMap.put("up_date",cre_date);
		userTraditionalMap.put("up_time",cre_time);
		agentUserEPosInfoMapper.updateAgentUserTraditionalPosInfo(userTraditionalMap);
		//4.更新POS用户的刷卡结算底价，云闪付结算底价，单笔分润比例，返现比例
		//用户传统POS机对象
		AgentUserTraditionalPosInfo agentUserTraditionalPosInfo = new AgentUserTraditionalPosInfo();
		agentUserTraditionalPosInfo.setSn(params.get("pos_sns").toString());
		agentUserTraditionalPosInfo.setUser_id(params.get("subAgent").toString());//用编号
		agentUserTraditionalPosInfo.setCard_settle_price(params.get("card_settle_price").toString());//刷卡结算底价
		agentUserTraditionalPosInfo.setCloud_settle_price(params.get("cloud_settle_price").toString());//云闪付结算底价
		agentUserTraditionalPosInfo.setWeixin_settle_price(params.get("weixin_settle_price").toString());//微信结算底价
		agentUserTraditionalPosInfo.setZhifubao_settle_price(params.get("zhifubao_settle_price").toString());//支付宝结算底价

		//add begin byqh 201912
		agentUserTraditionalPosInfo.setCard_settle_price_vip(params.get("card_settle_price_vip").toString());//刷卡结算底价
		agentUserTraditionalPosInfo.setCloud_settle_price_vip(params.get("cloud_settle_price_vip").toString());//云闪付结算底价
		agentUserTraditionalPosInfo.setWeixin_settle_price_vip(params.get("weixin_settle_price_vip").toString());//微信结算底价
		agentUserTraditionalPosInfo.setZhifubao_settle_price_vip(params.get("zhifubao_settle_price_vip").toString());//支付宝结算底价
		//add end byqh 201912


		agentUserTraditionalPosInfo.setSingle_profit_rate(params.get("single_profit_rate").toString());//单笔分润比例
		agentUserTraditionalPosInfo.setCash_back_rate(params.get("cash_back_rate").toString());//返现比例
		agentUserTraditionalPosInfo.setMer_cap_fee(params.get("mer_cap_fee").toString());//封顶费
		agentUserTraditionalPosInfo.setState_status(TypeStatusConstant.user_pos_info_state_status_1);//归属状态改为直属
		agentUserTraditionalPosInfo.setDel(TypeStatusConstant.user_pos_info_del_0);//删除状态改为0
		agentUserTraditionalPosInfo.setRemark(params.get("remark").toString());//备注
		agentUserTraditionalPosInfo.setCreate_by(ShiroUtils.getLoginName());
		agentUserTraditionalPosInfo.setCre_date(cre_date);
		agentUserTraditionalPosInfo.setCre_time(cre_time);
		agentUserEPosInfoMapper.addAgentUserTraditionalPosInfoByDis(agentUserTraditionalPosInfo);
		return R.ok("操作成功");
	}


	/**
	 * 批量自增处理保存用户传统POS机信息
	 * @param agentUserTraditionalPosInfo
	 * @param params
	 * @return
	 */
	private R batchAddAgentUserTraditionalPosInfo(AgentUserTraditionalPosInfo agentUserTraditionalPosInfo,
			Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] pos_sns = params.get("pos_sns").toString().split(";");
        for(int i=0;i<pos_sns.length;i++) {
        	agentUserTraditionalPosInfo.setSn(pos_sns[i]);
        	R result = SpringUtils.getAopProxy(this).addSingleAgentUserTraditionalPosInfo(agentUserTraditionalPosInfo);
        	if(Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 添加单个用户传统POS机信息
	 * @param agentUserTraditionalPosInfo
	 * @return
	 */
	@Transactional
	public R addSingleAgentUserTraditionalPosInfo(AgentUserTraditionalPosInfo agentUserTraditionalPosInfo) {
		try {
			//校验结算低价byqh202006
			AgentUserTraditionalPosInfo allot = new AgentUserTraditionalPosInfo();
			allot.setSn(agentUserTraditionalPosInfo.getSn());
			allot.setUser_id(String.valueOf(getShiroUserInfo().getId()));
			Map<String, Object> basePrice = agentUserEPosInfoMapper.getAgentUserTraditionalPosInfo(allot);
			String card_settle_price = String.valueOf(basePrice.get("card_settle_price"));
			String card_settle_price_vip = String.valueOf(basePrice.get("card_settle_price_vip"));
			String cloud_settle_price = String.valueOf(basePrice.get("cloud_settle_price"));
			String weixin_settle_price = String.valueOf(basePrice.get("weixin_settle_price"));
			String zhifubao_settle_price = String.valueOf(basePrice.get("zhifubao_settle_price"));
			String mer_cap_fee = String.valueOf(basePrice.get("mer_cap_fee"));

			String single_profit_rate = String.valueOf(basePrice.get("single_profit_rate"));
			String cash_back_rate = String.valueOf(basePrice.get("cash_back_rate"));
			if(Double.parseDouble(agentUserTraditionalPosInfo.getCard_settle_price())<Double.parseDouble(card_settle_price)){
				return R.error(Type.WARN, "设备号（机器编号）"+agentUserTraditionalPosInfo.getSn()+"刷卡结算底价不能低于"+card_settle_price);
			}
			if(Double.parseDouble(agentUserTraditionalPosInfo.getCloud_settle_price())<Double.parseDouble(cloud_settle_price)){
				return R.error(Type.WARN, "设备号（机器编号）"+agentUserTraditionalPosInfo.getSn()+"云闪付结算底价不能低于"+cloud_settle_price);
			}
			if(Double.parseDouble(agentUserTraditionalPosInfo.getCard_settle_price_vip())<Double.parseDouble(card_settle_price_vip)){
				return R.error(Type.WARN, "设备号（机器编号）"+agentUserTraditionalPosInfo.getSn()+"VIP秒到(商圈)刷卡结算底价不能低于"+card_settle_price_vip);
			}
			if(Double.parseDouble(agentUserTraditionalPosInfo.getWeixin_settle_price())<Double.parseDouble(weixin_settle_price)){
				return R.error(Type.WARN, "设备号（机器编号）"+agentUserTraditionalPosInfo.getSn()+"微信结算底价不能低于"+weixin_settle_price);
			}
			if(Double.parseDouble(agentUserTraditionalPosInfo.getZhifubao_settle_price())<Double.parseDouble(zhifubao_settle_price)){
				return R.error(Type.WARN, "设备号（机器编号）"+agentUserTraditionalPosInfo.getSn()+"支付宝结算底价不能低于"+zhifubao_settle_price);
			}
			if(Double.parseDouble(agentUserTraditionalPosInfo.getMer_cap_fee())<Double.parseDouble(mer_cap_fee)){
				return R.error(Type.WARN, "设备号（机器编号）"+agentUserTraditionalPosInfo.getSn()+"封顶费不能低于"+mer_cap_fee);
			}
			if(Double.parseDouble(agentUserTraditionalPosInfo.getSingle_profit_rate())<Double.parseDouble(single_profit_rate)){
				return R.error(Type.WARN, "设备号（机器编号）"+agentUserTraditionalPosInfo.getSn()+"单笔分润比例不能低于"+single_profit_rate);
			}
			if(Double.parseDouble(agentUserTraditionalPosInfo.getCash_back_rate())>Double.parseDouble(cash_back_rate)){
				return R.error(Type.WARN, "设备号（机器编号）"+agentUserTraditionalPosInfo.getSn()+"返现比例不能高于"+cash_back_rate);
			}
			//校验结算低价byqh202006


			int i=0;
			String cre_date = TimeUtil.getDayString();//创建日期
			String cre_time = TimeUtil.getTimeString();//创建时间
			//（3）更新该系统POS机的分配状态
			Map<String, Object> sysTraditionalPosMap = new HashMap<String, Object>();
			sysTraditionalPosMap.put("old_dis_status", TypeStatusConstant.sys_pos_info_dis_status_0);//旧状态：未分配
			sysTraditionalPosMap.put("new_dis_status", TypeStatusConstant.sys_pos_info_dis_status_1);//旧状态：已分配
			sysTraditionalPosMap.put("manager_id", getShiroUserInfo().getManager_id());//代理编号
			sysTraditionalPosMap.put("sn", agentUserTraditionalPosInfo.getSn());//设备号（机器编号）
			sysTraditionalPosMap.put("update_by", ShiroUtils.getLoginName());//代理编号
			sysTraditionalPosMap.put("up_date", cre_date);//更新日期
			sysTraditionalPosMap.put("up_time", cre_time);//更新时间
			sysTraditionalPosMap.put("remark", agentUserTraditionalPosInfo.getRemark());//操作备注
//			i = agentSysTraditionalPosInfoMapper.updateAgentSysTraditionalPosInfoDisStatus(sysTraditionalPosMap);
//			if(i != 1) {
//				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//				return R.error(Type.WARN, "设备号（机器编号）"+agentUserTraditionalPosInfo.getSn()+"新增失败：系统传统POS分配状态更新失败");
//			}
			//（4）查询是否已经存在该用户和传统POS的关系
			Map<String, Object> userTraditionalPosMap = agentUserEPosInfoMapper.getAgentUserTraditionalPosInfo(agentUserTraditionalPosInfo);
			//（5）处理关系
			agentUserTraditionalPosInfo.setCre_date(cre_date);//更新日期
			agentUserTraditionalPosInfo.setCre_time(TimeUtil.getTimeString());//更新时间
			agentUserTraditionalPosInfo.setCreate_by(ShiroUtils.getLoginName());//创建人
			agentUserTraditionalPosInfo.setState_status(TypeStatusConstant.user_pos_info_state_status_1);//归属状态：直属
			agentUserTraditionalPosInfo.setDel(TypeStatusConstant.user_pos_info_del_0);//删除状态：未删除
			if(userTraditionalPosMap!=null) {
				//已存在，更新该关系的信息
				agentUserTraditionalPosInfo.setId(Integer.parseInt(userTraditionalPosMap.get("id").toString()));
				i = agentUserTraditionalPosInfoMapper.updateAgentUserState0BySNUID(agentUserTraditionalPosInfo.getSn(),String.valueOf(getShiroUserInfo().getId()));
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "设备号（机器编号）"+agentUserTraditionalPosInfo.getSn()+"新增失败：归属关系修改失败");
				}
				i = agentUserEPosInfoMapper.updateAgentUserTraditionalPosInfoByDis(agentUserTraditionalPosInfo);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "设备号（机器编号）"+agentUserTraditionalPosInfo.getSn()+"新增失败：用户传统POS关系更新失败");
				}
			}else {
				i = agentUserTraditionalPosInfoMapper.updateAgentUserState0BySNUID(agentUserTraditionalPosInfo.getSn(),String.valueOf(getShiroUserInfo().getId()));
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "设备号（机器编号）"+agentUserTraditionalPosInfo.getSn()+"新增失败：归属关系修改失败");
				}
				i = agentUserEPosInfoMapper.addAgentUserTraditionalPosInfoByDis(agentUserTraditionalPosInfo);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "设备号（机器编号）"+agentUserTraditionalPosInfo.getSn()+"新增失败：用户传统POS关系建立失败");
				}
			}

			if("0".equals(agentUserTraditionalPosInfo.getIs_reward())){
				agentUserTraditionalPosInfoMapper.updateIsReWard0BySNUID(agentUserTraditionalPosInfo.getSn(),getShiroUserInfo().getId());
			}
			if(agentUserTraditionalPosInfo.getIs_reward1()!=null && !"".equals(agentUserTraditionalPosInfo.getIs_reward1())){
				String[] policyArray = agentUserTraditionalPosInfo.getIs_reward1().split(";");
				for(String policy : policyArray){
					if(policy!=null && !"".equals(policy)){
						agentUserTraditionalPosInfoMapper.updateIsReWard1BySNUID(policy,agentUserTraditionalPosInfo.getSn(),String.valueOf(getShiroUserInfo().getId()));
					}
				}
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "设备号（机器编号）"+agentUserTraditionalPosInfo.getSn()+"新增异常");
		}
	}


	/**
	 * 自增处理用户传统POS机信息
	 * @param agentUserTraditionalPosInfo
	 * @param params
	 * @return
	 */
	private R autoAddAgentUserTraditionalPosInfo(AgentUserTraditionalPosInfo agentUserTraditionalPosInfo,
			Map<String, Object> params) {
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "pos_start"))) {
			return R.error(Type.WARN, "设备号（机器编号）起始数值异常");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "pos_num"))) {
			return R.error(Type.WARN, "POS机数量数值异常");
		}
		//POS机设备号（机器编号）起始号
		String pos_start = params.get("pos_start").toString();
		//连号录入数据(默认后6位)，获取连续的机型号
		int pos_num = Integer.parseInt(params.get("pos_num").toString());//POS机数量
		if(pos_num<=0) {
			return R.error(Type.WARN, "POS机数量数值异常");
		}

		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        for(int i=0; i<pos_num; i++){
        	agentUserTraditionalPosInfo.setSn(pos_start);
			//add byqh 201912
//			sysPosPolicyService.insertPolicySNInfo(String.valueOf(params.get("policy")),pos_start,params.get("user_id").toString(),"TraditionalPOS");
        	R result = SpringUtils.getAopProxy(this).addSingleAgentUserTraditionalPosInfo(agentUserTraditionalPosInfo);
        	if(Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        	pos_start = StringUtil.addOneForTen(pos_start);
        }
        if(failure_num>0) {
        	failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	
	/**
	 * 根据编号查询用户传统POS信息
	 */
	@Override
	public Map<String, Object> getAgentUserTraditionalPosInfoById(String id) {
		return agentUserEPosInfoMapper.getAgentUserTraditionalPosInfoById(id);
	}


	/**
	 * 修改用户传统POS机信息
	 */
	@Override
	public R editAgentUserTraditionalPosInfo(Map<String, Object> params) {
		try {
			if(!ShiroUtils.getSysUser().isAuth()) {
				return R.error(Type.WARN, "身份信息未认证，不能操作");
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			//（1）查询刷卡结算底价参数是否有效
			List<Map<String, Object>> sysParamRateList1 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_2,StringUtil.getMapValue(params, "card_settle_price"));
			if(sysParamRateList1.size()<=0) {
				return R.error(Type.WARN, "刷卡结算底价数值异常");
			}
			//（2）查询云闪付结算底价参数是否有效
			List<Map<String, Object>> sysParamRateList2 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_3,StringUtil.getMapValue(params, "cloud_settle_price"));
			if(sysParamRateList2.size()<=0) {
				return R.error(Type.WARN, "云闪付结算底价数值异常");
			}
			//（3）查询微信结算底价参数是否有效
			List<Map<String, Object>> sysParamRateList3 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_5,StringUtil.getMapValue(params, "weixin_settle_price"));
			if(sysParamRateList3.size()<=0) {
				return R.error(Type.WARN, "微信结算底价数值异常");
			}
			//（4）查询支付宝结算底价参数是否有效
			List<Map<String, Object>> sysParamRateList4 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_7,StringUtil.getMapValue(params, "zhifubao_settle_price"));
			if(sysParamRateList4.size()<=0) {
				return R.error(Type.WARN, "支付宝结算底价数值异常");
			}
			//（5）查询单笔分润比例参数是否有效
			List<Map<String, Object>> sysParamRateList5 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_9,StringUtil.getMapValue(params, "single_profit_rate"));
			if(sysParamRateList5.size()<=0) {
				return R.error(Type.WARN, "单笔分润比例（%）数值异常");
			}
			//（6）查询返现比例参数是否有效
			List<Map<String, Object>> sysParamRateList6 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_10,StringUtil.getMapValue(params, "cash_back_rate"));
			if(sysParamRateList6.size()<=0) {
				return R.error(Type.WARN, "返现比例（%）数值异常");
			}
			//（7）查询封顶费参数是否有效
			List<Map<String, Object>> sysParamRateList7 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_11,StringUtil.getMapValue(params, "mer_cap_fee"));
			if(sysParamRateList7.size()<=0) {
				return R.error(Type.WARN, "封顶费数值异常");
			}
			int i=0;
			//（5）查询old参数信息
			Map<String, Object> oldValue = agentUserEPosInfoMapper.getAgentUserTraditionalPosInfoById(params.get("user_pos_id").toString());
			//（6）更新用户传统POS信息
			params.put("up_date", TimeUtil.getDayString());//更新日期
			params.put("up_time", TimeUtil.getTimeString());//更新时间
			params.put("update_by", ShiroUtils.getLoginName());//创建人
			i = agentUserEPosInfoMapper.updateAgentUserTraditionalPosInfo(params);
			if(i != 1) {
				return R.error(Type.WARN, "系统传统POS信息更新失败");
			}
			//（7）查询new参数信息
			Map<String, Object> newValue = agentUserEPosInfoMapper.getAgentUserTraditionalPosInfoById(params.get("user_pos_id").toString());
			//（8）记录修改记录
			params.put("table_name", SysTableNameConstant.t_user_traditional_pos_info);
			params.put("old_value", NetJsonUtils.mapToJson1(oldValue));
			params.put("new_value", NetJsonUtils.mapToJson1(newValue));
			i = manaSysEditMapper.addManaSysEdit(params);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "修改记录记录失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "操作异常");
		}
	}

	/***
	 * 查询一级代理的子代理byqh
	 * @param user_id
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectSubAgentInfo(String user_id) {
		return agentUserEPosInfoMapper.selectSubAgentInfo(user_id);
	}

}

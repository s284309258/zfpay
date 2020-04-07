package com.ruoyi.project.deveagent.syspos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.SysTableNameConstant;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.json.NetJsonUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.string.TrimUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.account.mapper.AgentUserAccountMapper;
import com.ruoyi.project.deveagent.syspos.domain.AgentSysTraditionalPosInfo;
import com.ruoyi.project.deveagent.syspos.mapper.AgentSysTraditionalPosInfoMapper;
import com.ruoyi.project.deveagent.syspos.service.AgentSysTraditionalPosInfoService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.param.mapper.ManaSysEditMapper;
import com.ruoyi.project.devemana.param.mapper.ManaSysParamRateMapper;


/**
 * 代理====》系统传统POS信息管理
 * @author Administrator
 *
 */
@Service
public class AgentSysTraditionalPosInfoServiceImpl implements AgentSysTraditionalPosInfoService {
	
	@Autowired
	private ManaSysEditMapper manaSysEditMapper;
	@Autowired
	private AgentSysTraditionalPosInfoMapper agentSysTraditionalPosInfoMapper;
	@Autowired
	private ManaSysParamRateMapper manaSysParamRateMapper;
	@Autowired
	private AgentUserAccountMapper agentUserAccountMapper;


	
	/**
	 * 查询系统传统POS信息列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSysTraditionalPosInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSysTraditionalPosInfoMapper.getAgentSysTraditionalPosInfoList(params);
	}
	
	
	/**
	 * 导出系统传统POS信息列表
	 */
	@Override
	public List<AgentSysTraditionalPosInfo> selectAgentSysTraditionalPosInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSysTraditionalPosInfoMapper.selectAgentSysTraditionalPosInfoList(params);
	}

	/**
	 * 查询系统一级代理传统POS信息列表byqh
	 * @param params
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getSubAgentSysTraditionalPosInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSysTraditionalPosInfoMapper.getSubAgentSysTraditionalPosInfoList(params);
	}


	/**
	 * 导入系统传统POS信息
	 */
	@Override
	public R importAgentSysTraditionalPosInfoList(List<AgentSysTraditionalPosInfo> agentSysTraditionalPosInfoList, String account_id) {
		if (StringUtil.isNull(agentSysTraditionalPosInfoList) || agentSysTraditionalPosInfoList.size() == 0){
			return R.error(Type.WARN, "导入系统传统POS数据不能为空！");
        }
		//校验代理中付账号编号
		Map<String, Object> params = new HashMap<>();
		params.put("account_id", account_id);//代理中付账号编号
		params.put("manager_id", ShiroUtils.getUserId());//代理编号
		Map<String, Object> userAccountMap = agentUserAccountMapper.getAgentUserAccount(params);
		if(userAccountMap==null) {
			return R.error(Type.WARN, "该中付账号无效，请重新选择设置");
		}
		/*if(!TypeStatusConstant.sys_user_account_is_start_0.equals(userAccountMap.get("is_start").toString())) {
			return R.error(Type.WARN, "该中付账号已被关闭了，不能使用");
		}*/
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        for(int i=0;i<agentSysTraditionalPosInfoList.size();i++) {
        	//依次导入每一个传统POS机信息
        	agentSysTraditionalPosInfoList.get(i).setAccount_id(account_id);//代理中付账号编号
        	R result = SpringUtils.getAopProxy(this).importAgentSysTraditionalPosInfo(agentSysTraditionalPosInfoList.get(i),i);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
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
	 * @param agentSysTraditionalPosInfo
	 * @param i 
	 * @return
	 */
	@Transactional
	public R importAgentSysTraditionalPosInfo(AgentSysTraditionalPosInfo agentSysTraditionalPosInfo, int i) {
		try {
			agentSysTraditionalPosInfo = (AgentSysTraditionalPosInfo) TrimUtil.trimObject(agentSysTraditionalPosInfo);
			if(StringUtils.isEmpty(agentSysTraditionalPosInfo.getSn())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：设备号（机器编号）必填");
			}
			if(StringUtils.isEmpty(agentSysTraditionalPosInfo.getCredit_card_rate())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：刷卡费率（%）必填");
			}
			if(StringUtils.isEmpty(agentSysTraditionalPosInfo.getCloud_flash_rate())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：云闪付费率（%）必填");
			}
			if(StringUtils.isEmpty(agentSysTraditionalPosInfo.getWeixin_rate())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：微信费率（%） 必填");
			}
			if(StringUtils.isEmpty(agentSysTraditionalPosInfo.getZhifubao_rate())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：支付宝费率（%） 必填");
			}
			if(StringUtils.isEmpty(agentSysTraditionalPosInfo.getCash_back_type())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：返现获取类型必填");
			}
			if(StringUtils.isEmpty(agentSysTraditionalPosInfo.getCash_back_condition())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：返现条件必填");
			}
			if(StringUtils.isEmpty(agentSysTraditionalPosInfo.getCash_back_money())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：返现金额必填");
			}
			//（1）返现获取类型值判断
			if(!"0".equals(agentSysTraditionalPosInfo.getCash_back_type()) && !"1".equals(agentSysTraditionalPosInfo.getCash_back_type())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：返现获取类型值异常");
			}
			//（2）查询刷卡费率参数是否有效
			List<Map<String, Object>> sysParamRateList1 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_1,agentSysTraditionalPosInfo.getCredit_card_rate());
			if(sysParamRateList1.size()<=0) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：刷卡费率数值异常");
			}
			//（3）查询云闪付费率参数是否有效
			List<Map<String, Object>> sysParamRateList2 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_4,agentSysTraditionalPosInfo.getCloud_flash_rate());
			if(sysParamRateList2.size()<=0) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：云闪付费率数值异常");
			}
			//（4）查询微信费率参数是否有效
			List<Map<String, Object>> sysParamRateList3 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_6,agentSysTraditionalPosInfo.getCloud_flash_rate());
			if(sysParamRateList3.size()<=0) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：微信费率数值异常");
			}
			//（5）查询支付宝费率参数是否有效
			List<Map<String, Object>> sysParamRateList4 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_8,agentSysTraditionalPosInfo.getCloud_flash_rate());
			if(sysParamRateList4.size()<=0) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：支付宝费率数值异常");
			}
			//（6）校验该POS机是否存在（根据设备号（机器编号）查询）
			Map<String, Object> sysTraditionalPosInfoMap = agentSysTraditionalPosInfoMapper.getAgentSysTraditionalPosInfoBySn(agentSysTraditionalPosInfo.getSn());
			if(sysTraditionalPosInfoMap!=null) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：该设备号（机器编号）已存在");
			}
			//（7）保存POS机信息
			agentSysTraditionalPosInfo.setManager_id(ShiroUtils.getUserId().toString());//代理编号
			agentSysTraditionalPosInfo.setCre_date(TimeUtil.getDayString());//创建日期
			agentSysTraditionalPosInfo.setCre_time(TimeUtil.getTimeString());//创建时间
			agentSysTraditionalPosInfo.setCreate_by(ShiroUtils.getLoginName());//创建人
			int j = agentSysTraditionalPosInfoMapper.addAgentTraditionalPosInfo(agentSysTraditionalPosInfo);
			if(j != 1) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：数据信息保存失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "第"+(i+1)+"条数据导入失败：数据导入异常");
		}
	}


	/**
	 * 新增系统传统传统POS信息
	 */
	@Override
	public R addAgentSysTraditionalPosInfo(Map<String, Object> params) {
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "cash_back_condition"))) {
			return R.error(Type.WARN, "返现条件必填");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "cash_back_money"))) {
			return R.error(Type.WARN, "返现金额必填");
		}
		//（1）校验代理中付账号编号
		params.put("manager_id", ShiroUtils.getUserId());//代理编号
		Map<String, Object> userAccountMap = agentUserAccountMapper.getAgentUserAccount(params);
		if(userAccountMap==null) {
			return R.error(Type.WARN, "该中付账号无效，请重新选择设置");
		}
		/*if(!TypeStatusConstant.sys_user_account_is_start_0.equals(userAccountMap.get("is_start").toString())) {
			return R.error(Type.WARN, "该中付账号已被关闭了，不能使用");
		}*/
		//（2）返现获取类型值判断
		if(!"0".equals(StringUtil.getMapValue(params, "cash_back_type")) && !"1".equals(StringUtil.getMapValue(params, "cash_back_type"))) {
			return R.error(Type.WARN, "返现获取类型值异常");
		}
		//（3）查询刷卡费率参数是否有效
		List<Map<String, Object>> sysParamRateList1 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_1,StringUtil.getMapValue(params, "credit_card_rate"));
		if(sysParamRateList1.size()<=0) {
			return R.error(Type.WARN, "刷卡费率数值异常");
		}
		//（4）查询云闪付费率参数是否有效
		List<Map<String, Object>> sysParamRateList2 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_4,StringUtil.getMapValue(params, "cloud_flash_rate"));
		if(sysParamRateList2.size()<=0) {
			return R.error(Type.WARN, "云闪付费率数值异常");
		}
		//（5）查询微信费率参数是否有效
		List<Map<String, Object>> sysParamRateList3 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_6,StringUtil.getMapValue(params, "weixin_rate"));
		if(sysParamRateList3.size()<=0) {
			return R.error(Type.WARN, "微信费率数值异常");
		}
		//（6）查询支付宝费率参数是否有效
		List<Map<String, Object>> sysParamRateList4 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_8,StringUtil.getMapValue(params, "zhifubao_rate"));
		if(sysParamRateList4.size()<=0) {
			return R.error(Type.WARN, "支付宝费率数值异常");
		}
		
		//系统传统POS机对象
		AgentSysTraditionalPosInfo agentSysTraditionalPosInfo = new AgentSysTraditionalPosInfo();
		agentSysTraditionalPosInfo.setAccount_id(params.get("account_id").toString());//代理中付账号编号
		agentSysTraditionalPosInfo.setMer_id(params.get("mer_id").toString());//商户号
		agentSysTraditionalPosInfo.setMer_name(params.get("mer_name").toString());//商户名称
		agentSysTraditionalPosInfo.setCash_back_type(params.get("cash_back_type").toString());//返现获取类型
		agentSysTraditionalPosInfo.setCash_back_condition(params.get("cash_back_condition").toString());//返现条件
		agentSysTraditionalPosInfo.setCash_back_money(params.get("cash_back_money").toString());//返现金额
		agentSysTraditionalPosInfo.setCredit_card_rate(params.get("credit_card_rate").toString());//刷卡费率
		agentSysTraditionalPosInfo.setCloud_flash_rate(params.get("cloud_flash_rate").toString());//云闪付费率
		agentSysTraditionalPosInfo.setWeixin_rate(params.get("weixin_rate").toString());//微信费率
		agentSysTraditionalPosInfo.setZhifubao_rate(params.get("zhifubao_rate").toString());//支付宝费率

		//add begin byqh 201912
		agentSysTraditionalPosInfo.setCredit_card_rate_vip(params.get("credit_card_rate_vip").toString());//刷卡费率
		agentSysTraditionalPosInfo.setCloud_flash_rate_vip(params.get("cloud_flash_rate_vip").toString());//云闪付费率
		agentSysTraditionalPosInfo.setWeixin_rate_vip(params.get("weixin_rate_vip").toString());//微信费率
		agentSysTraditionalPosInfo.setZhifubao_rate_vip(params.get("zhifubao_rate_vip").toString());//支付宝费率
		//add end byqh 201912


		agentSysTraditionalPosInfo.setRemark(params.get("remark").toString());//备注
		if(TypeStatusConstant.sys_add_type_1.equals(StringUtil.getMapValue(params, "add_type"))) {
			//批量处理
			return this.batchAddAgentSysTraditionalPosInfo(agentSysTraditionalPosInfo, params);
		}else if(TypeStatusConstant.sys_add_type_2.equals(StringUtil.getMapValue(params, "add_type"))) {
			//自增处理
			return this.autoAddAgentSysTraditionalPosInfo(agentSysTraditionalPosInfo, params);
		}else {
			return R.error(Type.WARN, "新增类型异常");
		}
	}


	/**
	 * 批量自增处理保存系统传统POS机信息
	 * @param agentSysTraditionalPosInfo
	 * @param params
	 * @return
	 */
	private R batchAddAgentSysTraditionalPosInfo(AgentSysTraditionalPosInfo agentSysTraditionalPosInfo,
			Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] pos_sns = params.get("pos_sns").toString().split(";");
        for(int i=0;i<pos_sns.length;i++) {
        	agentSysTraditionalPosInfo.setSn(pos_sns[i]);
        	//依次删除每一个公益(USDT)
        	R result = SpringUtils.getAopProxy(this).addSingleAgentSysTraditionalPosInfo(agentSysTraditionalPosInfo);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
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
	 * 添加单个系统传统POS机信息
	 * @param agentSysTraditionalPosInfo
	 * @return
	 */
	@Transactional
	public R addSingleAgentSysTraditionalPosInfo(AgentSysTraditionalPosInfo agentSysTraditionalPosInfo) {
		try {
			//（1）校验该POS机是否存在（根据设备号（机器编号）查询）
			Map<String, Object> sysTraditionalPosInfoMap = agentSysTraditionalPosInfoMapper.getAgentSysTraditionalPosInfoBySn(agentSysTraditionalPosInfo.getSn());
			if(sysTraditionalPosInfoMap!=null) {
				return R.error(Type.WARN, "设备号（机器编号）"+agentSysTraditionalPosInfo.getSn()+"保存失败：该设备号（机器编号）已存在");
			}
			agentSysTraditionalPosInfo.setManager_id(ShiroUtils.getUserId().toString());//代理编号
			agentSysTraditionalPosInfo.setCre_date(TimeUtil.getDayString());//创建日期
			agentSysTraditionalPosInfo.setCre_time(TimeUtil.getTimeString());//创建时间
			agentSysTraditionalPosInfo.setCreate_by(ShiroUtils.getLoginName());//创建人
			int j = agentSysTraditionalPosInfoMapper.addAgentTraditionalPosInfo(agentSysTraditionalPosInfo);
			if(j != 1) {
				return R.error(Type.WARN, "设备号（机器编号）"+agentSysTraditionalPosInfo.getSn()+"保存失败：数据信息保存失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "设备号（机器编号）"+agentSysTraditionalPosInfo.getSn()+"新增异常");
		}
	}
	
	
	/**
	 * 自增处理系统传统POS机信息
	 * @param agentSysTraditionalPosInfo
	 * @param params
	 * @return
	 */
	private R autoAddAgentSysTraditionalPosInfo(AgentSysTraditionalPosInfo agentSysTraditionalPosInfo,
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
        	agentSysTraditionalPosInfo.setSn(pos_start);
        	R result = SpringUtils.getAopProxy(this).addSingleAgentSysTraditionalPosInfo(agentSysTraditionalPosInfo);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
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
	 * 根据编号查询系统传统POS机详情
	 */
	@Override
	public Map<String, Object> getAgentSysTraditionalPosInfoById(String id) {
		return agentSysTraditionalPosInfoMapper.getAgentSysTraditionalPosInfoById(id);
	}


	/**
	 * 修改系统传统POS机信息
	 */
	@Override
	public R editAgentSysTraditionalPosInfo(Map<String, Object> params) {
		try {
			if(!ShiroUtils.getSysUser().isAuth()) {
				return R.error(Type.WARN, "身份信息未认证，不能操作");
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			int i=0;
			//（1）校验代理中付账号编号
			params.put("manager_id", ShiroUtils.getUserId());//代理编号
			Map<String, Object> userAccountMap = agentUserAccountMapper.getAgentUserAccount(params);
			if(userAccountMap==null) {
				return R.error(Type.WARN, "该中付账号无效，请重新选择设置");
			}
			/*if(!TypeStatusConstant.sys_user_account_is_start_0.equals(userAccountMap.get("is_start").toString())) {
				return R.error(Type.WARN, "该中付账号已被关闭了，不能使用");
			}*/
			//（2）返现获取类型值判断
			if(!"0".equals(StringUtil.getMapValue(params, "cash_back_type")) && !"1".equals(StringUtil.getMapValue(params, "cash_back_type"))) {
				return R.error(Type.WARN, "返现获取类型值异常");
			}
			//（3）查询刷卡费率参数是否有效
			List<Map<String, Object>> sysParamRateList1 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_1,StringUtil.getMapValue(params, "credit_card_rate"));
			if(sysParamRateList1.size()<=0) {
				return R.error(Type.WARN, "刷卡费率数值异常");
			}
			//（4）查询云闪付费率参数是否有效
			List<Map<String, Object>> sysParamRateList2 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_4,StringUtil.getMapValue(params, "cloud_flash_rate"));
			if(sysParamRateList2.size()<=0) {
				return R.error(Type.WARN, "云闪付费率数值异常");
			}
			//（5）查询微信费率参数是否有效
			List<Map<String, Object>> sysParamRateList3 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_6,StringUtil.getMapValue(params, "weixin_rate"));
			if(sysParamRateList3.size()<=0) {
				return R.error(Type.WARN, "微信费率数值异常");
			}
			//（6）查询支付宝费率参数是否有效
			List<Map<String, Object>> sysParamRateList4 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_8,StringUtil.getMapValue(params, "zhifubao_rate"));
			if(sysParamRateList4.size()<=0) {
				return R.error(Type.WARN, "支付宝费率数值异常");
			}
			//（11）查询刷卡费率参数是否有效
			List<Map<String, Object>> sysParamRateList11 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_1,StringUtil.getMapValue(params, "credit_card_rate_vip"));
			if(sysParamRateList11.size()<=0) {
				return R.error(Type.WARN, "VIP秒到(商圈)刷卡费率数值异常");
			}
			//（7）查询old参数信息
			Map<String, Object> oldValue = agentSysTraditionalPosInfoMapper.getAgentSysTraditionalPosInfoById(params.get("pos_id").toString());
			//（8）更新系统传统POS信息
			params.put("manager_id", ShiroUtils.getUserId());//代理编号
			params.put("up_date", TimeUtil.getDayString());//更新日期
			params.put("up_time", TimeUtil.getTimeString());//更新时间
			params.put("update_by", ShiroUtils.getLoginName());//创建人
			i = agentSysTraditionalPosInfoMapper.updateAgentSysTraditionalPosInfo(params);
			if(i != 1) {
				return R.error(Type.WARN, "系统传统POS信息更新失败");
			}
			//（9）查询new参数信息
			Map<String, Object> newValue = agentSysTraditionalPosInfoMapper.getAgentSysTraditionalPosInfoById(params.get("pos_id").toString());
			//（10）记录修改记录
			params.put("table_name", SysTableNameConstant.t_sys_traditional_pos_info);
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


	/**
	 * 批量删除系统传统POS信息====>只能删除未分配的传统POS
	 */
	@Override
	public R batchRemoveAgentSysTraditionalPosInfo(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
			return R.error(Type.WARN, "操作备注不能为空");
		}
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
		//拼接id转换成long型数组
        String[] pos_ids = Convert.toStrArray(StringUtil.getMapValue(params, "pos_ids"));
        for(int i=0;i<pos_ids.length;i++) {
        	Map<String, Object> sysTraditionalPosMap = new HashMap<>();
        	sysTraditionalPosMap.put("pos_id", pos_ids[i]);
        	R result = SpringUtils.getAopProxy(this).removeAgentSysTraditionalPosInfo(sysTraditionalPosMap);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        }
        if(failure_num>0) {
        	failure_msg = "删除结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "删除结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 删除单个系统传统POS信息======》只能删除未分配的
	 * @param sysTraditionalPosMap
	 * @return
	 */
	@Transactional
	public R removeAgentSysTraditionalPosInfo(Map<String, Object> sysTraditionalPosMap) {
		try {
			sysTraditionalPosMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			sysTraditionalPosMap.put("dis_status", TypeStatusConstant.sys_pos_info_dis_status_0);
			int i = agentSysTraditionalPosInfoMapper.deleteAgentSysTraditionalPosInfo(sysTraditionalPosMap);
			if(i != 1) {
				return R.error(Type.WARN, "编号"+sysTraditionalPosMap.get("pos_id").toString()+"：删除失败，只能删除待未分配的POS机");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.WARN, "编号"+sysTraditionalPosMap.get("pos_id").toString()+"：删除异常");
		}
	}


	/**
	 * 根据设备号（机器编号）查询详情
	 */
	@Override
	public Map<String, Object> getAgentSysTraditionalPosInfoDetailBySn(String id) {
		return agentSysTraditionalPosInfoMapper.getAgentSysTraditionalPosInfoDetailBySn(id);
	}

}

package com.ruoyi.project.deveagent.usermpos.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.project.deveagent.syspospolicy.service.SysPosPolicyServiceImpl;
import com.ruoyi.project.deveagent.user.domain.AgentUserInfo;
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
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.syspos.mapper.AgentSysMposInfoMapper;
import com.ruoyi.project.deveagent.user.mapper.AgentUserInfoMapper;
import com.ruoyi.project.deveagent.usermpos.domain.AgentSelectUserMposInfo;
import com.ruoyi.project.deveagent.usermpos.domain.AgentUserMposInfo;
import com.ruoyi.project.deveagent.usermpos.mapper.AgentUserMposInfoMapper;
import com.ruoyi.project.deveagent.usermpos.service.AgentUserMposInfoService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.param.mapper.ManaSysEditMapper;
import com.ruoyi.project.devemana.param.mapper.ManaSysParamRateMapper;


/**
 * 代理====》用户MPOS信息管理
 * @author Administrator
 *
 */
@Service
public class AgentUserMposInfoServiceImpl implements AgentUserMposInfoService {
	
	@Autowired
	private ManaSysEditMapper manaSysEditMapper;
	@Autowired
	private AgentUserInfoMapper agentUserInfoMapper;
	@Autowired
	private AgentSysMposInfoMapper agentSysMposInfoMapper;
	@Autowired
	private AgentUserMposInfoMapper agentUserMposInfoMapper;
	@Autowired
	private ManaSysParamRateMapper manaSysParamRateMapper;

	@Autowired
	private SysPosPolicyServiceImpl sysPosPolicyService;

	@Autowired
	private AgentUserMposInfoService agentUserMposInfoService;

	
	/**
	 * 查询用户MPOS信息列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserMposInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserMposInfoMapper.getAgentUserMposInfoList(params);
	}
	
	
	/**
	 * 导出用户MPOS信息列表
	 */
	@Override
	public List<AgentUserMposInfo> selectAgentUserMposInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserMposInfoMapper.selectAgentUserMposInfoList(params);
	}


	/**
	 * 导入用户MPOS数据
	 */
	@Override
	public R importAgentUserMposInfoList(List<AgentUserMposInfo> agentUserMposInfoList, String user_id) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if (StringUtil.isNull(agentUserMposInfoList) || agentUserMposInfoList.size() == 0){
			return R.error(Type.WARN, "导入用户MPOS数据不能为空！");
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
        for(int i=0;i<agentUserMposInfoList.size();i++) {
        	//依次导入每一个MPOS机信息
        	agentUserMposInfoList.get(i).setUser_id(user_id);//用户编号
        	R result = SpringUtils.getAopProxy(this).importAgentUserMposInfo(agentUserMposInfoList.get(i),i);
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
	 * @param agentUserMposInfo
	 * @param m
	 * @return
	 */
	@Transactional
	public R importAgentUserMposInfo(AgentUserMposInfo agentUserMposInfo, int m) {
		try {
			agentUserMposInfo = (AgentUserMposInfo) TrimUtil.trimObject(agentUserMposInfo);
			if(StringUtils.isEmpty(agentUserMposInfo.getSn())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：设备号（机器编号）必填");
			}
			if(StringUtils.isEmpty(agentUserMposInfo.getCard_settle_price())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：刷卡结算底价必填");
			}
			if(StringUtils.isEmpty(agentUserMposInfo.getCloud_settle_price())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：云闪付结算底价必填 ");
			}
			if(StringUtils.isEmpty(agentUserMposInfo.getSingle_profit_rate())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：单笔分润比例（%）必填");
			}
			if(StringUtils.isEmpty(agentUserMposInfo.getCash_back_rate())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：返现比例（%）必填");
			}
			//（1）查询刷卡结算底价参数是否有效
			List<Map<String, Object>> sysParamRateList1 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_2,agentUserMposInfo.getCard_settle_price());
			if(sysParamRateList1.size()<=0) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：刷卡结算底价数值异常");
			}
			//（2）查询云闪付结算底价参数是否有效
			List<Map<String, Object>> sysParamRateList2 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_3,agentUserMposInfo.getCloud_settle_price());
			if(sysParamRateList2.size()<=0) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：云闪付结算底价数值异常");
			}
			//（3）查询单笔分润比例参数是否有效
			List<Map<String, Object>> sysParamRateList3 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_9,agentUserMposInfo.getSingle_profit_rate());
			if(sysParamRateList3.size()<=0) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：单笔分润比例（%）数值异常");
			}
			//（4）查询返现比例参数是否有效
			List<Map<String, Object>> sysParamRateList4 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_10,agentUserMposInfo.getCash_back_rate());
			if(sysParamRateList4.size()<=0) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：返现比例（%）数值异常");
			}
			int i=0;
			String cre_date = TimeUtil.getDayString();//创建日期
			String cre_time = TimeUtil.getTimeString();//创建时间
			//（3）更新该系统POS机的分配状态
			Map<String, Object> sysMposMap = new HashMap<String, Object>();
			sysMposMap.put("old_dis_status", TypeStatusConstant.sys_pos_info_dis_status_0);//旧状态：未分配
			sysMposMap.put("new_dis_status", TypeStatusConstant.sys_pos_info_dis_status_1);//旧状态：已分配
			sysMposMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			sysMposMap.put("sn", agentUserMposInfo.getSn());//设备号（机器编号）
			sysMposMap.put("update_by", ShiroUtils.getLoginName());//代理编号
			sysMposMap.put("up_date", cre_date);//更新日期
			sysMposMap.put("up_time", cre_time);//更新时间
			i = agentSysMposInfoMapper.updateAgentSysMposInfoDisStatus(sysMposMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：系统MPOS分配状态更新失败");
			}
			//（4）查询是否已经存在该用户和MPOS的关系
			Map<String, Object> userMposMap = agentUserMposInfoMapper.getAgentUserMposInfo(agentUserMposInfo);
			//（5）处理关系
			agentUserMposInfo.setCre_date(cre_date);//更新日期
			agentUserMposInfo.setCre_time(TimeUtil.getTimeString());//更新时间
			agentUserMposInfo.setCreate_by(ShiroUtils.getLoginName());//创建人
			agentUserMposInfo.setState_status(TypeStatusConstant.user_pos_info_state_status_1);//归属状态：直属
			agentUserMposInfo.setDel(TypeStatusConstant.user_pos_info_del_0);//删除状态：未删除
			if(userMposMap!=null) {
				//已存在，更新该关系的信息
				agentUserMposInfo.setId(Integer.parseInt(userMposMap.get("id").toString()));
				i = agentUserMposInfoMapper.updateAgentUserMposInfoByDis(agentUserMposInfo);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：用户MPOS关系更新失败");
				}
			}else {
				i = agentUserMposInfoMapper.addAgentUserMposInfoByDis(agentUserMposInfo);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：用户MPOS关系建立失败");
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
	 * 导出可分配的Mpos导入模板
	 */
	@Override
	public List<AgentSelectUserMposInfo> selectAgentNoDisSysMposInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSysMposInfoMapper.selectAgentNoDisSysMposInfoList(params);
	}


	/**
	 * 新增保存用户MPOS机信息
	 */
	@Override
	public R addAgentUserMposInfo(Map<String, Object> params) {
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
		//（3）查询单笔分润比例参数是否有效
		List<Map<String, Object>> sysParamRateList3 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_9,StringUtil.getMapValue(params, "single_profit_rate"));
		if(sysParamRateList3.size()<=0) {
			return R.error(Type.WARN, "单笔分润比例（%）数值异常");
		}
		//（4）查询返现比例参数是否有效
		List<Map<String, Object>> sysParamRateList4 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_10,StringUtil.getMapValue(params, "cash_back_rate"));
		if(sysParamRateList4.size()<=0) {
			return R.error(Type.WARN, "返现比例（%）数值异常");
		}
		
		//用户MPOS机对象
        AgentUserMposInfo agentUserMposInfo = new AgentUserMposInfo();
        agentUserMposInfo.setUser_id(params.get("user_id").toString());//用编号
        agentUserMposInfo.setCard_settle_price(params.get("card_settle_price").toString());//刷卡结算底价
        agentUserMposInfo.setCloud_settle_price(params.get("cloud_settle_price").toString());//云闪付结算底价
        agentUserMposInfo.setSingle_profit_rate(params.get("single_profit_rate").toString());//单笔分润比例
        agentUserMposInfo.setCash_back_rate(params.get("cash_back_rate").toString());//返现比例
        agentUserMposInfo.setRemark(params.get("remark").toString());//备注
		if(TypeStatusConstant.sys_add_type_1.equals(StringUtil.getMapValue(params, "add_type"))) {
			//add byqh 201912
			sysPosPolicyService.insertPolicySNInfo(String.valueOf(params.get("policy")),params.get("pos_sns").toString(),params.get("user_id").toString(),"MPOS");
			//批量处理
			return this.batchAddAgentUserMposInfo(agentUserMposInfo, params);
		}else if(TypeStatusConstant.sys_add_type_2.equals(StringUtil.getMapValue(params, "add_type"))) {
			//自增处理
			return this.autoAddAgentUserMposInfo(agentUserMposInfo, params);
		}else {
			return R.error(Type.WARN, "新增类型异常");
		}
	}

	/***
	 * add byqh202006
	 * @param params
	 * @return
	 */
	@Override
	public R batchUpdate(Map<String, Object> params,List<Map<String,Object>> list) {
		String message = "操作成功";
		String user_id = params.get("user_id").toString();
//		AgentUserInfo userInfo = agentUserInfoMapper.getAgentUserInfoById(user_id);
//		List<Map<String,Object>> list = agentUserMposInfoMapper.getAgentAllUserMpos(userInfo.getParent_chain()+","+user_id,user_id);
		for(Map<String,Object> mm : list){
			try {
				if(!ShiroUtils.getSysUser().isAuth()) {
					return R.error(Type.WARN, "身份信息未认证，不能操作");
				}
				if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
					return R.error(Type.WARN, "操作备注不能为空");
				}

				Map<String,Object> map = new HashMap<>();
				if(params.get("card_settle_price")!=null && !"".equals(params.get("card_settle_price"))){
					BigDecimal card_settle_price1 = new BigDecimal(mm.get("card_settle_price").toString());
					BigDecimal card_settle_price2 = new BigDecimal(params.get("card_settle_price").toString());
					Double card_settle_price = card_settle_price1.add(card_settle_price2).doubleValue();
					map.put("card_settle_price",card_settle_price);
					Map<String, Object> check = agentSysMposInfoMapper.getAgentSysMposInfoBySn(mm.get("sn").toString());
					if(card_settle_price>=Double.parseDouble(check.get("credit_card_rate").toString())){
						card_settle_price = Double.parseDouble(check.get("credit_card_rate").toString());
						map.put("card_settle_price",card_settle_price);
						if("操作成功".equals(message)){
							message = mm.get("real_name")+"代理及下级分润为0";
						}
					}
				}
				if(params.get("cloud_settle_price")!=null && !"".equals(params.get("cloud_settle_price"))){
					BigDecimal cloud_settle_price1 = new BigDecimal(mm.get("cloud_settle_price").toString());
					BigDecimal cloud_settle_price2 = new BigDecimal(params.get("cloud_settle_price").toString());
					Double cloud_settle_price = cloud_settle_price1.add(cloud_settle_price2).doubleValue();
					map.put("cloud_settle_price",cloud_settle_price);
					Map<String, Object> check = agentSysMposInfoMapper.getAgentSysMposInfoBySn(mm.get("sn").toString());
					if(cloud_settle_price>=Double.parseDouble(check.get("cloud_flash_rate").toString())){
						cloud_settle_price = Double.parseDouble(check.get("cloud_flash_rate").toString());
						map.put("cloud_settle_price",cloud_settle_price);
						if("操作成功".equals(message)){
							message = mm.get("real_name")+"代理及下级分润为0";
						}
					}
				}
				map.put("user_pos_id",mm.get("id"));
				//（1）查询刷卡结算底价参数是否有效
				List<Map<String, Object>> sysParamRateList1 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_2,StringUtil.getMapValue(map, "card_settle_price"));
				if(sysParamRateList1.size()<=0) {
					return R.error(Type.WARN, "刷卡结算底价数值异常");
				}
				//（2）查询云闪付结算底价参数是否有效
				List<Map<String, Object>> sysParamRateList2 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_3,StringUtil.getMapValue(map, "cloud_settle_price"));
				if(sysParamRateList2.size()<=0) {
					return R.error(Type.WARN, "云闪付结算底价数值异常");
				}
				int i=0;
				//（5）查询old参数信息
				Map<String, Object> oldValue = agentUserMposInfoMapper.getAgentUserMposInfoById(map.get("user_pos_id").toString());
				//（6）更新用户MPOS信息
				params.put("up_date", TimeUtil.getDayString());//更新日期
				params.put("up_time", TimeUtil.getTimeString());//更新时间
				params.put("update_by", ShiroUtils.getLoginName());//创建人
				i = agentUserMposInfoMapper.updateAgentUserMposInfo(map);
				if(i != 1) {
					return R.error(Type.WARN, "系统MPOS信息更新失败");
				}
				//（7）查询new参数信息
				Map<String, Object> newValue = agentUserMposInfoMapper.getAgentUserMposInfoById(map.get("user_pos_id").toString());
				//（8）记录修改记录
				params.put("table_name", SysTableNameConstant.t_user_mpos_info);
				params.put("old_value", NetJsonUtils.mapToJson1(oldValue));
				params.put("new_value", NetJsonUtils.mapToJson1(newValue));
				i = manaSysEditMapper.addManaSysEdit(params);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "修改记录记录失败");
				}
//				return R.ok(message);
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.ERROR, "操作异常");
			}
		}
		return R.ok(message);
	}


	/**
	 * 批量自增处理保存用户MPOS机信息
	 * @param agentUserMposInfo 
	 * @param params
	 * @return
	 */
	private R batchAddAgentUserMposInfo(AgentUserMposInfo agentUserMposInfo, Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] pos_sns = params.get("pos_sns").toString().split(";");
        for(int i=0;i<pos_sns.length;i++) {
        	agentUserMposInfo.setSn(pos_sns[i]);
        	//依次删除每一个公益(USDT)
        	R result = SpringUtils.getAopProxy(this).addSingleAgentUserMposInfo(agentUserMposInfo);
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
	 * 添加单个用户MPOS机信息
	 * @param agentUserMposInfo
	 * @return
	 */
	@Transactional
	public R addSingleAgentUserMposInfo(AgentUserMposInfo agentUserMposInfo) {
		try {
			int i=0;
			String cre_date = TimeUtil.getDayString();//创建日期
			String cre_time = TimeUtil.getTimeString();//创建时间
			//（3）更新该系统POS机的分配状态
			Map<String, Object> sysMposMap = new HashMap<String, Object>();
			sysMposMap.put("old_dis_status", TypeStatusConstant.sys_pos_info_dis_status_0);//旧状态：未分配
			sysMposMap.put("new_dis_status", TypeStatusConstant.sys_pos_info_dis_status_1);//旧状态：已分配
			sysMposMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			sysMposMap.put("sn", agentUserMposInfo.getSn());//设备号（机器编号）
			sysMposMap.put("update_by", ShiroUtils.getLoginName());//代理编号
			sysMposMap.put("up_date", cre_date);//更新日期
			sysMposMap.put("up_time", cre_time);//更新时间
			sysMposMap.put("remark", agentUserMposInfo.getRemark());//操作备注
			i = agentSysMposInfoMapper.updateAgentSysMposInfoDisStatus(sysMposMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "设备号（机器编号）"+agentUserMposInfo.getSn()+"新增失败：系统MPOS分配状态更新失败");
			}
			//（4）查询是否已经存在该用户和MPOS的关系
			Map<String, Object> userMposMap = agentUserMposInfoMapper.getAgentUserMposInfo(agentUserMposInfo);
			//（5）处理关系
			agentUserMposInfo.setCre_date(cre_date);//更新日期
			agentUserMposInfo.setCre_time(TimeUtil.getTimeString());//更新时间
			agentUserMposInfo.setCreate_by(ShiroUtils.getLoginName());//创建人
			agentUserMposInfo.setState_status(TypeStatusConstant.user_pos_info_state_status_1);//归属状态：直属
			agentUserMposInfo.setDel(TypeStatusConstant.user_pos_info_del_0);//删除状态：未删除
			if(userMposMap!=null) {
				//已存在，更新该关系的信息
				agentUserMposInfo.setId(Integer.parseInt(userMposMap.get("id").toString()));
				i = agentUserMposInfoMapper.updateAgentUserMposInfoByDis(agentUserMposInfo);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "设备号（机器编号）"+agentUserMposInfo.getSn()+"新增失败：用户MPOS关系更新失败");
				}
			}else {
				i = agentUserMposInfoMapper.addAgentUserMposInfoByDis(agentUserMposInfo);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "设备号（机器编号）"+agentUserMposInfo.getSn()+"新增失败：用户MPOS关系建立失败");
				}
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "设备号（机器编号）"+agentUserMposInfo.getSn()+"新增异常");
		} 
	}
	
	
	/**
	 * 自增处理用户MPOS机信息
	 * @param agentUserMposInfo
	 * @param params
	 * @return
	 */
	private R autoAddAgentUserMposInfo(AgentUserMposInfo agentUserMposInfo, Map<String, Object> params) {
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
        	agentUserMposInfo.setSn(pos_start);
        	//add byqh 201912
			sysPosPolicyService.insertPolicySNInfo(String.valueOf(params.get("policy")),pos_start,params.get("user_id").toString(),"MPOS");
        	//依次删除每一个公益(USDT)
        	R result = SpringUtils.getAopProxy(this).addSingleAgentUserMposInfo(agentUserMposInfo);
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
	 * 根据编号查询用户MPOS信息
	 */
	@Override
	public Map<String, Object> getAgentUserMposInfoById(String id) {
		return agentUserMposInfoMapper.getAgentUserMposInfoById(id);
	}


	/**
	 * 修改用户MPOS信息
	 */
	@Override
	@Transactional
	public R editAgentUserMposInfo(Map<String, Object> params) {
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
			//（3）查询单笔分润比例参数是否有效
			List<Map<String, Object>> sysParamRateList3 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_9,StringUtil.getMapValue(params, "single_profit_rate"));
			if(sysParamRateList3.size()<=0) {
				return R.error(Type.WARN, "单笔分润比例（%）数值异常");
			}
			//（4）查询返现比例参数是否有效
			List<Map<String, Object>> sysParamRateList4 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_10,StringUtil.getMapValue(params, "cash_back_rate"));
			if(sysParamRateList4.size()<=0) {
				return R.error(Type.WARN, "返现比例（%）数值异常");
			}
			int i=0;
			//（5）查询old参数信息
			Map<String, Object> oldValue = agentUserMposInfoMapper.getAgentUserMposInfoById(params.get("user_pos_id").toString());
			//（6）更新用户MPOS信息
			params.put("up_date", TimeUtil.getDayString());//更新日期
			params.put("up_time", TimeUtil.getTimeString());//更新时间
			params.put("update_by", ShiroUtils.getLoginName());//创建人
			i = agentUserMposInfoMapper.updateAgentUserMposInfo(params);
			if(i != 1) {
				return R.error(Type.WARN, "系统MPOS信息更新失败");
			}
			//（7）查询new参数信息
			Map<String, Object> newValue = agentUserMposInfoMapper.getAgentUserMposInfoById(params.get("user_pos_id").toString());
			//（8）记录修改记录
			params.put("table_name", SysTableNameConstant.t_user_mpos_info);
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
		return agentUserInfoMapper.selectSubAgentInfo(user_id);
	}

	/***
	 *选中POS分配给代理保存操作byqh
	 * @param params
	 * @return
	 */
	@Override
	@Transactional
	public R addSubAgentUserMposInfo(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
			return R.error(Type.WARN, "操作备注不能为空");
		}
		//校验是否是一级代理用户并且实名认证
		params.put("manager_id", ShiroUtils.getUserId());//代理编号

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
		//（3）查询单笔分润比例参数是否有效
		List<Map<String, Object>> sysParamRateList3 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_9,StringUtil.getMapValue(params, "single_profit_rate"));
		if(sysParamRateList3.size()<=0) {
			return R.error(Type.WARN, "单笔分润比例（%）数值异常");
		}
		//（4）查询返现比例参数是否有效
		List<Map<String, Object>> sysParamRateList4 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_10,StringUtil.getMapValue(params, "cash_back_rate"));
		if(sysParamRateList4.size()<=0) {
			return R.error(Type.WARN, "返现比例（%）数值异常");
		}

		Map<String, Object> baseMposInfo = agentUserMposInfoMapper.getAgentUserMposInfoById(params.get("user_pos_id").toString());
		Double original_card_settle_price = Double.parseDouble(baseMposInfo.get("card_settle_price").toString());
		Double original_cloud_settle_price = Double.parseDouble(baseMposInfo.get("cloud_settle_price").toString());
		Double current_card_settle_price = Double.parseDouble(StringUtil.getMapValue(params, "card_settle_price"));
		Double current_cloud_settle_price = Double.parseDouble(StringUtil.getMapValue(params, "cloud_settle_price"));

		Double original_single_profit_rate = Double.parseDouble(baseMposInfo.get("single_profit_rate").toString());
		Double original_cash_back_rate = Double.parseDouble(baseMposInfo.get("cash_back_rate").toString());
		Double current_single_profit_rate = Double.parseDouble(StringUtil.getMapValue(params, "single_profit_rate"));
		Double current_cash_back_rate = Double.parseDouble(StringUtil.getMapValue(params, "cash_back_rate"));
		if(current_card_settle_price<original_card_settle_price){
			return R.error(Type.WARN, "刷卡结算底价不能低于"+original_card_settle_price);
		}

		if(current_cloud_settle_price<original_cloud_settle_price){
			return R.error(Type.WARN, "云闪付结算底价不能低于"+original_cloud_settle_price);
		}

		if(current_single_profit_rate>original_single_profit_rate){
			return R.error(Type.WARN, "单笔分润比例不能高于"+original_single_profit_rate);
		}

		if(current_cash_back_rate>original_cash_back_rate){
			return R.error(Type.WARN, "返现比例不能高于"+original_cash_back_rate);
		}


		//用户MPOS机对象
		AgentUserMposInfo agentUserMposInfo = new AgentUserMposInfo();
		agentUserMposInfo.setUser_id(params.get("subAgent").toString());//用编号
		agentUserMposInfo.setCard_settle_price(params.get("card_settle_price").toString());//刷卡结算底价
		agentUserMposInfo.setCloud_settle_price(params.get("cloud_settle_price").toString());//云闪付结算底价
		agentUserMposInfo.setSingle_profit_rate(params.get("single_profit_rate").toString());//单笔分润比例
		agentUserMposInfo.setCash_back_rate(params.get("cash_back_rate").toString());//返现比例
		agentUserMposInfo.setRemark(params.get("remark").toString());//备注
		agentUserMposInfo.setSn(params.get("pos_sns").toString());//pos的sn号


		String cre_date = TimeUtil.getDayString();//创建日期
		String cre_time = TimeUtil.getTimeString();//创建时间
		//1.更新该系统POS机的分配状态
		Map<String, Object> sysMposMap = new HashMap<String, Object>();
		sysMposMap.put("new_dis_status", TypeStatusConstant.sys_pos_info_dis_status_1);//旧状态：已分配
		sysMposMap.put("manager_id", ShiroUtils.getUserId());//代理编号
		sysMposMap.put("sn", agentUserMposInfo.getSn());//设备号（机器编号）
		sysMposMap.put("update_by", ShiroUtils.getLoginName());//代理编号
		sysMposMap.put("up_date", cre_date);//更新日期
		sysMposMap.put("up_time", cre_time);//更新时间
		sysMposMap.put("remark", agentUserMposInfo.getRemark());//操作备注

		//1.更新传统POS分配状态
		int i = agentSysMposInfoMapper.updateSubAgentSysMposInfo(sysMposMap);
		if(i != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "设备号（机器编号）"+agentUserMposInfo.getSn()+"新增失败：系统MPOS分配状态更新失败");
		}

		//查询pos基础信息
//		Map<String,Object> updateMposMap = new HashMap<>();
//		Map<String,Object> posInfo = agentSysMposInfoMapper.getAgentSysMposInfoBySn(agentUserMposInfo.getSn());
//		String posId = posInfo.get("id").toString();
//		updateMposMap.put("pos_id",posId);
//		updateMposMap.put("credit_card_rate",agentUserMposInfo.getCard_settle_price());
//		updateMposMap.put("cloud_flash_rate",agentUserMposInfo.getCloud_settle_price());
//		updateMposMap.put("manager_id", ShiroUtils.getUserId());//代理编号
//		//2.更新pos机的刷卡结算底价，云闪付结算底价
//		agentSysMposInfoMapper.updateAgentSysMposInfo(updateMposMap);
		//3.更新直属关系和上下级关系
		AgentUserMposInfo previousUserMposMap = new AgentUserMposInfo();
		previousUserMposMap.setId(Integer.valueOf(params.get("user_pos_id").toString()));
		previousUserMposMap.setState_status(TypeStatusConstant.user_pos_info_state_status_0);
		previousUserMposMap.setUp_date(cre_date);
		previousUserMposMap.setUp_time(cre_time);
		agentUserMposInfoMapper.updateAgentUserMposInfoBase(previousUserMposMap);
		//4.更新POS用户的刷卡结算底价，云闪付结算底价，单笔分润比例，返现比例
		AgentUserMposInfo userMposInfo = new AgentUserMposInfo();
		userMposInfo.setSn(agentUserMposInfo.getSn());
		userMposInfo.setUser_id(agentUserMposInfo.getUser_id());
		userMposInfo.setCard_settle_price(agentUserMposInfo.getCard_settle_price());
		userMposInfo.setCloud_settle_price(agentUserMposInfo.getCloud_settle_price());
		userMposInfo.setSingle_profit_rate(agentUserMposInfo.getSingle_profit_rate());
		userMposInfo.setCash_back_rate(agentUserMposInfo.getCash_back_rate());
		userMposInfo.setState_status(TypeStatusConstant.user_pos_info_state_status_1);
		userMposInfo.setDel(TypeStatusConstant.user_pos_info_del_0);
		userMposInfo.setRemark(agentUserMposInfo.getRemark());
		userMposInfo.setCreate_by(ShiroUtils.getLoginName());
		userMposInfo.setCre_date(cre_date);
		userMposInfo.setCre_time(cre_time);
		agentUserMposInfoMapper.addAgentUserMposInfoByDis(userMposInfo);
		return R.ok("操作成功");
	}

}

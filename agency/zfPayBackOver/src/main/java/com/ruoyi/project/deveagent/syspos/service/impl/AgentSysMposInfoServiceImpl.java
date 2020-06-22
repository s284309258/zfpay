package com.ruoyi.project.deveagent.syspos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.BasicSerivce;
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
import com.ruoyi.project.deveagent.syspos.domain.AgentSysMposInfo;
import com.ruoyi.project.deveagent.syspos.mapper.AgentSysMposInfoMapper;
import com.ruoyi.project.deveagent.syspos.service.AgentSysMposInfoService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.param.mapper.ManaSysEditMapper;
import com.ruoyi.project.devemana.param.mapper.ManaSysParamRateMapper;


/**
 * 代理====》系统MPOS信息管理
 * @author Administrator
 *
 */
@Service
public class AgentSysMposInfoServiceImpl extends BasicSerivce implements AgentSysMposInfoService {
	
	@Autowired
	private ManaSysEditMapper manaSysEditMapper;
	@Autowired
	private AgentSysMposInfoMapper agentSysMposInfoMapper;
	@Autowired
	private ManaSysParamRateMapper manaSysParamRateMapper;
	@Autowired
	private AgentUserAccountMapper agentUserAccountMapper;
	

	
	/**
	 * 查询系统MPOS信息列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSysMposInfoList(Map<String, Object> params) {
		MapChainParams(params);
		return agentSysMposInfoMapper.getAgentSysMposInfoList(params);
	}

	/**
	 * 查询系统一级代理MPOS信息列表byqh
	 * @param params
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getSubAgentSysMposInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSysMposInfoMapper.getSubAgentSysMposInfoList(params);
	}


	/**
	 * 导出系统MPOS信息列表
	 */
	@Override
	public List<AgentSysMposInfo> selectAgentSysMposInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSysMposInfoMapper.selectAgentSysMposInfoList(params);
	}


	/**
	 * 导入系统MPOS信息数据
	 */
	@Override
	public R importAgentSysMposInfoList(List<AgentSysMposInfo> agentSysMposInfoList, String account_id) {
		if (StringUtil.isNull(agentSysMposInfoList) || agentSysMposInfoList.size() == 0)
        {
			return R.error(Type.WARN, "导入系统MPOS数据不能为空！");
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
        for(int i=0;i<agentSysMposInfoList.size();i++) {
        	//依次导入每一个MPOS机信息
        	agentSysMposInfoList.get(i).setAccount_id(account_id);//代理中付账号编号
        	R result = SpringUtils.getAopProxy(this).importAgentSysMposInfo(agentSysMposInfoList.get(i),i);
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
	 * @param agentSysMposInfo
	 * @param i 
	 * @return
	 */
	@Transactional
	public R importAgentSysMposInfo(AgentSysMposInfo agentSysMposInfo, int i) {
		try {
			agentSysMposInfo = (AgentSysMposInfo) TrimUtil.trimObject(agentSysMposInfo);
			if(StringUtils.isEmpty(agentSysMposInfo.getSn())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：设备号（机器编号）必填");
			}
			if(StringUtils.isEmpty(agentSysMposInfo.getCredit_card_rate())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：刷卡费率（%）必填");
			}
			if(StringUtils.isEmpty(agentSysMposInfo.getCloud_flash_rate())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：云闪付费率（%）必填 ");
			}
			if(StringUtils.isEmpty(agentSysMposInfo.getCash_back_type())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：返现获取类型必填");
			}
			if(StringUtils.isEmpty(agentSysMposInfo.getCash_back_condition())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：返现条件必填");
			}
			if(StringUtils.isEmpty(agentSysMposInfo.getCash_back_money())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：返现金额必填");
			}
			//（1）返现获取类型值判断
			if(!"0".equals(agentSysMposInfo.getCash_back_type()) && !"1".equals(agentSysMposInfo.getCash_back_type())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：返现获取类型值异常");
			}
			//（2）查询刷卡费率参数是否有效
			List<Map<String, Object>> sysParamRateList1 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_1,agentSysMposInfo.getCredit_card_rate());
			if(sysParamRateList1.size()<=0) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：刷卡费率数值异常");
			}
			//（3）查询云闪付费率参数是否有效
			List<Map<String, Object>> sysParamRateList2 = manaSysParamRateMapper.getManaSysParamRateIsValid(TypeStatusConstant.sys_param_rate_type_4,agentSysMposInfo.getCloud_flash_rate());
			if(sysParamRateList2.size()<=0) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：云闪付费率数值异常");
			}
			//（4）校验该POS机是否存在（根据设备号（机器编号）查询）
			Map<String, Object> sysMposInfoMap = agentSysMposInfoMapper.getAgentSysMposInfoBySn(agentSysMposInfo.getSn());
			if(sysMposInfoMap!=null) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：该设备号（机器编号）已存在");
			}
			agentSysMposInfo.setManager_id(ShiroUtils.getUserId().toString());//代理编号
			agentSysMposInfo.setCre_date(TimeUtil.getDayString());//创建日期
			agentSysMposInfo.setCre_time(TimeUtil.getTimeString());//创建时间
			agentSysMposInfo.setCreate_by(ShiroUtils.getLoginName());//创建人
			int j = agentSysMposInfoMapper.addAgentSysMposInfo(agentSysMposInfo);
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
	 * 跳转到系统新增MPOS
	 */
	@Override
	public R addAgentSysMposInfo(Map<String, Object> params) {
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
		
		//系统MPOS机对象
		AgentSysMposInfo agentSysMposInfo = new AgentSysMposInfo();
		agentSysMposInfo.setAccount_id(params.get("account_id").toString());//代理中付账号编号
		agentSysMposInfo.setMer_id(params.get("mer_id").toString());//商户号
		agentSysMposInfo.setMer_name(params.get("mer_name").toString());//商户名称
		agentSysMposInfo.setCash_back_type(params.get("cash_back_type").toString());//返现获取类型
		agentSysMposInfo.setCash_back_condition(params.get("cash_back_condition").toString());//返现条件
		agentSysMposInfo.setCash_back_money(params.get("cash_back_money").toString());//返现金额
		agentSysMposInfo.setCredit_card_rate(params.get("credit_card_rate").toString());//刷卡费率
		agentSysMposInfo.setCloud_flash_rate(params.get("cloud_flash_rate").toString());//云闪付费率
		agentSysMposInfo.setRemark(params.get("remark").toString());//备注
		if(TypeStatusConstant.sys_add_type_1.equals(StringUtil.getMapValue(params, "add_type"))) {
			//批量处理
			return this.batchAddAgentSysMposInfo(agentSysMposInfo, params);
		}else if(TypeStatusConstant.sys_add_type_2.equals(StringUtil.getMapValue(params, "add_type"))) {
			//自增处理
			return this.autoAddAgentSysMposInfo(agentSysMposInfo, params);
		}else {
			return R.error(Type.WARN, "新增类型异常");
		}
	}


	/**
	 * 批量自增处理保存系统MPOS机信息
	 * @param agentSysMposInfo
	 * @param params
	 * @return
	 */
	private R batchAddAgentSysMposInfo(AgentSysMposInfo agentSysMposInfo, Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] pos_sns = params.get("pos_sns").toString().split(";");
        for(int i=0;i<pos_sns.length;i++) {
        	agentSysMposInfo.setSn(pos_sns[i]);
        	//依次删除每一个公益(USDT)
        	R result = SpringUtils.getAopProxy(this).addSingleAgentSysMposInfo(agentSysMposInfo);
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
	 * 添加单个系统MPOS机信息
	 * @param agentSysMposInfo
	 * @return
	 */
	@Transactional
	public R addSingleAgentSysMposInfo(AgentSysMposInfo agentSysMposInfo) {
		try {
			//（1）校验该POS机是否存在（根据设备号（机器编号）查询）
			Map<String, Object> sysMposInfoMap = agentSysMposInfoMapper.getAgentSysMposInfoBySn(agentSysMposInfo.getSn());
			if(sysMposInfoMap!=null) {
				return R.error(Type.WARN, "设备号（机器编号）"+agentSysMposInfo.getSn()+"保存失败：该设备号（机器编号）已存在");
			}
			agentSysMposInfo.setManager_id(ShiroUtils.getUserId().toString());//代理编号
			agentSysMposInfo.setCre_date(TimeUtil.getDayString());//创建日期
			agentSysMposInfo.setCre_time(TimeUtil.getTimeString());//创建时间
			agentSysMposInfo.setCreate_by(ShiroUtils.getLoginName());//创建人
			int j = agentSysMposInfoMapper.addAgentSysMposInfo(agentSysMposInfo);
			if(j != 1) {
				return R.error(Type.WARN, "设备号（机器编号）"+agentSysMposInfo.getSn()+"保存失败：数据信息保存失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "设备号（机器编号）"+agentSysMposInfo.getSn()+"新增异常");
		}
	}
	
	
	/**
	 * 自增处理系统MPOS机信息
	 * @param agentSysMposInfo
	 * @param params
	 * @return
	 */
	private R autoAddAgentSysMposInfo(AgentSysMposInfo agentSysMposInfo, Map<String, Object> params) {
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
        	agentSysMposInfo.setSn(pos_start);
        	R result = SpringUtils.getAopProxy(this).addSingleAgentSysMposInfo(agentSysMposInfo);
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
	 * 根据编号查询系统MPOS详情
	 */
	@Override
	public Map<String, Object> getAgentSysMposInfoById(String id) {
		return agentSysMposInfoMapper.getAgentSysMposInfoById(id);
	}
	

	/**
	 * 修改系统MPOS
	 */
	@Override
	@Transactional
	public R editAgentSysMposInfo(Map<String, Object> params) {
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
			//（5）查询old参数信息
			Map<String, Object> oldValue = agentSysMposInfoMapper.getAgentSysMposInfoById(params.get("pos_id").toString());
			//（6）更新系统MPOS信息
			params.put("manager_id", ShiroUtils.getUserId());//代理编号
			params.put("up_date", TimeUtil.getDayString());//更新日期
			params.put("up_time", TimeUtil.getTimeString());//更新时间
			params.put("update_by", ShiroUtils.getLoginName());//创建人
			i = agentSysMposInfoMapper.updateAgentSysMposInfo(params);
			if(i != 1) {
				return R.error(Type.WARN, "系统MPOS信息更新失败");
			}
			//（7）查询new参数信息
			Map<String, Object> newValue = agentSysMposInfoMapper.getAgentSysMposInfoById(params.get("pos_id").toString());
			//（8）记录修改记录
			params.put("table_name", SysTableNameConstant.t_sys_mpos_info);
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
	 * 批量删除系统MPOS信息====>只能删除未分配的MPOS
	 */
	@Override
	public R batchRemoveAgentSysMposInfo(Map<String, Object> params) {
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
        	Map<String, Object> sysMposMap = new HashMap<>();
        	sysMposMap.put("pos_id", pos_ids[i]);
        	R result = SpringUtils.getAopProxy(this).removeAgentSysMposInfo(sysMposMap);
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
	 * 删除单个系统MPOS信息======》只能删除未分配的
	 * @param sysMposMap
	 * @return
	 */
	@Transactional
	public R removeAgentSysMposInfo(Map<String, Object> sysMposMap) {
		try {
			sysMposMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			sysMposMap.put("dis_status", TypeStatusConstant.sys_pos_info_dis_status_0);
			int i = agentSysMposInfoMapper.deleteAgentSysMposInfo(sysMposMap);
			if(i != 1) {
				return R.error(Type.WARN, "编号"+sysMposMap.get("pos_id").toString()+"：删除失败，只能删除待未分配的POS机");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.WARN, "编号"+sysMposMap.get("pos_id").toString()+"：删除异常");
		}
	}


	/**
	 * 根据编号查询系统MPOS详情
	 */
	@Override
	public Map<String, Object> getAgentSysMposInfoDetailBySn(String id) {
		return agentSysMposInfoMapper.getAgentSysMposInfoDetailBySn(id);
	}

}

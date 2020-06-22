package com.ruoyi.project.deveagent.syspos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.string.TrimUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.syspos.domain.AgentSysTrafficCardInfo;
import com.ruoyi.project.deveagent.syspos.mapper.AgentSysTrafficCardInfoMapper;
import com.ruoyi.project.deveagent.syspos.service.AgentSysTrafficCardInfoService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;


/**
 * 代理====》系统流量卡信息管理
 * @author Administrator
 *
 */
@Service
public class AgentSysTrafficCardInfoServiceImpl implements AgentSysTrafficCardInfoService {
	
	@Autowired
	private AgentSysTrafficCardInfoMapper agentSysTrafficCardInfoMapper;


	
	/**
	 * 查询系统流量卡信息列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSysTrafficCardInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSysTrafficCardInfoMapper.getAgentSysTrafficCardInfoList(params);
	}
	
	
	/**
	 * 导出系统流量卡信息列表
	 */
	@Override
	public List<AgentSysTrafficCardInfo> selectAgentSysTrafficCardInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSysTrafficCardInfoMapper.selectAgentSysTrafficCardInfoList(params);
	}


	/**
	 * 导入系统流量卡信息数据
	 */
	@Override
	public R importAgentSysTrafficCardInfoList(List<AgentSysTrafficCardInfo> agentSysTrafficCardInfoList) {
		if (StringUtil.isNull(agentSysTrafficCardInfoList) || agentSysTrafficCardInfoList.size() == 0)
        {
			return R.error(Type.WARN, "导入系统流量卡数据不能为空！");
        }
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        for(int i=0;i<agentSysTrafficCardInfoList.size();i++) {
        	//依次导入每一个流量卡机信息
        	R result = SpringUtils.getAopProxy(this).importAgentSysTrafficCardInfo(agentSysTrafficCardInfoList.get(i),i);
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
	 * @param agentSysTrafficCardInfo
	 * @param i 
	 * @return
	 */
	@Transactional
	public R importAgentSysTrafficCardInfo(AgentSysTrafficCardInfo agentSysTrafficCardInfo, int i) {
		try {
			agentSysTrafficCardInfo = (AgentSysTrafficCardInfo) TrimUtil.trimObject(agentSysTrafficCardInfo);
			if(StringUtils.isEmpty(agentSysTrafficCardInfo.getCard_no())) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：流量卡号必填");
			}
			//（4）校验该流量卡号是否存在（根据流量卡号查询）
			Map<String, Object> sysTrafficCardInfoMap = agentSysTrafficCardInfoMapper.getAgentSysTrafficCardInfoByCardNo(agentSysTrafficCardInfo.getCard_no());
			if(sysTrafficCardInfoMap!=null) {
				return R.error(Type.WARN, "第"+(i+1)+"条数据导入失败：该流量卡号已存在");
			}
			agentSysTrafficCardInfo.setManager_id(ShiroUtils.getUserId().toString());//代理编号
			agentSysTrafficCardInfo.setCre_date(TimeUtil.getDayString());//创建日期
			agentSysTrafficCardInfo.setCre_time(TimeUtil.getTimeString());//创建时间
			agentSysTrafficCardInfo.setCreate_by(ShiroUtils.getLoginName());//创建人
			int j = agentSysTrafficCardInfoMapper.addAgentSysTrafficCardInfo(agentSysTrafficCardInfo);
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
	 * 跳转到系统新增流量卡
	 */
	@Override
	public R addAgentSysTrafficCardInfo(Map<String, Object> params) {
		//系统流量卡机对象
		AgentSysTrafficCardInfo agentSysTrafficCardInfo = new AgentSysTrafficCardInfo();
		agentSysTrafficCardInfo.setRemark(params.get("remark").toString());//备注
		if(TypeStatusConstant.sys_add_type_1.equals(StringUtil.getMapValue(params, "add_type"))) {
			//批量处理
			return this.batchAddAgentSysTrafficCardInfo(agentSysTrafficCardInfo, params);
		}else if(TypeStatusConstant.sys_add_type_2.equals(StringUtil.getMapValue(params, "add_type"))) {
			//自增处理
			return this.autoAddAgentSysTrafficCardInfo(agentSysTrafficCardInfo, params);
		}else {
			return R.error(Type.WARN, "新增类型异常");
		}
	}


	/**
	 * 批量自增处理保存系统流量卡机信息
	 * @param agentSysTrafficCardInfo
	 * @param params
	 * @return
	 */
	private R batchAddAgentSysTrafficCardInfo(AgentSysTrafficCardInfo agentSysTrafficCardInfo, Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] card_nos = params.get("card_nos").toString().split(";");
        for(int i=0;i<card_nos.length;i++) {
        	agentSysTrafficCardInfo.setCard_no(card_nos[i]);
        	//依次删除每一个公益(USDT)
        	R result = SpringUtils.getAopProxy(this).addSingleAgentSysTrafficCardInfo(agentSysTrafficCardInfo);
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
	 * 添加单个系统流量卡机信息
	 * @param agentSysTrafficCardInfo
	 * @return
	 */
	@Transactional
	public R addSingleAgentSysTrafficCardInfo(AgentSysTrafficCardInfo agentSysTrafficCardInfo) {
		try {
			//（1）校验该POS机是否存在（根据设备号（机器编号）查询）
			Map<String, Object> sysTrafficCardInfoMap = agentSysTrafficCardInfoMapper.getAgentSysTrafficCardInfoByCardNo(agentSysTrafficCardInfo.getCard_no());
			if(sysTrafficCardInfoMap!=null) {
				return R.error(Type.WARN, "流量卡号"+agentSysTrafficCardInfo.getCard_no()+"保存失败：该流量卡号已存在");
			}
			agentSysTrafficCardInfo.setManager_id(ShiroUtils.getUserId().toString());//代理编号
			agentSysTrafficCardInfo.setCre_date(TimeUtil.getDayString());//创建日期
			agentSysTrafficCardInfo.setCre_time(TimeUtil.getTimeString());//创建时间
			agentSysTrafficCardInfo.setCreate_by(ShiroUtils.getLoginName());//创建人
			int j = agentSysTrafficCardInfoMapper.addAgentSysTrafficCardInfo(agentSysTrafficCardInfo);
			if(j != 1) {
				return R.error(Type.WARN, "流量卡号"+agentSysTrafficCardInfo.getCard_no()+"保存失败：数据信息保存失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "流量卡号"+agentSysTrafficCardInfo.getCard_no()+"新增异常");
		}
	}
	
	
	/**
	 * 自增处理系统流量卡机信息
	 * @param agentSysTrafficCardInfo
	 * @param params
	 * @return
	 */
	private R autoAddAgentSysTrafficCardInfo(AgentSysTrafficCardInfo agentSysTrafficCardInfo, Map<String, Object> params) {
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "card_start"))) {
			return R.error(Type.WARN, "设备号（机器编号）起始数值异常");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "card_num"))) {
			return R.error(Type.WARN, "流量卡数量数值异常");
		}
		//流量卡卡号起始号
		String card_start = params.get("card_start").toString();
		//连号录入数据(默认后6位)，获取连续的机型号
		int card_num = Integer.parseInt(params.get("card_num").toString());//POS机数量
		if(card_num<=0) {
			return R.error(Type.WARN, "流量卡数量数值异常");
		}
		
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        for(int i=0; i<card_num; i++){
        	agentSysTrafficCardInfo.setCard_no(card_start);
        	R result = SpringUtils.getAopProxy(this).addSingleAgentSysTrafficCardInfo(agentSysTrafficCardInfo);
        	if(R.Type.SUCCESS.value.equals(result.get("code").toString())) {
            	success_num++;
            }else {
            	failure_num++;
            	failure_msg = failure_msg+"<br/>"+result.get("msg").toString();
            }
        	card_start = StringUtil.addOneForTen(card_start);
        }
        if(failure_num>0) {
        	failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条<br/>失败信息如下：<br/>"+failure_msg;
		}else {
			failure_msg = "操作结果：成功"+success_num+"条，失败"+failure_num+"条";
		}
        return R.ok(failure_msg);
	}


	/**
	 * 根据编号查询系统流量卡详情
	 */
	@Override
	public Map<String, Object> getAgentSysTrafficCardInfoById(String id) {
		return agentSysTrafficCardInfoMapper.getAgentSysTrafficCardInfoById(id);
	}
	

	/**
	 * 批量删除系统流量卡信息====>只能删除未分配的流量卡
	 */
	@Override
	public R batchRemoveAgentSysTrafficCardInfo(Map<String, Object> params) {
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
        String[] card_ids = Convert.toStrArray(StringUtil.getMapValue(params, "card_ids"));
        for(int i=0;i<card_ids.length;i++) {
        	Map<String, Object> sysTrafficCardMap = new HashMap<>();
        	sysTrafficCardMap.put("card_id", card_ids[i]);
        	R result = SpringUtils.getAopProxy(this).removeAgentSysTrafficCardInfo(sysTrafficCardMap);
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
	 * 删除单个系统流量卡信息======》只能删除未分配的
	 * @param sysTrafficCardMap
	 * @return
	 */
	@Transactional
	public R removeAgentSysTrafficCardInfo(Map<String, Object> sysTrafficCardMap) {
		try {
			sysTrafficCardMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			sysTrafficCardMap.put("dis_status", TypeStatusConstant.sys_pos_info_dis_status_0);
			int i = agentSysTrafficCardInfoMapper.deleteAgentSysTrafficCardInfo(sysTrafficCardMap);
			if(i != 1) {
				return R.error(Type.WARN, "编号"+sysTrafficCardMap.get("card_id").toString()+"：删除失败，只能删除待未分配的POS机");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.WARN, "编号"+sysTrafficCardMap.get("card_id").toString()+"：删除异常");
		}
	}


	/**
	 * 根据卡号查询详情
	 */
	@Override
	public Map<String, Object> getAgentSysTrafficCardInfoDetailByCardNo(String id) {
		return agentSysTrafficCardInfoMapper.getAgentSysTrafficCardInfoDetailByCardNo(id);
	}

}

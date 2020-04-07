package com.ruoyi.project.deveagent.usertracard.service.impl;

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
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.syspos.mapper.AgentSysTrafficCardInfoMapper;
import com.ruoyi.project.deveagent.user.mapper.AgentUserInfoMapper;
import com.ruoyi.project.deveagent.usertracard.domain.AgentSelectUserTrafficCardInfo;
import com.ruoyi.project.deveagent.usertracard.domain.AgentUserTrafficCardInfo;
import com.ruoyi.project.deveagent.usertracard.mapper.AgentUserTrafficCardInfoMapper;
import com.ruoyi.project.deveagent.usertracard.service.AgentUserTrafficCardInfoService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;


/**
 * 代理====》用户流量卡信息管理
 * @author Administrator
 *
 */
@Service
public class AgentUserTrafficCardInfoServiceImpl implements AgentUserTrafficCardInfoService {
	
	@Autowired
	private AgentUserInfoMapper agentUserInfoMapper;
	@Autowired
	private AgentSysTrafficCardInfoMapper agentSysTrafficCardInfoMapper;
	@Autowired
	private AgentUserTrafficCardInfoMapper agentUserTrafficCardInfoMapper;

	
	/**
	 * 查询用户流量卡信息列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTrafficCardInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserTrafficCardInfoMapper.getAgentUserTrafficCardInfoList(params);
	}
	
	
	/**
	 * 导出用户流量卡信息列表
	 */
	@Override
	public List<AgentUserTrafficCardInfo> selectAgentUserTrafficCardInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserTrafficCardInfoMapper.selectAgentUserTrafficCardInfoList(params);
	}


	/**
	 * 导入用户流量卡数据
	 */
	@Override
	public R importAgentUserTrafficCardInfoList(List<AgentUserTrafficCardInfo> agentUserTrafficCardInfoList, String user_id) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if (StringUtil.isNull(agentUserTrafficCardInfoList) || agentUserTrafficCardInfoList.size() == 0){
			return R.error(Type.WARN, "导入用户流量卡数据不能为空！");
        }
		//校验是否是一级代理用户并且实名认证
		Map<String, Object> params = new HashMap<>();
		params.put("user_id", user_id);//用户编号
		params.put("manager_id", ShiroUtils.getUserId());//代理编号
		Map<String, Object> userMap = agentUserInfoMapper.getAgentFirstAgentUserInfo(params);
		if(userMap==null) {
			return R.error(Type.WARN, "只能给属于自己的一级代理分配流量卡");
		}
		if(!TypeStatusConstant.user_info_auth_status_09.equals(userMap.get("auth_status").toString())) {
			return R.error(Type.WARN, "该用户未实名认证，不能分配流量卡");
		}
		
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        for(int i=0;i<agentUserTrafficCardInfoList.size();i++) {
        	//依次导入每一个流量卡机信息
        	agentUserTrafficCardInfoList.get(i).setUser_id(user_id);//用户编号
        	R result = SpringUtils.getAopProxy(this).importAgentUserTrafficCardInfo(agentUserTrafficCardInfoList.get(i),i);
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
	 * @param agentUserTrafficCardInfo
	 * @param i 
	 * @return
	 */
	@Transactional
	public R importAgentUserTrafficCardInfo(AgentUserTrafficCardInfo agentUserTrafficCardInfo, int m) {
		try {
			agentUserTrafficCardInfo = (AgentUserTrafficCardInfo) TrimUtil.trimObject(agentUserTrafficCardInfo);
			if(StringUtils.isEmpty(agentUserTrafficCardInfo.getCard_no())) {
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：流量卡号必填");
			}
			int i=0;
			String cre_date = TimeUtil.getDayString();//创建日期
			String cre_time = TimeUtil.getTimeString();//创建时间
			//（3）更新该系统POS机的分配状态
			Map<String, Object> sysTrafficCardMap = new HashMap<String, Object>();
			sysTrafficCardMap.put("old_dis_status", TypeStatusConstant.sys_pos_info_dis_status_0);//旧状态：未分配
			sysTrafficCardMap.put("new_dis_status", TypeStatusConstant.sys_pos_info_dis_status_1);//旧状态：已分配
			sysTrafficCardMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			sysTrafficCardMap.put("card_no", agentUserTrafficCardInfo.getCard_no());//流量卡号
			sysTrafficCardMap.put("update_by", ShiroUtils.getLoginName());//代理编号
			sysTrafficCardMap.put("up_date", cre_date);//更新日期
			sysTrafficCardMap.put("up_time", cre_time);//更新时间
			i = agentSysTrafficCardInfoMapper.updateAgentSysTrafficCardInfoDisStatus(sysTrafficCardMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：系统流量卡分配状态更新失败");
			}
			//（4）查询是否已经存在该用户和流量卡的关系
			Map<String, Object> userTrafficCardMap = agentUserTrafficCardInfoMapper.getAgentUserTrafficCardInfo(agentUserTrafficCardInfo);
			//（5）处理关系
			agentUserTrafficCardInfo.setCre_date(cre_date);//更新日期
			agentUserTrafficCardInfo.setCre_time(TimeUtil.getTimeString());//更新时间
			agentUserTrafficCardInfo.setCreate_by(ShiroUtils.getLoginName());//创建人
			agentUserTrafficCardInfo.setState_status(TypeStatusConstant.user_pos_info_state_status_1);//归属状态：直属
			agentUserTrafficCardInfo.setDel(TypeStatusConstant.user_pos_info_del_0);//删除状态：未删除
			if(userTrafficCardMap!=null) {
				//已存在，更新该关系的信息
				agentUserTrafficCardInfo.setId(Integer.parseInt(userTrafficCardMap.get("id").toString()));
				i = agentUserTrafficCardInfoMapper.updateAgentUserTrafficCardInfoByDis(agentUserTrafficCardInfo);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：用户流量卡关系更新失败");
				}
			}else {
				i = agentUserTrafficCardInfoMapper.addAgentUserTrafficCardInfoByDis(agentUserTrafficCardInfo);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "第"+(m+1)+"条数据导入失败：用户流量卡关系建立失败");
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
	 * 导出可分配的流量卡导入模板
	 */
	@Override
	public List<AgentSelectUserTrafficCardInfo> selectAgentNoDisSysTrafficCardInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSysTrafficCardInfoMapper.selectAgentNoDisSysTrafficCardInfoList(params);
	}


	/**
	 * 新增保存用户流量卡机信息
	 */
	@Override
	public R addAgentUserTrafficCardInfo(Map<String, Object> params) {
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
			return R.error(Type.WARN, "只能给属于自己的一级代理分配流量卡");
		}
		if(!TypeStatusConstant.user_info_auth_status_09.equals(userMap.get("auth_status").toString())) {
			return R.error(Type.WARN, "该用户未实名认证，不能分配POS机");
		}
		
		//用户流量卡机对象
        AgentUserTrafficCardInfo agentUserTrafficCardInfo = new AgentUserTrafficCardInfo();
        agentUserTrafficCardInfo.setUser_id(params.get("user_id").toString());//用编号
        agentUserTrafficCardInfo.setRemark(params.get("remark").toString());//备注
		if(TypeStatusConstant.sys_add_type_1.equals(StringUtil.getMapValue(params, "add_type"))) {
			//批量处理
			return this.batchAddAgentUserTrafficCardInfo(agentUserTrafficCardInfo, params);
		}else if(TypeStatusConstant.sys_add_type_2.equals(StringUtil.getMapValue(params, "add_type"))) {
			//自增处理
			return this.autoAddAgentUserTrafficCardInfo(agentUserTrafficCardInfo, params);
		}else {
			return R.error(Type.WARN, "新增类型异常");
		}
	}


	/**
	 * 批量自增处理保存用户流量卡机信息
	 * @param agentUserTrafficCardInfo 
	 * @param params
	 * @return
	 */
	private R batchAddAgentUserTrafficCardInfo(AgentUserTrafficCardInfo agentUserTrafficCardInfo, Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] card_nos = params.get("card_nos").toString().split(";");
        for(int i=0;i<card_nos.length;i++) {
        	agentUserTrafficCardInfo.setCard_no(card_nos[i]);
        	//依次删除每一个公益(USDT)
        	R result = SpringUtils.getAopProxy(this).addSingleAgentUserTrafficCardInfo(agentUserTrafficCardInfo);
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
	 * 添加单个用户流量卡机信息
	 * @param agentUserTrafficCardInfo
	 * @return
	 */
	@Transactional
	public R addSingleAgentUserTrafficCardInfo(AgentUserTrafficCardInfo agentUserTrafficCardInfo) {
		try {
			int i=0;
			String cre_date = TimeUtil.getDayString();//创建日期
			String cre_time = TimeUtil.getTimeString();//创建时间
			//（3）更新该系统流量卡的分配状态
			Map<String, Object> sysTrafficCardMap = new HashMap<String, Object>();
			sysTrafficCardMap.put("old_dis_status", TypeStatusConstant.sys_pos_info_dis_status_0);//旧状态：未分配
			sysTrafficCardMap.put("new_dis_status", TypeStatusConstant.sys_pos_info_dis_status_1);//旧状态：已分配
			sysTrafficCardMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			sysTrafficCardMap.put("card_no", agentUserTrafficCardInfo.getCard_no());//流量卡号
			sysTrafficCardMap.put("update_by", ShiroUtils.getLoginName());//代理编号
			sysTrafficCardMap.put("up_date", cre_date);//更新日期
			sysTrafficCardMap.put("up_time", cre_time);//更新时间
			sysTrafficCardMap.put("remark", agentUserTrafficCardInfo.getRemark());//操作备注
			i = agentSysTrafficCardInfoMapper.updateAgentSysTrafficCardInfoDisStatus(sysTrafficCardMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "流量卡号"+agentUserTrafficCardInfo.getCard_no()+"新增失败：系统流量卡分配状态更新失败");
			}
			//（4）查询是否已经存在该用户和流量卡的关系
			Map<String, Object> userTrafficCardMap = agentUserTrafficCardInfoMapper.getAgentUserTrafficCardInfo(agentUserTrafficCardInfo);
			//（5）处理关系
			agentUserTrafficCardInfo.setCre_date(cre_date);//更新日期
			agentUserTrafficCardInfo.setCre_time(TimeUtil.getTimeString());//更新时间
			agentUserTrafficCardInfo.setCreate_by(ShiroUtils.getLoginName());//创建人
			agentUserTrafficCardInfo.setState_status(TypeStatusConstant.user_pos_info_state_status_1);//归属状态：直属
			agentUserTrafficCardInfo.setDel(TypeStatusConstant.user_pos_info_del_0);//删除状态：未删除
			if(userTrafficCardMap!=null) {
				//已存在，更新该关系的信息
				agentUserTrafficCardInfo.setId(Integer.parseInt(userTrafficCardMap.get("id").toString()));
				i = agentUserTrafficCardInfoMapper.updateAgentUserTrafficCardInfoByDis(agentUserTrafficCardInfo);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "流量卡号"+agentUserTrafficCardInfo.getCard_no()+"新增失败：用户流量卡关系更新失败");
				}
			}else {
				i = agentUserTrafficCardInfoMapper.addAgentUserTrafficCardInfoByDis(agentUserTrafficCardInfo);
				if(i != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return R.error(Type.WARN, "流量卡号"+agentUserTrafficCardInfo.getCard_no()+"新增失败：用户流量卡关系建立失败");
				}
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "流量卡号"+agentUserTrafficCardInfo.getCard_no()+"新增异常");
		} 
	}
	
	
	/**
	 * 自增处理用户流量卡信息
	 * @param agentUserTrafficCardInfo
	 * @param params
	 * @return
	 */
	private R autoAddAgentUserTrafficCardInfo(AgentUserTrafficCardInfo agentUserTrafficCardInfo, Map<String, Object> params) {
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "card_start"))) {
			return R.error(Type.WARN, "设备号（机器编号）起始数值异常");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "card_num"))) {
			return R.error(Type.WARN, "POS机数量数值异常");
		}
		//POS机设备号（机器编号）起始号
		String card_start = params.get("card_start").toString();
		//连号录入数据(默认后6位)，获取连续的机型号
		int card_num = Integer.parseInt(params.get("card_num").toString());//流量卡数量
		if(card_num<=0) {
			return R.error(Type.WARN, "POS机数量数值异常");
		}
		
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        for(int i=0; i<card_num; i++){
        	agentUserTrafficCardInfo.setCard_no(card_start);
        	//依次删除每一个公益(USDT)
        	R result = SpringUtils.getAopProxy(this).addSingleAgentUserTrafficCardInfo(agentUserTrafficCardInfo);
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

}

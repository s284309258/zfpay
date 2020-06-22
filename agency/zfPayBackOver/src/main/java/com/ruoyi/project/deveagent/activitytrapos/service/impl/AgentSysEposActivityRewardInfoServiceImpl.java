package com.ruoyi.project.deveagent.activitytrapos.service.impl;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.SysTableNameConstant;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.json.NetJsonUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.deveagent.activitytrapos.domain.AgentSysTraposActivityRewardInfo;
import com.ruoyi.project.deveagent.activitytrapos.mapper.AgentSysEposActivityInfoMapper;
import com.ruoyi.project.deveagent.activitytrapos.mapper.AgentSysEposActivityRewardInfoMapper;
import com.ruoyi.project.deveagent.activitytrapos.mapper.AgentSysTraposActivityInfoMapper;
import com.ruoyi.project.deveagent.activitytrapos.mapper.AgentSysTraposActivityRewardInfoMapper;
import com.ruoyi.project.deveagent.activitytrapos.service.AgentSysEposActivityRewardInfoService;
import com.ruoyi.project.deveagent.activitytrapos.service.AgentSysTraposActivityRewardInfoService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.param.mapper.ManaSysEditMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 代理====》线上活动(传统POS)奖励类型管理
 * @author Administrator
 *
 */
@Service
public class AgentSysEposActivityRewardInfoServiceImpl implements AgentSysEposActivityRewardInfoService {

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private ManaSysEditMapper manaSysEditMapper;
	@Autowired
	private AgentSysEposActivityInfoMapper agentSysEposActivityInfoMapper;
	@Autowired
	private AgentSysEposActivityRewardInfoMapper agentSysEposActivityRewardInfoMapper;



	/**
	 * 查询线上活动(传统POS)奖励类型列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSysTraposActivityRewardInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSysEposActivityRewardInfoMapper.getAgentSysTraposActivityRewardInfoList(params);
	}


	/**
	 * 导出线上活动(传统POS)奖励类型列表
	 */
	@Override
	public List<AgentSysTraposActivityRewardInfo> selectAgentSysTraposActivityRewardInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentSysEposActivityRewardInfoMapper.selectAgentSysTraposActivityRewardInfoList(params);
	}


	/**
	 * 批量新增线上活动(传统POS)奖励类型
	 */
	@Override
	public R addAgentSysTraposActivityRewardInfos(Map<String, Object> params) {
		try {
			if(!ShiroUtils.getSysUser().isAuth()) {
				return R.error(Type.WARN, "身份信息未认证，不能操作");
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			//（1）只有待发布且未删除的活动才能添加奖励规则
			//根据活动id查询活动详情
			Map<String, Object> activityInfo = agentSysEposActivityInfoMapper.getAgentSysTraposActivityInfoById(params.get("activity_id").toString());
			if(!TypeStatusConstant.sys_activity_info_status_00.equals(StringUtil.getMapValue(activityInfo, "status"))
				|| !TypeStatusConstant.sys_activity_info_del_0.equals(StringUtil.getMapValue(activityInfo, "del"))) {
				return R.error(Type.WARN, "抱歉，只有待发布且未删除的活动才能添加奖励规则");
			}

			int success_num = 0;//成功数量
	        int failure_num = 0;//失败数量
	        String failure_msg = "";//失败消息

			 //拼接id转换成long型数组
	    	String[] pos_nums = params.get("pos_nums").toString().split(";");
			String[] reward_moneys = params.get("reward_moneys").toString().split(";");
			String[] expenditures = params.get("expenditures").toString().split(";");
	    	if(pos_nums.length != reward_moneys.length || pos_nums.length != expenditures.length) {
	    		return R.error(Type.WARN, "参数信息异常");
	    	}
	        for(int i=0;i<pos_nums.length;i++) {
	        	Map<String, Object> activityRewardMap = new HashMap<>();
	        	activityRewardMap.put("id", null);//主键
				activityRewardMap.put("activity_id", params.get("activity_id").toString());//活动id
				activityRewardMap.put("pos_num", pos_nums[i]);//POS机数量
				activityRewardMap.put("reward_money", reward_moneys[i]);//返现金额
				activityRewardMap.put("expenditure", expenditures[i]);//达到交易额
				activityRewardMap.put("remark", params.get("remark"));//备注
				activityRewardMap.put("create_by", ShiroUtils.getLoginName());//创建人
				activityRewardMap.put("cre_date", TimeUtil.getDayString());//创建日期
				activityRewardMap.put("cre_time", TimeUtil.getTimeString());//创建时间
				activityRewardMap.put("length", i+1);//创建时间
	        	//依次新增每一个活动奖励类型
	        	R result = SpringUtils.getAopProxy(this).addAgentSysTraposActivityRewardInfo(activityRewardMap);
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

	        //删除缓存
	        redisUtils.remove(RedisNameConstants.zfpay_sys_activity_reward_list_01+params.get("activity_id").toString());//活动奖励列表
	        return R.ok(failure_msg);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "操作异常");
		}
	}


	/**
	 * 新增保存线上活动（传统POS）奖励类型
	 * @param activityRewardMap
	 * @return
	 */
	@Transactional
	public R addAgentSysTraposActivityRewardInfo(Map<String, Object> activityRewardMap) {
		try {
			int i = agentSysEposActivityRewardInfoMapper.addAgentSysTraposActivityRewardInfo(activityRewardMap);
			if(i != 1) {
				return R.error(Type.WARN, "第"+activityRewardMap.get("length").toString()+"条记录添加失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.WARN, "第"+activityRewardMap.get("length").toString()+"条记录添加异常");
		}
	}


	/**
	 * 根据id查询线上活动(传统POS)奖励类型详情
	 */
	@Override
	public Map<String, Object> getAgentSysTraposActivityRewardInfoById(String id) {
		return agentSysEposActivityRewardInfoMapper.getAgentSysTraposActivityRewardInfoById(id);
	}


	/**
	 * 修改线上活动(传统POS)奖励类型（只能编辑待发布、未删除的状态）
	 */
	@Override
	public R editAgentSysTraposActivityRewardInfo(Map<String, Object> params) {
		try {
			if(!ShiroUtils.getSysUser().isAuth()) {
				return R.error(Type.WARN, "身份信息未认证，不能操作");
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			int i=0;
			//（1）查询old参数信息
			Map<String, Object> oldValue = agentSysEposActivityRewardInfoMapper.getAgentSysTraposActivityRewardInfoById(params.get("reward_id").toString());
			//（2）更新
			params.put("maneger_id", ShiroUtils.getUserId());//代理编号
			params.put("old_status", TypeStatusConstant.sys_activity_info_status_00);//旧状态：待发布
			params.put("old_del",TypeStatusConstant.sys_activity_reward_info_del_0);//旧删除状态：未删除
			params.put("old_activity_del",TypeStatusConstant.sys_activity_info_del_0);//旧删除状态：未删除
			params.put("up_date", TimeUtil.getDayString());//更新日期
			params.put("up_time", TimeUtil.getTimeString());//更新时间
			params.put("update_by", ShiroUtils.getLoginName());//创建人
			i = agentSysEposActivityRewardInfoMapper.updateAgentSysTraposActivityRewardInfo(params);
			if(i != 1) {
				return R.error(Type.WARN, "更新失败");
			}
			//（3）查询new参数信息
			Map<String, Object> newValue = agentSysEposActivityRewardInfoMapper.getAgentSysTraposActivityRewardInfoById(params.get("reward_id").toString());
			//（4）记录修改记录
			params.put("table_name", SysTableNameConstant.t_sys_trapos_activity_reward_info);
			params.put("old_value", NetJsonUtils.mapToJson1(oldValue));
			params.put("new_value", NetJsonUtils.mapToJson1(newValue));
			i = manaSysEditMapper.addManaSysEdit(params);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "修改记录记录失败");
			}
			//（5）删除缓存
			System.out.println("缓存信息===============");
			System.out.println(RedisNameConstants.zfpay_sys_activity_reward_list_01+newValue.get("activity_id").toString());
			System.out.println(RedisNameConstants.zfpay_sys_activity_reward_01+params.get("reward_id").toString());

	        redisUtils.remove(RedisNameConstants.zfpay_sys_activity_reward_list_01+newValue.get("activity_id").toString());//活动奖励列表
	        redisUtils.remove(RedisNameConstants.zfpay_sys_activity_reward_01+params.get("reward_id").toString());//活动奖励详情
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "操作异常");
		}
	}


	/**
	 * 批量删除线上活动(传统POS)奖励类型(只能待发布、未删除的状态才能)
	 */
	@Override
	public R batchRemoveAgentSysTraposActivityRewardInfo(Map<String, Object> params) {
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
        String[] reward_ids = Convert.toStrArray(StringUtil.getMapValue(params, "reward_ids"));
        for(int i=0;i<reward_ids.length;i++) {
        	Map<String, Object> activityRewardMap = new HashMap<>();
            activityRewardMap.put("remark", params.get("remark"));//备注
        	activityRewardMap.put("reward_id", reward_ids[i]);
        	//依次删除每一个公益(USDT)
        	R result = SpringUtils.getAopProxy(this).removeAgentSysTraposActivityRewardInfo(activityRewardMap);
        	if(Type.SUCCESS.value.equals(result.get("code").toString())) {
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
	 * 删除单个线上活动(传统POS)奖励类型(只能待发布、未删除的状态才能)
	 * @param activityRewardMap
	 * @return
	 */
	@Transactional
	public R removeAgentSysTraposActivityRewardInfo(Map<String, Object> activityRewardMap) {
		try {
			int i=0;
			//（1）查询old参数信息
			Map<String, Object> oldValue = agentSysEposActivityRewardInfoMapper.getAgentSysTraposActivityRewardInfoById(activityRewardMap.get("reward_id").toString());
			//（2）更新状态
			activityRewardMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			activityRewardMap.put("old_status", TypeStatusConstant.sys_activity_info_status_00);//旧状态：待发布
			activityRewardMap.put("old_del", TypeStatusConstant.sys_activity_reward_info_del_0);//旧删除状态：未删除
			activityRewardMap.put("new_del", TypeStatusConstant.sys_activity_reward_info_del_1);//新删除状态：已删除
			activityRewardMap.put("update_by", ShiroUtils.getLoginName());//修改人
			activityRewardMap.put("up_date", TimeUtil.getDayString());//修改日期
			activityRewardMap.put("up_time", TimeUtil.getTimeString());//修改时间
			i = agentSysEposActivityRewardInfoMapper.updateAgentSysTraposActivityRewardInfoDel(activityRewardMap);
			if(i != 1) {
				return R.error(Type.WARN, "活动(传统POS)奖励编号"+activityRewardMap.get("reward_id").toString()+"：删除失败，只能删除待发布未删除的活动");
			}
			//（3）查询new参数信息
			Map<String, Object> newValue = agentSysEposActivityRewardInfoMapper.getAgentSysTraposActivityRewardInfoById(activityRewardMap.get("reward_id").toString());
			//（4）记录修改记录
			activityRewardMap.put("table_name", SysTableNameConstant.t_sys_trapos_activity_reward_info);
			activityRewardMap.put("old_value", NetJsonUtils.mapToJson1(oldValue));
			activityRewardMap.put("new_value", NetJsonUtils.mapToJson1(newValue));
			i = manaSysEditMapper.addManaSysEdit(activityRewardMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "修改记录记录失败");
			}
			//（5）删除缓存
			System.out.println("缓存信息===============");
			System.out.println(RedisNameConstants.zfpay_sys_activity_reward_list_01+newValue.get("activity_id").toString());
			System.out.println(RedisNameConstants.zfpay_sys_activity_reward_01+activityRewardMap.get("reward_id").toString());
			
	        redisUtils.remove(RedisNameConstants.zfpay_sys_activity_reward_list_01+newValue.get("activity_id").toString());//活动奖励列表
	        redisUtils.remove(RedisNameConstants.zfpay_sys_activity_reward_01+activityRewardMap.get("reward_id").toString());//活动奖励详情
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "活动(传统POS)奖励编号"+activityRewardMap.get("reward_id").toString()+"：删除异常");
		}
	}

}

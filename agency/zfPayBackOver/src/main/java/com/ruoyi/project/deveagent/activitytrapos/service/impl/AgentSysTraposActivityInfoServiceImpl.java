package com.ruoyi.project.deveagent.activitytrapos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.deveagent.activitytrapos.domain.AgentSysTraposActivityInfo;
import com.ruoyi.project.deveagent.activitytrapos.mapper.AgentSysTraposActivityInfoMapper;
import com.ruoyi.project.deveagent.activitytrapos.mapper.AgentUserTraposActivityApplyInfoMapper;
import com.ruoyi.project.deveagent.activitytrapos.service.AgentSysTraposActivityInfoService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;


/**
 * 代理====》线上活动(传统POS)管理
 * @author Administrator
 *
 */
@Service
public class AgentSysTraposActivityInfoServiceImpl implements AgentSysTraposActivityInfoService {
	
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private AgentSysTraposActivityInfoMapper agentSysTraposActivityInfoMapper;
	@Autowired
	private AgentUserTraposActivityApplyInfoMapper agentUserTraposActivityApplyInfoMapper;
	

	
	/**
	 * 查询线上活动(传统POS)列表
	 */
	@Override
	public List<Map<String, Object>> getAgentSysTraposActivityInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		params.put("today_date", TimeUtil.getDayString());
		return agentSysTraposActivityInfoMapper.getAgentSysTraposActivityInfoList(params);
	}
	
	
	/**
	 * 导出线上活动(传统POS)列表
	 */
	@Override
	public List<AgentSysTraposActivityInfo> selectAgentSysTraposActivityInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		params.put("today_date", TimeUtil.getDayString());
		return agentSysTraposActivityInfoMapper.selectAgentSysTraposActivityInfoList(params);
	}
	
	
	/**
	 * 新增线上活动(传统POS)
	 */
	@Override
	public R addAgentSysTraposActivityInfo(Map<String, Object> params) {
		try {
			if(!ShiroUtils.getSysUser().isAuth()) {
				return R.error(Type.WARN, "身份信息未认证，不能操作");
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			int i=0;
			//（1）参数信息校验
			Integer start_date = Integer.parseInt(params.get("start_date").toString());
			Integer end_date = Integer.parseInt(params.get("end_date").toString());
			Integer sys_date = Integer.parseInt(TimeUtil.getDayString());
			if(start_date<sys_date) {
				return R.error(Type.WARN, "开始时间必须大于等于当前时间");
			}
			if(start_date>end_date) {
				return R.error(Type.WARN, "开始时间必须小于等于结束时间");
			}
			//（2）新增活动信息
			params.put("manager_id", ShiroUtils.getUserId());//代理编号
			params.put("order_id", StringUtil.getDateTimeAndRandomForID());//活动流水订单号
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			params.put("id", null);//活动id，用作主键返回
			i = agentSysTraposActivityInfoMapper.addAgentSysTraposActivityInfo(params);
			if(i != 1) {
				return R.error(Type.WARN, "线上活动(传统POS)新增失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "操作异常");
		}
	}


	/**
	 * 根据id查询线上活动详情
	 */
	@Override
	public Map<String, Object> getAgentSysTraposActivityInfoById(String id) {
		Map<String, Object> params = new HashMap<>();
		params.put("today_date", TimeUtil.getDayString());
		params.put("activity_id", id);
		return agentSysTraposActivityInfoMapper.getAgentSysTraposActivityInfo(params);
	}


	/**
	 * 修改保存线上活动（只能待发布未删除的状态才能编辑）
	 */
	@Override
	public R editAgentSysTraposActivityInfo(Map<String, Object> params) {
		try {
			if(!ShiroUtils.getSysUser().isAuth()) {
				return R.error(Type.WARN, "身份信息未认证，不能操作");
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			int i=0;
			//（1）参数信息校验
			Integer start_date = Integer.parseInt(params.get("start_date").toString());
			Integer end_date = Integer.parseInt(params.get("end_date").toString());
			Integer sys_date = Integer.parseInt(TimeUtil.getDayString());
			if(start_date<sys_date) {
				return R.error(Type.WARN, "开始时间必须大于等于当前时间");
			}
			if(start_date>end_date) {
				return R.error(Type.WARN, "开始时间必须小于等于结束时间");
			}
			//（2）修改活动信息
			params.put("manager_id", ShiroUtils.getUserId());//代理编号
			params.put("update_by", ShiroUtils.getLoginName());//修改人
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			i = agentSysTraposActivityInfoMapper.updateAgentSysTraposActivityInfo(params);
			if(i != 1) {
				return R.error(Type.WARN, "线上活动(传统POS)修改失败，只能编辑待发布未删除的活动");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "操作异常");
		}
	}


	/**
	 * 批量删除线上活动（传统POS）====>只能删除未发布且未删除的活动
	 */
	@Override
	public R batchRemoveAgentSysTraposActivityInfo(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
			return R.error(Type.WARN, "操作备注不能为空");
		}
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        Map<String, Object> activityMap = new HashMap<>();
        activityMap.put("manager_id", ShiroUtils.getUserId());//代理编号
    	activityMap.put("old_status", TypeStatusConstant.sys_activity_info_status_00);//旧状态：待发布
    	activityMap.put("old_del", TypeStatusConstant.sys_activity_info_del_0);//旧删除状态：未删除
    	activityMap.put("new_del", TypeStatusConstant.sys_activity_info_del_1);//新删除状态：已删除
    	activityMap.put("update_by", ShiroUtils.getLoginName());//修改人
    	activityMap.put("up_date", TimeUtil.getDayString());//修改日期
    	activityMap.put("up_time", TimeUtil.getTimeString());//修改时间
    	activityMap.put("remark", params.get("remark"));//备注
    	
		 //拼接id转换成long型数组
        String[] activity_ids = Convert.toStrArray(StringUtil.getMapValue(params, "activity_ids"));
        for(int i=0;i<activity_ids.length;i++) {
        	activityMap.put("activity_id", activity_ids[i]);
        	//依次删除每一个公益(USDT)
        	R result = SpringUtils.getAopProxy(this).removeAgentSysTraposActivityInfo(activityMap);
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
	 * 删除单个线上活动（传统POS）=====》只能删除未发布且未删除的活动
	 * @param activityMap
	 * @return
	 */
	@Transactional
	public R removeAgentSysTraposActivityInfo(Map<String, Object> activityMap) {
		try {
			int i = agentSysTraposActivityInfoMapper.updateAgentSysTraposActivityInfoDel(activityMap);
			if(i != 1) {
				return R.error(Type.WARN, "活动(传统POS)编号"+activityMap.get("activity_id").toString()+"：删除失败，只能删除待发布未删除的活动");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.WARN, "活动(传统POS)编号"+activityMap.get("activity_id").toString()+"：删除异常");
		}
	}


	/**
	 * 系统批量发布和取消系统活动
	 */
	@Override
	public R batchSysReleaseAgentSysTraposActivityInfo(Map<String, Object> params) {
		if(!ShiroUtils.getSysUser().isAuth()) {
			return R.error(Type.WARN, "身份信息未认证，不能操作");
		}
		if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
        	return R.error(Type.WARN, "请输入操作备注");
        }
        if(TypeStatusConstant.sys_activity_info_status_09.equals(StringUtil.getMapValue(params, "status"))) {
        	//批量发布活动（注意：正在进行中的同类活动只有一个）=====》只能发布：待发布且未删除的活动
        	return this.batchReleaseAgentSysTraposActivityInfo(params);
        }else if(TypeStatusConstant.sys_activity_info_status_00.equals(StringUtil.getMapValue(params, "status"))){
        	//批量取消活动
        	return this.batchNoReleaseAgentSysTraposActivityInfo(params);
        }else {
        	return R.error(Type.WARN, "操作指令异常");
        }
	}


	/**
	 * 批量发布线上活动(传统POS)（注意：正在进行中的同类活动只有一个）=====》只能发布：待发布且未删除的活动
	 * @param params
	 * @return
	 */
	private R batchReleaseAgentSysTraposActivityInfo(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] activity_ids = Convert.toStrArray(StringUtil.getMapValue(params, "activity_ids"));
    	Map<String, Object> activityMap = new HashMap<>();
        for(int i=0;i<activity_ids.length;i++) {
        	activityMap.put("remark", params.get("remark"));
        	activityMap.put("activity_id", activity_ids[i]);
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).releaseAgentSysTraposActivityInfo(activityMap);
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
	 * 发布单个线上活动(传统POS)（注意：正在进行中的同类活动只有一个）=====》只能发布：待发布且未删除的活动
	 * @param userMap
	 * @return
	 */
	@Transactional
	public R releaseAgentSysTraposActivityInfo(Map<String, Object> activityMap) {
		try {
			int i=0;
			//（1）查询是否存在同类型的正在进行中的未删除的活动信息
			activityMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			Map<String, Object> sameActivityMap = agentSysTraposActivityInfoMapper.getAgentOnTraposActivitySameType(activityMap);
			if(sameActivityMap!=null) {
				return R.error(Type.WARN, "活动(传统POS)编号"+activityMap.get("activity_id").toString()+"：有同类型的正在进行中的活动，暂不能发布");
			}
			//（2）查询活动详情，已经结束的活动不能发布
			activityMap.put("today_date", TimeUtil.getDayString());
			Map<String, Object> activityDetailMap = agentSysTraposActivityInfoMapper.getAgentSysTraposActivityInfo(activityMap);
			if(TypeStatusConstant.sys_activity_info_open_status_2.equals(activityDetailMap.get("open_status").toString())) {
				return R.error(Type.WARN, "活动(传统POS)编号"+activityMap.get("activity_id").toString()+"：该活动已结束，不能发布");
			}
			//（3）更新活动状态
			activityMap.put("old_del", TypeStatusConstant.sys_activity_info_del_0);//旧状态：未删除
			activityMap.put("old_status", TypeStatusConstant.sys_activity_info_status_00);//旧状态：待发布
			activityMap.put("new_status", TypeStatusConstant.sys_activity_info_status_09);//新状态：发布进行中
			activityMap.put("update_by", ShiroUtils.getLoginName());//修改人
	    	activityMap.put("up_date", TimeUtil.getDayString());//修改日期
	    	activityMap.put("up_time", TimeUtil.getTimeString());//修改时间
			i = agentSysTraposActivityInfoMapper.updateAgentSysTraposActivityInfoStatus(activityMap);
			if(i!=1) {
				return R.error(Type.WARN, "活动(传统POS)编号"+activityMap.get("activity_id").toString()+"：活动发布状态更新失败");
			}
			//（3）删除缓存
			redisUtils.remove(RedisNameConstants.zfpay_sys_activity_list_01+ShiroUtils.getUserId()+"_"+TimeUtil.getDayString());//列表缓存
			redisUtils.remove(RedisNameConstants.zfpay_sys_activity_01+activityMap.get("activity_id").toString());//详情缓存
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.WARN, "活动(传统POS)编号"+activityMap.get("activity_id").toString()+"：发布异常");
		}
	}
	
	
	/**
	 * 批量取消线上活动(传统POS)（注意：查询审核通过的活动申请记录，如果有，则不能取消发布活动）
	 * @param params
	 * @return
	 */
	private R batchNoReleaseAgentSysTraposActivityInfo(Map<String, Object> params) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
		 //拼接id转换成long型数组
        String[] activity_ids = Convert.toStrArray(StringUtil.getMapValue(params, "activity_ids"));
    	Map<String, Object> activityMap = new HashMap<>();
        for(int i=0;i<activity_ids.length;i++) {
        	activityMap.put("remark", params.get("remark"));
        	activityMap.put("activity_id", activity_ids[i]);
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).noReleaseAgentSysTraposActivityInfo(activityMap);
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
	 * 取消单个线上活动（传统POS）（注意：查询审核通过的活动申请记录，如果有，则不能取消发布活动）
	 * @param activityMap
	 * @return
	 */
	@Transactional
	public R noReleaseAgentSysTraposActivityInfo(Map<String, Object> activityMap) {
		try {
			int i=0;
			//（1）查询审核通过的活动申请记录，如果有，则不能取消发布活动
			Integer apply_num = agentUserTraposActivityApplyInfoMapper.getAgentUserTraposActivityApplyNum(activityMap.get("activity_id").toString());
			if(apply_num>0) {
				return R.error(Type.WARN, "活动(传统POS)编号"+activityMap.get("activity_id").toString()+"：该活动已经有人申请了，不能取消发布");
			}
			//（2）更新活动状态
			activityMap.put("manager_id", ShiroUtils.getUserId());//代理编号
			activityMap.put("old_del", TypeStatusConstant.sys_activity_info_del_0);//旧状态：未删除
			activityMap.put("old_status", TypeStatusConstant.sys_activity_info_status_09);//旧状态：待发布
			activityMap.put("new_status", TypeStatusConstant.sys_activity_info_status_00);//新状态：发布进行中
			activityMap.put("update_by", ShiroUtils.getLoginName());//修改人
			activityMap.put("up_date", TimeUtil.getDayString());//修改日期
			activityMap.put("up_time", TimeUtil.getTimeString());//修改时间
			i = agentSysTraposActivityInfoMapper.updateAgentSysTraposActivityInfoStatus(activityMap);
			if(i!=1) {
				return R.error(Type.WARN, "活动(传统POS)编号"+activityMap.get("activity_id").toString()+"：活动发布状态更新失败");
			}
			//（3）删除缓存
			redisUtils.remove(RedisNameConstants.zfpay_sys_activity_list_01+ShiroUtils.getUserId()+"_"+TimeUtil.getDayString());//列表缓存
			redisUtils.remove(RedisNameConstants.zfpay_sys_activity_01+activityMap.get("activity_id").toString());//详情缓存
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.WARN, "活动(传统POS)编号"+activityMap.get("activity_id").toString()+"：取消发布异常");
		}
	}
	
}

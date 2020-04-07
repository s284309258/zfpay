package com.ruoyi.project.deveagent.activitympos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.ScheduleConstants;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.CornUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.activitympos.domain.AgentUserMposAssessInfo;
import com.ruoyi.project.deveagent.activitympos.mapper.AgentUserMposAssessInfoMapper;
import com.ruoyi.project.deveagent.activitympos.service.AgentUserMposAssessInfoService;
import com.ruoyi.project.deveagent.user.mapper.AgentUserInfoMapper;
import com.ruoyi.project.deveagent.usermpos.mapper.AgentUserMposInfoMapper;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.monitor.job.domain.Job;
import com.ruoyi.project.monitor.job.mapper.JobMapper;
import com.ruoyi.project.monitor.job.service.JobServiceImpl;
import com.ruoyi.project.monitor.job.util.ScheduleUtils;


/**
 * 代理====》MPOS系统考核管理
 * @author Administrator
 *
 */
@Service
public class AgentUserMposAssessInfoServiceImpl implements AgentUserMposAssessInfoService {
	
	 @Autowired
	 private JobServiceImpl jobServiceImpl;
	 
	 @Autowired
	 private AgentUserInfoMapper agentUserInfoMapper;
	 @Autowired
	 private AgentUserMposAssessInfoMapper agentUserMposAssessInfoMapper;
	 @Autowired
	 private AgentUserMposInfoMapper agentUserMposInfoMapper;
	 @Autowired
	 private JobMapper jobMapper;
	 @Autowired
	 private Scheduler scheduler;



	
	/**
	 * 查询MPOS系统考核列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserMposAssessInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		params.put("today_date", TimeUtil.getDayString());
		return agentUserMposAssessInfoMapper.getAgentUserMposAssessInfoList(params);
	}
	
	
	/**
	 * 导出MPOS系统考核列表
	 */
	@Override
	public List<AgentUserMposAssessInfo> selectAgentUserMposAssessInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		params.put("today_date", TimeUtil.getDayString());
		return agentUserMposAssessInfoMapper.selectAgentUserMposAssessInfoList(params);
	}


	/**
	 * 新增用户MPOS系统考核信息
	 */
	@Override
	@Transactional
	public R addAgentUserMposAssessInfo(Map<String, Object> params) {
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
			//（2）校验申请的用户信息，校验是否是一级代理用户并且实名认证
			params.put("manager_id", ShiroUtils.getUserId());//代理编号
			Map<String, Object> userMap = agentUserInfoMapper.getAgentFirstAgentUserInfo(params);
//			if(userMap==null) {
//				return R.error(Type.WARN, "只能给属于自己的一级代理设置考核任务");
//			}
			if(!TypeStatusConstant.user_info_auth_status_09.equals(userMap.get("auth_status").toString())) {
				return R.error(Type.WARN, "该用户未实名认证，不能设置考核任务");
			}
			//（3）校验申请的SN信息列表
			String[] sn_lists = params.get("sn_list").toString().split(",");
			int m = agentUserMposInfoMapper.getAgentJoinMposAssesInfoNum(params);
			if(m != sn_lists.length) {
				return R.error(Type.WARN, "只能选择直属的未删除的MPOS");
			}
			//（4）校验该POS是否已经参与扣款活动
			for(int j=0;j<sn_lists.length;j++) {
				//查询是否已经申请过，存在表中
				params.put("sn", sn_lists[j]);
				int n = agentUserMposAssessInfoMapper.getAgentHaveJoinSnNum(params);
				if(n > 0) {
					return R.error(Type.WARN, "选择的MPOS中有参与扣款项目，不能设置");
				}
			}
			//（5）新增活动信息
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			params.put("id", null);//活动id，用作主键返回
			i = agentUserMposAssessInfoMapper.addAgentUserMposAssessInfo(params);
			if(i != 1) {
				return R.error(Type.WARN, "MPOS系统考核新增失败");
			}
			//（6）建立任务对象，并保存任务信息
			Job job = new Job();
			job.setJobName("用户MPOS系统考核"+params.get("id").toString());//任务名称
			job.setJobGroup("DEFAULT");//任务组名
			job.setInvokeTarget("activitySettlementTask.mposAssessTaskSettlement('"+params.get("id").toString()+"')");//调用目标字符串
			job.setCronExpression(CornUtil.getCron(TimeUtil.getOneDayOneTime(end_date.toString(),4)));//corn表达式：计算活动结束的下一天的凌晨四点
			job.setMisfirePolicy(ScheduleConstants.MISFIRE_DO_NOTHING);//执行策略：不触发立即执行
			job.setConcurrent(TypeStatusConstant.sys_task_concurrent2);//并发执行：否
			job.setStatus(ScheduleConstants.Status.NORMAL.getValue());//任务状态：启动
			job.setJobType(TypeStatusConstant.sys_job_job_type_02);//任务类型
			job.setRemark("用户MPOS系统考核:"+params.get("id").toString());
			job.setCreateBy(ShiroUtils.getLoginName());//创建人
			i = jobMapper.insertJob(job);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "调度任务保存失败");
			}
			//（7）更新活动绑定的任务id
			params.put("job_id", job.getJobId());
			i = agentUserMposAssessInfoMapper.updateAgentUserMposAssessInfoJobId(params);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "活动绑定任务失败");
			}
			//（8）创建定时任务
			ScheduleUtils.createScheduleJob(scheduler, job);
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "操作异常");
		}
	}


	/**
	 * 批量删除MPOS系统考核
	 */
	@Override
	public R batchRemoveAgentUserMposAssessInfo(Map<String, Object> params) {
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
        String[] assess_ids = Convert.toStrArray(StringUtil.getMapValue(params, "assess_ids"));
        for(int i=0;i<assess_ids.length;i++) {
        	//依次删除MPOS系统考核
        	Map<String, Object> assessMap = new HashMap<String, Object>();
        	assessMap.put("assess_id", assess_ids[i]);
        	assessMap.put("manager_id",ShiroUtils.getUserId());
        	R result = SpringUtils.getAopProxy(this).removeAgentSysMposAssessInfo(assessMap);
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
	 * 删除单个MPOS系统考核
	 * @param assess_ids
	 * @return
	 */
	@Transactional
	public R removeAgentSysMposAssessInfo(Map<String, Object> assessMap) {
		try {
			//（1）根据编号查询考核详情
			Map<String, Object> userMposAssessMap = agentUserMposAssessInfoMapper.getAgentUserMposAssessInfoById(assessMap.get("assess_id").toString());
			//（2）删除单个MPOS考核
			int i=0;
			i = agentUserMposAssessInfoMapper.deleteAgentUserMposAssessInfo(assessMap);
			if(i != 1) {
				return R.error(Type.WARN, "考核(MPOS)编号"+assessMap.get("assess_id").toString()+"：删除失败");
			}
			//（3）删除调度任务
			R deleteResult = jobServiceImpl.removeJob(Long.parseLong(userMposAssessMap.get("job_id").toString()));
			if(!R.Type.SUCCESS.value.equals(deleteResult.get("code").toString())) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "调度任务删除失败");
            }
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "考核(MPOS)编号"+assessMap.get("assess_id").toString()+"：删除异常");
		}
	}


	/**
	 * 根据id查询考核活动详情
	 */
	@Override
	public Map<String, Object> getAgentUserMposAssessInfoById(String id) {
		return agentUserMposAssessInfoMapper.getAgentUserMposAssessInfoById(id);
	}

}

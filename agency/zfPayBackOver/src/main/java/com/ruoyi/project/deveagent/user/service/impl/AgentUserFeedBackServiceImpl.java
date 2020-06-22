package com.ruoyi.project.deveagent.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.user.domain.AgentUserFeedBack;
import com.ruoyi.project.deveagent.user.mapper.AgentUserFeedBackMapper;
import com.ruoyi.project.deveagent.user.service.AgentUserFeedBackService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;


/**
 * 用户意见反馈管理
 * @author Administrator
 *
 */
@Service
public class AgentUserFeedBackServiceImpl implements AgentUserFeedBackService {
	
	@Autowired
	private AgentUserFeedBackMapper agentUserFeedBackMapper;

	
	/**
	 * 查询用户意见反馈列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserFeedBackList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserFeedBackMapper.getAgentUserFeedBackList(params);
	}

	
	/**
	 * 导出用户意见反馈列表
	 */
	@Override
	public List<AgentUserFeedBack> selectAgentUserFeedBackList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserFeedBackMapper.selectAgentUserFeedBackList(params);
	}


	/**
	 * 根据id查询详情
	 */
	@Override
	public Map<String, Object> getAgentUserFeedBackById(String id) {
		return agentUserFeedBackMapper.getAgentUserFeedBackById(id);
	}


	/**
	 * 修改系统回复
	 */
	@Override
	public R editAgentUserFeedBack(Map<String, Object> params) {
		try {
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = agentUserFeedBackMapper.updateAgentUserFeedBack(params);
			if(i != 1) {
				return R.error(Type.WARN, "更新失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "操作异常");
		}
	}
	
}

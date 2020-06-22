package com.ruoyi.project.deveagent.usertrapos.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.deveagent.user.mapper.AgentUserInfoMapper;
import com.ruoyi.project.deveagent.usertrapos.domain.AgentUserTraditionalPosInstallInfo;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraditionalPosInstallDetailMapper;
import com.ruoyi.project.deveagent.usertrapos.mapper.AgentUserTraditionalPosInstallInfoMapper;
import com.ruoyi.project.deveagent.usertrapos.service.AgentUserTraditionalPosInstallInfoService;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;


/**
 * 代理====》用户传统POS商户进件管理
 * @author Administrator
 *
 */
@Service
public class AgentUserTraditionalPosInstallInfoServiceImpl implements AgentUserTraditionalPosInstallInfoService {
	
	@Autowired
	private AgentUserInfoMapper agentUserInfoMapper;
	
	@Autowired
	private AgentUserTraditionalPosInstallInfoMapper agentUserTraditionalPosInstallInfoMapper;
	@Autowired
	private AgentUserTraditionalPosInstallDetailMapper agentUserTraditionalPosInstallDetailMapper;


	
	/**
	 * 查询用户传统POS商户进件列表
	 */
	@Override
	public List<Map<String, Object>> getAgentUserTraditionalPosInstallInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserTraditionalPosInstallInfoMapper.getAgentUserTraditionalPosInstallInfoList(params);
	}
	
	
	/**
	 * 导出用户传统POS商户进件列表
	 */
	@Override
	public List<AgentUserTraditionalPosInstallInfo> selectAgentUserTraditionalPosInstallInfoList(Map<String, Object> params) {
		params.put("manager_id", ShiroUtils.getUserId());
		return agentUserTraditionalPosInstallInfoMapper.selectAgentUserTraditionalPosInstallInfoList(params);
	}


	/**
	 * 新增用户进件信息
	 */
	@Override
	public R addAgentUserTraditionalPosInstallInfo(Map<String, Object> params) {
		try {
			if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			int i=0;
			//（1）校验申请的用户信息
			params.put("manager_id", ShiroUtils.getUserId());//代理编号
			Map<String, Object> userMap = agentUserInfoMapper.getAgentUserMapInfo(params);
			if(userMap==null) {
				return R.error(Type.WARN, "用户信息无效");
			}
			//（2）保存进件信息
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			i = agentUserTraditionalPosInstallInfoMapper.addAgentUserTraditionalPosInstallInfo(params);
			if(i != 1) {
				return R.error(Type.WARN, "进件信息保存失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "操作异常");
		}
	}


	/**
	 * 根据编号查询进件详情
	 */
	@Override
	public AgentUserTraditionalPosInstallInfo getAgentUserTraditionalPosInstallInfoById(String id) {
		return agentUserTraditionalPosInstallInfoMapper.getAgentUserTraditionalPosInstallInfoById(id);
	}


	/**
	 * 修改保存进件信息
	 */
	@Override
	public R editAgentUserTraditionalPosInstallInfo(Map<String, Object> params) {
		try {
			if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			int i=0;
			//（1）保存进件信息
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			i = agentUserTraditionalPosInstallInfoMapper.updateAgentUserTraditionalPosInstallInfo(params);
			if(i != 1) {
				return R.error(Type.WARN, "进件信息保存失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "操作异常");
		}
	}


	/**
	 * 批量删除商户进件信息
	 */
	@Override
	public R batchRemoveAgentUserTraditionalPosInstallInfo(String ids) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        //拼接id转换成long型数组
        String[] installIds = Convert.toStrArray(ids);
        //依次循环遍历删除
        for (String installId : installIds)
        {
            //依次删除每个
        	R result = SpringUtils.getAopProxy(this).removeAgentUserTraditionalPosInstallInfo(installId);
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
	 * 依次删除商户进件信息
	 * @param installId
	 * @return
	 */
	@Transactional
	public R removeAgentUserTraditionalPosInstallInfo(String installId) {
		try {
			//（1）校验是否存在进件详情
			Integer num = agentUserTraditionalPosInstallDetailMapper.countAgentUserTraditionalPosInstallDetai(installId);
			if(num>0) {
				return R.error(Type.WARN, "编号"+installId+"：存在装机明细，不允许删除");
			}
			//（2）删除装机信息
			int i = agentUserTraditionalPosInstallInfoMapper.deleteAgentUserTraditionalPosInstallInfoById(installId);
			if(i != 1) {
				return R.error(Type.WARN, "编号"+installId+"：删除失败");
			}
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "编号"+installId+"：删除异常");
		}
	}

}

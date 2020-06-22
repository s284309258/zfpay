package com.ruoyi.project.devemana.param.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.param.domain.ManaSysVersion;
import com.ruoyi.project.devemana.param.mapper.ManaSysVersionMapper;
import com.ruoyi.project.devemana.param.service.ManaSysVersionService;


/**
 * 系统版本信息管理
 * @author Administrator
 *
 */
@Service
public class ManaSysVersionServiceImpl implements ManaSysVersionService {
	
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private ManaSysVersionMapper manaSysVersionMapper;


	/**
	 * 查询系统版本列表
	 */
	@Override
	public List<Map<String, Object>> getManaSysVersionList(Map<String, Object> params) {
		return manaSysVersionMapper.getManaSysVersionList(params);
	}


	/**
	 * 导出系统版本
	 */
	@Override
	public List<ManaSysVersion> selectManaSysVersiontList(Map<String, Object> params) {
		return manaSysVersionMapper.selectManaSysVersiontList(params);
	}


	/**
	 * 根据参数id查询版本详情
	 */
	@Override
	public ManaSysVersion getManaSysVersionById(String id) {
		return manaSysVersionMapper.getManaSysVersionById(id);
	}


	/**
	 * 更新版本
	 */
	@Override
	@Transactional
	public R editManaSysVersion(Map<String, Object> map) {
		try {
			if(StringUtils.isEmpty(StringUtil.getMapValue(map, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			int i=0;
			//（1）更新参数信息
			map.put("update_by", ShiroUtils.getSysUser().getLoginName());
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			i = manaSysVersionMapper.updateManaSysVersion(map);
			if(i != 1) {
				return R.error(Type.WARN, "版本信息更新失败");
			}
			redisUtils.remove(RedisNameConstants.zfpay_sys_version_info_new+TypeStatusConstant.user_info_device_type_iOS);
			redisUtils.remove(RedisNameConstants.zfpay_sys_version_info_new+TypeStatusConstant.user_info_device_type_android);
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "版本信息更新异常");
		}
	}


	/**
	 * 新增版本
	 */
	@Override
	public R addManaSysVersion(Map<String, Object> map) {
		try {
			if(StringUtils.isEmpty(StringUtil.getMapValue(map, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			int i=0;
			//（1）更新参数信息
			map.put("create_by", ShiroUtils.getSysUser().getLoginName());
			map.put("cre_date", TimeUtil.getDayString());
			map.put("cre_time", TimeUtil.getTimeString());
			i = manaSysVersionMapper.addManaSysVersion(map);
			if(i != 1) {
				return R.error(Type.WARN, "版本信息新增失败");
			}
			redisUtils.remove(RedisNameConstants.zfpay_sys_version_info_new+TypeStatusConstant.user_info_device_type_iOS);
			redisUtils.remove(RedisNameConstants.zfpay_sys_version_info_new+TypeStatusConstant.user_info_device_type_android);
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "版本信息新增异常");
		}
	}


	/**
	 * 批量删除系统版本
	 */
	@Override
	public R batchRemoveManaSysVersion(String ids) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        //拼接id转换成long型数组
        String[] sysVersionIds = Convert.toStrArray(ids);
        //依次循环遍历删除
        for (String sysVersionId : sysVersionIds)
        {
            //依次删除每个
        	R result = SpringUtils.getAopProxy(this).removeManaSysVersion(sysVersionId);
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
        redisUtils.remove(RedisNameConstants.zfpay_sys_version_info_new+TypeStatusConstant.user_info_device_type_iOS);
		redisUtils.remove(RedisNameConstants.zfpay_sys_version_info_new+TypeStatusConstant.user_info_device_type_android);
        return R.ok(failure_msg);
	}


	/**
	 * 删除单个系统版本
	 * @param sysVersionId
	 * @return
	 */
	@Transactional
	public R removeManaSysVersion(String sysVersionId) {
		try {
			int i = manaSysVersionMapper.deleteManaSysVersion(sysVersionId);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "版本编号"+sysVersionId+"：删除失败");
			}
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "版本编号"+sysVersionId+"：删除异常");
		} 
	}
	
}
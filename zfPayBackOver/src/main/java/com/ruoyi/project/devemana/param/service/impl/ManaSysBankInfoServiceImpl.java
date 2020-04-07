package com.ruoyi.project.devemana.param.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.param.domain.ManaSysBankInfo;
import com.ruoyi.project.devemana.param.mapper.ManaSysBankInfoMapper;
import com.ruoyi.project.devemana.param.service.ManaSysBankInfoService;

/**
 * 管理员====银行基础信息  服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Service
public class ManaSysBankInfoServiceImpl implements ManaSysBankInfoService 
{

	@Autowired
	private ManaSysBankInfoMapper manaSysBankInfoMapper;

	
	/**
	 * 查询银行基础信息列表
	 */
	@Override
	public List<Map<String, Object>> getManaSysBankInfoList(Map<String, Object> params) {
		return manaSysBankInfoMapper.getManaSysBankInfoList(params);
	}

	
	/**
	 * 导出银行基础信息列表
	 */
	@Override
	public List<ManaSysBankInfo> selectManaSysBankInfoList(Map<String, Object> params) {
		return manaSysBankInfoMapper.selectManaSysBankInfoList(params);
	}


	/**
	 * 根据id查询详情
	 */
	@Override
	public Map<String, Object> getManaSysBankInfoById(String id) {
		return manaSysBankInfoMapper.getManaSysBankInfoById(id);
	}


	/**
	 * 编辑银行基础信息
	 */
	@Override
	public R editManaSysBankInfo(Map<String, Object> params) {
		try {
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = manaSysBankInfoMapper.updateManaSysBankInfo(params);
			if(i != 1) {
				return R.error(Type.WARN, "更新失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "更新异常");
		}
	}


	/**
	 * 新增银行基础信息
	 */
	@Override
	public R addManaSysBankInfo(Map<String, Object> params) {
		try {
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			
			int i = manaSysBankInfoMapper.addManaSysBankInfo(params);
			if(i != 1) {
				return R.error(Type.WARN, "新增失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "新增异常");
		}
	}


	/**
	 * 批量删除银行基础信息
	 */
	@Override
	public R batchRemoveManaSysBankInfo(String ids) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        //拼接id转换成long型数组
        String[] bankIds = Convert.toStrArray(ids);
        //依次循环遍历删除
        for (String bankId : bankIds)
        {
            //依次删除每个
        	R result = SpringUtils.getAopProxy(this).removeManaSysBankInfo(bankId);
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
	 * 删除单个数据
	 * @param jobId
	 * @return
	 */
	@Transactional
	public R removeManaSysBankInfo(String bankId) {
		try {
			int i = manaSysBankInfoMapper.deleteManaSysBankInfo(bankId);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "编号"+bankId+"：删除失败");
			}
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "编号"+bankId+"：删除异常");
		} 
	}
	
}

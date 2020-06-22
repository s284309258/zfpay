package com.ruoyi.project.devemana.email.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.SysParamCodeConstants;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.email.domain.ManaSysEmailAccount;
import com.ruoyi.project.devemana.email.mapper.ManaSysEmailAccountMapper;
import com.ruoyi.project.devemana.email.service.ManaSysEmailAccountService;
import com.ruoyi.project.devemana.param.service.ManaSysParamService;

/**
 * 邮箱账号  服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Service
public class ManaSysEmailAccountServiceImpl implements ManaSysEmailAccountService 
{
	@Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private ManaSysParamService manaSysParamService;
	
	@Autowired
	private ManaSysEmailAccountMapper manaSysEmailAccountMapper;

	
	/**
	 * 查询APP图片列表
	 */
	@Override
	public List<Map<String, Object>> getManaSysEmailAccountList(Map<String, Object> params) {
		return manaSysEmailAccountMapper.getManaSysEmailAccountList(params);
	}

	
	/**
	 * 导出APP图片列表
	 */
	@Override
	public List<ManaSysEmailAccount> selectManaSysEmailAccountList(Map<String, Object> params) {
		return manaSysEmailAccountMapper.selectManaSysEmailAccountList(params);
	}


	/**
	 * 根据id查询详情
	 */
	@Override
	public Map<String, Object> getManaSysEmailAccountById(String id) {
		return manaSysEmailAccountMapper.getManaSysEmailAccountById(id);
	}


	/**
	 * 更新邮箱账号
	 */
	@Override
	public R editManaSysEmailAccount(Map<String, Object> params) {
		try {
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = manaSysEmailAccountMapper.updateManaSysEmailAccount(params);
			if(i != 1) {
				return R.error(Type.WARN, "更新失败");
			}
			redisUtils.remove(RedisNameConstants.zfpay_sys_email_number+params.get("num"));
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "更新异常");
		}
	}


	/**
	 * 新增邮箱账号
	 */
	@Override
	public R addManaSysEmailAccount(Map<String, Object> params) {
		try {
			int i = 0;
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			//（1）新增邮箱账号
			i = manaSysEmailAccountMapper.addManaSysEmailAccount(params);
			if(i != 1) {
				return R.error(Type.WARN, "新增失败");
			}
			//（2）查询当前剩余邮箱个数
			Integer remainEmailNum = manaSysEmailAccountMapper.selectManaRemainEmailNum();
			//（3）更新系统参数信息
			Map<String, Object> sysParamMap = new HashMap<>();
			sysParamMap.put("sys_oper_flag", TypeStatusConstant.sys_oper_flag_modify_email_account);//系统操作标识
			sysParamMap.put("remark", "删除邮箱，更新可发送的邮箱数量");//操作备注
			sysParamMap.put("code", SysParamCodeConstants.sys_param_code_sysEmailAccountNum);//参数代码
			sysParamMap.put("value", remainEmailNum);//参数值
			R reslut = manaSysParamService.editManaSysParam(sysParamMap);
			if(!R.Type.SUCCESS.value.equals(reslut.get("code").toString())) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return reslut;
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "新增异常");
		}
	}


	/**
	 * 批量删除APP图片
	 */
	@Override
	public R batchRemoveManaSysEmailAccount(String ids) {
		int success_num = 0;//成功数量
        int failure_num = 0;//失败数量
        String failure_msg = "";//失败消息
        
        //拼接id转换成long型数组
        String[] emailAccountNums = Convert.toStrArray(ids);
        //依次循环遍历删除
        for (String emailAccountNum : emailAccountNums)
        {
            //依次删除每个
        	R result = SpringUtils.getAopProxy(this).removeManaSysEmailAccount(emailAccountNum);
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
	public R removeManaSysEmailAccount(String emailAccountNum) {
		try {
			int i = 0;
			//（1）删除邮箱
			i = manaSysEmailAccountMapper.deleteManaSysEmailAccount(emailAccountNum);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "邮箱编号"+emailAccountNum+"：删除失败");
			}
			//（2）查询大于当前编号的邮箱编号信息
			List<Map<String, Object>> emailAccountList = manaSysEmailAccountMapper.getManaLagerEmailAccountList(emailAccountNum);
			//（3）更新大于当前邮箱邮箱编号的邮箱编号（编号=编号-1）
			i = manaSysEmailAccountMapper.updateManaLagerEmailAccount(emailAccountNum);
			if(i != emailAccountList.size()) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "邮箱编号"+emailAccountNum+"：更新大于当前编号邮箱信息失败");
			}
			//（4）查询当前剩余邮箱个数
			Integer remainEmailNum = manaSysEmailAccountMapper.selectManaRemainEmailNum();
			//（5）更新系统邮箱的参数信息
			Map<String, Object> sysParamMap = new HashMap<>();
			sysParamMap.put("sys_oper_flag", TypeStatusConstant.sys_oper_flag_modify_email_account);//系统操作标识
			sysParamMap.put("remark", "删除邮箱，更新可发送的邮箱数量");//操作备注
			sysParamMap.put("code", SysParamCodeConstants.sys_param_code_sysEmailAccountNum);//参数代码
			sysParamMap.put("value", remainEmailNum);//参数值
			R reslut = manaSysParamService.editManaSysParam(sysParamMap);
			if(!R.Type.SUCCESS.value.equals(reslut.get("code").toString())) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return reslut;
			}
			//（5）依次删除缓存
			for(int j=0; j<emailAccountList.size(); j++) {
				redisUtils.remove(RedisNameConstants.zfpay_sys_email_number+emailAccountList.get(j).get("num"));
			}
			return R.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "邮箱编号"+emailAccountNum+"：删除异常");
		} 
	}
	
}

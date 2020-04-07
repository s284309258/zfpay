package com.ruoyi.project.devemana.param.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.SysTableNameConstant;
import com.ruoyi.common.utils.json.NetJsonUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.redis.RedisUtils;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.param.domain.ManaSysParamRate;
import com.ruoyi.project.devemana.param.mapper.ManaSysEditMapper;
import com.ruoyi.project.devemana.param.mapper.ManaSysParamRateMapper;
import com.ruoyi.project.devemana.param.service.ManaSysParamRateService;


/**
 * 管理员======》系统费率参数管理
 * @author Administrator
 *
 */
@Service
public class ManaSysParamRateServiceImpl implements ManaSysParamRateService {
	
	@Autowired
	private ManaSysParamRateMapper manaSysParamRateMapper;
	@Autowired
	private ManaSysEditMapper manaSysEditMapper;
	@Autowired
	private RedisUtils redisUtils;


	/**
	 * 查询系统费率参数列表
	 */
	@Override
	public List<Map<String, Object>> getManaSysParamRateList(Map<String, Object> params) {
		return manaSysParamRateMapper.getManaSysParamRateList(params);
	}
	
	
	/**
	 * 导出系统费率参数列表
	 */
	@Override
	public List<ManaSysParamRate> selectManaSysParamRateList(Map<String, Object> params) {
		return manaSysParamRateMapper.selectManaSysParamRateList(params);
	}


	/**
	 * 根据id查询系统费率参数详情
	 */
	@Override
	public Map<String, Object> getManaSysParamRateById(String id) {
		return manaSysParamRateMapper.getManaSysParamRateById(id);
	}


	/**
	 * 新增保存系统费率参数
	 */
	@Override
	public R addManaSysParamRate(Map<String, Object> params) {
		try {
			if(!ShiroUtils.getSysUser().isAuth()) {
				return R.error(Type.WARN, "身份信息未认证，不能操作");
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			//（1）保存参数信息
			params.put("create_by", ShiroUtils.getSysUser().getLoginName());
			params.put("cre_date", TimeUtil.getDayString());
			params.put("cre_time", TimeUtil.getTimeString());
			int i = manaSysParamRateMapper.addManaSysParamRate(params);
			if(i != 1) {
				return R.error(Type.WARN, "系统费率参数新增失败");
			}
			//（2）删除缓存
			redisUtils.remove(RedisNameConstants.zfpay_sys_param_rate_list+params.get("type").toString());
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(Type.ERROR, "操作异常");
		}finally{
			redisUtils.remove(RedisNameConstants.zfpay_sys_param_rate_traditionalpos);
			redisUtils.remove(RedisNameConstants.zfpay_sys_param_rate_mpos);
		}
	}


	/**
	 * 修改保存系统费率参数
	 */
	@Override
	@Transactional
	public R editManaSysParamRate(Map<String, Object> params) {
		try {
			if(!ShiroUtils.getSysUser().isAuth()) {
				return R.error(Type.WARN, "身份信息未认证，不能操作");
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(params, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			//（1）查询old参数信息
			Map<String, Object> oldValue = manaSysParamRateMapper.getManaSysParamRateById(params.get("param_rate_id").toString());
			//（2）更新参数信息
			params.put("update_by", ShiroUtils.getSysUser().getLoginName());
			params.put("up_date", TimeUtil.getDayString());
			params.put("up_time", TimeUtil.getTimeString());
			int i = manaSysParamRateMapper.updateManaSysParamRate(params);
			if(i != 1) {
				return R.error(Type.WARN, "系统费率参数更新失败");
			}
			//（3）查询new参数信息
			Map<String, Object> newValue = manaSysParamRateMapper.getManaSysParamRateById(params.get("param_rate_id").toString());
			//（4）记录修改记录
			params.put("table_name", SysTableNameConstant.t_sys_param_rate);
			params.put("old_value", NetJsonUtils.mapToJson1(oldValue));
			params.put("new_value", NetJsonUtils.mapToJson1(newValue));
			i = manaSysEditMapper.addManaSysEdit(params);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "修改记录记录失败");
			}
			//（5）删除缓存
			redisUtils.remove(RedisNameConstants.zfpay_sys_param_rate_list+newValue.get("type").toString());
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "系统费率参数更新异常");
		}finally{
			redisUtils.remove(RedisNameConstants.zfpay_sys_param_rate_traditionalpos);
			redisUtils.remove(RedisNameConstants.zfpay_sys_param_rate_mpos);
		}
	}


	/**
	 * 批量删除系统费率参数
	 */
	@Override
	public R batchRemoveManaSysParamRate(Map<String, Object> params) {
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
        String[] param_rate_ids = Convert.toStrArray(StringUtil.getMapValue(params, "param_rate_ids"));
        for(int i=0;i<param_rate_ids.length;i++) {
        	Map<String, Object> paramRateMap = new HashMap<>();
        	paramRateMap.put("remark", params.get("remark"));
        	paramRateMap.put("param_rate_id", param_rate_ids[i]);
        	//依次冻结每一个账号
        	R result = SpringUtils.getAopProxy(this).removeManaSysParamRate(paramRateMap);
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
	 * 删除单个系统费率参数
	 * @param accountMap
	 * @return
	 */
	@Transactional
	public R removeManaSysParamRate(Map<String, Object> paramRateMap) {
		try {
			int i=0;
			//（1）查询old参数信息
			Map<String, Object> oldValue = manaSysParamRateMapper.getManaSysParamRateById(paramRateMap.get("param_rate_id").toString());
			//（2）删除活动信息
			i = manaSysParamRateMapper.delManaSysParamRateById(paramRateMap);
			if(i != 1) {
				return R.error(Type.WARN, "编号"+paramRateMap.get("param_rate_id").toString()+"：删除失败");
			}
			//（3）记录修改记录
			paramRateMap.put("update_by", ShiroUtils.getSysUser().getLoginName());
			paramRateMap.put("up_date", TimeUtil.getDayString());
			paramRateMap.put("up_time", TimeUtil.getTimeString());
			paramRateMap.put("table_name", SysTableNameConstant.t_sys_param_rate);
			paramRateMap.put("old_value", NetJsonUtils.mapToJson1(oldValue));
			paramRateMap.put("new_value", "");
			i = manaSysEditMapper.addManaSysEdit(paramRateMap);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "编号"+paramRateMap.get("param_rate_id").toString()+"：修改记录记录失败");
			}
			//（4）删除缓存
			redisUtils.remove(RedisNameConstants.zfpay_sys_param_rate_list+oldValue.get("type").toString());
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.WARN, "编号"+paramRateMap.get("param_rate_id").toString()+"：删除异常");
		}finally{
			redisUtils.remove(RedisNameConstants.zfpay_sys_param_rate_traditionalpos);
			redisUtils.remove(RedisNameConstants.zfpay_sys_param_rate_mpos);
		}
	}


	/**
	 * 根据类型查询参数值列表
	 */
	@Override
	public List<ManaSysParamRate> getManaParamRateListByType(String type) {
		return manaSysParamRateMapper.getManaParamRateListByType(type);
	}

	
}

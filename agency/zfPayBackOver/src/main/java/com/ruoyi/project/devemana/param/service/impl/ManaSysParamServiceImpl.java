package com.ruoyi.project.devemana.param.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruoyi.common.constant.RedisNameConstants;
import com.ruoyi.common.constant.SysTableNameConstant;
import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.common.utils.json.NetJsonUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.string.StringUtil;
import com.ruoyi.common.utils.time.TimeUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Redis;
import com.ruoyi.framework.aspectj.lang.annotation.Redis.CacheOperation;
import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.develop.common.domain.R.Type;
import com.ruoyi.project.devemana.param.domain.ManaSysParam;
import com.ruoyi.project.devemana.param.mapper.ManaSysEditMapper;
import com.ruoyi.project.devemana.param.mapper.ManaSysParamMapper;
import com.ruoyi.project.devemana.param.service.ManaSysParamService;


/**
 * 参数管理
 * @author Administrator
 *
 */
@Service
public class ManaSysParamServiceImpl implements ManaSysParamService {
	
	@Autowired
	private ManaSysParamMapper manaSysParamMapper;
	@Autowired
	private ManaSysEditMapper manaSysEditMapper;
	

	
	/**
	 * 根据参数代码查询参数值
	 */
	@Redis(keyPrefix=RedisNameConstants.zfpay_sys_param,
			fieldKey="#code", cacheOperation=CacheOperation.QUERY)
	@Override
	public String getParamByCode(String code) {
		return manaSysParamMapper.getParamByCode(code);
	}


	/**
	 * 查询系统参数列表
	 */
	@Override
	public List<Map<String, Object>> getManaSysParamList(Map<String, Object> params) {
		return manaSysParamMapper.getManaSysParamList(params);
	}


	/**
	 * 导出系统参数
	 */
	@Override
	public List<ManaSysParam> selectManaSysParamList(Map<String, Object> params) {
		return manaSysParamMapper.selectManaSysParamList(params);
	}


	/**
	 * 根据参数id查询参数详情
	 */
	@Override
	public Map<String, Object> getManaSysParamById(String id) {
		return manaSysParamMapper.getManaSysParamById(id);
	}


	/**
	 * 更新参数
	 */
	@Redis(keyPrefix=RedisNameConstants.zfpay_sys_param,
			fieldKey="#map['code']", cacheOperation=CacheOperation.UPDATE)
	@Override
	@Transactional
	public R editManaSysParam(Map<String, Object> map) {
		try {
			if(!TypeStatusConstant.sys_oper_flag_modify_email_account.equals(StringUtil.getMapValue(map, "sys_oper_flag"))) {
				if(!ShiroUtils.getSysUser().isAuth()) {
					return R.error(Type.WARN, "身份信息未认证，不能操作");
				}
			}
			if(StringUtils.isEmpty(StringUtil.getMapValue(map, "remark"))) {
				return R.error(Type.WARN, "操作备注不能为空");
			}
			int i=0;
			//（1）查询old参数信息
			Map<String, Object> oldValue = manaSysParamMapper.getManaParamMapByCode(map.get("code").toString());
			//（2）更新参数信息
			map.put("update_by", ShiroUtils.getSysUser().getLoginName());
			map.put("up_date", TimeUtil.getDayString());
			map.put("up_time", TimeUtil.getTimeString());
			i = manaSysParamMapper.updateManaSysParam(map);
			if(i != 1) {
				return R.error(Type.WARN, "系统参数更新失败");
			}
			//（3）查询new参数信息
			Map<String, Object> newValue = manaSysParamMapper.getManaParamMapByCode(map.get("code").toString());
			//（4）记录修改记录
			map.put("table_name", SysTableNameConstant.t_sys_param);
			map.put("old_value", NetJsonUtils.mapToJson1(oldValue));
			map.put("new_value", NetJsonUtils.mapToJson1(newValue));
			i = manaSysEditMapper.addManaSysEdit(map);
			if(i != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return R.error(Type.WARN, "系统参数修改记录记录失败");
			}
			return R.ok("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error(Type.ERROR, "系统参数更新异常");
		}
	}
}

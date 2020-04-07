package com.ruoyi.project.devemana.email.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.devemana.email.domain.ManaVerifyCode;

/**
 * 验证码发送 服务层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface ManaVerifyCodeService 
{

	/**
	 * 查询验证码发列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaVerifyCodeList(Map<String, Object> params);

	
	/**
	 * 导出验证码发列表
	 * @param params
	 * @return
	 */
	List<ManaVerifyCode> selectManaVerifyCodeList(Map<String, Object> params);
	
}

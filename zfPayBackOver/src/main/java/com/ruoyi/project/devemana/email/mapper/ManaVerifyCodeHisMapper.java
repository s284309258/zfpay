package com.ruoyi.project.devemana.email.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.email.domain.ManaVerifyCode;

/**
 * 验证码发送  数据层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface ManaVerifyCodeHisMapper 
{

	/**
	 * 查询验证码发送列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaVerifyCodeHisList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 导出验证码发送列表
	 * @param params
	 * @return
	 */
	List<ManaVerifyCode> selectManaVerifyCodeHisList(@Param("map") Map<String, Object> params);

}
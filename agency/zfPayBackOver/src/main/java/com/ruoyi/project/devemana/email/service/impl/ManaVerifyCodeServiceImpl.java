package com.ruoyi.project.devemana.email.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.devemana.email.domain.ManaVerifyCode;
import com.ruoyi.project.devemana.email.mapper.ManaVerifyCodeMapper;
import com.ruoyi.project.devemana.email.service.ManaVerifyCodeService;

/**
 * 验证码发送  服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Service
public class ManaVerifyCodeServiceImpl implements ManaVerifyCodeService 
{
	
	@Autowired
	private ManaVerifyCodeMapper manaVerifyCodeMapper;

	
	/**
	 * 查询验证码发送列表
	 */
	@Override
	public List<Map<String, Object>> getManaVerifyCodeList(Map<String, Object> params) {
		return manaVerifyCodeMapper.getManaVerifyCodeList(params);
	}

	
	/**
	 * 导出验证码发送列表
	 */
	@Override
	public List<ManaVerifyCode> selectManaVerifyCodeList(Map<String, Object> params) {
		return manaVerifyCodeMapper.selectManaVerifyCodeList(params);
	}

}

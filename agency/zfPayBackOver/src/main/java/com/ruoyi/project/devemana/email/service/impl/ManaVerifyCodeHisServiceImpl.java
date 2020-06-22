package com.ruoyi.project.devemana.email.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.devemana.email.domain.ManaVerifyCode;
import com.ruoyi.project.devemana.email.mapper.ManaVerifyCodeHisMapper;
import com.ruoyi.project.devemana.email.service.ManaVerifyCodeHisService;

/**
 * 验证码发送  服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Service
public class ManaVerifyCodeHisServiceImpl implements ManaVerifyCodeHisService 
{
	
	@Autowired
	private ManaVerifyCodeHisMapper manaVerifyCodeHisMapper;

	
	/**
	 * 查询验证码发送列表
	 */
	@Override
	public List<Map<String, Object>> getManaVerifyCodeHisList(Map<String, Object> params) {
		return manaVerifyCodeHisMapper.getManaVerifyCodeHisList(params);
	}

	
	/**
	 * 导出验证码发送列表
	 */
	@Override
	public List<ManaVerifyCode> selectManaVerifyCodeHisList(Map<String, Object> params) {
		return manaVerifyCodeHisMapper.selectManaVerifyCodeHisList(params);
	}

}

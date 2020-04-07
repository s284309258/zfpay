package com.ruoyi.project.devemana.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.devemana.statistics.domain.ManaSummaryUserRegisterEvery;
import com.ruoyi.project.devemana.user.domain.ManaUserInfo;
import com.ruoyi.project.devemana.user.mapper.ManaUserInfoMapper;
import com.ruoyi.project.devemana.user.service.ManaUserInfoService;


/**
 * 管理员====》用户信息管理
 * @author Administrator
 *
 */
@Service
public class ManaUserInfoServiceImpl implements ManaUserInfoService {
	
	
	@Autowired
	private ManaUserInfoMapper manaUserInfoMapper;


	
	/**
	 * 查询用户列表
	 */
	@Override
	public List<Map<String, Object>> getManaUserInfoList(Map<String, Object> params) {
		return manaUserInfoMapper.getManaUserInfoList(params);
	}
	
	
	/**
	 * 统计用户信息
	 */
	@Override
	public Map<String, Object> summaryManaUserInfoList(Map<String, Object> params) {
		return manaUserInfoMapper.summaryManaUserInfoList(params);
	}

	
	/**
	 * 导出用户列表
	 */
	@Override
	public List<ManaUserInfo> selectManaUserInfoList(Map<String, Object> params) {
		return manaUserInfoMapper.selectManaUserInfoList(params);
	}


	/**
	 * 根据id查询用户详情
	 */
	@Override
	public ManaUserInfo getManaUserInfoById(String id) {
		return manaUserInfoMapper.getManaUserInfoById(id);
	}


	/**
	 * 统计用户注册量
	 */
	@Override
	public List<Map<String, Object>> getManaSummaryUserRegisterList(Map<String, Object> params) {
		return manaUserInfoMapper.getManaSummaryUserRegisterList(params);
	}


	/**
	 * 导出用户注册量
	 */
	@Override
	public List<ManaSummaryUserRegisterEvery> selectManaSummaryUserRegisterList(Map<String, Object> params) {
		return manaUserInfoMapper.selectManaSummaryUserRegisterList(params);
	}


	/**
	 * 用户注册量统计
	 */
	@Override
	public List<Map<String, Object>> getManaUserRegisterList() {
		return manaUserInfoMapper.getManaUserRegisterList();
	}
	
}

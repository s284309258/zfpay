package com.ruoyi.project.devemana.user.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.devemana.statistics.domain.ManaSummaryUserRegisterEvery;
import com.ruoyi.project.devemana.user.domain.ManaUserInfo;

/**
 * 管理员======用户信息管理
 * @author Administrator
 *
 */
public interface ManaUserInfoService {

	
	/**
	 * 查询用户列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaUserInfoList(Map<String, Object> params);
	
	
	/**
	 * 统计用户信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryManaUserInfoList(Map<String, Object> params);

	
	/**
	 * 导出用户信息
	 * @param params
	 * @return
	 */
	List<ManaUserInfo> selectManaUserInfoList(Map<String, Object> params);


	/**
	 * 根据id查询用户详情
	 * @param id
	 * @return
	 */
	ManaUserInfo getManaUserInfoById(String id);


	/**
	 * 按天查询统计用户注册量
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSummaryUserRegisterList(Map<String, Object> params);


	/**
	 * 导出用户每天注册量
	 * @param params
	 * @return
	 */
	List<ManaSummaryUserRegisterEvery> selectManaSummaryUserRegisterList(Map<String, Object> params);


	/**
	 * 用户注册量统计
	 * @return
	 */
	List<Map<String, Object>> getManaUserRegisterList();

}
	
	

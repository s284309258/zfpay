package com.ruoyi.project.devemana.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.statistics.domain.ManaSummaryUserRegisterEvery;
import com.ruoyi.project.devemana.user.domain.ManaUserInfo;



/**
 * 管理员====》用户信息管理
 * @author Administrator
 *
 */
public interface ManaUserInfoMapper {


	/**
	 * 查询用户信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaUserInfoList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 统计用户信息
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryManaUserInfoList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户列表
	 * @param params
	 * @return
	 */
	List<ManaUserInfo> selectManaUserInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 根据id查询用户详情
	 * @param id
	 * @return
	 */
	ManaUserInfo getManaUserInfoById(@Param("user_id") String user_id);


	/**
	 * 查询用户注册量
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSummaryUserRegisterList(@Param("map") Map<String, Object> params);


	/**
	 * 导出用户注册量
	 * @param params
	 * @return
	 */
	List<ManaSummaryUserRegisterEvery> selectManaSummaryUserRegisterList(@Param("map") Map<String, Object> params);


	/**
	 * 用户注册量统计
	 * @return
	 */
	List<Map<String, Object>> getManaUserRegisterList();
	
	/**
	 * 更新用户收益
	 * @param map
	 * @return
	 */
	int updateUserMoneyBenefit(@Param("map") Map<String, Object> map);
	
	/**
	 * 更新扣除金额
	 * @param map
	 * @return
	 */
	int updateUserDeductMoney(@Param("map") Map<String, Object> map);

}

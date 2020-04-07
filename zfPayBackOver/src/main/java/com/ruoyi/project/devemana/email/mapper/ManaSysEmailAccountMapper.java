package com.ruoyi.project.devemana.email.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.email.domain.ManaSysEmailAccount;

/**
 * 邮箱账号  数据层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface ManaSysEmailAccountMapper 
{

	/**
	 * 查询邮箱账号列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysEmailAccountList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 导出邮箱账号列表
	 * @param params
	 * @return
	 */
	List<ManaSysEmailAccount> selectManaSysEmailAccountList(@Param("map") Map<String, Object> params);


	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getManaSysEmailAccountById(@Param("email_account_num") String emailAccount_id);


	/**
	 * 更新邮箱账号
	 * @param params
	 * @return
	 */
	int updateManaSysEmailAccount(@Param("map") Map<String, Object> params);


	/**
	 * 新增邮箱账号
	 * @param params
	 * @return
	 */
	int addManaSysEmailAccount(@Param("map") Map<String, Object> params);


	/**
	 * 根据邮箱账号编号删除
	 * @param exchangeUrlId
	 * @return
	 */
	int deleteManaSysEmailAccount(@Param("email_account_num") String email_account_num);


	/**
	 * 查询大于当前编号的邮箱编号信息
	 * @param emailAccountNum
	 * @return
	 */
	List<Map<String, Object>> getManaLagerEmailAccountList(@Param("email_account_num") String emailAccountNum);


	/**
	 * 更新大于当前邮箱邮箱编号的邮箱编号（编号=编号-1）
	 * @param emailAccountNum
	 * @return
	 */
	int updateManaLagerEmailAccount(@Param("email_account_num") String emailAccountNum);


	/**
	 * 查询当前剩余邮箱个数
	 * @return
	 */
	Integer selectManaRemainEmailNum();
}
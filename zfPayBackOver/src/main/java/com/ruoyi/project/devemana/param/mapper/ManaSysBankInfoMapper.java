package com.ruoyi.project.devemana.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.param.domain.ManaSysBankInfo;

/**
 * 管理员====银行基础信息 数据层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface ManaSysBankInfoMapper 
{

	/**
	 * 查询银行基础信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysBankInfoList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 导出银行基础信息列表
	 * @param params
	 * @return
	 */
	List<ManaSysBankInfo> selectManaSysBankInfoList(@Param("map") Map<String, Object> params);


	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getManaSysBankInfoById(@Param("bank_id") String bank_id);


	/**
	 * 更新银行基础信息
	 * @param params
	 * @return
	 */
	int updateManaSysBankInfo(@Param("map") Map<String, Object> params);


	/**
	 * 新增银行基础信息
	 * @param params
	 * @return
	 */
	int addManaSysBankInfo(@Param("map") Map<String, Object> params);


	/**
	 * 根据银行基础信息id删除
	 * @param exchangeUrlId
	 * @return
	 */
	int deleteManaSysBankInfo(@Param("bank_id") String bank_id);
}
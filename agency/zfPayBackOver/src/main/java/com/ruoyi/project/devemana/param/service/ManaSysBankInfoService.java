package com.ruoyi.project.devemana.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.devemana.param.domain.ManaSysBankInfo;

/**
 * 管理员====银行基础信息 服务层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface ManaSysBankInfoService 
{

	/**
	 * 查询银行基础信息列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysBankInfoList(Map<String, Object> params);

	
	/**
	 * 导出银行基础信息列表
	 * @param params
	 * @return
	 */
	List<ManaSysBankInfo> selectManaSysBankInfoList(Map<String, Object> params);

	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getManaSysBankInfoById(String id);

	
	/**
	 * 编辑银行基础信息
	 * @param params
	 * @return
	 */
	R editManaSysBankInfo(Map<String, Object> params);

	
	/**
	 * 新增银行基础信息
	 * @param params
	 * @return
	 */
	R addManaSysBankInfo(Map<String, Object> params);


	/**
	 * 批量删除银行基础信息
	 * @param ids
	 * @return
	 */
	R batchRemoveManaSysBankInfo(String ids);
	
}

package com.ruoyi.project.devemana.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.devemana.param.domain.ManaSysBenefitName;

/**
 * 流水类型 服务层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface ManaSysBenefitNameService 
{
	
	/**
	 * 查询流水类型列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaBenefitTypeList(Map<String, Object> params);

	
	/**
	 * 查询流水类型列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysBenefitNameList(Map<String, Object> params);

	
	/**
	 * 导出流水类型
	 * @param params
	 * @return
	 */
	List<ManaSysBenefitName> selectManaSysBenefitNameList(Map<String, Object> params);
	
}

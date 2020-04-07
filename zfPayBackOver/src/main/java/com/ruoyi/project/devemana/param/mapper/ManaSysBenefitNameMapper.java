package com.ruoyi.project.devemana.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.param.domain.ManaSysBenefitName;

/**
 * 流水类型 数据层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface ManaSysBenefitNameMapper 
{

	/**
	 * 查询流水类型列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaBenefitTypeList(@Param("map") Map<String, Object> params);

	
	/**
	 * 查询流水类型列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysBenefitNameList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出流水类型列表
	 * @param params
	 * @return
	 */
	List<ManaSysBenefitName> selectManaSysBenefitNameList(@Param("map") Map<String, Object> params);
}
package com.ruoyi.project.devemana.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.devemana.param.domain.ManaSysParamRate;

/**
 * 管理员======系统费率参数管理
 * @author Administrator
 *
 */
public interface ManaSysParamRateService {

	
	/**
	 * 查询系统费率参数列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysParamRateList(Map<String, Object> params);
	
	
	/**
	 * 导出系统费率参数信息
	 * @param params
	 * @return
	 */
	List<ManaSysParamRate> selectManaSysParamRateList(Map<String, Object> params);


	/**
	 * 根据id查询系统费率参数详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getManaSysParamRateById(String id);


	/**
	 * 新增保存系统费率参数
	 * @param params
	 * @return
	 */
	R addManaSysParamRate(Map<String, Object> params);


	/**
	 * 修改保存系统费率参数
	 * @param params
	 * @return
	 */
	R editManaSysParamRate(Map<String, Object> params);


	/**
	 * 批量删除系统费率参数
	 * @param params
	 * @return
	 */
	R batchRemoveManaSysParamRate(Map<String, Object> params);


	/**
	 * 根据类型查询参数值列表
	 * @param type
	 * @return
	 */
	List<ManaSysParamRate> getManaParamRateListByType(String type,String pos_type);

}
	
	

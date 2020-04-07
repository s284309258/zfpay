package com.ruoyi.project.devemana.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.devemana.param.domain.ManaSysParam;

/**
 * 参数管理
 * @author Administrator
 *
 */
public interface ManaSysParamService {
	
	
	/**
	 * 根据参数代码查询参数值
	 * @param code
	 * @return
	 */
	String getParamByCode(String code);

	
	/**
	 * 查询系统参数列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysParamList(Map<String, Object> params);

	
	/**
	 * 导出系统参数
	 * @param params
	 * @return
	 */
	List<ManaSysParam> selectManaSysParamList(Map<String, Object> params);


	/**
	 * 根据参数id查询参数详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getManaSysParamById(String id);


	/**
	 * 编辑系统参数
	 * @param params
	 * @return
	 */
	R editManaSysParam(Map<String, Object> map);
}

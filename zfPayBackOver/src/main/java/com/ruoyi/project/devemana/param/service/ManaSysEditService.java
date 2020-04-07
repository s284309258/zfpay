package com.ruoyi.project.devemana.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.devemana.param.domain.ManaSysEdit;

/**
 * 参数修改记录管理
 * @author Administrator
 *
 */
public interface ManaSysEditService {
	
	
	/**
	 * 查询系统参数修改记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysEditList(Map<String, Object> params);

	
	/**
	 * 导出系统参数修改记录
	 * @param params
	 * @return
	 */
	List<ManaSysEdit> selectManaSysEditList(Map<String, Object> params);


	/**
	 * 根据id查询参数修改记录详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getManaSysEditById(String id);

}

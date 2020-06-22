package com.ruoyi.project.devemana.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.devemana.param.domain.ManaSysVersion;

/**
 * 系统版本信息管理
 * @author Administrator
 *
 */
public interface ManaSysVersionService {
	
	/**
	 * 查询系统版本列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysVersionList(Map<String, Object> params);

	
	/**
	 * 导出系统版本列表
	 * @param params
	 * @return
	 */
	List<ManaSysVersion> selectManaSysVersiontList(Map<String, Object> params);


	/**
	 * 根据参数id查询版本详情
	 * @param id
	 * @return
	 */
	ManaSysVersion getManaSysVersionById(String id);


	/**
	 * 编辑系统版本
	 * @param params
	 * @return
	 */
	R editManaSysVersion(Map<String, Object> map);


	/**
	 * 新增保存系统版本
	 * @param params
	 * @return
	 */
	R addManaSysVersion(Map<String, Object> params);


	/**
	 * 批量删除系统版本
	 * @param ids
	 * @return
	 */
	R batchRemoveManaSysVersion(String ids);

}

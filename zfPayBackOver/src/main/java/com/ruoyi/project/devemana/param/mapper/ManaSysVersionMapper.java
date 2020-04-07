package com.ruoyi.project.devemana.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.param.domain.ManaSysVersion;



/**
 * 版本信息管理
 * @author Administrator
 *
 */
public interface ManaSysVersionMapper {

	
	/**
	 * 查询系统版本列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysVersionList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出系统版本
	 * @param params
	 * @return
	 */
	List<ManaSysVersion> selectManaSysVersiontList(@Param("map") Map<String, Object> params);


	/**
	 * 根据参数id查询版本详情
	 * @param id
	 * @return
	 */
	ManaSysVersion getManaSysVersionById(@Param("version_id") String version_id);


	/**
	 * 更新版本信息
	 * @param params
	 * @return
	 */
	int updateManaSysVersion(@Param("map") Map<String, Object> params);


	/**
	 * 新增版本信息
	 * @param map
	 * @return
	 */
	int addManaSysVersion(@Param("map") Map<String, Object> map);


	/**
	 * 删除系统版本
	 * @param sysVersionId
	 * @return
	 */
	int deleteManaSysVersion(@Param("version_id") String version_id);
	
}

package com.ruoyi.project.devemana.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.param.domain.ManaSysParam;



/**
 * 参数管理
 * @author Administrator
 *
 */
public interface ManaSysParamMapper {

	/**
	 * 根据参数代码查询参数值
	 * @param code
	 * @return
	 */
	String getParamByCode(@Param("code") String code);

	
	/**
	 * 查询系统参数列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysParamList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出系统参数
	 * @param params
	 * @return
	 */
	List<ManaSysParam> selectManaSysParamList(@Param("map") Map<String, Object> params);


	/**
	 * 根据参数id查询参数详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getManaSysParamById(@Param("param_id") String id);


	/**
	 * 更新参数信息
	 * @param params
	 * @return
	 */
	int updateManaSysParam(@Param("map") Map<String, Object> params);


	/**
	 * 根据参数代码查询参数详情
	 * @param string
	 * @return
	 */
	Map<String, Object> getManaParamMapByCode(@Param("code") String code);
	
}

package com.ruoyi.project.devemana.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.param.domain.ManaSysParamRate;



/**
 * 管理员======》系统费率参数管理
 * @author Administrator
 *
 */
public interface ManaSysParamRateMapper {


	/**
	 * 查询系统费率参数列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysParamRateList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出系统费率参数列表
	 * @param params
	 * @return
	 */
	List<ManaSysParamRate> selectManaSysParamRateList(@Param("map") Map<String, Object> params);


	/**
	 * 根据id查询系统费率参数详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getManaSysParamRateById(@Param("param_rate_id") String param_rate_id);


	/**
	 * 更新保存系统费率参数
	 * @param params
	 * @return
	 */
	int updateManaSysParamRate(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 新增保存系统费率参数
	 * @param map
	 * @return
	 */
	int addManaSysParamRate(@Param("map") Map<String, Object> params);


	/**
	 * 根据id删除系统费率参数
	 * @param string
	 * @return
	 */
	int delManaSysParamRateById(@Param("map") Map<String, Object> params);


	/**
	 * 查询指定类型的费率是否存在
	 * @param sysParamRateType1
	 * @param credit_card_rate
	 * @return
	 */
	List<Map<String, Object>> getManaSysParamRateIsValid(@Param("type") String type, @Param("rate") String rate);


	/**
	 * 根据类型查询参数值列表
	 * @param type
	 * @return
	 */
	List<ManaSysParamRate> getManaParamRateListByType(@Param("type") String type);

}

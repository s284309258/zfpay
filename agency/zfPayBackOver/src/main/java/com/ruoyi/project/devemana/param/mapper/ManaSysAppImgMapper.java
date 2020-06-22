package com.ruoyi.project.devemana.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.param.domain.ManaSysAppImg;

/**
 * APP图片 数据层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface ManaSysAppImgMapper 
{

	/**
	 * 查询APP图片列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysAppImgList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 导出APP图片列表
	 * @param params
	 * @return
	 */
	List<ManaSysAppImg> selectManaSysAppImgList(@Param("map") Map<String, Object> params);


	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getManaSysAppImgById(@Param("appImg_id") String appImg_id);


	/**
	 * 更新APP图片
	 * @param params
	 * @return
	 */
	int updateManaSysAppImg(@Param("map") Map<String, Object> params);


	/**
	 * 新增APP图片
	 * @param params
	 * @return
	 */
	int addManaSysAppImg(@Param("map") Map<String, Object> params);


	/**
	 * 根据APP图片id删除
	 * @param exchangeUrlId
	 * @return
	 */
	int deleteManaSysAppImg(@Param("appImg_id") String appImg_id);
}
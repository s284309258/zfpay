package com.ruoyi.project.devemana.param.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.develop.common.domain.R;
import com.ruoyi.project.devemana.param.domain.ManaSysAppImg;

/**
 * APP图片 服务层
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
public interface ManaSysAppImgService 
{

	/**
	 * 查询APP图片列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaSysAppImgList(Map<String, Object> params);

	
	/**
	 * 导出APP图片列表
	 * @param params
	 * @return
	 */
	List<ManaSysAppImg> selectManaSysAppImgList(Map<String, Object> params);

	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> getManaSysAppImgById(String id);

	
	/**
	 * 编辑APP图片
	 * @param params
	 * @return
	 */
	R editManaSysAppImg(Map<String, Object> params);

	
	/**
	 * 新增APP图片
	 * @param params
	 * @return
	 */
	R addManaSysAppImg(Map<String, Object> params);


	/**
	 * 批量删除APP图片
	 * @param ids
	 * @return
	 */
	R batchRemoveManaSysAppImg(String ids);
	
}

package com.ruoyi.project.devemana.param.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.devemana.param.domain.ManaSysEdit;
import com.ruoyi.project.devemana.param.mapper.ManaSysEditMapper;
import com.ruoyi.project.devemana.param.service.ManaSysEditService;


/**
 * 参数修改记录管理
 * @author Administrator
 *
 */
@Service
public class ManaSysEditServiceImpl implements ManaSysEditService {
	
	@Autowired
	private ManaSysEditMapper manaSysEditMapper;

	
	/**
	 * 查询系统参数列表
	 */
	@Override
	public List<Map<String, Object>> getManaSysEditList(Map<String, Object> params) {
		return manaSysEditMapper.getManaSysEditList(params);
	}


	/**
	 * 导出系统参数
	 */
	@Override
	public List<ManaSysEdit> selectManaSysEditList(Map<String, Object> params) {
		return manaSysEditMapper.selectManaSysEditList(params);
	}


	/**
	 * 根据id查询修改参数详情
	 */
	@Override
	public Map<String, Object> getManaSysEditById(String id) {
		return manaSysEditMapper.getManaSysEditById(id);
	}
	
}

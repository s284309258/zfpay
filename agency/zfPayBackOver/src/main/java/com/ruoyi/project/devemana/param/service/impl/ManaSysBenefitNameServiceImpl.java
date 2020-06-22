package com.ruoyi.project.devemana.param.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.constant.TypeStatusConstant;
import com.ruoyi.project.devemana.param.domain.ManaSysBenefitName;
import com.ruoyi.project.devemana.param.mapper.ManaSysBenefitNameMapper;
import com.ruoyi.project.devemana.param.service.ManaSysBenefitNameService;

/**
 * 流水类型 服务层实现
 * 
 * @author ruoyi
 * @date 2019-05-16
 */
@Service
public class ManaSysBenefitNameServiceImpl implements ManaSysBenefitNameService 
{
	
	@Autowired
	private ManaSysBenefitNameMapper manaSysBenefitNameMapper;

	
	/**
	 * 查询流水类型列表
	 */
	@Override
	public List<Map<String, Object>> getManaBenefitTypeList(Map<String, Object> params) {
		//RET钱包
		if(TypeStatusConstant.BENEFIT_TYPE_01.equals(params.get("purse_type").toString())) {
			params.put("change_type", "("+TypeStatusConstant.sys_benefit_name_change_type_01+","
										+TypeStatusConstant.sys_benefit_name_change_type_05+","
										+TypeStatusConstant.sys_benefit_name_change_type_07+","
										+TypeStatusConstant.sys_benefit_name_change_type_09+")");
		}else if(TypeStatusConstant.BENEFIT_TYPE_02.equals(params.get("purse_type").toString())) {
			//定存钱包
			params.put("change_type", "("+TypeStatusConstant.sys_benefit_name_change_type_02+","
					+TypeStatusConstant.sys_benefit_name_change_type_05+","
					+TypeStatusConstant.sys_benefit_name_change_type_06+","
					+TypeStatusConstant.sys_benefit_name_change_type_09+")");
		}else if(TypeStatusConstant.BENEFIT_TYPE_03.equals(params.get("purse_type").toString())) {
			//奖励钱包
			params.put("change_type", "("+TypeStatusConstant.sys_benefit_name_change_type_03+","
					+TypeStatusConstant.sys_benefit_name_change_type_06+","
					+TypeStatusConstant.sys_benefit_name_change_type_07+","
					+TypeStatusConstant.sys_benefit_name_change_type_08+","
					+TypeStatusConstant.sys_benefit_name_change_type_09+")");
		}else {
			//杠杆钱包
			params.put("change_type", "("+TypeStatusConstant.sys_benefit_name_change_type_04+","
					+TypeStatusConstant.sys_benefit_name_change_type_08+","
					+TypeStatusConstant.sys_benefit_name_change_type_09+")");
		}
		return manaSysBenefitNameMapper.getManaBenefitTypeList(params);
	}


	/**
	 * 查询流水类型列表
	 */
	@Override
	public List<Map<String, Object>> getManaSysBenefitNameList(Map<String, Object> params) {
		return manaSysBenefitNameMapper.getManaSysBenefitNameList(params);
	}


	/**
	 * 导出流水类型列表
	 */
	@Override
	public List<ManaSysBenefitName> selectManaSysBenefitNameList(Map<String, Object> params) {
		return manaSysBenefitNameMapper.selectManaSysBenefitNameList(params);
	}
	
}

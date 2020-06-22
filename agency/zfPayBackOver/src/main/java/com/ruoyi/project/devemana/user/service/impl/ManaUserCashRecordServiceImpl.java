package com.ruoyi.project.devemana.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.devemana.user.domain.ManaUserCashRecord;
import com.ruoyi.project.devemana.user.mapper.ManaUserCashRecordMapper;
import com.ruoyi.project.devemana.user.service.ManaUserCashRecordService;


/**
 * 管理员======用户取现记录管理
 * @author Administrator
 *
 */
@Service
public class ManaUserCashRecordServiceImpl implements ManaUserCashRecordService {
	
	
	@Autowired
	private ManaUserCashRecordMapper manaUserCashRecordMapper;


	
	/**
	 * 查询用户取现记录列表
	 */
	@Override
	public List<Map<String, Object>> getManaUserCashRecordList(Map<String, Object> params) {
		return manaUserCashRecordMapper.getManaUserCashRecordList(params);
	}
	
	
	/**
	 * 汇总取现记录
	 */
	@Override
	public Map<String, Object> summaryManaUserCashRecordList(Map<String, Object> params) {
		return manaUserCashRecordMapper.summaryManaUserCashRecordList(params);
	}
	
	
	/**
	 * 导出用户取现记录列表
	 */
	@Override
	public List<ManaUserCashRecord> selectManaUserCashRecordList(Map<String, Object> params) {
		return manaUserCashRecordMapper.selectManaUserCashRecordList(params);
	}


	/**
	 * 查询取现记录详情列表
	 */
	@Override
	public List<Map<String, Object>> getManaUserCashRecordDetailList(Map<String, Object> params) {
		return manaUserCashRecordMapper.getManaUserCashRecordDetailList(params);
	}
	
}

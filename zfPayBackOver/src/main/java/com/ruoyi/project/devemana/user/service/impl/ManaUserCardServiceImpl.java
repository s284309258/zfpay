package com.ruoyi.project.devemana.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.devemana.user.domain.ManaUserCard;
import com.ruoyi.project.devemana.user.mapper.ManaUserCardMapper;
import com.ruoyi.project.devemana.user.service.ManaUserCardService;


/**
 * 管理员====》用户结算卡管理
 * @author Administrator
 *
 */
@Service
public class ManaUserCardServiceImpl implements ManaUserCardService {
	
	
	@Autowired
	private ManaUserCardMapper manaUserCardMapper;


	
	/**
	 * 查询用户结算卡列表
	 */
	@Override
	public List<Map<String, Object>> getManaUserCardList(Map<String, Object> params) {
		return manaUserCardMapper.getManaUserCardList(params);
	}
	
	
	/**
	 * 导出用户结算卡列表
	 */
	@Override
	public List<ManaUserCard> selectManaUserCardList(Map<String, Object> params) {
		return manaUserCardMapper.selectManaUserCardList(params);
	}


	/**
	 * 根据id查询用户结算卡详情
	 */
	@Override
	public ManaUserCard getManaUserCardById(String id) {
		return manaUserCardMapper.getManaUserCardById(id);
	}
	
}

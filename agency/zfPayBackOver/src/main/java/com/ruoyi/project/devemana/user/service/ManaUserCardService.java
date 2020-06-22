package com.ruoyi.project.devemana.user.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.devemana.user.domain.ManaUserCard;

/**
 * 管理员======用户结算卡管理
 * @author Administrator
 *
 */
public interface ManaUserCardService {

	
	/**
	 * 查询用户结算卡列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaUserCardList(Map<String, Object> params);
	
	
	/**
	 * 导出用户结算卡信息
	 * @param params
	 * @return
	 */
	List<ManaUserCard> selectManaUserCardList(Map<String, Object> params);


	/**
	 * 根据id查询用户结算卡详情
	 * @param id
	 * @return
	 */
	ManaUserCard getManaUserCardById(String id);

}
	
	

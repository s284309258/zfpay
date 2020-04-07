package com.ruoyi.project.devemana.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.user.domain.ManaUserCard;



/**
 * 管理员====》用户结算卡管理
 * @author Administrator
 *
 */
public interface ManaUserCardMapper {


	/**
	 * 查询用户结算卡列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaUserCardList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户结算卡列表
	 * @param params
	 * @return
	 */
	List<ManaUserCard> selectManaUserCardList(@Param("map") Map<String, Object> params);


	/**
	 * 根据id查询用户结算卡详情
	 * @param id
	 * @return
	 */
	ManaUserCard getManaUserCardById(@Param("card_id") String card_id);

}

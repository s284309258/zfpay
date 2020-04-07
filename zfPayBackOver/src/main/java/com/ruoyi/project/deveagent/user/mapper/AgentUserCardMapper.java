package com.ruoyi.project.deveagent.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.deveagent.user.domain.AgentUserCard;
import org.apache.ibatis.annotations.Select;


/**
 * 代理====》用户结算卡管理
 * @author Administrator
 *
 */
public interface AgentUserCardMapper {


	/**
	 * 查询用户结算卡列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAgentUserCardList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户结算卡列表
	 * @param params
	 * @return
	 */
	List<AgentUserCard> selectAgentUserCardList(@Param("map") Map<String, Object> params);


	/**
	 * 根据id查询用户结算卡详情
	 * @param id
	 * @return
	 */
	AgentUserCard getAgentUserCardById(@Param("card_id") String card_id);


	/**
	 * 更新用户结算卡状态
	 * @param cardMap
	 * @return
	 */
	int updateAgentUserCardStatus(@Param("map") Map<String, Object> cardMap);


	/***
	 * 查询user_id根据卡id add byqh 201912
	 * @param card_id
	 * @return
	 */
	@Select("select user_id from t_user_card_info where id=#{card_id}")
	String selectCardUserIDByCardID(@Param("card_id") String card_id);


	/**
	 * 查询结算卡信息
	 * @param params
	 * @return
	 */
	Map<String, Object> getAgentUserCard(@Param("map") Map<String, Object> params);

}

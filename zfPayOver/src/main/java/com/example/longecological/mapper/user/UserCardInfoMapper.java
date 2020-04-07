package com.example.longecological.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 用户结算卡管理
 * @author Administrator
 *
 */
public interface UserCardInfoMapper {

	
	/**
	 * 查询用户结算卡列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getUserCardList(@Param("map") Map<String, Object> map);

	
	/**
	 * 查询用户添加的结算卡总数量
	 * @param string
	 * @return
	 */
	Integer getUserCardNum(@Param("user_id") String user_id);


	/**
	 * 查询用户添加的默认卡数量
	 * @param string
	 * @return
	 */
	Integer getDefaultCardNum(@Param("user_id") String user_id);

	
	/**
	 * 保存用户银行卡
	 * @param dataMap
	 * @return
	 */
	int addUserCard(@Param("map") Map<String, Object> dataMap);


	/**
	 * 将用户的银行卡设置成非默认卡
	 * @param dataMap
	 * @return
	 */
	int updateCardToNoDefault(@Param("map") Map<String, Object> dataMap);


	/**
	 * 删除用户银行卡
	 * @param dataMap
	 * @return
	 */
	int delUserCardById(@Param("map") Map<String, Object> dataMap);


	/**
	 * 更新用户银行卡信息
	 * @param dataMap
	 * @return
	 */
	int updateUserCard(@Param("map") Map<String, Object> dataMap);


	/**
	 * 查询用户结算卡信息
	 * @param dataMap
	 * @return
	 */
	Map<String, Object> getUserCardInfo(@Param("map") Map<String, Object> dataMap);

}

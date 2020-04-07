package com.example.longecological.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.longecological.entity.cash.CashRecord;

/**
 * 用户提现管理
 * @author Administrator
 *
 */
public interface UserCashRecordMapper {


	/**
	 * 保存用户提现申请记录
	 * @param dataMap
	 * @return
	 */
	int addUserCashRecord(@Param("map") Map<String, Object> dataMap);

	
	/**
	 * 保存用户提现记录详情
	 * @param dataMap
	 * @return
	 */
	int addUserCashRecordDetail(@Param("map") Map<String, Object> dataMap);


	/**
	 * 查询提现记录列表
	 * @param map
	 * @return
	 */
	List<CashRecord> getCashRecordList(@Param("map") Map<String, Object> map);

}

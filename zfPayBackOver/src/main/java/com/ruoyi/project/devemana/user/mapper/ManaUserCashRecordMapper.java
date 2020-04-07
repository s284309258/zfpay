package com.ruoyi.project.devemana.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.user.domain.ManaUserCashRecord;



/**
 * 管理员======用户取现记录管理
 * @author Administrator
 *
 */
public interface ManaUserCashRecordMapper {


	/**
	 * 查询用户取现记录列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaUserCashRecordList(@Param("map") Map<String, Object> params);
	
	
	/**
	 * 汇总取现记录数据
	 * @param params
	 * @return
	 */
	Map<String, Object> summaryManaUserCashRecordList(@Param("map") Map<String, Object> params);

	
	/**
	 * 导出用户取现记录列表
	 * @param params
	 * @return
	 */
	List<ManaUserCashRecord> selectManaUserCashRecordList(@Param("map") Map<String, Object> params);


	/**
	 * 查询取现记录详情列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getManaUserCashRecordDetailList(@Param("map") Map<String, Object> params);
	

}

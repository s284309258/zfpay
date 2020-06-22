package com.ruoyi.project.devemana.notice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ruoyi.project.devemana.notice.domain.ManaSysMoneyLockerCollege;

/**
 * 管理员=====钱柜学院 数据层
 * 
 * @author ruoyi
 */
public interface ManaSysMoneyLockerCollegeMapper
{

	/**
	 * 查询钱柜学院列表
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getManaSysMoneyLockerCollegeList(@Param("map") Map<String, Object> params);

	
	/**
	 * 查询钱柜学院详情
	 * @param id
	 * @return
	 */
	public ManaSysMoneyLockerCollege getManaSysMoneyLockerCollegeById(@Param("college_id") String college_id);

	
	/**
	 * 新增钱柜学院
	 * @param params
	 * @return
	 */
	public int addManaSysMoneyLockerCollege(@Param("map") Map<String, Object> params);

	
	/**
	 * 删除钱柜学院
	 * @param notice_id
	 * @return
	 */
	public int deleteManaSysMoneyLockerCollege(@Param("college_id") String college_id);

}
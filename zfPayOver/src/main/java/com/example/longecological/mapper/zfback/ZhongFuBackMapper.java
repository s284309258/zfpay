package com.example.longecological.mapper.zfback;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ZhongFuBackMapper {

	int insertTraditionalPosInstallProcess(@Param("map") Map<String, Object> map);

	int updateTraditionalPosInstallProcess(@Param("map") Map<String, Object> map);

	List<Map<String, Object>> getTraditionalPosInstallProcess(@Param("map") Map<String, Object> map);

	/**
	 * 查询设备号直属用户
	 * @param machine_id
	 * @return
	 */
	String getMachineIdUser(@Param("machine_id") String machine_id);
	
	/**
	 * 保存装机记录
	 * @param map
	 * @return
	 */
	int insertUserTraditionalPosInstall(@Param("map") Map<String, Object> map);
	
	/**
	 * 保存设备记录
	 * @param map
	 * @return
	 */
	int insertUserTraditionalPosInstallDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询进件记录
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTraditionalPosInstallList(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询进件详情
	 * @param map
	 * @return
	 */
	Map<String, Object> getTraditionalPosInstallDetail(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询设备列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTerminalList(@Param("map") Map<String, Object> map);
}

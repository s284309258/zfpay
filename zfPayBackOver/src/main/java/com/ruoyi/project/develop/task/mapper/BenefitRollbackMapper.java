package com.ruoyi.project.develop.task.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BenefitRollbackMapper {

	List<Map<String, Object>> getDealTraposDataList();
	
	List<Map<String, Object>> getTraposBenefitList(@Param("map") Map<String, Object> map);
	
	int updateUserBenefit(@Param("map") Map<String, Object> map);
	
	Map<String, Object> getBenefitRecordMap(@Param("map") Map<String, Object> map);
	
	int updateBenefitRecord(@Param("map") Map<String, Object> map);
	
	int delBenefitRecord(@Param("map") Map<String, Object> map);
	
	List<Map<String, Object>> getAgencyList(@Param("map") Map<String, Object> map);
	
	int delTraposBenefit(@Param("map") Map<String, Object> map);
	
	int updateMerchantTraposPerformance(@Param("map") Map<String, Object> map);
	
	int updateUserMerchantPerformance(@Param("map") Map<String, Object> map);
	
	int updateAgencyTraposPerformance(@Param("map") Map<String, Object> map);
	
	int updateUserAgencyPerformance(@Param("map") Map<String, Object> map);
	
	int updateTraposDataStatus(@Param("map") Map<String, Object> map);
}

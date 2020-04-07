package com.example.longecological.mapper.test;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface Test {

	int insert(@Param("map") Map<String, Object> map);
}

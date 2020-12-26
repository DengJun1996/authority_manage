package com.dj.system.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;


@Mapper
public interface CommonMapper {

	/**
	 *根公共查询方法
	 */
	List<T> selectfinddate(@Param("sqlstr") String sqlstr);
	/**
	 *公共更新方法
	 */
	int updatedate(@Param("updatesqlstr") String updatesqlstr);

	int updatetoken(@Param("token") String token);

	int importdate(String importdate);
}

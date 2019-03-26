package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

public interface FaceMapper {
	
	@Select("select name from face where = #{id} ")
	List<String> getName(@Param("id") Integer id);
	
	@SelectProvider(type = SqlProvider.class, method = "getStuName" )
	List<String> getNames(@Param("id") Integer id);
	
	
	class SqlProvider{
		
		public static String getStuName() {
			
			return null;
		}
	}
	
	

}

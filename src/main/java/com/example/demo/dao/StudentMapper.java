package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.Student;

public interface StudentMapper {
	
	Student selectByNameAndPwd(@Param("uname")String uname,@Param("upass")String upass);
	
	List<Student> queryStuByName(String stuName);
	
	String queryMaxStuNoByMonth(String prefix);
    
    String getSameNameMaxName(String name);
	
	List<Student> listAllStu();
	
    int deleteByPrimaryKey(Integer stuId);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer stuId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    
}
package com.icia.drawAcademy.dao.classdao;

import org.apache.ibatis.annotations.Param;

import com.icia.drawAcademy.dto.ClassDto;


public interface ClassDao {

	void class1proc(ClassDto classdto);
	
	void PlusMemberClassName(@Param("className")String className,@Param("m_email") String m_email);
	
	void PlusClassNameMember(@Param("m_name")String m_name,@Param("className")String className, @Param("m_email") String m_email);
	
	Integer classLimit(String className);
}

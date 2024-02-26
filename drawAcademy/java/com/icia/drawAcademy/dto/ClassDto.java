package com.icia.drawAcademy.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Alias("classDto")
public class ClassDto {
	private Integer c_id;
	private String className;
	private String day;
	private String date;
	private String time;
	private String m_name;
	private String m_email;

}

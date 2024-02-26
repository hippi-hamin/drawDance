package com.icia.drawAcademy.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Alias("draw")
@ToString
public class MemberDto {
	private Integer m_id;
	private String m_name;
	private String m_phoneNumber;
	private String m_email;
	private String m_password;
	private String m_address;
	private String m_age;
	private String sORt;
	private String className; 
		
	
	
}

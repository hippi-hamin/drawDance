package com.icia.drawAcademy.dao;




import com.icia.drawAcademy.dto.MemberDto;

public interface MemberDao {

	void signUp(MemberDto memberDto);
	
	MemberDto login(MemberDto memberDto);
	
	void updateMember(MemberDto memberDto);
	
	void memout(Integer m_id);
	
	Integer checkEmail(String m_email);
	
	MemberDto myPage(Integer m_id);
} // interface end

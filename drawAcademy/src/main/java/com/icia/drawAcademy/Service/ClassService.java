package com.icia.drawAcademy.Service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.icia.drawAcademy.dao.classdao.ClassDao;
import com.icia.drawAcademy.dto.ClassDto;
import com.icia.drawAcademy.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClassService {

	@Autowired
	private ClassDao cDao;

	public String classPage(HttpSession session) {
		log.info("classPage()");
		
		String view = null;
		MemberDto loggedInMember = (MemberDto) session.getAttribute("login");
		
		try {
		if (loggedInMember != null) {
		
			view = "Class/classpage";
		
			
			return view;
			
			} else {
				view = "redirect:login";
			
			return view;
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			view = "redirect:login";
			
			return view;
		}
	}

	public String class1proc(ClassDto classDto, HttpSession session, RedirectAttributes rttr) {
		log.info("class1proc()");
		String msg = null;
		String view = null;
		MemberDto loggedInMember = (MemberDto) session.getAttribute("login");
		String m_name = loggedInMember.getM_name();
		String className = classDto.getClassName();
		String m_email = loggedInMember.getM_email();

		try {
			view = "redirect:/?";
			msg = "������û ����";
			classDto.setM_name(m_name);
			classDto.setM_email(m_email);
			loggedInMember.setClassName(className);
			// System.out.println("classDto = " + classDto);
			// System.out.println("loggedInMember = " + loggedInMember);

			// MemberDto�� ClassName ���� ������Ʈ
			cDao.class1proc(classDto); // ���� ����
			
			cDao.PlusMemberClassName(className, m_email);
			cDao.PlusClassNameMember(className, m_name, m_email);
			System.out.println(m_email);
			rttr.addFlashAttribute("msg", msg);
			return view;
		} catch (Exception e) { // ���� ����
			e.printStackTrace();
			view = "redirect:/?";
			msg = "������û ����";
		}

		rttr.addFlashAttribute("msg", msg);
		return view;

	}

	public Integer getClassLimit(String className) {
	    log.info("getClassLimitServ()");
	    
	    int classNameCount = cDao.classLimit(className);
	    
	   return classNameCount;
	}


}
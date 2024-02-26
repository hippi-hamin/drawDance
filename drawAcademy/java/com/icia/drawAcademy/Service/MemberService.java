package com.icia.drawAcademy.Service;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.icia.drawAcademy.dao.MemberDao;

import com.icia.drawAcademy.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {

	@Autowired
	private MemberDao mDao;
	
	

	// 회원가입
	public String signUp(MemberDto member, HttpSession session, RedirectAttributes rttr) {
		log.info("signUp()");
		String msg = null; // DB 저장 성공/실패 관련 메시지 저장
		String view = null; // 대상 페이지 지정 변수

		try {
			mDao.signUp(member); // 저장 성공
			System.out.println("member" + member);
			view = "redirect:/?";
			msg = "회원가입 성공";

		} catch (Exception e) { // 저장 실패
			e.printStackTrace();
			view = "redirect:/?";
			msg = "회원가입 실패";
		}

		rttr.addFlashAttribute("msg", msg);
		System.out.println(msg);
		return view;
	}

//-------------------------------------------------------------------------------------//
	// 로그인
	public String login(MemberDto memberDto, HttpSession session, RedirectAttributes rttr) {
		log.info("login()");
		String msg = null;
		String view = null;
		
		MemberDto loggedInMember = mDao.login(memberDto);

		if (loggedInMember != null) {
//			mDao.login(m_email, m_password);
			msg = "로그인 성공";
			view = "redirect:/";

			System.out.println(loggedInMember);
			// 로그인시 세션에 저장
			session.setAttribute("login", loggedInMember);

		} else {
			msg = "로그인 실패/ 이메일 및 비밀번호를 다시 확인해주시길 바랍니다.";
			view = "redirect:/login";
		}
		
		rttr.addFlashAttribute("msg", msg);
		return view;

	}

	public String logout(HttpSession session, RedirectAttributes rttr) {
		log.info("logout()");
		String msg = "로그아웃 성공";

		session.removeAttribute("login");

		rttr.addFlashAttribute("msg", msg);
		return "redirect:/";
	}
	// -------------------------------------------------------------------------------------//

	// 회원정보 수정

	public String updateMember(MemberDto memberDto, HttpSession session, RedirectAttributes rttr) {
		log.info("updateMember()");

		String msg = null;
		String view = null;
		MemberDto loggedInMember = (MemberDto) session.getAttribute("login");
		int m_id = loggedInMember.getM_id();
		memberDto.setM_id(m_id);
		try {
			mDao.updateMember(memberDto);
			System.out.println("memberDTO = " + loggedInMember);

			loggedInMember = mDao.login(memberDto);
			session.setAttribute("login", loggedInMember);

			msg = "수정 성공";
			view = "redirect:/mypage?m_id=" + loggedInMember.getM_id();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "수정 실패";
			view = "redirect:/setting?m_id=" + loggedInMember.getM_id();
		}

		rttr.addFlashAttribute("msg", msg);
		return view;
	}
//------------------------------------------------------------------------------------------------------------//
	// 회원탈퇴
	public String memout(Integer m_id, HttpSession session, RedirectAttributes rttr) {
		log.info("memout()");
		String msg = null;
		String view = null;
		MemberDto loggedInMember = (MemberDto) session.getAttribute("login");
		int id = loggedInMember.getM_id();
		
		
		try {
			System.out.println(id);
			mDao.memout(id);
			session.removeAttribute("login");
			msg = "탈퇴 성공";
			view = "redirect:/";
			
		} catch (Exception e) {
			e.printStackTrace();
			msg = "탈퇴 실패";
			view = "redirect:member/mypage";
		}

		rttr.addFlashAttribute("msg", msg);
		return view;
	}

	
	public String checkEmail(String m_email) {
			String checkMsg = null;
			
	    try {
	        int emailCount = mDao.checkEmail(m_email);
	        if (emailCount > 0) {
	        	checkMsg = "중복된 이메일입니다.";
	            return checkMsg;
	        } else {
	        	checkMsg = "작성가능한 이메일 입니다.";
	        	return checkMsg;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}

	public MemberDto myPage(Integer m_id, Model model) {
			log.info("mypage()");
			//DB에서 꺼내와야함
			MemberDto memberDto = mDao.myPage(m_id);
			//상자에 담기
			model.addAttribute("memberDto", memberDto);
			System.out.println("service memberDto = " + memberDto);
			return memberDto;
	}

}// end

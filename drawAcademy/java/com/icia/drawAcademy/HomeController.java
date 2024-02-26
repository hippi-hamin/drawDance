package com.icia.drawAcademy;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.icia.drawAcademy.Service.ClassService;
import com.icia.drawAcademy.Service.MemberService;
import com.icia.drawAcademy.dto.ClassDto;
import com.icia.drawAcademy.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@Autowired
	private MemberService mServ;

	@Autowired
	private ClassService cServ;

	// --------------------------------------------------------------------------------//
	@GetMapping("/")
	public String home(Model model) {
		log.info("home()");

		return "home";

	}

	@GetMapping("instructors")
	public String instructors() {
		log.info("instructors()");
		return "headerMenu/instructors";
	}

	// --------------------------------------------------------------------------------//
	@GetMapping("signUp")
	public String signUp() {
		log.info("signUp()");

		return "member/signUp";
	}

	@PostMapping("signUpProc")
	public String signUpProc(MemberDto memberDto, HttpSession session, RedirectAttributes rttr) {
		log.info("signUpProc()");
		String view = mServ.signUp(memberDto, session, rttr);
		System.out.println("rttr = " + rttr);

		return view;
	}

	@PostMapping(value = "emailCheckResult", produces = "application/text; charset=utf8")
	@ResponseBody
//@ResponseBody �� ���� ������ �ܼ��� xml���� �Ǵ� Json �������� ���� �� �ֱ� ������. / @ResponseBody�� ����ϸ� View Resolver�� ��ȸ�ϰ� ���� ��ȯ ���� HTTP ���� ������ �ֽ��ϴ�.
	public String checkEmail(@RequestParam String m_email) {
		log.info("checkEmail()");

		String result = mServ.checkEmail(m_email);
		return result;
	}

	// --------------------------------------------------------------------------------//
	@GetMapping("login")
	public String login() {
		log.info("login()");
		return "member/login";
	}

	@PostMapping("loginProc")
	public String loginProc(MemberDto memberDto, HttpSession session, RedirectAttributes rttr) {
		log.info("loginProc()");

		String view = mServ.login(memberDto, session, rttr);
		return view;
	}

	// --------------------------------------------------------------------------------//
	// �α׾ƿ�
	@GetMapping("logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		log.info("logout");
		String msg = "�α׾ƿ� ����";
		// ���ǿ��� "login" �Ӽ����� ����
		session.removeAttribute("login");

		// �α׾ƿ� �� �α��� �������� �����̷�Ʈ
		rttr.addFlashAttribute("msg", msg);
		return "redirect:/";
	}

	// --------------------------------------------------------------------------------//
	@GetMapping("mypage")
	public String mypage(Integer m_id, Model model, HttpSession session) {
		log.info("mypage()");

		// ���ǿ��� �α����� ȸ�� ������ ������
		MemberDto loggedInMember = (MemberDto) session.getAttribute("login");

		if (loggedInMember != null) {
			// �α����� ȸ�� ������ �𵨿� �߰��Ͽ� JSP�� ����
			MemberDto memberDto = mServ.myPage(loggedInMember.getM_id(), model);
			model.addAttribute("memberDto", memberDto);
			System.out.println(memberDto);
			return "member/mypage";
		} else {
			// �α����� ȸ�� ������ ������ �α��� �������� �����̷�Ʈ
			return "redirect:/login";
		}
	}

	// --------------------------------------------------------------------------------//
	@GetMapping("setting")
	public String setting(Model model, HttpSession session) {
		log.info("setting()");

		MemberDto loggedInMember = (MemberDto) session.getAttribute("login");

		if (loggedInMember != null) {
			// �α����� ȸ�� ������ �𵨿� �߰��Ͽ� JSP�� ����
			model.addAttribute("loggedInMember", loggedInMember);

			return "member/setting";
		} else {
			// �α����� ȸ�� ������ ������ �α��� �������� �����̷�Ʈ
			return "redirect:/login";
		}
	}

	@PostMapping("updateMember")
	public String updateMember(MemberDto memberDto, HttpSession session, RedirectAttributes rttr) {
		log.info("updateMember()");

		String view = mServ.updateMember(memberDto, session, rttr);
		return view;

	}

	@GetMapping("memout")
	public String memout(Integer m_id, HttpSession session, RedirectAttributes rttr) {
		log.info("memout()");

		String view = mServ.memout(m_id, session, rttr);
		return view;
	}

	// ������û ������
	// ---------------------------------------------------------------------------------------
	@GetMapping("classpage")
	public String classpage(HttpSession session) {
		log.info("classpage()");

		String view = cServ.classPage(session);
		return view;
	}

	@GetMapping("class1")
	public String class1(Model model) {
		log.info("class1()");
		model.addAttribute("classLimitA", cServ.getClassLimit("classA"));
		model.addAttribute("classLimitB", cServ.getClassLimit("classB"));
		model.addAttribute("classLimitC", cServ.getClassLimit("classC"));

		return "Class/class1";
	}

	@PostMapping("class1proc")
	public String class1proc(ClassDto classDto, HttpSession session, RedirectAttributes rttr) {
		log.info("class1proc()");

		String view = cServ.class1proc(classDto, session, rttr);
		System.out.println("rttr = " + rttr);

		return view;
	}

	@GetMapping("class2")
	public String class2() {
		log.info("class2()");
		return "Class/class2";
	}

	@GetMapping("class3")
	public String class3() {
		log.info("class3()");
		return "Class/class3";
	}
}
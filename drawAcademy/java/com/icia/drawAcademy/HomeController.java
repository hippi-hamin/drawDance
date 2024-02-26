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
//@ResponseBody 를 쓰는 이유는 단순한 xml형식 또는 Json 형식으로 보낼 수 있기 때문에. / @ResponseBody를 사용하면 View Resolver를 우회하고 직접 반환 값을 HTTP 응답 본문에 넣습니다.
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
	// 로그아웃
	@GetMapping("logout")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		log.info("logout");
		String msg = "로그아웃 성공";
		// 세션에서 "login" 속성만을 제거
		session.removeAttribute("login");

		// 로그아웃 후 로그인 페이지로 리다이렉트
		rttr.addFlashAttribute("msg", msg);
		return "redirect:/";
	}

	// --------------------------------------------------------------------------------//
	@GetMapping("mypage")
	public String mypage(Integer m_id, Model model, HttpSession session) {
		log.info("mypage()");

		// 세션에서 로그인한 회원 정보를 가져옴
		MemberDto loggedInMember = (MemberDto) session.getAttribute("login");

		if (loggedInMember != null) {
			// 로그인한 회원 정보를 모델에 추가하여 JSP로 전달
			MemberDto memberDto = mServ.myPage(loggedInMember.getM_id(), model);
			model.addAttribute("memberDto", memberDto);
			System.out.println(memberDto);
			return "member/mypage";
		} else {
			// 로그인한 회원 정보가 없으면 로그인 페이지로 리다이렉트
			return "redirect:/login";
		}
	}

	// --------------------------------------------------------------------------------//
	@GetMapping("setting")
	public String setting(Model model, HttpSession session) {
		log.info("setting()");

		MemberDto loggedInMember = (MemberDto) session.getAttribute("login");

		if (loggedInMember != null) {
			// 로그인한 회원 정보를 모델에 추가하여 JSP로 전달
			model.addAttribute("loggedInMember", loggedInMember);

			return "member/setting";
		} else {
			// 로그인한 회원 정보가 없으면 로그인 페이지로 리다이렉트
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

	// 수강신청 페이지
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

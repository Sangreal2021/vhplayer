package com.mega.vhplayer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.mega.vhplayer.beans.vo.MemberVO;
import com.mega.vhplayer.services.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
@Slf4j
public class LoginController {
	
	@Autowired
	private final MemberService service;
	
	// thymeleaf 적용을 위해 "memberLogin", "registerMember" 객체 둘다 반드시 존재해야함!!
	@GetMapping("/loginForm")
	public String showLoginForm(HttpSession session, Model model) {
		MemberVO memberLogin = (MemberVO) session.getAttribute("memberLogin");
		if(memberLogin == null) {
			memberLogin = new MemberVO();
		}
		
		model.addAttribute("memberLogin", new MemberVO());
		model.addAttribute("registerMember", new MemberVO());
		model.addAttribute("myInfo", new MemberVO());
		return "/login/loginForm";
	}
	
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
//    	model.addAttribute("registerMember", new MemberVO());
        return "loginForm";
    }
	
	@PostMapping("/register")
	public String registerMember(@ModelAttribute("registerMember") MemberVO vo, Model model,
								RedirectAttributes rttr) throws Exception {
		// 이메일 및 닉네임 중복체크
		int duplicateEmailCount = service.checkDuplicateEmail(vo.getUser_email());
		int duplicateNameCount = service.checkDuplicateName(vo.getUser_name());
		
		if(duplicateEmailCount == 1) {
			rttr.addFlashAttribute("emailError", "Email already in use");
			return "redirect:/login/loginForm";
		}
		if(duplicateNameCount == 1) {
			rttr.addFlashAttribute("nameError", "NickName already in use");
			return "redirect:/login/loginForm";
		}
		service.register(vo);
		rttr.addFlashAttribute("uno", vo.getUno());
		rttr.addFlashAttribute("registerSuccess", "회원가입을 축하드립니다 로그인해주세요.");
		return "redirect:/login/loginForm";
	}
	
	@GetMapping("/loginMember")
	public String showLoginMemberForm(Model model) {
//		model.addAttribute("memberLogin", new MemberVO());
		return "/login/loginForm";
	}

	@PostMapping("/loginMember")
	public String loginMember(@ModelAttribute("memberLogin") MemberVO vo,
							HttpSession session,
							Model model,
							RedirectAttributes rttr) throws Exception {
		MemberVO member = service.login(vo);
	    if(member != null) {
	        // 비활성화 계정 로그인 시도시
	        if(member.getDelete_yn() != 0) {
	            rttr.addFlashAttribute("actError", "비활성화된 계정입니다 관리자에게 문의하세요.");
	            session.invalidate();
	            return "redirect:/login/loginForm";
	        } else {
	            session.setAttribute("memberLogin", member);
	            
	            // 세션 만료 시간을 30분으로 설정
	            session.setMaxInactiveInterval(30 * 60);
	            
	            if(member.getUser_auth().equals("1")) { // Admin login
	                return "redirect:/admin/index_admin";
	            } else { 								// User login
	                return "redirect:/index";
	            }
	        }
	    } else {
	        // If the member's email and password don't match, return an error message.
	        rttr.addFlashAttribute("loginError", "이메일 계정이나 비밀번호가 틀렸습니다.");
	        return "redirect:/login/loginForm";
	    }
	}
	
	@GetMapping("/myPage")
	public String showMyPage(HttpSession session, RedirectAttributes rttr, Model model) {
		MemberVO myInfo = (MemberVO) session.getAttribute("memberLogin");
		if(myInfo == null) {
			myInfo = new MemberVO();
		}
		Long uno = myInfo.getUno();
		model.addAttribute("myInfo", service.getOne(uno));
		
		return "/login/myPage";
	}
	
	@GetMapping("/myModify")
	public String showMyModify(@RequestParam("uno") Long uno, Model model) {
		log.info("--------------------------------------");
		log.info("[Modify] " + uno);
		log.info("--------------------------------------");
		model.addAttribute("myInfo", service.getOne(uno));
		return "/login/loginForm#myPage";
	}
	
	@PostMapping("/myModify")
	public RedirectView updateMyPage(MemberVO member, RedirectAttributes rttr) {
		log.info("---------------------------------------");
		log.info("[BoardController] modify() : " + member);
		log.info("---------------------------------------");
		if(service.updateMember(member)) {
			rttr.addFlashAttribute("modifyMyPage", "회원정보 수정이 완료되었습니다.");
		}
		return new RedirectView("/login/loginForm");
	}
	
	@PostMapping("/deleteMember")
	public String deleteMember(HttpSession session, RedirectAttributes rttr) {
	    MemberVO member = (MemberVO) session.getAttribute("memberLogin");

	    if (member != null) {
	        if(service.deleteMember(member.getUno())) {
	            session.invalidate();
	            rttr.addFlashAttribute("deleteMsg", "회원 탈퇴가 성공적으로 완료되었습니다.");
	            return "redirect:/login/loginForm";
	        } else {
	            rttr.addFlashAttribute("deleteMsg", "회원 탈퇴에 실패하였습니다.");
	        }
	    }
	    return "redirect:/login/loginForm";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		service.logout(session);
		return "redirect:/login/loginForm";
	}
	
    // 세션 만료 및 연장
	@ResponseBody
	@RequestMapping("/keepAlive")
	public String keepAlive(HttpSession session) {
		// 이 요청의 목적은 세션을 활성 상태로 유지하기 위한 것
		return "Success";
	}
}








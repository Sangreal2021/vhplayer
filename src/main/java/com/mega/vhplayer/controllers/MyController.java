package com.mega.vhplayer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mega.vhplayer.beans.vo.MemberVO;
import com.mega.vhplayer.services.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/*")
@Slf4j
public class MyController {
	
	@Autowired
	private final MemberService service;
	
	@GetMapping("myPageForm")
	public String showMyPage(HttpSession session, RedirectAttributes rttr, Model model) {
		MemberVO myInfo = (MemberVO) session.getAttribute("memberLogin");
		if(myInfo == null) {
			myInfo = new MemberVO();
		}
		Long uno = myInfo.getUno();
		model.addAttribute("myInfo", service.getOne(uno));
		
		return "mypage/myPageForm";
	}
	
	@GetMapping("myModify")
	public void showMyModify(HttpSession session, Model model) {
		MemberVO myInfo = (MemberVO) session.getAttribute("memberLogin");
		if(myInfo == null) {
			myInfo = new MemberVO();
		}
		Long uno = myInfo.getUno();
		model.addAttribute("myInfo", service.getOne(uno));
	}
	
	@PostMapping("myModify")
	public String updateMyPage(MemberVO member, RedirectAttributes rttr) {
		log.info("---------------------------------------");
		log.info("[BoardController] modify() : " + member);
		log.info("---------------------------------------");
		if(service.updateMember(member)) {
			rttr.addFlashAttribute("modifyMyPage", "회원정보 수정이 완료되었습니다 재로그인 해주세요");
		}
		return "redirect:/login/loginForm";
	}
	
	@PostMapping("deleteMember")
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
}

package com.mega.vhplayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mega.vhplayer.beans.vo.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {
	
	
	@GetMapping("/playlist")
	public String playlist(HttpSession session, Model model, RedirectAttributes rttr) {
		MemberVO memberLogin = (MemberVO) session.getAttribute("memberLogin");
		if(memberLogin == null) {
			memberLogin = new MemberVO();
		}
		model.addAttribute("memberLogin", new MemberVO());

		if(session.getAttribute("memberLogin") == null) {
			rttr.addFlashAttribute("plmsg", "로그인이 필요한 서비스입니다.");
			return "redirect:/login/loginForm";
		}
		return "/player/playlist";
	}
}

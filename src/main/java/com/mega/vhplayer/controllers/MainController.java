package com.mega.vhplayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mega.vhplayer.beans.vo.MemberVO;
import com.mega.vhplayer.services.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/*")
@Slf4j
public class MainController {
	
	private final MemberService service;
	
//	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
//	public String index() {
//		return "index";
//	}
	
    @GetMapping(value = {"", "/"})
    public String home(@ModelAttribute("member") MemberVO member, Model model, @SessionAttribute(name = "uno", required = false) Long uno) {

        member = service.getOne(uno);

        if(member != null) {
            model.addAttribute("nickname", member.getUser_name());
        }

        return "index";
    }
	
	@RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST})
	public String admin() {
		return "index_admin";
	}
	
	@RequestMapping(value = "/player", method = {RequestMethod.GET, RequestMethod.POST})
	public String playlist() {
		return "playlist";
	}
	
	@RequestMapping(value = "/board", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardlist() {
		log.info("Called----------------------------------------");
		return "boardlist";
	}
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login() {
	      return "loginForm";
	}
	
	@GetMapping("logout")
	public String logoutMember(HttpSession session) throws Exception {
		service.logout(session);
		return "redirect:/index";
	}
}

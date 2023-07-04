package com.mega.vhplayer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.mega.vhplayer.beans.vo.Criteria;
import com.mega.vhplayer.beans.vo.MemberVO;
import com.mega.vhplayer.beans.vo.PageDTO;
import com.mega.vhplayer.services.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private final MemberService service;
	
	@GetMapping("member_list")
	public void list(Criteria cri, Model model) {
		log.info("------------------------------------");
		log.info("[MemberController : list()] : " + cri);
		log.info("------------------------------------");
		
		model.addAttribute("list", service.getList(cri));
		// endPage는 13, next 버튼 활성화
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
	}
	
	@GetMapping({"get", "get2"})
	public String get(@RequestParam("uno") Long uno, Criteria cri, HttpServletRequest req, Model model) {
		String reqURI = req.getRequestURI();
		String reqContextPath = req.getContextPath();
		// /board/ -> 7글자 이후 글자 가져오기 위해(숫자는 서비스 글자수에 따라 변경)
		String reqType = reqURI.substring(reqURI.indexOf(reqContextPath) + 7);
		log.info("-------------------------------------");
		log.info("[BoardController] get() : " + reqURI);
		log.info("[BoardController] get() : " + reqContextPath);
		log.info("[BoardController] get() : " + reqType);
		log.info("[BoardController] get() : " + uno);
		log.info("-------------------------------------");
		
		model.addAttribute("list", service.getOne(uno));
		model.addAttribute("cri", cri);
		
		return "admin/member/member_get";
	}
	
    @GetMapping("modify")
    public void showUpdateForm(@RequestParam("uno") Long uno, Criteria cri, Model model) {
		log.info("--------------------------------------");
		log.info("[Modify] " + uno);
		log.info("--------------------------------------");
		
    	model.addAttribute("list", service.getOne(uno));
    	model.addAttribute("cri", cri);
    }

    @PostMapping("modify")
    public RedirectView updateMember(MemberVO member, RedirectAttributes rttr) {
		log.info("---------------------------------------");
		log.info("[BoardController] modify() : " + member);
		log.info("---------------------------------------");
		
    	if(service.modifyAdmin(member)) {
    		rttr.addFlashAttribute("result", "success");
    	}
    	return new RedirectView("member_list");
    }

	@PostMapping("remove")
	public RedirectView remove(@RequestParam("uno") Long uno, RedirectAttributes rttr) throws Exception {
		log.info("----------------------------------------");
		log.info("[BoardController] remove() : " + uno);
		log.info("----------------------------------------");
		
		if(service.deleteMember(uno)) {
			rttr.addFlashAttribute("result", "success");
		}else {
			rttr.addFlashAttribute("result", "failure");
			throw new Exception("You Have No right to DELETE!");
		}
		
		return new RedirectView("boardlist");
	}
}
























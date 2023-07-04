package com.mega.vhplayer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mega.vhplayer.beans.vo.Criteria;
import com.mega.vhplayer.beans.vo.PageDTO;
import com.mega.vhplayer.services.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminController {
	
	@Autowired
	private final BoardService service;
	
	@GetMapping("index_admin")
	public void admin(Criteria cri, Model model) {
		log.info("------------------------------------");
		log.info("[BoardController : boardlist()]");
		log.info("------------------------------------");
		
		model.addAttribute("boardlist", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
	}
	
	@RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String upload() {
		return "uploadAjax";
	}
	
	@RequestMapping(value = "/member", method = {RequestMethod.GET, RequestMethod.POST})
	public String member() {
		return "member_list";
	}
	
}
















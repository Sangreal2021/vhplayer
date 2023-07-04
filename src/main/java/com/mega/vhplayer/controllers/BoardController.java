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

import com.mega.vhplayer.beans.vo.BoardVO;
import com.mega.vhplayer.beans.vo.Criteria;
import com.mega.vhplayer.beans.vo.PageDTO;
import com.mega.vhplayer.services.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Slf4j
public class BoardController {
	
	@Autowired
	private final BoardService service;
	
	@GetMapping("boardlist")
	public void list(Criteria cri, Model model) {
		log.info("------------------------------------");
		log.info("[BoardController : boardlist()]");
		log.info("------------------------------------");
		
		model.addAttribute("boardlist", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
	}
	
	@GetMapping("register")
	public void register() {
		
	}
	
	@PostMapping("register")
	public RedirectView register(BoardVO board, RedirectAttributes rttr) {
		log.info("---------------------------------------");
		log.info("[BoardController] register() : " + board);
		log.info("---------------------------------------");
		
		// xml로 실행 -> 새로운 번호를 채번
		service.register(board);
		
		// 새롭게 등록된 번호를 같이 전달하기 위해 RedirectAttributes를 이용.
		rttr.addFlashAttribute("bno", board.getBno());
		
		// RedirectView를 사용하면 redirect 방식으로 전송 가능
		return new RedirectView("boardlist");
	}
	
	@GetMapping({"get", "get2"})
	public void get(@RequestParam("bno") Long bno, Criteria cri, HttpServletRequest req, Model model) {
		String reqURI = req.getRequestURI();
		String reqContextPath = req.getContextPath();
		// /board/ -> 7글자 이후 글자 가져오기 위해(숫자는 서비스 글자수에 따라 변경)
		String reqType = reqURI.substring(reqURI.indexOf(reqContextPath) + 7);
		log.info("-------------------------------------");
		log.info("[BoardController] get() : " + reqURI);
		log.info("[BoardController] get() : " + reqContextPath);
		log.info("[BoardController] get() : " + reqType);
		log.info("[BoardController] get() : " + bno);
		log.info("-------------------------------------");
		
		model.addAttribute("board", service.get(bno));
		model.addAttribute("cri", cri);
	}
	
	@GetMapping("modify")
	public void modify(@RequestParam("bno") Long bno, Criteria cri, Model model) {
		log.info("--------------------------------------");
		log.info("[Modify] " + bno);
		log.info("--------------------------------------");
		
		model.addAttribute("board", service.get(bno));
		model.addAttribute("cri", cri);
	}
	
	@PostMapping("modify")
	public RedirectView modify(BoardVO board, RedirectAttributes rttr) {
		log.info("---------------------------------------");
		log.info("[BoardController] modify() : " + board);
		log.info("---------------------------------------");
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return new RedirectView("boardlist");
	}
	
	@PostMapping("remove")
	public RedirectView remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) throws Exception {
		log.info("----------------------------------------");
		log.info("[BoardController] remove() : " + bno);
		log.info("----------------------------------------");
		
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}else {
			rttr.addFlashAttribute("result", "failure");
			throw new Exception("You Have No right to DELETE!");
		}
		
		return new RedirectView("boardlist");
	}
	
	@GetMapping("error")
	public void error() throws Exception {
		log.info("-------------------------------------------");
		log.info("[BoardController] error() ");
		log.info("-------------------------------------------");
		
		throw new Exception("You Have No Right to DELETE!");
	}
}




















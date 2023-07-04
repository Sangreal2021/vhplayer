package com.mega.vhplayer.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CommonExceptionAdvice {
	
	// 정의 되어있는 오류 받음
	@ExceptionHandler(Exception.class)
	public String except(Exception e, Model model) {
		log.error("[Exception!!] " + e.getMessage());
		
		// 나중에 model을 받는 html이 꺼냄
		model.addAttribute("exception", e);
		log.error(model.toString());
		
		return "error_page";
	}
	
	// 위 이외의 에러 받음 ex) 404같은 경우
	// NoHandler 중 NOT_FOUND나오면 여기서 처리
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handler404(NoHandlerFoundException e) {
		log.error("[Exception!!] NoHandler Exception");
		return "error_page";
	}
}

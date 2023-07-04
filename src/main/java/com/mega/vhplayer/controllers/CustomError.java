package com.mega.vhplayer.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CustomError implements ErrorController {
	
	// req에서 데이터 꺼내는게 아니라 어떤 방식의 오류인지 확인.
	@GetMapping("/error")
	public String handlerError(HttpServletRequest req) {
		Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		log.info(status.toString());
		
		if(status != null) { // Error 발생
			int statusCode = Integer.valueOf(status.toString());
			
			// File Not Found 에러
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				// error에 404.html로 GO
				return "error/404";
			}
		}
		// FileNotFound 아니면 500.html로
		return "error/500";
	}
}

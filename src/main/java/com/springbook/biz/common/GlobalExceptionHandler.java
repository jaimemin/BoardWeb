package com.springbook.biz.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.springbook.view")
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		ModelAndView model = new ModelAndView();
		model.addObject("exception", e);
		model.setViewName("/common/error.jsp");
		
		return model;
	}
}

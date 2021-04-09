package com.springbook.view.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice("com.springbook.view")
// advice 공통관심사항을 뗴어서 명시자를 통해서 포인트컷을 써서 ~공통관사항 기능

public class CommonExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView handleIllegalArgumentException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/arithmeticError.jsp");
		
		return mav;
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullPointerException(Exception e){
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/NullPointerError.jsp");
		
		return mav;
	}
}


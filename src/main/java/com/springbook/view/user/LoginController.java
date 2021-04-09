package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

@Controller
public class LoginController{
	
	@Autowired // 주입하자
	private UserService userService;

	@RequestMapping(value = "/login.do", method=RequestMethod.GET)
	public String loginView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("로그인 처리");
					
		return "login.jsp";
	} // 페이지를 최종적으로 브라우저 출력 
	
	@RequestMapping(value = "/login.do", method=RequestMethod.POST)
	public String loginProcess(UserVO vo, HttpSession session){
		// 예외 처리 기능 추가
		if(vo.getId()==null || vo.getId().equals("")){
			throw new NullPointerException("아이디는 반드시 입력해야 합니다.");
		}// 예외처리마저도 스프링이 해준다. 
		
		UserVO user = userService.getUser(vo);
		
		if(user!=null){
			session.setAttribute("userName", user.getName());
			return "getBoardList.do";
		}else {
			return "login.jsp";
		}
	}
}

package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
public class LoginController {
	@RequestMapping(value="/login.do", method=RequestMethod.GET) 
	public String loginView(UserVO userVO) {
		System.out.println("로그인 화면으로 이동");
		
		userVO.setId("test");
		userVO.setPassword("test123");
		
		return "login.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO userVO, UserDAO userDAO, HttpSession session) {
		if (userVO.getId() == null || userVO.getId().equals("")) {
			throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다.");
		}
		
		UserVO user = userDAO.getUser(userVO);
		
		if (user == null) {
			return "login.jsp";
		}
		
		session.setAttribute("userName", user.getName());
		
		return "getBoards.do";
	}
}

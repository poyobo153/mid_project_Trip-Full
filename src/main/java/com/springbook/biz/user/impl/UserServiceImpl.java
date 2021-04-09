package com.springbook.biz.user.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	// 디비관련 동작을 주입한다.
	
	@Override
	public UserVO getUser(UserVO vo) {		
		return userDAO.getUser(vo);
	}

}

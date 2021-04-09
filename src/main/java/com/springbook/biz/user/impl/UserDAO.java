package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

@Repository
public class UserDAO {
	private Connection conn = null;
	private PreparedStatement stmt= null;
	private ResultSet rs = null;
	
	private final String USER_GET = "select * from users where id=? and password=?";
	
	// CRUD 기능의 메서드 구현
	public UserVO getUser(UserVO vo){
		System.out.println("==> JDBC로 getUser() 기능 처리");	
		
		UserVO user = null;
		
		try {
			conn = JDBCUtil.getConnection();
			// conn 필드에 저장 
			stmt = conn.prepareStatement(USER_GET);
			//stmt 필드에 저장
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			// rs 필드에 저장
		
			if(rs.next()){
				// rs.next 가 false라면 다음 읽어올 데이터 행이 없어서 커서 이동이 안된다는 의미 
				user = new UserVO();
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
				
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}	finally {
			JDBCUtil.close(rs,stmt, conn);
		}	
		
		return user;
	}
	
}

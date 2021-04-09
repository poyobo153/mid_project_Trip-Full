package com.springbook.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	// 기존엔 DAO 생성자로 한 거 -> 메서드로 구현  
	public static Connection  getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott", "tiger");
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return null;
	}
	
	// 연결 닫기 pstmt, conn
	public static void close(PreparedStatement pstmt, Connection conn) {
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {e.printStackTrace();
			}finally {pstmt = null;}
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {e.printStackTrace();	
			} finally {pstmt = null;} // 지역변수여서 소멸되지만 의미적으로 가독성 측면으로 
		}
	}
	
	// 연결 닫기 rs, pstmt, conn
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {e.printStackTrace();
			}finally {rs = null;}
		}
		
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {e.printStackTrace();
			}finally {pstmt = null;}
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {e.printStackTrace();	
			} finally {pstmt = null;} 
		}
	}
}

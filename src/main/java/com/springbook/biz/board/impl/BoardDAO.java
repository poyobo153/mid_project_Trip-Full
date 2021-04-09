package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

@Repository
public class BoardDAO { // Data Access Object
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private final String BOARD_INSERT = "insert into springboard(seq, title, writer, content) "
			+ "values((select nvl(max(seq), 0)+1 from springboard),?,?,? )";
	private final String BOARD_LIST ="select * from springboard order by seq desc";
	private final String BOARD_GET = "select * from springboard where seq=?";
	private final String BOARD_UPDATE = "update springboard set title=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete springboard where seq=?";
	private final String BOARD_LIST_T ="select * from springboard where title like '%'||?||'%' order by seq desc"; 
	private final String BOARD_LIST_C ="select * from springboard where content like '%'||?||'%' order by seq desc";
	
	//%_아무것도 안와도되거나, 하나이상 오거나, "||"이어서_연속의미
	// 담기위해 필드 생성 
	// CRUD 기능 탑재
	// 디폴트값은 적지 않음 
	// 글 등록
	//	 nvl(max(seq),0)  널이면 0으로 해준다 는 뜻 		
	public void insertBoard(BoardVO vo) {
		System.out.println("===>JDBC로 insertBoard() 기능 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(pstmt, conn);
			}
		}
	
	//글 목록 조회
	public List <BoardVO> getBoardList(BoardVO vo){
		System.out.println("===>JDBC로 getBoardList() 기능 처리");
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			//pstmt = conn.prepareStatement(BOARD_LIST);
			if(vo.getSearchCondition().equals("TITLE")){
				pstmt = conn.prepareStatement(BOARD_LIST_T);
			}else if(vo.getSearchCondition().equals("CONTENT")){
				pstmt = conn.prepareStatement(BOARD_LIST_C);
			}
			pstmt.setString(1, vo.getSearchKeyword());
			rs = pstmt.executeQuery(); // 디비에서 읽어오고 보관한 영역 
			//select 문 한정 executeQuery
			while(rs.next()) { 
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("seq"));//
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
				list.add(board); 
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}//이미 오버로딩을 해놓아서 편하게 등록 
		return list;
	}
	
	// 글 상세 조회
		public BoardVO getBoard(BoardVO vo){
			System.out.println("===> JDBC로 getBoard() 기능 처리");
			BoardVO board = null;
			// 조건에 맞는 게시글 데이터를 담기위한 필드 설정
			// 특정 조건에 맞는 sql : "select * from springboard where seq=?"
			// 글 상세 조회를 위해서도 DB와의 연동이 필요하다.
			try {
				conn = JDBCUtil.getConnection();
				pstmt = conn.prepareStatement(BOARD_GET);
				pstmt.setInt(1, vo.getSeq());
				// 1번은 sql 문에 ? 순서대로 뒤에 파라메타가 입력이된다.   
				// seq=? 에 vo.getSeq() 들어간다. 
				// seq에 맞는 데이터를 분석해서 찾는다.
				// 여기서 set은 데이터를 찾는 역할을 한다.
	
				rs = pstmt.executeQuery();
				//결과 집합 반환
				if (rs.next()) { // 데이터가 있다면 커서가 아래로 이동하고 없다면 거기서 멈추고 펄스 반환 , 데이터가 있다면 계속 반복된다. 
					board = new BoardVO(); // 상세조회도 다시 객체에 담아서 보내주는 동작이다.
					board.setSeq(rs.getInt("SEQ"));
					board.setTitle(rs.getString("TITLE"));
					board.setWriter(rs.getString("WRITER"));
					board.setContent(rs.getString("CONTENT"));
					board.setRegDate(rs.getDate("REGDATE"));
					board.setCnt(rs.getInt("CNT"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(rs, pstmt, conn);
			}
			return board;
		}
	
		// 글 수정
		public void updateBoard(BoardVO vo) {
			System.out.println("===> JDBC로 updateBoard() 기능 처리");
			try {
				conn = JDBCUtil.getConnection();
				pstmt = conn.prepareStatement(BOARD_UPDATE);
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContent());
				pstmt.setInt(3, vo.getSeq());
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(pstmt, conn);
			}
		}
		
		// 글 삭제
		public void deleteBoard(BoardVO vo) {
			System.out.println("===> JDBC로 deleteBoard() 기능 처리");
			try {
				conn = JDBCUtil.getConnection();
				pstmt = conn.prepareStatement(BOARD_DELETE);
				pstmt.setInt(1, vo.getSeq());
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(pstmt, conn);
			}
		}

}

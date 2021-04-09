package com.springbook.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository //bean객체 생성
public class BoardDAOMyBatisFirst extends SqlSessionDaoSupport{//SqlSessionDaoSupport 객체 제공 메서드를 상속받아 와서 사용

	
	@Autowired //객체주입요청//setter메서드에도 적용됨 -> 의미? 
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {		
		super.setSqlSessionFactory(sqlSessionFactory);//setter 메서드호출을 통해 설정정보를 담고 있는 객체정보를 부모쪽으로 전달.-> 마이바티스에게 전달
	}
	
	public void insertBoard(BoardVO vo) {
		getSqlSession().insert("BoardDAOTemp.insertBoard", vo);//mapper정보를 찾아감. springboard-mapping.xml
	}
	
	public void updateBoard(BoardVO vo) {
		getSqlSession().update("BoardDAOTemp.updateBoard", vo);
	}
	
	public void deleteBoard(BoardVO vo) {
		getSqlSession().delete("BoardDAOTemp.deleteBoard", vo);
	}
	
	public BoardVO getBoard(BoardVO vo) {//상세화면
		return getSqlSession().selectOne("BoardDAOTemp.getBoard", vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo){
		//검색 기능 전
		//return getSqlSession().selectList("BoardDAOTemp.getBoardList", vo);
		if(vo.getSearchCondition().equals("TITLE")) {
			return getSqlSession().selectList("BoardDAOTemp.getBoardList_T", vo);
		}else if(vo.getSearchCondition().equals("CONTENT")) {
			return getSqlSession().selectList("BoardDAOTemp.getBoardList_C", vo);
		}else {
			return getSqlSession().selectList("BoardDAOTemp.getBoardList", vo);
		}
	}
	
	

}

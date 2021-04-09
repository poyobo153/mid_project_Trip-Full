package com.springbook.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository
public class BoardDAOMyBatisSecond {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertBoard(BoardVO vo) {
		mybatis.insert("BoardDAOTemp.insertBoard", vo);//mapper정보를 찾아감. springboard-mapping.xml
	}
	
	public void updateBoard(BoardVO vo) {
		mybatis.update("BoardDAOTemp.updateBoard", vo);
	}
	
	public void deleteBoard(BoardVO vo) {
		mybatis.delete("BoardDAOTemp.deleteBoard", vo);
	}
	
	public BoardVO getBoard(BoardVO vo) {//상세화면
		return mybatis.selectOne("BoardDAOTemp.getBoard", vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo){
		//검색 기능 전
		//return getSqlSession().selectList("BoardDAOTemp.getBoardList", vo);
		if(vo.getSearchCondition().equals("TITLE")) {
			return mybatis.selectList("BoardDAOTemp.getBoardList_T", vo);
		}else if(vo.getSearchCondition().equals("CONTENT")) {
			return mybatis.selectList("BoardDAOTemp.getBoardList_C", vo);
		}else {
			return mybatis.selectList("BoardDAOTemp.getBoardList", vo);
		}
	}
	
}

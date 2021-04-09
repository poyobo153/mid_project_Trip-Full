package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;



@Service
// 빈생성 요청 어노테이션 
public class BoardServiceImpl implements BoardService {
// 인터 페이스 하고 모든 메서드 쓰고 나서 서비스 폴더에 뉴 해서 클래스 생성하고 인터페이스 에서 추가 시키기 
	// DI 는 이름변경 , 코드 수정을 최소화 할 수 있따 .
	@Autowired
	//주입하겠다. 
	
	//private BoardDAO boardDAO;
	//private BoardDAOMyBatisFirst boardDAO;
	private BoardDAOMyBatisSecond boardDAO;
	
	@Override
	public void insertBoard(BoardVO vo) {
		boardDAO.insertBoard(vo);

	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		List<BoardVO> list = boardDAO.getBoardList(vo);
		return list; // 메인쪽으로 리턴 
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		BoardVO board = boardDAO.getBoard(vo);
		return board;
	}

	@Override
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);

	}

	@Override
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);

	}

}

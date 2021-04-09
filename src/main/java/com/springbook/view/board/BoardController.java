package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Controller
@SessionAttributes("board") //model에 board정보를 저장할 때, session에도 저장 요청
public class BoardController {
	@Autowired 
	//주입하기
	private BoardService boardService;
	
	// 글 등록
	@RequestMapping("/insertBoard.do") 
	public String insertBoard(BoardVO vo, HttpSession session) throws IOException{ // 브라우저 데이터를 담아주는 커멘드 객체 역할 
		System.out.println("글 등록 처리");
/* 
		   1, 2, 3   의 과정을 스프링이 해준다. 
		   단 조건이 있다. 
		   
		  BoardVO.java(자바빈)의 필드 이름        ////////           InsertBoardController.java 
		  	private int seq;														String title = request.getParameter("title");
			private String title;												String writer = request.getParameter("writer");
			private String writer;												String content = request.getParameter("content");									
			private String content;
			private Date   regDate;
			private int      cnt;
		  
		  title = "title"  /////  writer = "writer"  //////   content = "content" 이렇게 같다면 
 		  get/set 메소드로 관리되는 데이터를 프로퍼티라고 하고 이 이름과 동일하다면 
 */
		// 파일 업로드 처리
		String fileSaveFolder = session.getServletContext().getRealPath("/boardUpload/"); // 어플리케이션 객체 반환 
		//application getrealpath로 절대경로 가져올 수 있다. 
		//session  getservletcontext
		// 이 절대경로 위치는 서블릿의 절대위치로 반환하는데 우리는 프로젝트의 위치를 원한다. 
		// 서버 설정을 통해 프로젝트 위치로 변경 
		
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename(); 
			uploadFile.transferTo(new File(fileSaveFolder+fileName));
		}// 해당위치의 파일이름을 저장해주는 메서드
		// uploadFile 는 자바빈 필드
		
		boardService.insertBoard(vo);
		// 이미 서비스임플리에 구현됨 가져오기 만 하면된다.
		
		return "redirect: getBoardList.do";
	}
	
	// 글 수정
	@RequestMapping("/updateBoard.do") 
	public String updateBoard(@ModelAttribute("board") BoardVO vo){//@ModelAttribute가 매개변수앞에 사용시-> 1) 입력으로 전달되는 객체의 이름을 board로 변경시, 먼저 할당. 그 다음 클라이언트 전송 정보를 업데이트 
		System.out.println("글 수정 처리" + vo);
		
		boardService.updateBoard(vo);
		return "redirect: getBoardList.do";
	}
		
	// 글 삭제
	@RequestMapping("/deleteBoard.do") 
	public String deleteBoard(BoardVO vo){
		System.out.println("글 삭제 처리");
		boardService.deleteBoard(vo);
		return "redirect: getBoardList.do";
	}	
	
	@ModelAttribute("conditionMap") //검색기능 이용목적
	// jsp에서 활용됨 키 값이 "conditionMap"
	// for each 문에서 
	//@RequestMapping 호출 직전 동작됨 
	//model.addAttribute("boardList", boardList); 와 유사 
	public Map<String, String> searchConditionMap(){
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}
	
	// 글 목록 검색
	@RequestMapping("/getBoardList.do") 
	public String getBoardList(BoardVO vo, Model model){
		System.out.println("글 목록 검색 처리");		
		// 검색 기능  Null check
		if(vo.getSearchCondition()==null) {
			vo.setSearchCondition("TITLE");
		}
		
		if(vo.getSearchKeyword()== null){
			vo.setSearchKeyword("");
		}
		
		List<BoardVO> boardList = boardService.getBoardList(vo);
		
		model.addAttribute("boardList", boardList);
		// "boardList" 저장할 이름 , boardList : "boardList"에 저장 될 값
		return "getBoardList.jsp";
	}	// 모델에는 2개의 값이 담긴다. 모델엔 뷰니까 모델값과 뷰값
	 
	// 글 상세 조회
	@RequestMapping("/getBoard.do") 
	public String getBoard(BoardVO vo, Model model){
		System.out.println("글 상세 조회 처리");
		
		BoardVO board = boardService.getBoard(vo);				
		
		model.addAttribute("board",board);//
		
		return "getBoard.jsp";
	}	
	//
}


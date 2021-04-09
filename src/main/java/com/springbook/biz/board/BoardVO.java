package com.springbook.biz.board;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO { //Value Object , DTO 와 같다 .
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date   regDate;
	private int      cnt;
	private String searchCondition;//title or content 선택
	private String searchKeyword;
	private MultipartFile uploadFile;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	@Override
	public String toString() { // 우리가 원하는 기능을 구현 가능 , 원래는 자료형의 특징이 뭔지 설명하는게 목적이였지만 	
		String value = "BoardVO [seq = "+seq+
											", title = "+title+
											", writer = "+writer+
											", content = "+content+
											", regDate = "+regDate+
											", cnt = "+cnt+"]";
		return value;
	}
	
	
	
}



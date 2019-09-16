package com.springbook.biz.board;

import java.util.List;

import com.springbook.biz.board.impl.BoardDAOMybatis;

public class BoardServiceClient {
	public static void main(String[] args) {
		BoardDAOMybatis boardDAO = new BoardDAOMybatis();
		
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("myBatis 제목");
		boardVO.setWriter("홍길동");
		boardVO.setContent("myBatis 내용입니다......");
		
		boardDAO.insertBoard(boardVO);
		
		boardVO.setSearchCondition("TITLE");
		boardVO.setSearchKeyword("");
		
		List<BoardVO> boards = boardDAO.getBoards(boardVO);
		for (BoardVO board : boards) {
			System.out.println("---> " + board.toString());
		}
	}
}

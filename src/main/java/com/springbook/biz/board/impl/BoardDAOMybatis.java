package com.springbook.biz.board.impl;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;


@Repository
public class BoardDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;

	public void insertBoard(BoardVO boardVO) {
		System.out.println("===> Mybatis로 insertBoard() 기능 처리");
		
		mybatis.insert("BoardDAO.insertBoard", boardVO);
	}

	public void updateBoard(BoardVO boardVO) {
		System.out.println("===> Mybatis로 updateBoard() 기능 처리");
		
		mybatis.update("BoardDAO.updateBoard", boardVO);
	}

	public void deleteBoard(BoardVO boardVO) {
		System.out.println("===> Mybatis로 deleteBoard() 기능 처리");
		
		mybatis.delete("BoardDAO.deleteBoard", boardVO);
	}

	public BoardVO getBoard(BoardVO boardVO) {
		System.out.println("===> Mybatis로 getBoard() 기능 처리");
		
		return (BoardVO) mybatis.selectOne("BoardDAO.getBoard", boardVO);
	}

	public List<BoardVO> getBoards(BoardVO boardVO) {
		System.out.println("===> Mybatis로 getBoards() 기능 처리");
		
		return mybatis.selectList("BoardDAO.getBoards", boardVO);
	}
}
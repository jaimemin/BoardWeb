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
		System.out.println("===> Mybatis�� insertBoard() ��� ó��");
		
		mybatis.insert("BoardDAO.insertBoard", boardVO);
	}

	public void updateBoard(BoardVO boardVO) {
		System.out.println("===> Mybatis�� updateBoard() ��� ó��");
		
		mybatis.update("BoardDAO.updateBoard", boardVO);
	}

	public void deleteBoard(BoardVO boardVO) {
		System.out.println("===> Mybatis�� deleteBoard() ��� ó��");
		
		mybatis.delete("BoardDAO.deleteBoard", boardVO);
	}

	public BoardVO getBoard(BoardVO boardVO) {
		System.out.println("===> Mybatis�� getBoard() ��� ó��");
		
		return (BoardVO) mybatis.selectOne("BoardDAO.getBoard", boardVO);
	}

	public List<BoardVO> getBoards(BoardVO boardVO) {
		System.out.println("===> Mybatis�� getBoards() ��� ó��");
		
		return mybatis.selectList("BoardDAO.getBoards", boardVO);
	}
}
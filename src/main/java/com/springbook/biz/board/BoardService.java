package com.springbook.biz.board;

import java.util.List;

public interface BoardService {
	// �� ���
	void insertBoard(BoardVO boardVO);
	
	// �� ����
	void updateBoard(BoardVO boardVO);
	
	// �� ����
	void deleteBoard(BoardVO boardVO);
	
	// �� �� ��ȸ
	BoardVO getBoard(BoardVO boardVO);
	
	// �� ��� ��ȸ
	List<BoardVO> getBoards(BoardVO boardVO);
	
}

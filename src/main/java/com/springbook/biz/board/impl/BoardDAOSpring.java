package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

// DAO(Data Access Object)
@Repository
public class BoardDAOSpring {
	// SQL ��ɾ��
	private final String INSERT_BOARD
		= "INSERT INTO "
				+ "board(seq, title, writer, content) "
				+ "VALUES((SELECT nvl(MAX(seq), 0) + 1 FROM board), ?, ?, ?)";
	private final String UPDATE_BOARD
		= "UPDATE board "
				+ "SET title=?, content=? "
				+ "WHERE seq=?";
	private final String DELETE_BOARD
		= "DELETE board "
				+ "WHERE seq=?";
	private final String GET_BOARD
		= "SELECT * "
				+ "FROM board "
				+ "WHERE seq=?";
	private final String GET_BOARDS
		= "SELECT * "
				+ "FROM board "
				+ "ORDER BY seq DESC";
	private final String BOARDS_T
	= "SELECT * "
			+ "FROM board "
			+ "WHERE title LIKE '%'||?||'%' "
			+ "ORDER BY seq DESC";
	private final String BOARDS_C
		= "SELECT * "
				+ "FROM board "
				+ "WHERE content LIKE '%'||?||'%' "
				+ "ORDER BY seq DESC";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public BoardDAOSpring(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// CRUD ����� �޼ҵ� ����
	// �� ���
	public void insertBoard(BoardVO boardVO) {
		System.out.println("===> Spring JDBC�� insertBoard() ��� ó��");
		
		jdbcTemplate.update(INSERT_BOARD, boardVO.getTitle(), boardVO.getWriter(), boardVO.getContent());
	}
	
	// �� ����
	public void updateBoard(BoardVO boardVO) {
		System.out.println("===> Spring JDBC�� updateBoard() ��� ó��");
		
		jdbcTemplate.update(UPDATE_BOARD, boardVO.getTitle(), boardVO.getContent(), boardVO.getSeq());
	}
	
	// �� ����
	public void deleteBoard(BoardVO boardVO) {
		System.out.println("===> Spring JDBC�� delteBoard() ��� ó��");
		
		jdbcTemplate.update(DELETE_BOARD, boardVO.getSeq());
	}
	
	// �� �� ��ȸ
	public BoardVO getBoard(BoardVO boardVO) {
		System.out.println("===> Spring JDBC�� getBoard() ��� ó��");
		
		Object[] args = {boardVO.getSeq()};
		
		return jdbcTemplate.queryForObject(GET_BOARD, args, new BoardRowMapper());
	}
	
	// �� ��� ��ȸ
	public List<BoardVO> getBoards(BoardVO boardVO) {
		System.out.println("===> Spring JDBC�� getBoards() ��� ó��");
		
		Object[] args = {boardVO.getSearchKeyword()};
		
		if (boardVO.getSearchCondition().equals("TITLE")) {
			return jdbcTemplate.query(BOARDS_T,  args, new BoardRowMapper());
		} else if (boardVO.getSearchCondition().equals("CONTENT")) {
			return jdbcTemplate.query(BOARDS_C,  args, new BoardRowMapper());
		}
		
		return null;
	}
}

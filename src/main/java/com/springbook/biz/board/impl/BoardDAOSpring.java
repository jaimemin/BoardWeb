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
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public BoardDAOSpring(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// CRUD ����� �޼ҵ� ����
	// �� ���
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC�� insertBoard() ��� ó��");
		
		jdbcTemplate.update(INSERT_BOARD, vo.getTitle(), vo.getWriter(), vo.getContent());
	}
	
	// �� ����
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC�� updateBoard() ��� ó��");
		
		jdbcTemplate.update(UPDATE_BOARD, vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	
	// �� ����
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC�� delteBoard() ��� ó��");
		
		jdbcTemplate.update(DELETE_BOARD, vo.getSeq());
	}
	
	// �� �� ��ȸ
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC�� getBoard() ��� ó��");
		
		Object[] args = {vo.getSeq()};
		
		return jdbcTemplate.queryForObject(GET_BOARD, args, new BoardRowMapper());
	}
	
	// �� ��� ��ȸ
	public List<BoardVO> getBoards(BoardVO vo) {
		System.out.println("===> Spring JDBC�� getBoards() ��� ó��");
		
		return jdbcTemplate.query(GET_BOARDS, new BoardRowMapper());
	}
}

package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

// DAO(Data Access Object)
@Repository
public class BoardDAOSpring {
	// SQL 명령어들
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

	// CRUD 기능의 메소드 구현
	// 글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 insertBoard() 기능 처리");
		
		jdbcTemplate.update(INSERT_BOARD, vo.getTitle(), vo.getWriter(), vo.getContent());
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 updateBoard() 기능 처리");
		
		jdbcTemplate.update(UPDATE_BOARD, vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	
	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 delteBoard() 기능 처리");
		
		jdbcTemplate.update(DELETE_BOARD, vo.getSeq());
	}
	
	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoard() 기능 처리");
		
		Object[] args = {vo.getSeq()};
		
		return jdbcTemplate.queryForObject(GET_BOARD, args, new BoardRowMapper());
	}
	
	// 글 목록 조회
	public List<BoardVO> getBoards(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoards() 기능 처리");
		
		return jdbcTemplate.query(GET_BOARDS, new BoardRowMapper());
	}
}

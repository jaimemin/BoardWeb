package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

// DAO(Data Access Object)
@Repository("boardDAO")
public class BoardDAO {
	// SQL 명령어들
	private final String INSERT_BOARD
		= "INSERT INTO "
				+ "board(seq, title, writer, content) "
				+ "VALUES((SELECT nvl(max(seq), 0) + 1 FROM board), ?, ?, ?)";
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
		="SELECT * "
			+ "FROM board "
			+ "ORDER BY seq DESC";

	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// CRUD 기능의 메서드 구현
	// 글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC로 insertBoard() 기능 처리");

		try {
			conn = JDBCUtil.getConnection();

			stmt = conn.prepareStatement(INSERT_BOARD);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());

			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");

		try {
			conn = JDBCUtil.getConnection();

			stmt = conn.prepareStatement(UPDATE_BOARD);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getSeq());

			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");

		try {
			conn = JDBCUtil.getConnection();

			stmt = conn.prepareStatement(DELETE_BOARD);
			stmt.setInt(1, vo.getSeq());

			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> JDBC로 getBoard() 기능 처리");

		BoardVO board = null;

		try {
			conn = JDBCUtil.getConnection();

			stmt = conn.prepareStatement(GET_BOARD);
			stmt.setInt(1, vo.getSeq());

			rs = stmt.executeQuery();

			if (rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}

		return board;
	}

	// 글 목록 조회
	public List<BoardVO> getBoards(BoardVO vo) {
		System.out.println("===> JDBC로 getBoards() 기능 처리");

		List<BoardVO> boards = new ArrayList<>();

		try {
			conn = JDBCUtil.getConnection();

			stmt = conn.prepareStatement(GET_BOARDS);

			rs = stmt.executeQuery();

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));

				boards.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}

		return boards;
	}
}
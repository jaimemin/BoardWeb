package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

@Repository("userDAO")
public class UserDAO {
	// SQL 명령어들
	private final String GET_USER
		= "SELECT * "
				+ "FROM users "
				+ "WHERE id=? "
				+ "AND password=?";
	
	// JDBC 관련 변수
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	// 회원 등록
	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		
		try {
			System.out.println("===> JDBC로 getUser() 기능 처리");
			
			conn = JDBCUtil.getConnection();
			
			stmt = conn.prepareStatement(GET_USER);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return user;
	}
}

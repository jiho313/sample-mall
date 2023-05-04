package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;

import util.ConnUtils;
import vo.User;

public class UserDao {
	
	public void updateUser(User user) {
		String sql = "update sample_users "
				+ "set "
				+ "		user_password = ?, "
				+ "		user_point = ? "
				+ "where user_no = ? ";
		try {
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getPassword());
			pstmt.setInt(2, user.getPoint());
			pstmt.setInt(3, user.getNo());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public User getUserByNo(int userNo) {
		String sql = "select * "
				+ "from sample_users "
				+ "where user_no= ?";
		try {
			User user = null;
			
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			ResultSet rs = pstmt.executeQuery();
			// 조회하는 값이 하나라면 while대신 if를 써도된다.
			if(rs.next()) {
				user = new User();
				user.setNo(rs.getInt("user_no"));
				user.setId(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));
				user.setName(rs.getString("user_name"));
				user.setPoint(rs.getInt("user_point"));
				user.setCreateDate(rs.getDate("user_create_date"));
			}
			return user; 
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/*
	 * 사용자정보 조회하기 - SELECT 작업
	 * 
	 * 반환타입: User 
	 * 메서드명: getUserById
	 * 매개변수: String id
	 * 아이디를 전달받아서 sample_users 테이블에서 사용자 정보를 조회한다.
	 */
	public User getUserById(String id) {
		String sql = "select * "
				+"from sample_users "
				+"where user_id = ?";
		try {
			User user = null;
			
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			// 조회하는 값이 하나라면 while대신 if를 써도된다.
			while(rs.next()) {
				user = new User();
				user.setNo(rs.getInt("user_no"));
				user.setId(rs.getString("user_id"));
				user.setPassword(rs.getString("user_password"));
				user.setName(rs.getString("user_name"));
				user.setPoint(rs.getInt("user_point"));
				user.setCreateDate(rs.getDate("user_create_date"));
			}
			rs.close();
			pstmt.close();
			conn.close();
			
			return user; 
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	/*
	 * 사용자정보 추가하기 - insert 작업
	 * 
	 * 반환타입: void
	 * 메소드명: insertUser
	 * 매개변수: User user
	 * 아이디, 비밀번호, 이름이 포한된 User객체를 전달받아서 SAMPLE_USERS 테이블에 저장한다.
	 */
	public void insertUser(User user) {
		// 1. insert 구문 SQL을 작성하시오.
		String sql = "insert into sample_users "
				+"(user_no, user_id, user_password, user_name) "
				+"values "
				+"(sample_users_seq.nextval, ?, ?, ?)";
		try {
			// 2. Connection을 획득하세요
			Connection conn = ConnUtils.getConnection();
			// 3. PreparedStatment를 획득하세요
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 4. ?에 값을 바인딩하세요
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			// 5. SQL을 DBMS로 전송해서 실행시키세요
			pstmt.executeUpdate();
			// 6. 자원을 반납하세요.
			pstmt.close();
			conn.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnUtils;
import vo.PointHistory;

public class PointHistoryDao {
	
	/*
	 * 전달받은 사용자번호로 포인트 변경이력을 조회하는 select 기능
	 * 
	 * 반환타입: List<PointHistory>
	 * 메서드명: getHistoryByUserNo
	 * 매개변수: int userNo
	 */
	public List<PointHistory> getHistoryByUserNo(int userNo){
		String sql = "select * "
				+ "from sample_point_history "
				+ "where user_no = ? "
				+ "order by create_date desc ";
		
		try {
			List<PointHistory> histories = new ArrayList<>();
			
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PointHistory history = new PointHistory();
				history.setUserNo(rs.getInt("user_no"));
				history.setOrderNo(rs.getInt("order_no"));
				history.setDepositPoint(rs.getInt("deposit_point"));
				history.setCurrentPoint(rs.getInt("current_point"));
				history.setCreateDate(rs.getDate("create_date"));
				
				histories.add(history);
			}
			
			
			rs.close();
			pstmt.close();
			conn.close();
			
			return histories;

		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/*
	 * 전달받은 포인트이력정보를 저장하는 insert 기능
	 * 
	 * 반환타입: void
	 * 메서드명: insertHistory
	 * 매개변수: PointHistory
	 */
	public void insertHistory(PointHistory history) {
		String sql = "insert into sample_point_history "
				+ "(user_no, order_no, deposit_point, current_point) "
				+ "values "
				+ "(?, ?, ?, ?)";
		try {
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, history.getUserNo());
			pstmt.setInt(2, history.getOrderNo());
			pstmt.setInt(3, history.getDepositPoint());
			pstmt.setInt(4, history.getCurrentPoint());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

}

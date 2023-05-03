package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ConnUtils;
import vo.Order;
import vo.User;

public class OrderDao {


	/*
	 * 주문일련번호를 조회하는 select 기능
	 * 
	 * 반환타입: int
	 * 메소드명: getSequence
	 * 매개변수: 없음
	 */
	public int getSequence() {
		// 이 쿼리를 실행하면 무조건 다음행이 있기 때문에 반복문을 사용하지 않아도 된다.
		String sql = "select sample_orders_seq.nextval as seq from dual";
		
		try {
			int sequence = 0;
			
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			// 위에 쿼리를 실행시켜서 다음 시퀀스의 번호 하나를 sequence 변수에 담아 놓는다.
			// 같은 주문번호로 다른 메서드에 넣기 위해
			sequence = rs.getInt("seq");
			
			rs.close();
			pstmt.close();
			conn.close();
			
			return sequence;
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/*
	 * 주문정보를 insert하는 기능이다.
	 * 
	 *  반환타입: void
	 *  메서드명: insertOrder
	 *  매개변수: Order
	 */
	public void insertOder(Order order) {
		String sql = "insert into sample_orders "
				+ "(order_no, total_order_price, used_point, total_credit_price, deposit_point, user_no) "
				+ "values "
				+ "(?, ?, ?, ?, ?, ?)";
		try {
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order.getNo());
			pstmt.setInt(2, order.getTotalOrderPrice());
			pstmt.setInt(3, order.getUsedPoint());
			pstmt.setInt(4, order.getTotalCreditPrice());
			pstmt.setInt(5, order.getDepositPoint());
			pstmt.setInt(6, order.getUserNo());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	

	
	
}

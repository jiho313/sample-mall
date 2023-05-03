package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.ConnUtils;
import vo.OrderItem;

public class OrderItemDao {

	/*
	 * 주문 상품정보를 insert하는 기능이다.
	 * 
	 * 반환타입: void
	 * 메서드명: insertOderItem
	 * 매개변수: OrderItem
	 */
	public void insertOrderItem(OrderItem orderItem) {
		String sql ="insert into sample_order_items "
				+ "(order_no, product_no, product_amount, product_price) "
				+ "values "
				+ "(?, ?, ?, ?)";
		try {
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderItem.getOrderNo());
			pstmt.setInt(2, orderItem.getProductNo());
			pstmt.setInt(3, orderItem.getAmount());
			pstmt.setInt(4, orderItem.getPrice());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}


}

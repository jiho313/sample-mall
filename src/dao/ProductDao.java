package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.ConnUtils;
import vo.Product;

public class ProductDao {
	
	public Product getProductByNo(int productNo) {
		String sql = "select * "
				+ "from sample_product "
				+ "where product_no = ?";
		try {
			Product product = new Product();
			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productNo);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				product.setNo(rs.getInt("product_no"));
				product.setName(rs.getString("product_name"));
				product.setMaker(rs.getString("product_name"));
				product.setMaker(rs.getString("product_name"));
				product.setPrice(rs.getInt("product_price"));
				product.setDiscountRate(rs.getDouble("product_discount_rate"));
				product.setStock(rs.getInt("product_stock"));
				product.setCreateDate(rs.getDate("product_create_date"));
			}
			rs.close();
			pstmt.close();
			conn.close();
			
			return product;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/*
	 * 모든 상품정보 조회하기 - select 작업
	 * 
	 * 반환타입: List<Product>
	 * 메서드명: getProdusts
	 * 매개변수: 없음
	 */
	public List<Product> getProdusts() {
		String sql = "select * "
				+ "from sample_product "
				+ "order by product_no asc ";
		
		try {
			List<Product> productList = new ArrayList<>();

			Connection conn = ConnUtils.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Product product = new Product();
				product.setNo(rs.getInt("product_no"));
				product.setName(rs.getString("product_name"));
				product.setMaker(rs.getString("product_name"));
				product.setMaker(rs.getString("product_name"));
				product.setPrice(rs.getInt("product_price"));
				product.setDiscountRate(rs.getDouble("product_discount_rate"));
				product.setStock(rs.getInt("product_stock"));
				product.setCreateDate(rs.getDate("product_create_date"));

				productList.add(product);
			}

			rs.close();
			pstmt.close();
			conn.close();
			
			return productList;
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}

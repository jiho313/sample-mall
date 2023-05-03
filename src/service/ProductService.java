package service;

import java.util.List;

import dao.ProductDao;
import vo.Product;

public class ProductService {
	// 기능이 구현되어 있는 객체는 메서드마다 똑같은 기능을 계속 사용하기 때문에 위에다가 만든다. 
	// 값을 담는 객체 예)Product, User 등은 매번 새로 만들어 새로운 값을 부여해야하기 때문에
	// 담을 때마다 메서드 안에서 새로 만든다.
	private ProductDao productDao = new ProductDao();
	
	/*
	 * 전체 상품 목록을 제공하는 서비스
	 * 
	 * 반환타입: List<Product>
	 * 메서드명: getAllProducts
	 * 매개변수: 없음
	 * 업무로직
	 * 	- 모든 상품을 조회하고 반환한다.
	 * 	- 1. 모든 상품정보를 조회하고 반환하기
	 * 		- ProductDao객체의 getProducts()를 호출해서 모든 상품정보를 조회하고 반환한다.
	 */
	public List<Product> getAllProducts() {
//		List<Product> productList = productDao.getProdusts();
//		return productList;
		return productDao.getProdusts();
	}
	
	
	
}

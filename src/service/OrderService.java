package service;

import dao.OrderDao;
import dao.OrderItemDao;
import dao.PointHistoryDao;
import dao.ProductDao;
import dao.UserDao;
import vo.Order;
import vo.OrderItem;
import vo.PointHistory;
import vo.Product;
import vo.User;

public class OrderService {
	private PointHistoryDao pointHistoryDao = new PointHistoryDao();
	private ProductDao productDao = new ProductDao();
	private OrderDao orderDao = new OrderDao();
	private OrderItemDao orderItemDao = new OrderItemDao();
	private UserDao userDao = new UserDao();

	/*
	 * 바로구매 서비스를 제공한다.
	 *
	 * 반환타입: void
	 * 메서드명: order
	 * 매개변수: int, int, int
	 * 업무로직
	 * 	- 주문정보, 주문상품정보를 저장한다.
	 * 	- 상품의 재고, 사용자의 포인트를 변경한다.
	 * 	- 포인트적립이력을 저장한다.
	 * 	- 1. 상품정보를 조회한다.
	 * 		- 상품번호로 상품정보를 조회한다.
	 *		- 상품정보가 존재하지 않으면 예외를 던진다.
	 *	- 2. 주문하기
	 * 		- OrderDao객체의 getSequence()를 호출해서 주문번호를 조회한다.
	 *		- 총 주문금액을 계산한다.
	 *		- 총 결제금액을 계산한다.
	 *		- 적립포인트를 계산한다.
	 *
	 *		- Order객체를 생성한다.
	 *		- Order객체에 주문번호, 총 주문금액, 총 결제금액, 적립포인트, 사용자번호를 저장한다.
	 *		- OrderDao의 inserOrder()를 호출해서 테이블에 저장시킨다.
	 *	- 3. 주문상품 저장하기
	 *		- OrderItem객체를 생성한다.
	 *		- OrderItem객체에 주문번호, 상품번호, 구매수량, 상품가격을 저장한다.
	 *		- OrderItemDao의 insertOrderItem() 메서드를 호출해서 테이블에 저장시킨다.
	 */
	public void order(int productNo, int amount, int userNo) {
		// 상품정보 조회하기
		Product product = productDao.getProductByNo(productNo);
		if (product == null) {
			throw new RuntimeException("상품정보가 존재하지 않습니다.");
		}
		// 신규 주문 일련번호 조회하기
		// 하나의 시퀀스 번호로 아래의 여러 메서드에 적용시키기
		int orderNo = orderDao.getSequence();
		
		
		// 주문정보 저장하기
		int totalOrderPrice =product.getPrice()*amount;
		int usedPoint = 0;
		int totalCreditPrice = product.getPrice()*amount;
		int depositPoint = (int)(totalCreditPrice*0.01);
				
		Order order = new Order();
		order.setNo(orderNo);
		order.setTotalOrderPrice(totalOrderPrice);
		order.setUsedPoint(usedPoint);
		order.setTotalCreditPrice(totalCreditPrice);
		order.setDepositPoint(depositPoint);
		order.setUserNo(userNo);
		
		orderDao.insertOder(order);
		
		// 주문상품정보 저장하기
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderNo(orderNo);
		orderItem.setProductNo(productNo);
		orderItem.setAmount(amount);
		orderItem.setPrice(product.getPrice());
		
		orderItemDao.insertOrderItem(orderItem);
		
		// 포인트변경이력 저장하기
		User user = userDao.getUserByNo(userNo);
		int currentDepositPoint = user.getPoint();
		currentDepositPoint += depositPoint; // 현재 포인트를 불러와서 현재 값에 추가될 포인트를 더한다.
		
		PointHistory history = new PointHistory();
		history.setUserNo(userNo);
		history.setOrderNo(orderNo);
		history.setDepositPoint(depositPoint);
		history.setCurrentPoint(currentDepositPoint);
		
		pointHistoryDao.insertHistory(history);
		
	}

}

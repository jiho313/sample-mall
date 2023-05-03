package service;

import java.util.List;

import dao.CartItemDao;
import dto.CartItemDto;
import dto.CartItemListDto;
import vo.CartItem;

public class CartService {

	private CartItemDao cartItemDao = new CartItemDao();
	
	/*
	 * 장바구니에 장바구니 아이템을 추가하는 서비스
	 * 
	 * 반환타입: void
	 * 메서드명: addCartItem
	 * 매개변수: CartItem
	 * 업무로직
	 * 	- 장바구니에 장바구니 아이템을 추가한다.
	 * 	- 1. 사용자번호, 상품번호, 수량이 포한된 CartItem 객체를 전달받아서 저장시킨다.
	 * 		- CartItemDao객체의 insertCartItem() 메서드를 호출해서 저장시킨다.
	 */
	public void addCartItem(CartItem cartItem) {
		CartItem savedCartItem = cartItemDao.getCartItem(cartItem.getUserNo(), cartItem.getProductNo());
		if (savedCartItem != null) {
			throw new RuntimeException("장바구니에 동일한 상품이 이미 존재합니다.");
		}
		cartItemDao.insertCartItem(cartItem);
	}
	
	/*
	 * 나의 장바구니 아이템 정보를 반환하는 서비스다.
	 *  이 방식은 총 구매 수량, 총 구매 가격이 계산되어 나오지 않기 때문에 
	 * 반환타입: List<CartItemDto>
	 * 메서드명: getMyCartItems
	 * 매개변수: int userId
	 * 업무로직
	 * 	- 사용자번호를 전달받아서 사용자번호로 등록된 장바구니 아이템정보를 반환한다.
	 * 	- 1. 장바구니 아이템 정보 조회 및 반환하기
	 * 		- CartItemDao의 getCartItemDtosByUserNo()을 호출해서 장바구니 아이템 정보를
	 * 		  조회하고, 조회된 결과를 반환한다.
	 * 		- sample_cart_items 테이블에 저장된 장바구니 아이템 정보가 없으면
	 * 		  빈 List<CartItemDto>객체가 반환된다. (null이 반환되는 게 아니다.)
	 */
	
	/*
	 * CartItemListDto객체를 만들어서 해당 객체 안에 이를 계산하는 메서드를 구현해서 만들었다.
	 */
	public CartItemListDto getMyCartItems(int userId){
		List<CartItemDto> dtos = cartItemDao.getCartItemDtosByUserNo(userId);
		
		CartItemListDto listDto = new CartItemListDto();
		listDto.setDtos(dtos);
		
		return listDto;
	}
	
	/*
	 * 나의 장바구니 아이템을 전부 삭제하는 서비스다.
	 * 
	 * 반환타입: void
	 * 메서드명: clearMyCartItems
	 * 매개변수: int userNo
	 * 업무로직
	 * 	- 사용자번호를 전달받아서 해당 사용자의 장바구니 아이템정보를 전부 삭제시킨다.
	 * 	- 1. 장바구니 아이템정보 삭제하기
	 * 		- CartItemDao객체의 deleteCartItemsByUserNo() 메서드를 호출해서
	 * 		  사용자의 모든 장바구니 아이템정보를 삭제한다.
	 */
	public void clearMyCartItems(int userNo) {
		cartItemDao.deleteCartItemsByUserNo(userNo);
	}










}

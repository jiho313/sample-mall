package dto;

import java.util.List;

public class CartItemListDto {
	// 카트에 저장된 상품들의 총 구매 수량과 총 구매 가격을 Controller에서 지저분하게 계산식을 쓰지 않고
	// 해당 클래스내에서 그것들을 계산하는 메서드들을 구현해놓은 객체
	
	// 이 클래스의 제네릭 List타입 private필드이다.
	private List<CartItemDto> dtos; // 재료가 되는 CartItemDto객체 

	public List<CartItemDto> getDtos() {
		return dtos;
	}

	// 아래의 메서드들을 사용하기 위해서 setter를 사용함
	public void setDtos(List<CartItemDto> dtos) {
		this.dtos = dtos;
	}
	
	// 총 구매 수량을 계산하는 메서드
	public int getTotalAmount() {
		int totalAmount = 0;
		
		for(CartItemDto dto : dtos) {
			totalAmount += dto.getItemAmount();
		}
		
		return totalAmount;
	}
	
	// 총 구매 가격을 계산하는 메서드
	public int getTotalOrderPrice() {
		int totalOrderPrice = 0;
		
		for(CartItemDto dto : dtos) {
			int price = dto.getProductPrice();
			int amount = dto.getItemAmount();
			int orderPrice = price * amount; // 한가지 종류의 물건에 대한 총 가격
			
			totalOrderPrice += orderPrice; // 모두 더해서 구매한 모든 물건의 총 가격을 구할 수 있다.
		}
		
		return totalOrderPrice;
	}

}

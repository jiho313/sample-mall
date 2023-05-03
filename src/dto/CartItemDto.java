package dto;

public class CartItemDto {
	// 테이블끼리 조인해서 값을 얻을 수 있을 때 조인해서 얻은 값을 저장하는 객체
	private int productNo;
	private int productPrice;
	private int itemAmount;
	private String productName;

	public CartItemDto() {
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(int itemAmount) {
		this.itemAmount = itemAmount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}

package trendy.cart.vo;

public class Cart {
	private int cartNo;
	private String memberId;
	private String productId;
	private int amount;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(int cartNo, String memberId, String productId, int amount) {
		super();
		this.cartNo = cartNo;
		this.memberId = memberId;
		this.productId = productId;
		this.amount = amount;
	}
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}

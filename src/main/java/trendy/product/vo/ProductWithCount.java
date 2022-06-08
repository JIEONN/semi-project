package trendy.product.vo;

public class ProductWithCount {
	private Product product;
	private int cartNo;
	private int amount;
	public ProductWithCount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductWithCount(Product product, int cartNo, int amount) {
		super();
		this.product = product;
		this.cartNo = cartNo;
		this.amount = amount;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}

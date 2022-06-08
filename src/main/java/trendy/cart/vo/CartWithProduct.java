package trendy.cart.vo;

public class CartWithProduct {
	private String memberId;
	private String productId;
	private int amount;
	private String productName;
	private int productPrice;
	private String productFileName;
	private String productColor;
	private String productSize;
	public CartWithProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartWithProduct(String memberId, String productId, int amount, String productName, int productPrice,
			String productFileName, String productColor, String productSize) {
		super();
		this.memberId = memberId;
		this.productId = productId;
		this.amount = amount;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productFileName = productFileName;
		this.productColor = productColor;
		this.productSize = productSize;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductFileName() {
		return productFileName;
	}
	public void setProductFileName(String productFileName) {
		this.productFileName = productFileName;
	}
	public String getProductColor() {
		return productColor;
	}
	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}
	public String getProductSize() {
		return productSize;
	}
	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}
	
	
	
	
	
	
}

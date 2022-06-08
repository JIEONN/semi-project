package trendy.orderDetails.vo;

public class OrderDetails {
	private int detailsNo;
	private int orderNo;
	private String productId;
	private int count;
	private String productName;
	private int productPrice;
	private String productDetail;
	private String productFilename;
	private String productFilepath;
	private String subCategory;
	private String mainCategory;
	private String productColor;
	private String productSize;
	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetails(int detailsNo, int orderNo, String productId, int count, String productName, int productPrice,
			String productDetail, String productFilename, String productFilepath, String subCategory,
			String mainCategory, String productColor, String productSize) {
		super();
		this.detailsNo = detailsNo;
		this.orderNo = orderNo;
		this.productId = productId;
		this.count = count;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDetail = productDetail;
		this.productFilename = productFilename;
		this.productFilepath = productFilepath;
		this.subCategory = subCategory;
		this.mainCategory = mainCategory;
		this.productColor = productColor;
		this.productSize = productSize;
	}
	public int getDetailsNo() {
		return detailsNo;
	}
	public void setDetailsNo(int detailsNo) {
		this.detailsNo = detailsNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	public String getProductFilename() {
		return productFilename;
	}
	public void setProductFilename(String productFilename) {
		this.productFilename = productFilename;
	}
	public String getProductFilepath() {
		return productFilepath;
	}
	public void setProductFilepath(String productFilepath) {
		this.productFilepath = productFilepath;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getMainCategory() {
		return mainCategory;
	}
	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
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

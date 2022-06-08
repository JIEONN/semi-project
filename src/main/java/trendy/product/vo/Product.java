package trendy.product.vo;

public class Product {
	private String productId;
	private String productName;
	private int productPrice;
	private int productCount;
	private String productDetail;
	private String productFilename;
	private String productFilepath;
	private String subCategory;
	private String mainCategory;
	private String productColor;
	private String productSize;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String productId, String productName, int productPrice, int productCount, String productDetail,
			String productFilename, String productFilepath, String subCategory, String mainCategory,
			String productColor, String productSize) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productCount = productCount;
		this.productDetail = productDetail;
		this.productFilename = productFilename;
		this.productFilepath = productFilepath;
		this.subCategory = subCategory;
		this.mainCategory = mainCategory;
		this.productColor = productColor;
		this.productSize = productSize;
	}
	
	public String getProductDetailBr() {
		return productDetail.replaceAll("\r\n", "<br>");
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
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

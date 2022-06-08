package trendy.product.vo;

import java.util.ArrayList;

public class ProductPageData {
	private ArrayList<Product> list;
	private String pageNavi;
	public ProductPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductPageData(ArrayList<Product> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Product> getList() {
		return list;
	}
	public void setList(ArrayList<Product> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}

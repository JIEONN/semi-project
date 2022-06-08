package trendy.payment.vo;

import java.util.ArrayList;

public class PaymentPageData {
	private ArrayList<Payment> list;
	private String pageNavi;
	public PaymentPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentPageData(ArrayList<Payment> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Payment> getList() {
		return list;
	}
	public void setList(ArrayList<Payment> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}

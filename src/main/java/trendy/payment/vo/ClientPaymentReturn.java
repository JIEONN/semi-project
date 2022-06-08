package trendy.payment.vo;

import java.util.ArrayList;

public class ClientPaymentReturn {
	private ArrayList<Payment> paymentList;	
	private String pageNavi;
	public ClientPaymentReturn() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClientPaymentReturn(ArrayList<Payment> paymentList, String pageNavi) {
		super();
		this.paymentList = paymentList;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Payment> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(ArrayList<Payment> paymentList) {
		this.paymentList = paymentList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}

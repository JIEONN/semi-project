package trendy.orderDetails.vo;

import java.util.ArrayList;

import trendy.payment.vo.Payment;

public class OrderDetailasReturn {
	private ArrayList<OrderDetails> list;
	private Payment payment;
	public OrderDetailasReturn() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetailasReturn(ArrayList<OrderDetails> list, Payment payment) {
		super();
		this.list = list;
		this.payment = payment;
	}
	public ArrayList<OrderDetails> getList() {
		return list;
	}
	public void setList(ArrayList<OrderDetails> list) {
		this.list = list;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	
	
	
}

package trendy.payment.vo;

import java.sql.Date;
import java.util.ArrayList;

import trendy.orderDetails.vo.OrderDetails;

public class Payment {
	private int orderNo;
	private String memberId;
	private String orderState;
	private String payMethod;
	private Date payDate;
	private int usePoint;
	private int payment;
	private int pointAdd;
	private String receiverName;
	private String receiverPhone;
	private String receiverAddress;
	private String receiverRequest;
	private String memberName;
	private ArrayList<OrderDetails> orderList;
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payment(int orderNo, String memberId, String orderState, String payMethod, Date payDate, int usePoint,
			int payment, int pointAdd, String receiverName, String receiverPhone, String receiverAddress,
			String receiverRequest, String memberName, ArrayList<OrderDetails> orderList) {
		super();
		this.orderNo = orderNo;
		this.memberId = memberId;
		this.orderState = orderState;
		this.payMethod = payMethod;
		this.payDate = payDate;
		this.usePoint = usePoint;
		this.payment = payment;
		this.pointAdd = pointAdd;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.receiverAddress = receiverAddress;
		this.receiverRequest = receiverRequest;
		this.memberName = memberName;
		this.orderList = orderList;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public int getUsePoint() {
		return usePoint;
	}
	public void setUsePoint(int usePoint) {
		this.usePoint = usePoint;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public int getPointAdd() {
		return pointAdd;
	}
	public void setPointAdd(int pointAdd) {
		this.pointAdd = pointAdd;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverRequest() {
		return receiverRequest;
	}
	public void setReceiverRequest(String receiverRequest) {
		this.receiverRequest = receiverRequest;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public ArrayList<OrderDetails> getOrderList() {
		return orderList;
	}
	public void setOrderList(ArrayList<OrderDetails> orderList) {
		this.orderList = orderList;
	}
	
	
}




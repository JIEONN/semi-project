package trendy.delivery.vo;

import java.sql.Date;

public class Delivery {
	private int deliveryNo;
	private int orderNo;
	private Date deliveryDate;
	private String deliveryName;
	private String deliveryNumber;
	
	public Delivery() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Delivery(int deliveryNo, int orderNo, Date deliveryDate, String deliveryName, String deliveryNumber) {
		super();
		this.deliveryNo = deliveryNo;
		this.orderNo = orderNo;
		this.deliveryDate = deliveryDate;
		this.deliveryName = deliveryName;
		this.deliveryNumber = deliveryNumber;
	}
	public int getDeliveryNo() {
		return deliveryNo;
	}
	public void setDeliveryNo(int deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getDeliveryNumber() {
		return deliveryNumber;
	}
	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
	
	
}

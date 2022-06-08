package trendy.orderDetails.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import trendy.orderDetails.dao.OrderDetailsDao;
import trendy.orderDetails.vo.OrderDetailasReturn;
import trendy.orderDetails.vo.OrderDetails;
import trendy.payment.dao.PaymentDao;
import trendy.payment.vo.Payment;

public class OrderDetailsService {

	public ArrayList<OrderDetails> selectAllOrderDetail() {
		Connection conn = JDBCTemplate.getConnection();
		OrderDetailsDao dao = new OrderDetailsDao();
		ArrayList<OrderDetails> orderDetailsList = dao.selectAllOrderDetail(conn);
		JDBCTemplate.close(conn);
		return orderDetailsList;
	}

	public OrderDetails selectOneOrderDetails(int detailsNo) {
		Connection conn = JDBCTemplate.getConnection();
		OrderDetailsDao dao = new OrderDetailsDao();
		OrderDetails orderDetails = new OrderDetails();
		orderDetails = dao.selectOneOrderDetails(conn, detailsNo);
		JDBCTemplate.close(conn);
		return orderDetails;
	}

	public int orderDetailsInsert(OrderDetails orderDetails) {
		Connection conn = JDBCTemplate.getConnection();
		OrderDetailsDao dao = new OrderDetailsDao();
		int result = dao.orderDetailsInsert(conn, orderDetails);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int orderDetailsDelete(int detailsNo) {
		Connection conn = JDBCTemplate.getConnection();
		OrderDetailsDao dao = new OrderDetailsDao();
		int result = dao.orderDetailsDelete(conn, detailsNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	//

	public OrderDetailasReturn selectOrderNoDetail(int orderNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<OrderDetails> orderList = new ArrayList<OrderDetails>();
		OrderDetailsDao dao = new OrderDetailsDao();
		orderList = dao.selectOrderNoDetail(conn, orderNo);
		
		PaymentDao pDao = new PaymentDao();
		Payment payment = pDao.selectOnePayment(conn, orderNo);
		
		OrderDetailasReturn orderReturn = new OrderDetailasReturn(orderList, payment);
		JDBCTemplate.close(conn);
		return orderReturn;
	}


	
	
	//
}
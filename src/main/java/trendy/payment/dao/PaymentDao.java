package trendy.payment.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import trendy.cart.vo.Cart;
import trendy.orderDetails.vo.OrderDetails;
import trendy.payment.vo.Payment;
import trendy.product.vo.Product;

public class PaymentDao {

	public Payment selectOnePayment(Connection conn, int orderNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Payment payment = null;
		String query = "select * from payment_tbl where order_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				payment = new Payment();
				payment.setOrderNo(rset.getInt("order_no"));
				payment.setMemberId(rset.getString("member_id"));
				payment.setOrderState(rset.getString("order_state"));
				payment.setPayDate(rset.getDate("pay_date"));
				payment.setUsePoint(rset.getInt("use_point"));
				payment.setPayment(rset.getInt("payment"));
				payment.setPayMethod(rset.getString("pay_method"));
				payment.setPointAdd(rset.getInt("point_add"));
				payment.setReceiverName(rset.getString("receiver_name"));
				payment.setReceiverPhone(rset.getString("receiver_phone"));
				payment.setReceiverAddress(rset.getString("receiver_address"));
				payment.setReceiverRequest(rset.getString("receiver_request"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return payment;
	}

	public int insertPayment(Connection conn, Payment payment) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO PAYMENT_TBL VALUES(PAYMENT_SEQ.NEXTVAL,?,'주문접수',?,sysdate,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, payment.getMemberId());
			pstmt.setString(2, payment.getPayMethod());
			pstmt.setInt(3, payment.getUsePoint());
			pstmt.setInt(4, payment.getPayment());
			pstmt.setInt(5, payment.getPointAdd());
			pstmt.setString(6, payment.getReceiverName());
			pstmt.setString(7, payment.getReceiverPhone());
			pstmt.setString(8, payment.getReceiverAddress());
			pstmt.setString(9, payment.getReceiverRequest());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deletePayment(Connection conn, int orderNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM PAYMENT_TBL WHERE ORDER_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public ArrayList<Payment> selectAllPayment(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Payment> paymentList = new ArrayList<Payment>();
		String query = "SELECT * FROM PAYMENT_TBL";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Payment payment = new Payment();
				payment.setOrderNo(rset.getInt("order_no"));
				payment.setMemberId(rset.getString("member_id"));
				payment.setOrderState(rset.getString("order_state"));
				payment.setPayMethod(rset.getString("pay_method"));
				payment.setPayDate(rset.getDate("pay_date"));
				payment.setUsePoint(rset.getInt("use_point"));
				payment.setPayment(rset.getInt("payment"));
				payment.setPointAdd(rset.getInt("point_add"));
				payment.setReceiverName(rset.getString("receiver_name"));
				payment.setReceiverPhone(rset.getString("receiver_phone"));
				payment.setReceiverAddress(rset.getString("receiver_address"));
				payment.setReceiverRequest(rset.getString("receiver_request"));
				paymentList.add(payment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return paymentList;
	}

	// 위까지 기본select, delete, insert

	public ArrayList<Payment> selectOrderStateList(Connection conn, int start, int end, String type, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Payment> paymentList = new ArrayList<Payment>();
		String query = "select * from (select rownum as rnum,n.*,(select member_name from member_tbl where member_id=n.member_id) as \"주문자명\" from(select * from PAYMENT_TBL";
		if (type.equals("취소/교환/환불")) {
			query += " where order_state in('반품신청','반품진행중', '반품거부', '반품완료', '교환신청', '교환진행중', '교환거부', '교환완료', '결제취소')";
		} else if (!type.equals("전체")) {
			query += " where order_state=?";
		}
		if (memberId != null && type.equals("전체")) {
			query += " where member_id=?";
		} else if (memberId != null) {
			query += " and member_id=?";
		}
		query += " order by ORDER_NO desc)n) where rnum between ? and ?";
		// System.out.println(query);
		try {
			pstmt = conn.prepareStatement(query);
			// System.out.println("start : " + start);
			// System.out.println("end : " + end);
			int i = 1;
			if (!type.equals("전체") && !type.equals("취소/교환/환불")) {
				pstmt.setString(i++, type);
			}
			if (memberId != null) {
				pstmt.setString(i++, memberId);
			}
			pstmt.setInt(i++, start);
			pstmt.setInt(i++, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Payment payment = new Payment();
				payment.setOrderNo(rset.getInt("order_no"));
				payment.setPayDate(rset.getDate("pay_date"));
				payment.setMemberId(rset.getString("member_id"));
				payment.setMemberName(rset.getString("주문자명"));
				payment.setPayment(rset.getInt("payment"));
				payment.setPayMethod(rset.getString("pay_method"));
				payment.setOrderState(rset.getString("order_state"));
				paymentList.add(payment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return paymentList;
	}

	public int totalOrderStateCount(Connection conn, String type, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		// "select count(*) as cnt from payment_tbl where order_state=?";
		String query = "select count(*) as cnt from payment_tbl";
		if (type.equals("취소/교환/환불")) {
			query += " where order_state in('반품신청', '반품진행중', '반품거부', '반품완료', '교환신청', '교환진행중', '교환거부', '교환완료', '결제취소')";
		} else {
			query += " where order_state=?";
		}
		if (memberId != null && type.equals("전체")) {
			query += " where member_id=?";
		} else if (memberId != null) {
			query += " and member_id=?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			if (!type.equals("취소/교환/환불")) {
				pstmt.setString(1, type);
			}
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return result;
	}

	public int totalOrderStateCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) as cnt from payment_tbl";
		if (memberId != null) {
			query += " where member_id=?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			int i = 1;
			if (memberId != null) {
				pstmt.setString(i++, memberId);
			}
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return result;
	}

	public int updatePayment(Connection conn, int orderNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE PAYMENT_TBL SET ORDER_STATE='주문취소' WHERE ORDER_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Payment> selectMemberIdPayment(Connection conn, int start, int end, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Payment payment = null;
		ArrayList<Payment> paymentList = new ArrayList<Payment>();
		String query = "select * from (select rownum as rnum,n.* from(select * from PAYMENT_TBL where member_id=?";
		query += "order by ORDER_NO desc)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				payment = new Payment();
				payment.setOrderNo(rset.getInt("order_no"));
				payment.setMemberId(rset.getString("member_id"));
				payment.setOrderState(rset.getString("order_state"));
				payment.setPayDate(rset.getDate("pay_date"));
				payment.setPayment(rset.getInt("payment"));
				payment.setPayMethod(rset.getString("pay_method"));
				paymentList.add(payment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return paymentList;
	}

	public ArrayList<OrderDetails> selectMemberOrderDetails(Connection conn, int orderNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OrderDetails orderdetails = null;
		ArrayList<OrderDetails> orderList = new ArrayList<OrderDetails>();
		String query = "select product_name,count,product_filepath,product_color,product_size,product_price from order_details_tbl join product_tbl using (product_id) where order_no=? and rownum=1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				orderdetails = new OrderDetails();
				orderdetails.setProductName(rset.getString("product_name"));
				orderdetails.setCount(rset.getInt("count"));
				orderdetails.setProductFilepath(rset.getString("product_filepath"));
				orderdetails.setProductColor(rset.getString("product_color"));
				orderdetails.setProductSize(rset.getString("product_size"));
				orderdetails.setProductPrice(rset.getInt("product_price"));
				orderList.add(orderdetails);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return orderList;
	}

	public Cart selectCartNo(Connection conn, int cartNoNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Cart cart = null;
		String query = "select * from cart_tbl where cart_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cartNoNum);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				cart = new Cart();
				cart.setCartNo(rset.getInt("cart_no"));
				cart.setMemberId(rset.getString("member_id"));
				cart.setProductId(rset.getString("product_id"));
				cart.setAmount(rset.getInt("amount"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return cart;
	}

	public Product selectProduct(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product product = null;
		String query = "select * from product_tbl where product_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				product = new Product();
				product.setProductId(rset.getString("product_id"));
				product.setProductName(rset.getString("product_name"));
				product.setProductPrice(rset.getInt("product_price"));
				product.setProductFilepath(rset.getString("product_filepath"));
				product.setProductColor(rset.getString("product_color"));
				product.setProductSize(rset.getString("product_size"));
			}
			// cart.setCartNo(rset.getInt("cart_no"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return product;
	}

	public Payment selectMAX(Connection conn, Payment p) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select max(order_no) from payment_tbl";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				p.setOrderNo(rset.getInt("max(order_no)"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return p;
	}

	public int paymentDeleteCart(Connection conn, Cart cart) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from cart_tbl where cart_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cart.getCartNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int upDatePayment(Connection conn, Payment p, int sumPrice) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update PAYMENT_TBL set PAYMENT=? where ORDER_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sumPrice);
			pstmt.setInt(2, p.getOrderNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
package trendy.orderDetails.dao;

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

public class OrderDetailsDao {

	public ArrayList<OrderDetails> selectAllOrderDetail(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
		String query = "SELECT * FROM ORDER_DETAILS_TBL";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				OrderDetails orderdetails = new OrderDetails();
				orderdetails.setDetailsNo(rset.getInt("details_no"));
				orderdetails.setOrderNo(rset.getInt("order_no"));
				orderdetails.setProductId(rset.getString("product_id"));
				orderdetails.setCount(rset.getInt("count"));
				orderDetailsList.add(orderdetails);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return orderDetailsList;
	}

	public OrderDetails selectOneOrderDetails(Connection conn, int detailsNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OrderDetails orderDetails = null;
		String query = "SELECT * FROM ORDER_DETAILS_TBL WHERE DETAILS_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, detailsNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				orderDetails = new OrderDetails();
				orderDetails.setDetailsNo(rset.getInt("details_no"));
				orderDetails.setOrderNo(rset.getInt("order_no"));
				orderDetails.setProductId(rset.getString("product_id"));
				orderDetails.setCount(rset.getInt("count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return orderDetails;
	}

	public int orderDetailsInsert(Connection conn, OrderDetails orderDetails) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO ORDER_DETAILS_TBL VALUES(ORDER_DETAILS_SEQ.NEXTVAL,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderDetails.getOrderNo());
			pstmt.setString(2, orderDetails.getProductId());
			pstmt.setInt(3, orderDetails.getCount());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int orderDetailsDelete(Connection conn, int detailsNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM ORDER_DETAILS_TBL WHERE DETAILS_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, detailsNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<OrderDetails> selectOrderNoDetail(Connection conn, int orderNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
		OrderDetails orderDetails = null;
		String query = "select details_no,product_name,count,product_filepath,product_color,product_size,product_price from order_details_tbl join product_tbl using (product_id) where order_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				OrderDetails orderdetails = new OrderDetails();
				orderdetails.setDetailsNo(rset.getInt("details_no"));
				orderdetails.setProductName(rset.getString("product_name"));
				orderdetails.setProductFilepath(rset.getString("product_filepath"));
				orderdetails.setProductColor(rset.getString("product_color"));
				orderdetails.setProductSize(rset.getString("product_size"));
				orderdetails.setCount(rset.getInt("count"));
				orderdetails.setProductPrice(rset.getInt("product_price"));
				orderDetailsList.add(orderdetails);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return orderDetailsList;
	}

	public int insertOrderDetails(Connection conn, Payment p, Cart cart) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO ORDER_DETAILS_TBL VALUES(ORDER_DETAILS_SEQ.NEXTVAL,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, p.getOrderNo());
			pstmt.setString(2, cart.getProductId());
			pstmt.setInt(3, cart.getAmount());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertOrderDetails(Connection conn, Payment p, int amount, Product product) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO ORDER_DETAILS_TBL VALUES(ORDER_DETAILS_SEQ.NEXTVAL,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, p.getOrderNo());
			pstmt.setString(2, product.getProductId());
			pstmt.setInt(3, amount);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}


}

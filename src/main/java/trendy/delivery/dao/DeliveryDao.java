package trendy.delivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import trendy.delivery.vo.Delivery;

public class DeliveryDao {

	public ArrayList<Delivery> deliveryAllSelect(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Delivery> deliveryList = new ArrayList<Delivery>();
		String query = "SELECT * FROM DELIVERY_TBL";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Delivery delivery = new Delivery();
				delivery.setDeliveryNo(rset.getInt("delivery_no"));
				delivery.setOrderNo(rset.getInt("order_no"));
				delivery.setDeliveryDate(rset.getDate("delivery_date"));
				delivery.setDeliveryName(rset.getString("delivery_name"));
				delivery.setDeliveryNumber(rset.getString("delivery_number"));
				deliveryList.add(delivery);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return deliveryList;
	}

	public Delivery deliveryOneSelect(Connection conn, int deliveryNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Delivery delivery = null;
		String query = "SELECT * FROM DELIVERY_TBL WHERE DELIVERY_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, deliveryNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				delivery = new Delivery();
				delivery.setDeliveryNo(rset.getInt("delivery_no"));
				delivery.setOrderNo(rset.getInt("order_no"));
				delivery.setDeliveryDate(rset.getDate("delivery_date"));
				delivery.setDeliveryName(rset.getString("delivery_name"));
				delivery.setDeliveryNumber(rset.getString("delivery_number"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return delivery;
	}

	public int deliveryDelete(Connection conn, int deliveryNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM DELIVERY_TBL WHERE DELIVERY_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, deliveryNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public int deliveryInsert(Connection conn, Delivery delivery) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO DELIVERY_TBL VALUES(DELIVERY_SEQ.NEXTVAL,?,sysdate,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, delivery.getOrderNo());
			pstmt.setString(2, delivery.getDeliveryName());
			pstmt.setString(3, delivery.getDeliveryNumber());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
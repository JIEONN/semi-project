package trendy.delivery.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import trendy.delivery.dao.DeliveryDao;
import trendy.delivery.vo.Delivery;

public class DeliveryService {

	public ArrayList<Delivery> deliveryAllSelect() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Delivery> deliveryList = new ArrayList<Delivery>();
		DeliveryDao dao = new DeliveryDao();
		deliveryList = dao.deliveryAllSelect(conn);
		JDBCTemplate.close(conn);
		return deliveryList;
	}

	public Delivery deliveryOneSelect(int deliveryNo) {
		Connection conn = JDBCTemplate.getConnection();
		Delivery delivery = new Delivery();
		DeliveryDao dao = new DeliveryDao();
		delivery = dao.deliveryOneSelect(conn, deliveryNo);
		JDBCTemplate.close(conn);
		return delivery;
	}

	public int deliveryDelete(int deliveryNo) {
		Connection conn = JDBCTemplate.getConnection();
		DeliveryDao dao = new DeliveryDao();
		int result = dao.deliveryDelete(conn, deliveryNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deliveryInsert(Delivery delivery) {
		Connection conn = JDBCTemplate.getConnection();
		DeliveryDao dao = new DeliveryDao();
		int result = dao.deliveryInsert(conn, delivery);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
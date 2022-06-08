package trendy.cart.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import trendy.cart.dao.CartDao;
import trendy.cart.vo.Cart;
import trendy.cart.vo.CartWithProduct;

public class CartService {
	public ArrayList<Cart> selectAllCart() {
		Connection conn = JDBCTemplate.getConnection();
		CartDao dao = new CartDao();
		ArrayList<Cart> list = dao.selectAllCart(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<Cart> selectCart(String selectCart) {
		Connection conn = JDBCTemplate.getConnection();
		CartDao dao = new CartDao();
		ArrayList<Cart> list = dao.selectCart(conn, selectCart);
		JDBCTemplate.close(conn);
		return list;
	}

	public int insertCart(Cart c) {
		Connection conn = JDBCTemplate.getConnection();
		CartDao dao = new CartDao();
		int result = dao.insertCart(conn, c);
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteCart(String memberId, String productId) {
		Connection conn = JDBCTemplate.getConnection();
		CartDao dao = new CartDao();
		int result = dao.deleteCart(conn, memberId, productId);
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<CartWithProduct> selectMemberCart(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		CartDao dao = new CartDao();
		ArrayList<CartWithProduct> list = dao.selectMemberCart(conn, memberId);
		JDBCTemplate.close(conn);
		return list;
	}

	public int plusCartAmount(String memberId, String productId, int currAmount) {
		Connection conn = JDBCTemplate.getConnection();
		CartDao dao = new CartDao();
		int result = dao.plusCartAmount(conn, memberId, productId, currAmount);
		JDBCTemplate.close(conn);
		return result;
	}
	public int minusCartAmount(String memberId, String productId, int currAmount) {
		Connection conn = JDBCTemplate.getConnection();
		CartDao dao = new CartDao();
		int result = dao.minusCartAmount(conn, memberId, productId, currAmount);
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteCart2(String memberId, String checkBoxArr) {
		Connection conn = JDBCTemplate.getConnection();
		CartDao dao = new CartDao();
		int result=0;
		StringTokenizer sT1 = new StringTokenizer(checkBoxArr,"/");
		while(sT1.hasMoreTokens()) {
			String productId = sT1.nextToken();
			int checkResult = dao.deleteCart(conn, memberId, productId);
			if(checkResult == 0) {
				result=0;
				break;
			}
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Cart selectOneCart(Cart c) {
		Connection conn = JDBCTemplate.getConnection();
		CartDao dao = new CartDao();
		Cart c1 = dao.selectOneCart(conn, c);
		JDBCTemplate.close(conn);
		return c1;
	}
}

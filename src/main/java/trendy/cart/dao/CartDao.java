package trendy.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import trendy.cart.vo.Cart;
import trendy.cart.vo.CartWithProduct;

public class CartDao {

	public ArrayList<Cart> selectAllCart(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Cart> list = new ArrayList<Cart>();
		String query = "select * from cart_tbl";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Cart c = new Cart();
				c.setAmount(rset.getInt("amount"));
				c.setCartNo(rset.getInt("cart_no"));
				c.setMemberId(rset.getString("member_id"));
				c.setProductId(rset.getString("product_id"));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public ArrayList<Cart> selectCart(Connection conn, String selectCart) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Cart> list = new ArrayList<Cart>();
		String query = "select * from cart_tbl where member_id=?";		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, selectCart);
			rset = pstmt.executeQuery();			
			while(rset.next()) {
				Cart c = new Cart();
				c.setAmount(rset.getInt("amount"));
				c.setCartNo(rset.getInt("cart_no"));
				c.setMemberId(rset.getString("member_id"));
				c.setProductId(rset.getString("product_id"));
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public int insertCart(Connection conn, Cart c) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into cart_tbl values(cart_sequence.nextval, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, c.getMemberId());
			pstmt.setString(2, c.getProductId());
			pstmt.setInt(3, c.getAmount());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}

	public int deleteCart(Connection conn, String memberId, String productId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from cart_tbl where member_id=? and product_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, productId);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<CartWithProduct> selectMemberCart(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<CartWithProduct> list = new ArrayList<CartWithProduct>();
		String query = "select cart_tbl.member_id, cart_tbl.product_id, cart_tbl.amount, product_tbl.product_name, product_tbl.product_price, product_tbl.product_filename, product_tbl.product_color, product_tbl.product_size from cart_tbl join product_tbl on cart_tbl.product_id =product_tbl.product_id where member_id=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, memberId);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					CartWithProduct c = new CartWithProduct();
					c.setMemberId(rset.getString("member_id"));
					c.setProductId(rset.getString("product_id"));
					c.setAmount(rset.getInt("amount"));
					c.setProductName(rset.getString("product_name"));
					c.setProductPrice(rset.getInt("product_price"));
					c.setProductFileName(rset.getString("product_filename"));
					c.setProductColor(rset.getString("product_color"));
					c.setProductSize(rset.getString("product_size"));
					list.add(c);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
				JDBCTemplate.close(rset);
			}
		return list;
	}

	public int plusCartAmount(Connection conn, String memberId, String productId, int currAmount) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update cart_tbl set amount = ? where member_id=? and product_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (currAmount+1));
			pstmt.setString(2, memberId);
			pstmt.setString(3, productId);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int minusCartAmount(Connection conn, String memberId, String productId, int currAmount) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update cart_tbl set amount = ? where member_id=? and product_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (currAmount-1));
			pstmt.setString(2, memberId);
			pstmt.setString(3, productId);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Cart selectOneCart(Connection conn, Cart c) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Cart c1 = null;
		String query = "select * from cart_tbl where member_id=? and product_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, c.getMemberId());
			pstmt.setString(2, c.getProductId());
			rset=pstmt.executeQuery();
			while(rset.next()) {
				c1 = new Cart();
				c1.setAmount(rset.getInt("amount"));
				c1.setCartNo(rset.getInt("cart_no"));
				c1.setMemberId(rset.getString("member_id"));
				c1.setProductId(rset.getString("product_id"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}		
		return c1;
	}

}
package trendy.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import trendy.product.vo.Product;

public class ProductDao {

	public ArrayList<Product> selectProductList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		String query = "select * from product_tbl";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setProductCount(rset.getInt("product_count"));
				p.setProductDetail(rset.getString("product_detail"));
				p.setProductFilename(rset.getString("product_filename"));
				p.setProductFilepath(rset.getString("product_filepath"));
				p.setSubCategory(rset.getString("sub_category"));
				p.setMainCategory(rset.getString("main_category"));
				p.setProductColor(rset.getString("product_color"));
				p.setProductSize(rset.getString("product_size"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int insertProduct(Connection conn, Product p) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into product_tbl values(?||product_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getProductId());
			pstmt.setString(2, p.getProductName());
			pstmt.setInt(3, p.getProductPrice());
			pstmt.setInt(4, p.getProductCount());
			pstmt.setString(5, p.getProductDetail());
			pstmt.setString(6, p.getProductFilename());
			pstmt.setString(7, p.getProductFilepath());
			pstmt.setString(8, p.getSubCategory());
			pstmt.setString(9, p.getMainCategory());
			pstmt.setString(10, p.getProductColor());
			pstmt.setString(11, p.getProductSize());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteProduct(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from product_tbl where product_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Product selectProduct(Connection conn, String productId) {
		PreparedStatement pstmt = null;
		Product p = null;
		ResultSet rset = null;
		String query = "select * from product_tbl where product_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, productId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setProductCount(rset.getInt("product_count"));
				p.setProductDetail(rset.getString("product_detail"));
				p.setProductFilename(rset.getString("product_filename"));
				p.setProductFilepath(rset.getString("product_filepath"));
				p.setSubCategory(rset.getString("sub_category"));
				p.setMainCategory(rset.getString("main_category"));
				p.setProductColor(rset.getString("product_color"));
				p.setProductSize(rset.getString("product_size"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return p;
	}

	public int updateProduct(Connection conn, Product p) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update product_tbl set product_name=?, product_price=?, product_count=?, product_detail=?,product_filename=?, product_filepath=? where product_id=? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getProductName());
			pstmt.setInt(2, p.getProductPrice());
			pstmt.setInt(3, p.getProductCount());
			pstmt.setString(4, p.getProductDetail());
			pstmt.setString(5, p.getProductFilename());
			pstmt.setString(6, p.getProductFilepath());
			pstmt.setString(7, p.getProductId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
//검색
	public ArrayList<Product> searchProduct(Connection conn, String mainCategory, String searchBox, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		String query = "select * from (select rownum as rnum, s.* from (select substr(product_id,7) as subs, p.* from "
		+ "(select * from product_tbl where main_category=? and product_id like '%'||?||'%')p order by subs desc)s) where rnum between ? and ?";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mainCategory);
			pstmt.setString(2, searchBox);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p  = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setProductCount(rset.getInt("product_count"));
				p.setProductDetail(rset.getString("product_detail"));
				p.setProductFilename(rset.getString("product_filename"));
				p.setProductFilepath(rset.getString("product_filepath"));
				p.setSubCategory(rset.getString("sub_category"));
				p.setMainCategory(rset.getString("main_category"));
				p.setProductColor(rset.getString("product_color"));
				p.setProductSize(rset.getString("product_size"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Product> searchProductList(Connection conn, String mainCategory, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		String query = "select * from (select rownum as rnum, s.* from (select substr(product_id,7) as subs, p.* from "
				+ "(select * from product_tbl where main_category=?)p order by subs desc)s) where rnum between ? and ?";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mainCategory);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setProductCount(rset.getInt("product_count"));
				p.setProductDetail(rset.getString("product_detail"));
				p.setProductFilename(rset.getString("product_filename"));
				p.setProductFilepath(rset.getString("product_filepath"));
				p.setSubCategory(rset.getString("sub_category"));
				p.setMainCategory(rset.getString("main_category"));
				p.setProductColor(rset.getString("product_color"));
				p.setProductSize(rset.getString("product_size"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Product> searchProduct(Connection conn, String searchBox, int start, int end) {
		PreparedStatement pstmt = null;
		ArrayList<Product> list = new ArrayList<Product>();
		ResultSet rset = null;
		
		String query = "select * from (select rownum as rnum, s.* from (select substr(product_id,7) as subs, p.* from "
				+ "(select * from product_tbl where product_id like '%'||?||'%')p order by subs desc)s) where rnum between ? and ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchBox);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setProductCount(rset.getInt("product_count"));
				p.setProductDetail(rset.getString("product_detail"));
				p.setProductFilename(rset.getString("product_filename"));
				p.setProductFilepath(rset.getString("product_filepath"));
				p.setSubCategory(rset.getString("sub_category"));
				p.setMainCategory(rset.getString("main_category"));
				p.setProductColor(rset.getString("product_color"));
				p.setProductSize(rset.getString("product_size"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Product> searchProductList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ArrayList<Product> list = new ArrayList<Product>();
		ResultSet rset = null;
		
		String query = "select * from (select rownum as rnum, s.* from (select substr(product_id,7) as subs, p.* from "
				+ "(select * from product_tbl)p order by subs desc)s) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setProductCount(rset.getInt("product_count"));
				p.setProductDetail(rset.getString("product_detail"));
				p.setProductFilename(rset.getString("product_filename"));
				p.setProductFilepath(rset.getString("product_filepath"));
				p.setSubCategory(rset.getString("sub_category"));
				p.setMainCategory(rset.getString("main_category"));
				p.setProductColor(rset.getString("product_color"));
				p.setProductSize(rset.getString("product_size"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;

	}

	public int totalProductCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		int result=0;
		String query = "select count(*) as cnt from product_tbl";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int totalProductCount(Connection conn, String mainCategory) {
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		int result=0;
		String query = "select count(*) as cnt from product_tbl where main_category=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mainCategory);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	
	}

	public int totalProductCountList(Connection conn, String searchBox) {
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		int result=0;
		String query = "select count(*) as cnt from product_tbl where product_id like '%'||?||'%'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchBox);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int totalProductCount(Connection conn, String mainCategory, String searchBox) {
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		int result=0;
		String query = "select count(*) as cnt from product_tbl where main_category=? and product_id like '%'||?||'%'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mainCategory);
			pstmt.setString(2, searchBox);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Product> productListHigh(Connection conn, String mainCategory, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		String query = "select * from (select rownum as rnum, s.*  from(select * from product_tbl where main_category=? order by product_price desc)s) where rnum between ? and ? ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mainCategory);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setProductCount(rset.getInt("product_count"));
				p.setProductDetail(rset.getString("product_detail"));
				p.setProductFilename(rset.getString("product_filename"));
				p.setProductFilepath(rset.getString("product_filepath"));
				p.setSubCategory(rset.getString("sub_category"));
				p.setMainCategory(rset.getString("main_category"));
				p.setProductColor(rset.getString("product_color"));
				p.setProductSize(rset.getString("product_size"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Product> productListLow(Connection conn, String mainCategory, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		String query = "select * from (select rownum as rnum, s.*  from(select * from product_tbl where main_category=? order by product_price asc)s) where rnum between ? and ? ";
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mainCategory);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setProductCount(rset.getInt("product_count"));
				p.setProductDetail(rset.getString("product_detail"));
				p.setProductFilename(rset.getString("product_filename"));
				p.setProductFilepath(rset.getString("product_filepath"));
				p.setSubCategory(rset.getString("sub_category"));
				p.setMainCategory(rset.getString("main_category"));
				p.setProductColor(rset.getString("product_color"));
				p.setProductSize(rset.getString("product_size"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Product> ProductList(Connection conn, String mainCategory, String subCategory, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		String query = "select * from (select rownum as rnum, s.* from (select substr(product_id,7) as subs, p.* from "
				+ "(select * from product_tbl where main_category=? and sub_category=?)p order by subs desc)s) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mainCategory);
			pstmt.setString(2, subCategory);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setProductCount(rset.getInt("product_count"));
				p.setProductDetail(rset.getString("product_detail"));
				p.setProductFilename(rset.getString("product_filename"));
				p.setProductFilepath(rset.getString("product_filepath"));
				p.setSubCategory(rset.getString("sub_category"));
				p.setMainCategory(rset.getString("main_category"));
				p.setProductColor(rset.getString("product_color"));
				p.setProductSize(rset.getString("product_size"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Product> productListHigh(Connection conn, String mainCategory, String subCategory, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		String query = "select * from (select rownum as rnum, s.*  from(select * from product_tbl where main_category=? and sub_category=? order by product_price desc)s) where rnum between ? and ? ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mainCategory);
			pstmt.setString(2, subCategory);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setProductCount(rset.getInt("product_count"));
				p.setProductDetail(rset.getString("product_detail"));
				p.setProductFilename(rset.getString("product_filename"));
				p.setProductFilepath(rset.getString("product_filepath"));
				p.setSubCategory(rset.getString("sub_category"));
				p.setMainCategory(rset.getString("main_category"));
				p.setProductColor(rset.getString("product_color"));
				p.setProductSize(rset.getString("product_size"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Product> productListLow(Connection conn, String mainCategory, String subCategory, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		String query = "select * from (select rownum as rnum, s.*  from(select * from product_tbl where main_category=? and sub_category=? order by product_price asc)s) where rnum between ? and ? ";
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mainCategory);
			pstmt.setString(2, subCategory);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setProductCount(rset.getInt("product_count"));
				p.setProductDetail(rset.getString("product_detail"));
				p.setProductFilename(rset.getString("product_filename"));
				p.setProductFilepath(rset.getString("product_filepath"));
				p.setSubCategory(rset.getString("sub_category"));
				p.setMainCategory(rset.getString("main_category"));
				p.setProductColor(rset.getString("product_color"));
				p.setProductSize(rset.getString("product_size"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int totalProductCountList(Connection conn, String mainCategory, String subCategory) {
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		int result=0;
		String query = "select count(*) as cnt from product_tbl where main_category=? and sub_category=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mainCategory);
			pstmt.setString(2, subCategory);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Product> mainSearch(Connection conn, String searchValue, int start, int end) {
		PreparedStatement pstmt = null;
		ArrayList<Product> list = new ArrayList<Product>();
		ResultSet rset = null;
		
		String query = "select * from (select rownum as rnum, s.* from (select substr(product_id,7) as subs, p.* from "
				+ "(select * from (select lower(product_name) as low, p.* from (select * from product_tbl)p) where low like  '%'||?||'%')p order by subs desc)s) where rnum between ? and ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchValue);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setProductCount(rset.getInt("product_count"));
				p.setProductDetail(rset.getString("product_detail"));
				p.setProductFilename(rset.getString("product_filename"));
				p.setProductFilepath(rset.getString("product_filepath"));
				p.setSubCategory(rset.getString("sub_category"));
				p.setMainCategory(rset.getString("main_category"));
				p.setProductColor(rset.getString("product_color"));
				p.setProductSize(rset.getString("product_size"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Product> mainSearchHigh(Connection conn, String searchValue, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		String query = "select * from (select rownum as rnum, s.*  from(select * from (select lower(product_name) as low, p.* from (select * from product_tbl)p) where low like  '%'||?||'%' order by product_price desc)s) where rnum between ? and ? ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchValue);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setProductCount(rset.getInt("product_count"));
				p.setProductDetail(rset.getString("product_detail"));
				p.setProductFilename(rset.getString("product_filename"));
				p.setProductFilepath(rset.getString("product_filepath"));
				p.setSubCategory(rset.getString("sub_category"));
				p.setMainCategory(rset.getString("main_category"));
				p.setProductColor(rset.getString("product_color"));
				p.setProductSize(rset.getString("product_size"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Product> mainSearchLow(Connection conn, String searchValue, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Product> list = new ArrayList<Product>();
		
		String query = "select * from (select rownum as rnum, s.*  from(select * from (select lower(product_name) as low, p.* from (select * from product_tbl)p) where low like  '%'||?||'%' order by product_price asc)s) where rnum between ? and ? ";

		//String query = "select * from (select lower(product_id) as low, p.* from (select * from product_tbl)p) where low like  '%'||?||'%'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchValue);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product p = new Product();
				p.setProductId(rset.getString("product_id"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setProductCount(rset.getInt("product_count"));
				p.setProductDetail(rset.getString("product_detail"));
				p.setProductFilename(rset.getString("product_filename"));
				p.setProductFilepath(rset.getString("product_filepath"));
				p.setSubCategory(rset.getString("sub_category"));
				p.setMainCategory(rset.getString("main_category"));
				p.setProductColor(rset.getString("product_color"));
				p.setProductSize(rset.getString("product_size"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int mainSearchTotal(Connection conn, String searchValue) {
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		int result=0;
		String query = "select count(*) as cnt from (select lower(product_name) as low, p.* from (select * from product_tbl)p) where low like  '%'||?||'%' ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchValue);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}

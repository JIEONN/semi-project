package trendy.product.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import trendy.product.dao.ProductDao;
import trendy.product.vo.Product;
import trendy.product.vo.ProductPageData;


public class ProductService {
	public ArrayList<Product> selectProductList(){
		Connection conn = JDBCTemplate.getConnection();
		ProductDao dao = new ProductDao();
		ArrayList<Product> list = dao.selectProductList(conn);
		JDBCTemplate.close(conn);
		return list;
	}
	
	public int insertProduct(Product p) {
		Connection conn = JDBCTemplate.getConnection();
		ProductDao dao = new ProductDao();
		int result = dao.insertProduct(conn, p);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteProduct(String productId) {
		Connection conn = JDBCTemplate.getConnection();
		ProductDao dao = new ProductDao();
		int result = dao.deleteProduct(conn, productId);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Product selectProduct(String productId) {
		Connection conn = JDBCTemplate.getConnection();
		ProductDao dao = new ProductDao();
		Product p = dao.selectProduct(conn,productId);
		JDBCTemplate.close(conn);
		return p;
	}

	public int updateProduct(Product p ) {
		Connection conn = JDBCTemplate.getConnection();
		ProductDao dao = new ProductDao();
		int result = dao.updateProduct(conn, p);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	
	
	//페이징처리 + 상품조회
	public ProductPageData searchProductList(String mainCategory, String searchBox, int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		ProductDao dao = new ProductDao();
		
		
		
		 
		int numPerPage = 10;

		
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		
		ArrayList<Product> list = new ArrayList<Product>();
		if(searchBox.equals("")) {
			if(mainCategory.equals("all")) {
				list = dao.searchProductList(conn, start, end);
			}else {
				list = dao.searchProductList(conn, mainCategory, start, end);
			}
			
		}else {
			if(mainCategory.equals("all")) {
				list = dao.searchProduct(conn, searchBox, start, end);
			}else {
				list = dao.searchProduct(conn, mainCategory, searchBox, start, end);
			}
			
		}
		
		
		
		
		int totalCount = dao.totalProductCount(conn);
		
		if(searchBox.equals("")) {
			if(mainCategory.equals("all")) {
				totalCount = dao.totalProductCount(conn);
			}else {
				totalCount = dao.totalProductCount(conn, mainCategory);
			}
			
		}else {
			if(mainCategory.equals("all")) {
				totalCount = dao.totalProductCountList(conn, searchBox);
			}else {
				totalCount = dao.totalProductCount(conn, mainCategory, searchBox);
			}
			
		}
		
		
		
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		 
		int pageNaviSize = 5;
		
		 
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;

		
	
		 
		String pageNavi = "<ul class='pagination navi-style'>";
	
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-each' href='searchProduct.do?reqPage="+(pageNo-1)+"&mainCategory="+mainCategory+"&searchBox="+searchBox+"'>";
			pageNavi += "<span class='material-icons'>arrow_back_ios</span>";
			pageNavi += "</a></li>";
		}
		
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
					pageNavi += "<li>";
					pageNavi += "<a class='page-each active-page' href='/searchProduct.do?reqPage="+pageNo+"&mainCategory="+mainCategory+"&searchBox="+searchBox+"'>";
					pageNavi += pageNo;
					pageNavi += "</a></li>";
				}else {
					pageNavi += "<li>";
					pageNavi += "<a class='page-each' href='/searchProduct.do?reqPage="+pageNo+"&mainCategory="+mainCategory+"&searchBox="+searchBox+"'>";
					pageNavi += pageNo;
					pageNavi += "</a></li>";
				}
			pageNo++;
			if(pageNo > totalPage) {
					break;
				}
		}//for

		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-each' href='searchProduct.do?reqPage="+pageNo+"&mainCategory="+mainCategory+"&searchBox="+searchBox+"'>"; 
			pageNavi += "<span class='material-icons'>arrow_forward_ios</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		
		
		ProductPageData ppd = new ProductPageData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		return ppd;
		

	}
/*상품카테고리 클릭 후 목록화면*/
	public ProductPageData searchProductList(int reqPage, String mainCategory, String subCategory, String sort) {
		Connection conn = JDBCTemplate.getConnection();
		ProductDao dao = new ProductDao();
		
		 
		int numPerPage = 8;
		
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		ArrayList<Product> list = new ArrayList<Product>();
		if(subCategory.equals("all")) {
			if(sort.equals("new")) {
				list = dao.searchProductList(conn,mainCategory, start,end);
			}else if(sort.equals("high")) {
				list = dao.productListHigh(conn, mainCategory, start, end);
			}else if(sort.equals("low")) {
				list = dao.productListLow(conn, mainCategory, start, end);
			}
			
		}else {
			if(sort.equals("new")) {
				list = dao.ProductList(conn,mainCategory,subCategory, start,end);
			}else if(sort.equals("high")) {
				list = dao.productListHigh(conn, mainCategory, subCategory, start, end);
			}else if(sort.equals("low")) {
				list = dao.productListLow(conn, mainCategory, subCategory, start, end);
			}
			
		}
		
		int totalCount = 0;
		if(subCategory.equals("all")) {
			totalCount = dao.totalProductCount(conn, mainCategory);
		}else {
			totalCount = dao.totalProductCountList(conn, mainCategory, subCategory);
		}
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		
		int pageNaviSize = 5;
		
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination style'>";
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-each' href='/productListView.do?reqPage="+(pageNo-1)+"&mainCategory="+mainCategory+"&subCategory="+subCategory+"&sort="+sort+"'>";
			pageNavi += "<span class='material-icons'>arrow_back_ios</span>";
			pageNavi += "</a></li>";
		}
		for(int i=0;i<pageNaviSize;i++) {
		
		if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-each active-page' href='/productListView.do?reqPage="+pageNo+"&mainCategory="+mainCategory+"&subCategory="+subCategory+"&sort="+sort+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-each' href='/productListView.do?reqPage="+pageNo+"&mainCategory="+mainCategory+"&subCategory="+subCategory+"&sort="+sort+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}//for
		
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-each' href='productListView.do?reqPage="+pageNo+"&mainCategory="+mainCategory+"&subCategory="+subCategory+"&sort="+sort+"'>"; 
			pageNavi += "<span class='material-icons'>arrow_forward_ios</span>";
			pageNavi += "</a></li>";
		}
		
		pageNavi += "</ul>";
		
		ProductPageData ppd = new ProductPageData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		return ppd;
	}

	public int searchProductCount(String mainCategory, String subCategory) {
		Connection conn = JDBCTemplate.getConnection();
		ProductDao dao = new ProductDao();
		int totalCount = 0;
		if(subCategory.equals("all")) {
			totalCount = dao.totalProductCount(conn, mainCategory);
		}else {
			totalCount = dao.totalProductCountList(conn, mainCategory, subCategory);
		}
		
		JDBCTemplate.close(conn);
		return totalCount;

	}

	public ProductPageData mainSearchResult(int reqPage, String sort, String searchValue) {
		Connection conn = JDBCTemplate.getConnection();
		ProductDao dao = new ProductDao();
		
		 
		int numPerPage = 8;
		
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		ArrayList<Product> list = new ArrayList<Product>();
		if(sort.equals("new")) {
			list = dao.mainSearch(conn, searchValue ,start,end);
		}else if(sort.equals("high")) {
			list = dao.mainSearchHigh(conn,searchValue ,start, end);
		}else if(sort.equals("low")) {
			list = dao.mainSearchLow(conn,  searchValue ,start, end);
		}

		
		int totalCount = dao.mainSearchTotal(conn, searchValue);
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		
		int pageNaviSize = 5;
		
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination style'>";
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-each' href='/mainSearchResult.do?reqPage="+(pageNo-1)+"&searchValue="+searchValue+"&sort="+sort+"'>";
			pageNavi += "<span class='material-icons'>arrow_back_ios</span>";
			pageNavi += "</a></li>";
		}
		for(int i=0;i<pageNaviSize;i++) {
		
		if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-each active-page' href='/mainSearchResult.do?reqPage="+pageNo+"&searchValue="+searchValue+"&sort="+sort+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-each' href='/mainSearchResult.do?reqPage="+pageNo+"&searchValue="+searchValue+"&sort="+sort+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}//for
		
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-each' href='/mainSearchResult.do?reqPage="+pageNo+"&searchValue="+searchValue+"&sort="+sort+"'>"; 
			pageNavi += "<span class='material-icons'>arrow_forward_ios</span>";
			pageNavi += "</a></li>";
		}
		
		pageNavi += "</ul>";
		
		ProductPageData ppd = new ProductPageData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		return ppd;
	}

	public int mainSearchResultTotal(String searchValue) {
		Connection conn = JDBCTemplate.getConnection();
		ProductDao dao = new ProductDao();
		int total = dao.mainSearchTotal(conn, searchValue);
		JDBCTemplate.close(conn);
		return total;
	}


	

	
	
		
}

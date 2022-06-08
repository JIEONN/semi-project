package trendy.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.product.service.ProductService;
import trendy.product.vo.Product;
import trendy.product.vo.ProductPageData;

/**
 * Servlet implementation class ProductListViewServlet
 */
@WebServlet(name = "ProductListView", urlPatterns = { "/productListView.do" })
public class ProductListViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductListViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.인코딩
		request.setCharacterEncoding("utf-8");
		// 2.값추출
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String mainCategory = request.getParameter("mainCategory");
		String subCategory = request.getParameter("subCategory");
		String sort = request.getParameter("sort");
		// 3.비지니스로직
		ProductService service = new ProductService();
		ProductPageData ppd = service.searchProductList(reqPage, mainCategory, subCategory, sort);
		ArrayList<Product> list = ppd.getList();
		String pageNavi = ppd.getPageNavi();
		int totalCount = service.searchProductCount(mainCategory, subCategory);
		// 4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/product/productListView.jsp");
		request.setAttribute("pageNavi", pageNavi);
		request.setAttribute("list", list);
		request.setAttribute("mainCategory", mainCategory);
		request.setAttribute("subCategory", subCategory);
		request.setAttribute("totalCount", totalCount);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

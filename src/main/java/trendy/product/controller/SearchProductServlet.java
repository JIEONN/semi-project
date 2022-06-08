package trendy.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import trendy.product.service.ProductService;
import trendy.product.vo.Product;
import trendy.product.vo.ProductPageData;

/**
 * Servlet implementation class SearchProductServlet
 */
@WebServlet(name = "SearchProduct", urlPatterns = { "/searchProduct.do" })
public class SearchProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchProductServlet() {
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
		String mainCategory = request.getParameter("mainCategory");
		String searchBox = request.getParameter("searchBox");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		// 3.비지니스로직
		ProductService service = new ProductService();
		ProductPageData ppd = service.searchProductList(mainCategory, searchBox, reqPage);
		ArrayList<Product> list = ppd.getList();
		String pageNavi = ppd.getPageNavi();
		// 4.결과처리

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/product/searchProductResult.jsp");
		request.setAttribute("list", list);
		request.setAttribute("pageNavi", pageNavi);
		request.setAttribute("mainCategory", mainCategory);
		request.setAttribute("regPage", reqPage);
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

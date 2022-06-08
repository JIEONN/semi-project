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
 * Servlet implementation class MainSearchResultServlet
 */
@WebServlet(name = "MainSearchResult", urlPatterns = { "/mainSearchResult.do" })
public class MainSearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainSearchResultServlet() {
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
		String sort = request.getParameter("sort");
		String searchValue = request.getParameter("searchValue");
		// 3.비지니스로직
		ProductService service = new ProductService();
		ProductPageData ppd = service.mainSearchResult(reqPage, sort, searchValue);
		ArrayList<Product> list = ppd.getList();
		String pageNavi = ppd.getPageNavi();

		int total = service.mainSearchResultTotal(searchValue);
		// 4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/product/mainSearchResult.jsp");
		request.setAttribute("list", list);
		request.setAttribute("pageNavi", pageNavi);
		request.setAttribute("total", total);
		request.setAttribute("searchValue", searchValue);
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

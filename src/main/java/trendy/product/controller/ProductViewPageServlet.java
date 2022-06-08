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
import trendy.review.service.ReviewService;
import trendy.review.vo.Review;
import trendy.review.vo.ReviewComment;
import trendy.review.vo.ReviewPageData;

/**
 * Servlet implementation class ProductViewPageServlet
 */
@WebServlet(name = "ProductViewPage", urlPatterns = { "/productViewPage.do" })
public class ProductViewPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductViewPageServlet() {
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
		String productId = request.getParameter("productId");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		// 3.비지니스로직
		// 상품불러오기
		ProductService service = new ProductService();
		Product p = service.selectProduct(productId);
		// 페이지당 리뷰불러오기
		ReviewService reviewService = new ReviewService();
		ReviewPageData rpd = reviewService.selectAllReview(productId, reqPage);
		ArrayList<Review> list = rpd.getList();
		String pageNavi = rpd.getPageNavi();
		// 전체리뷰 불러오기
		int totalReview = reviewService.selectAllReviewCount(productId);
		// 리뷰댓글 불러오기
		ArrayList<ReviewComment> commentList = reviewService.selectReviewCommentList();

		// 4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/product/productViewPage.jsp");
		request.setAttribute("p", p);
		request.setAttribute("list", list);
		request.setAttribute("pageNavi", pageNavi);
		request.setAttribute("totalReview", totalReview);
		request.setAttribute("reviewCommentList", commentList);
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

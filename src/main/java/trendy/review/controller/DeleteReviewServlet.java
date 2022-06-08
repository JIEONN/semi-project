package trendy.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.review.service.ReviewService;

/**
 * Servlet implementation class DeleteReviewServlet
 */
@WebServlet(name = "DeleteReview", urlPatterns = { "/deleteReview.do" })
public class DeleteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteReviewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.
		request.setCharacterEncoding("utf-8");
		// 2.
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		String productId = request.getParameter("productId");
		// 3.
		ReviewService service = new ReviewService();
		int result = service.deleteReview(reviewNo);
		// 4.
		response.sendRedirect("/productViewPage.do?productId=" + productId + "&reqPage=1");
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

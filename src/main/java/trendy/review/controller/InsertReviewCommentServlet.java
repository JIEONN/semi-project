package trendy.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.review.service.ReviewService;
import trendy.review.vo.ReviewComment;

/**
 * Servlet implementation class InsertReviewCommentServlet
 */
@WebServlet(name = "InsertReviewComment", urlPatterns = { "/insertReviewComment.do" })
public class InsertReviewCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertReviewCommentServlet() {
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
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		String memberId = request.getParameter("memberId");
		String reviewCommentContent = request.getParameter("reviewCommentContent");
		String productId = request.getParameter("productId");
		ReviewComment rc = new ReviewComment(0, memberId, reviewNo, null, reviewCommentContent);
		// 3.비지니스로직
		ReviewService service = new ReviewService();
		int result = service.insertReviewComment(rc);
		// 4.결과처리
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

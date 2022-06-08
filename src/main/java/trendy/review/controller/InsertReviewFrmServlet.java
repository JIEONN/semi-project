package trendy.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import trendy.review.service.ReviewService;
import trendy.review.vo.Review;

/**
 * Servlet implementation class InsertReviewFrmServlet
 */
@WebServlet(name = "InsertReviewFrm", urlPatterns = { "/insertReviewFrm.do" })
public class InsertReviewFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertReviewFrmServlet() {
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
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/review";
		int maxSize = 10 * 1024 * 1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		String filename = mRequest.getOriginalFileName("file");
		String filepath = mRequest.getFilesystemName("file");

		String productId = mRequest.getParameter("productId");
		String memberId = mRequest.getParameter("memberId");
		String reviewContent = mRequest.getParameter("reviewContent");

		Review r = new Review(0, productId, memberId, reviewContent, filename, filepath, null, 0);
		// 3.비지니스로직
		ReviewService service = new ReviewService();
		int result = service.insertReview(r);

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

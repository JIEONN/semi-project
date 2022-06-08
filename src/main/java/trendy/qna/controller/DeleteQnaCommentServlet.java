package trendy.qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.qna.service.QnaService;
import trendy.qna.vo.Qna;
import trendy.qna.vo.QnaComment;

/**
 * Servlet implementation class DeleteQnaCommentServlet
 */
@WebServlet(name = "DeleteQnaComment", urlPatterns = { "/deleteQnaComment.do" })
public class DeleteQnaCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteQnaCommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		// 2. 값추출
		// 3. 비즈니스로직
		int result = 0;
		QnaService service = new QnaService();
		QnaComment qc = new QnaComment();
		String memberId = "test1";
		result = service.deleteQnaComment(memberId);
		// 4. 화면출력
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

package trendy.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.qna.service.QnaService;
import trendy.qna.vo.QnaReply;

/**
 * Servlet implementation class InsertReQnaServlet
 */
@WebServlet(name = "InsertReQna", urlPatterns = { "/insertReQna.do" })
public class InsertReQnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertReQnaServlet() {
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
		QnaReply qr = new QnaReply();
		qr.setMemberId(request.getParameter("qnaWriter"));
		int qnaNo = Integer.parseInt(request.getParameter("qnaRef"));
		qr.setQnaNo(qnaNo);
		qr.setProductId(request.getParameter("qnaProductId"));
		//결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/qna/qnaReWriteFrm.jsp");
		request.setAttribute("qnaWriter", qr.getMemberId());
		request.setAttribute("qnaRef", qr.getQnaNo());
		request.setAttribute("qnaProductId", qr.getProductId());
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

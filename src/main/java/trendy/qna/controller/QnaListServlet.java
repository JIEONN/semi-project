package trendy.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import trendy.qna.service.QnaService;
import trendy.qna.vo.QnaPageData;

/**
 * Servlet implementation class QnaListServlet
 */
@WebServlet(name = "QnaList", urlPatterns = { "/qnaList.do" })
public class QnaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QnaListServlet() {
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
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		// 3. 비즈니스 로직
		QnaService service = new QnaService();
		QnaPageData qpd = service.selectQnaList(reqPage);
		// 4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/qna/qnaList.jsp");
		request.setAttribute("list", qpd.getList());//이 리스트에 다반영된거라서 화면에서 QNA객체에서 qr이 null이면 답변없는거 있으면 그데이터로 화면출력하시면 돼요
		request.setAttribute("pageNavi", qpd.getPageNavi());
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

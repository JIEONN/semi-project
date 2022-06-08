package trendy.qna.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.qna.service.QnaService;
import trendy.qna.vo.QnaReply;
import trendy.qna.vo.QnaViewData;

/**
 * Servlet implementation class QnaReViewServlet
 */
@WebServlet(name = "QnaReView", urlPatterns = { "/qnaReView.do" })
public class QnaReViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		// 2. 값추출
		int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));// qnaNo
		// 3. 비즈니스로직
		QnaService service = new QnaService();
		QnaReply qr = service.selectOneQnaRe(qnaNo);
		
		// 4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/qna/qnaReView.jsp");
		request.setAttribute("memberId", qr.getMemberId());
		request.setAttribute("qnaNo", qr.getQnaNo());
		request.setAttribute("productId", qr.getProductId());
		request.setAttribute("qnaReDate", qr.getQnaReplyDate());
		request.setAttribute("qnaReContent", qr.getQnaReplyContent());
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

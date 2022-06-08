package trendy.qna.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.qna.service.QnaService;
import trendy.qna.vo.Qna;

/**
 * Servlet implementation class InsertQnaServlet
 */
@WebServlet(name = "InsertQna", urlPatterns = { "/insertQna.do" })
public class InsertQnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertQnaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		//3. 비즈니스로직
		int result = 0;
		QnaService service = new QnaService();
		Qna q = new Qna();
		q.setMemberId("test1");
		q.setProductId("top");
		q.setQnaContent("테스트2");
		q.setQnaReadcount(0);
		q.setQnaCategory("테스트카테고리");
		q.setFilepath("테스트경로");
		result = service.InsertQna(q);
		
		
		//4. 화면출력
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

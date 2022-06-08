package trendy.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import trendy.qna.service.QnaService;
import trendy.qna.vo.Qna;

/**
 * Servlet implementation class QnaWriteServlet
 */
@WebServlet(name = "QnaWrite", urlPatterns = { "/qnaWrite.do" })
public class QnaWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaWriteServlet() {
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
		//2-1. 파일업로드
		//1) 파일업로드 경로 지정
		String root = getServletContext().getRealPath("/"); //webapp폴더 절대경로를 구함
		String saveDirectory = root+"upload/qna";
		System.out.println("파일저장경로 : "+saveDirectory);
		//2) 파일업로드 최대용량 지정
		int maxSize = 10*1024*1024;
		//3) multipart/form-data에서 데이터를 꺼내기위한 변환
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize,"UTF-8", new DefaultFileRenamePolicy());
		
		//form에서 name값을 가져온다
		String qnaCategory = mRequest.getParameter("qnaCategory"); //카테고리
		String memberId = mRequest.getParameter("qnaWriter"); //작성자 아이디 추후 수정예정
		String qnaContent = mRequest.getParameter("qnaContent"); //내용
		String productId = mRequest.getParameter("productId"); //상품코드 추후 수정예정
		String filepath = mRequest.getFilesystemName("file");//서버에 저장되는 파일이름
		
		Qna q = new Qna();
		q.setQnaCategory(qnaCategory);
		q.setMemberId(memberId);
		q.setQnaContent(qnaContent);
		q.setProductId(productId);
		q.setFilepath(filepath);
		
		//3. 비지니스 로직
		QnaService service = new QnaService();
		int result = service.InsertQna(q);
		
		//4. 화면구현
		response.sendRedirect("/qnaList.do?reqPage=1");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

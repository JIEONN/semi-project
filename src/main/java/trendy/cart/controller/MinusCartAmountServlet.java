package trendy.cart.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import trendy.cart.service.CartService;

/**
 * Servlet implementation class MinusCartAmountServlet
 */
@WebServlet(name = "MinusCartAmount", urlPatterns = { "/minusCartAmount.do" })
public class MinusCartAmountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MinusCartAmountServlet() {
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
		String memberId = request.getParameter("memberId");
		String productId = request.getParameter("productId");
		int currAmount = Integer.parseInt(request.getParameter("currAmount"));
		// 3. 비즈니스로직
		CartService service = new CartService();
		int result = service.minusCartAmount(memberId, productId, currAmount);
		if (result > 0) {
			currAmount = currAmount - 1;
		}
		// 4. 결과처리
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		new Gson().toJson(currAmount, out); // 매개변수 변환할 데이터, 화면에 데이터를 전송할 객체
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

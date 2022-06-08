package trendy.cart.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.cart.service.CartService;
import trendy.cart.vo.Cart;

/**
 * Servlet implementation class InsertCartServlet
 */
@WebServlet(name = "InsertCart", urlPatterns = { "/insertCart.do" })
public class InsertCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		Cart c = new Cart();
		c.setMemberId(request.getParameter("memberId"));
		c.setProductId(request.getParameter("productId"));
		c.setAmount(Integer.parseInt(request.getParameter("count")));

		// 장바구니에 추가할 목록이 겹치지않도록 (아이디/상품키)가 같이 중복되는 경우를 제외하기 위함 (이거 원래 아이디하고 상품키를
		// 중복으로해가지구 프라이머리키로 묶었어야했는데 그렇게하면 지금은 수정하는 시간이 너무 많이 걸려서...)
		CartService service = new CartService();
		Cart c1 = service.selectOneCart(c);
		// 중복된 값이 없을때 insert 시작
		if (c1 == null) {
			int result = service.insertCart(c);
			if (result > 0) {
				System.out.println("등록성공");
			} else {
				System.out.println("등록실패");
			}
		}
		RequestDispatcher view = request.getRequestDispatcher("/");
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

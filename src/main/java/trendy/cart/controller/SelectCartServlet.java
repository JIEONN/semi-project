package trendy.cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.cart.service.CartService;
import trendy.cart.vo.Cart;

/**
 * Servlet implementation class SelectCartServlet
 */
@WebServlet(name = "SelectCart", urlPatterns = { "/selectCart.do" })
public class SelectCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String selectCart = request.getParameter("selectCart");

		CartService service = new CartService();
		ArrayList<Cart> list = service.selectCart(selectCart);

		for (Cart c : list) {
			System.out.println(c.getAmount() + " " + c.getCartNo() + " " + c.getMemberId() + " " + c.getProductId());
		}
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

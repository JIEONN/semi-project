package trendy.cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.cart.service.CartService;
import trendy.cart.vo.Cart;

/**
 * Servlet implementation class AllCartServlet
 */
@WebServlet(name = "AllCart", urlPatterns = { "/allCart.do" })
public class AllCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		CartService service = new CartService();
		ArrayList<Cart> list = service.selectAllCart();

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

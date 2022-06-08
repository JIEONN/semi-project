package trendy.payment.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trendy.cart.service.CartService;
import trendy.cart.vo.Cart;
import trendy.member.vo.Member;
import trendy.orderDetails.vo.OrderDetails;
import trendy.payment.vo.Payment;
import trendy.product.service.ProductService;
import trendy.product.vo.Product;
import trendy.product.vo.ProductWithCount;

/**
 * Servlet implementation class ClientPaymentFrmServlet
 */
@WebServlet(name = "ClientPaymentFrm", urlPatterns = { "/clientPaymentFrm.do" })
public class ClientPaymentFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientPaymentFrmServlet() {
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

		ProductService productService = new ProductService();
		CartService cartService = new CartService();

		ArrayList<ProductWithCount> productWithCountList = new ArrayList<ProductWithCount>();

		// buy
		if (request.getParameter("productId") != null) {
			String productId = request.getParameter("productId");
			int amount = Integer.parseInt(request.getParameter("amount"));
			Product product = productService.selectProduct(productId);
			productWithCountList.add(new ProductWithCount(product, 0, amount));

			// 상품조회

			// cart
		} else {
			String memberId = request.getParameter("memberId");
			ArrayList<Cart> cartList = cartService.selectCart(memberId);
			for (int i = 0; i < cartList.size(); i++) {
				int amount = cartList.get(i).getAmount();
				String productId = cartList.get(i).getProductId();
				int cartNo = cartList.get(i).getCartNo();
				Product product = productService.selectProduct(productId);
				productWithCountList.add(new ProductWithCount(product, cartNo, amount));
			}

		}
		Payment payment = new Payment();
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setCount(2);
		request.setAttribute("payment", payment);
		request.setAttribute("orderDetials", orderDetails);
		request.setAttribute("productWithCountList", productWithCountList);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/client/clientPaymentFrm.jsp");
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

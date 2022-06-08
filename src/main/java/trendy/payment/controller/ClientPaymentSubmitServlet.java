package trendy.payment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.payment.service.PaymentService;
import trendy.payment.vo.Payment;

/**
 * Servlet implementation class ClientPaymentSubmitServlet
 */
@WebServlet(name = "ClientPaymentSubmit", urlPatterns = { "/clientPaymentSubmit.do" })
public class ClientPaymentSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientPaymentSubmitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// String addressName = request.getParameter("addressName");
		// System.out.println(+addressName);

		request.setCharacterEncoding("utf-8");
		String receiverName = request.getParameter("receiverName");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String receiverRequest = request.getParameter("receiverRequest");
		String payMethod = request.getParameter("payMethod");
		String cartNo = request.getParameter("cartNo");
		int point = Integer.parseInt(request.getParameter("point"));
		String memberId = request.getParameter("memberId");
		Payment p = new Payment();
		p.setReceiverName(receiverName);
		p.setReceiverAddress(address);
		p.setReceiverPhone(phone);
		p.setReceiverRequest(receiverRequest);
		p.setPayMethod(payMethod);
		p.setUsePoint(point);
		p.setMemberId(memberId);

		PaymentService service = new PaymentService();
		int result = service.insertPayment(p, cartNo);
		
		

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

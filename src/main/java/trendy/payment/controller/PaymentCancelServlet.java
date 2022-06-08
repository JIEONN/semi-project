package trendy.payment.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.payment.service.PaymentService;

/**
 * Servlet implementation class PaymentCancelServlet
 */
@WebServlet(name = "PaymentCancel", urlPatterns = { "/paymentCancel.do" })
public class PaymentCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentCancelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		PaymentService service = new PaymentService();
		int result = service.updatePayment(orderNo);
		//response.setCharacterEncoding("utf-8");
		if(result > 0) {
			String name = URLEncoder.encode("전체","utf-8"); //url인코딩
			response.sendRedirect("/paymentList.do?reqPage=1&type="+name);			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

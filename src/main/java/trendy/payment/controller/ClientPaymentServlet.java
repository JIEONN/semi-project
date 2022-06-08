package trendy.payment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.payment.service.PaymentService;
import trendy.payment.vo.ClientPaymentReturn;

/**
 * Servlet implementation class ClientPaymentServlet
 */
@WebServlet(name = "ClientPayment", urlPatterns = { "/clientPayment.do" })
public class ClientPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientPaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));		 
		PaymentService service = new PaymentService();
		ClientPaymentReturn cPReturn = service.selectMemberIdDetail(memberId, reqPage);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/client/clientPayment.jsp");
		request.setAttribute("paymentList", cPReturn.getPaymentList());		
		request.setAttribute("pageNavi", cPReturn.getPageNavi());
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

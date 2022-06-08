package trendy.orderDetails.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.orderDetails.service.OrderDetailsService;
import trendy.orderDetails.vo.OrderDetailasReturn;

/**
 * Servlet implementation class OrderDetailsServlet
 */
@WebServlet(name = "OrderDetails", urlPatterns = { "/orderDetails.do" })
public class OrderDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		OrderDetailasReturn orderReturn = new OrderDetailasReturn();
		OrderDetailsService service = new OrderDetailsService();
		orderReturn = service.selectOrderNoDetail(orderNo);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/admin/adminOrderDetail.jsp");
		request.setAttribute("orderList",orderReturn.getList());
		request.setAttribute("payment",orderReturn.getPayment());
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

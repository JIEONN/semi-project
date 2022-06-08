package trendy.product.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trendy.product.service.ProductService;
import trendy.product.vo.Product;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet(name = "DeleteProduct", urlPatterns = { "/deleteProduct.do" })
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.인코딩
		request.setCharacterEncoding("utf-8");
		// 2.값추출
		String productId = request.getParameter("productId");

		// 3.비지니스로직
		ProductService service = new ProductService();
		System.out.println(1);
		Product p = service.selectProduct(productId);
		System.out.println(2);
		int result = service.deleteProduct(productId);
		System.out.println(3);

		// 4.결과처리
		if (result > 0) {

			if (p.getProductFilepath() != null) {
				String root = getServletContext().getRealPath("/");
				String deleteFile = root + "upload/product/" + p.getProductFilepath();
				File delFile = new File(deleteFile);
				delFile.delete();
			}
		}

		response.sendRedirect("/searchProduct.do?reqPage=1&mainCategory=all&searchBox=");
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

package trendy.product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import trendy.product.service.ProductService;
import trendy.product.vo.Product;

/**
 * Servlet implementation class InsertProductServlet
 */
@WebServlet(name = "InsertProduct", urlPatterns = { "/insertProduct.do" })
public class InsertProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertProductServlet() {
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
		// 파일업로드
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/product";
		int maxSize = 10 * 1024 * 1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());
		// 값추출
		String productName = mRequest.getParameter("productName");
		int productPrice = Integer.parseInt(mRequest.getParameter("productPrice"));
		int productCount = Integer.parseInt(mRequest.getParameter("productCount"));
		String productColor = mRequest.getParameter("productColor");
		String productSize = mRequest.getParameter("productSize");
		String mainCategory = mRequest.getParameter("mainCategory");
		String subCategory = mRequest.getParameter("subCategory");
		String productDetail = mRequest.getParameter("productDetail");
		// 파일추출
		String filename = mRequest.getOriginalFileName("file");
		String filepath = mRequest.getFilesystemName("file");
		// 상품코드생성
		String productId = mainCategory.substring(0, 3) + subCategory.substring(0, 3);

		Product p = new Product(productId, productName, productPrice, productCount, productDetail, filename, filepath,
				subCategory, mainCategory, productColor, productSize);
		// 3.비지니스로직
		ProductService service = new ProductService();
		int result = service.insertProduct(p);

		// 4.결과처리
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

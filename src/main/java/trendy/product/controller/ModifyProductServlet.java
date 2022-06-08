package trendy.product.controller;

import java.io.File;
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
 * Servlet implementation class ModifyProductServlet
 */
@WebServlet(name = "ModifyProduct", urlPatterns = { "/modifyProduct.do" })
public class ModifyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/product";
		int maxSize = 10*1024*1024;
		
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		
		String filename = mRequest.getOriginalFileName("file");
		String filepath = mRequest.getFilesystemName("file");
		String status = mRequest.getParameter("status");
		
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");
		if(status.equals("delete")) {
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();
		}else if(oldFilename != null) {
			 
			filename = oldFilename;
			filepath = oldFilepath;
		}
		
		String productId = mRequest.getParameter("productId");
		String productName = mRequest.getParameter("productName");
		int productPrice = Integer.parseInt(mRequest.getParameter("productPrice"));
		int productCount = Integer.parseInt(mRequest.getParameter("productCount"));
		String mainCategory = mRequest.getParameter("mainCategory");
		String subCategory = mRequest.getParameter("subCategory");
		String productDetail = mRequest.getParameter("productDetail");
		String productSize = mRequest.getParameter("productSize");
		String productColor = mRequest.getParameter("productColor");
		Product p = new Product(productId, productName, productPrice, productCount, productDetail, filename, filepath, subCategory, mainCategory, productColor, productSize);
		//3.비지니스로직
		ProductService service = new ProductService();
		int result = service.updateProduct(p);
		//4.결과처리
		response.sendRedirect("/searchProduct.do?reqPage=1&mainCategory=all&searchBox=");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

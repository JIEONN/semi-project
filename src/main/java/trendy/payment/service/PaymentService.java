package trendy.payment.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import trendy.cart.vo.Cart;
import trendy.orderDetails.dao.OrderDetailsDao;
import trendy.orderDetails.vo.OrderDetails;
import trendy.payment.dao.PaymentDao;
import trendy.payment.vo.ClientPaymentReturn;
import trendy.payment.vo.Payment;
import trendy.payment.vo.PaymentPageData;
import trendy.product.vo.Product;

public class PaymentService {
	public Payment selectOnePayment(int orderNo) {
		Connection conn = JDBCTemplate.getConnection();
		PaymentDao dao = new PaymentDao();
		Payment payment = dao.selectOnePayment(conn, orderNo);
		JDBCTemplate.close(conn);
		return payment;
	}

	public int insertPayment(Payment payment) {
		Connection conn = JDBCTemplate.getConnection();
		PaymentDao dao = new PaymentDao();
		int result = dao.insertPayment(conn, payment);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteMember(int orderNo) {
		Connection conn = JDBCTemplate.getConnection();
		PaymentDao dao = new PaymentDao();
		int result = dao.deletePayment(conn, orderNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Payment> selectAllPayment() {
		Connection conn = JDBCTemplate.getConnection();
		PaymentDao dao = new PaymentDao();
		ArrayList<Payment> paymentList = dao.selectAllPayment(conn);
		JDBCTemplate.close(conn);
		return paymentList;
	}

	// 위까지 기본select, delete, insert

	public PaymentPageData selectAllPayment(int reqPage, String type, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		PaymentDao dao = new PaymentDao();

		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		ArrayList<Payment> paymentList = dao.selectOrderStateList(conn, start, end, type, memberId);

		/*
		 * for(Payment p : paymentList) { String pId = p.getMemberId(); String
		 * memberName = dao.selectName(conn,pId); //select member_name from member_tbl
		 * where member_id=pid p.setMemberName(memberName); }
		 */

		int totalCount = 0;
		if (!type.equals("전체")) {
			totalCount = dao.totalOrderStateCount(conn, type, memberId); // type이 전체가 아니면
		} else {
			totalCount = dao.totalOrderStateCount(conn, memberId); // 전체면
		}
		int totalPage = 0;
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		String pageNavi = "<ul class='pagination circle-style'>";
		// 이전버튼
		if (pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-each' href='/paymentList.do?reqPage=" + (pageNo - 1) + "&type=" + type + "'>";
			pageNavi += "<span class='meterial-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
			// html '' 자바 ""
		}
		// 페이지숫자
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-each active-page' href='/paymentList.do?reqPage=" + pageNo + "&type=" + type
						+ "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			} else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-each' href='/paymentList.do?reqPage=" + pageNo + "&type=" + type + "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		// 다음버튼
		if (pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-each' href='/paymentList.do?reqPage=" + (pageNo) + "&type=" + type + "'>";
			pageNavi += "<span class='meterial-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		//System.out.println(pageNavi);
		PaymentPageData ppd = new PaymentPageData(paymentList, pageNavi);
		JDBCTemplate.close(conn);
		return ppd;
	}

	public int updatePayment(int orderNo) {
		Connection conn = JDBCTemplate.getConnection();
		PaymentDao dao = new PaymentDao();
		int result = dao.updatePayment(conn, orderNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ClientPaymentReturn selectMemberIdDetail(String memberId, int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		PaymentDao dao = new PaymentDao();
		
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		int totalCount = 0;
		
		ArrayList<Payment> paymentList = new ArrayList<Payment>();
		paymentList = dao.selectMemberIdPayment(conn,start, end, memberId); //멤버 아이디 값을 줘서 결제내역을 가져오고
		
		totalCount = dao.totalOrderStateCount(conn, memberId);
		int totalPage = 0;
		
		if (totalCount % numPerPage == 0) {
			totalPage = totalCount / numPerPage;
		} else {
			totalPage = totalCount / numPerPage + 1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;

		String pageNavi = "<ul class='pagination circle-style'>";
		// 이전버튼
		if (pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-each' href='/paymentList.do?memberId=" + memberId + "&reqPage=" + (pageNo - 1)+"'>";
			pageNavi += "<span class='meterial-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
			// html '' 자바 ""
		}
		// 페이지숫자
		for (int i = 0; i < pageNaviSize; i++) {
			if (pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-each active-page' href='/paymentList.do?memberId="+ memberId + "&reqPage=" + pageNo + "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			} else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-each' href='/paymentList.do?memberId="+ memberId + "&reqPage=" + pageNo + "'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if (pageNo > totalPage) {
				break;
			}
		}
		// 다음버튼
		if (pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-each' href='/paymentList.do?memberId=" + (memberId) + "&reqPage="+ (pageNo) + "'>";
			pageNavi += "<span class='meterial-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		//System.out.println(pageNavi);
		
		//상세 결제내역에서 주문번호로 상품을 조회해야하는데..........
		for(Payment p : paymentList) {
			int orderNo = p.getOrderNo();
			ArrayList<OrderDetails> orderList = dao.selectMemberOrderDetails(conn,orderNo);
			p.setOrderList(orderList);
		}
				
		ClientPaymentReturn cPReturn = new ClientPaymentReturn(paymentList,  pageNavi);
		return cPReturn;
	}

	public int insertPayment(Payment p, String cartNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		int result4 = 0;
		int result5 = 0;
		int result6 = 0;

		int check = cartNo.indexOf("_");
		// PaymentInsert
		PaymentDao paymentDao = new PaymentDao();
		OrderDetailsDao orderDetailsDao = new OrderDetailsDao();
		Product product = null;

		if (check == -1) {
			// 장바구니(1/2/3/4/5)
			StringTokenizer sT = new StringTokenizer(cartNo, "/");
			Cart cart = null;
			int price = 0;
			int PAmount = 0;
			int sumPrice = 0;
			result1 = paymentDao.insertPayment(conn, p);
			Payment pOrderNo = paymentDao.selectMAX(conn, p);
			while (sT.hasMoreTokens()) {
				int cartNoNum = Integer.parseInt(sT.nextToken());
				cart = paymentDao.selectCartNo(conn, cartNoNum);
				product = paymentDao.selectProduct(conn, cart.getProductId());

				price = product.getProductPrice();
				PAmount = cart.getAmount();
				sumPrice += (price * PAmount);

				
				p.setOrderNo(pOrderNo.getOrderNo());
				result2 = orderDetailsDao.insertOrderDetails(conn, p, cart);
				result5 = paymentDao.paymentDeleteCart(conn, cart);

				// cartNoNum = Cart테이블에서 장바구니 정보 조회(amount, productId)
				// productId -> Product, 옵션, 가격
				// 조회한 amount(Cart)+ 옵션,가격(Product) + -> insert진행
			}
			result6 = paymentDao.upDatePayment(conn, p, sumPrice);
		} else {
			// 10_pro10
			StringTokenizer sT = new StringTokenizer(cartNo, "_");
			// 바로구매
			int amount = Integer.parseInt(sT.nextToken());
			String productId = sT.nextToken();
			// productId로 Product
			product = paymentDao.selectProduct(conn, productId);
			int price = product.getProductPrice();
			p.setPayment(price * amount);

			System.out.println(p.getPayment());

			result3 = paymentDao.insertPayment(conn, p);
			Payment pOrderNo = paymentDao.selectMAX(conn, p);
			
			p.setOrderNo(pOrderNo.getOrderNo());
			System.out.println(pOrderNo.getOrderNo());
			result4 = orderDetailsDao.insertOrderDetails(conn, p, amount, product); // 수정사항 재공유할 것

		}

		if ((result1 > 0 && result2 > 0 && result5 > 0 && result6 > 0) || (result3 > 0 && result4 > 0)) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return (result1 * result2 * result3 * result4);
	}


}
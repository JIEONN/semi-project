package trendy.review.service;

import java.sql.Connection;
import java.util.ArrayList;
import common.JDBCTemplate;
import trendy.review.dao.ReviewDao;
import trendy.review.vo.Review;
import trendy.review.vo.ReviewComment;
import trendy.review.vo.ReviewPageData;

public class ReviewService {
	//리뷰 CRUD
		public int insertReview(Review r) {
			Connection conn = JDBCTemplate.getConnection();
			ReviewDao dao = new ReviewDao();
			int result = dao.insertReview(conn,r);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}

		public ArrayList<Review> selectReviewList() {
			Connection conn = JDBCTemplate.getConnection();
			ReviewDao dao = new ReviewDao();
			ArrayList<Review> list = dao.selectReviewList(conn);
			JDBCTemplate.close(conn);
			return list;
		}

		public Review selectReview(int reviewNo) {
			Connection conn = JDBCTemplate.getConnection();
			ReviewDao dao = new ReviewDao();
			
			//조회수 늘려주는 작업
			int result = dao.updateReviewRead(conn,reviewNo);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
				JDBCTemplate.close(conn);
				return null;
			}
			
			Review r = dao.selectReview(conn, reviewNo);
			JDBCTemplate.close(conn);
			return r;
		}

		public int updateReview(Review r) {
			Connection conn = JDBCTemplate.getConnection();
			ReviewDao dao = new ReviewDao();
			int result = dao.updateReview(conn, r);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}

		public int deleteReview(int reviewNo) {
			Connection conn = JDBCTemplate.getConnection();
			ReviewDao dao = new ReviewDao();
			int result = dao.deleteReview(conn, reviewNo);
			
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}
		
	//댓글 CRUD
		public int insertReviewComment(ReviewComment rc) {
			Connection conn = JDBCTemplate.getConnection();
			ReviewDao dao = new ReviewDao();
			int result = dao.insertReviewComment(conn,rc);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;

		}

		public ArrayList<ReviewComment> selectReviewCommentList() {
			Connection conn = JDBCTemplate.getConnection();
			ReviewDao dao = new ReviewDao();
			ArrayList<ReviewComment> list = dao.selectReviewCommentList(conn);
			JDBCTemplate.close(conn);
			return list;
		}

		public ReviewComment selectReviewComment(int reviewReNo) {
			Connection conn = JDBCTemplate.getConnection();
			ReviewDao dao = new ReviewDao();
			ReviewComment rc = dao.selectReviewComment(conn, reviewReNo);
			JDBCTemplate.close(conn);
			return rc;
		}

		public int updateReviewComment(ReviewComment rc) {
			Connection conn = JDBCTemplate.getConnection();
			ReviewDao dao = new ReviewDao();
			int result = dao.updateReviewComment(conn, rc);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}

		public int deleteReviewComment(int reviewReNo) {
			Connection conn = JDBCTemplate.getConnection();
			ReviewDao dao = new ReviewDao();
			int result = dao.deleteReviewComment(conn, reviewReNo);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}

	//페이지네비
		public ReviewPageData selectAllReview(String productId, int reqPage) {
			Connection conn = JDBCTemplate.getConnection();
			ReviewDao dao = new ReviewDao();
			
			
			int numPerPage = 5;
			int end = reqPage * numPerPage;
			int start = end - numPerPage + 1;
			ArrayList<Review> list = dao.selectAllReview(conn, productId, start, end);
			int totalCount = dao.selectReviewCount(conn, productId);

			int totalPage = 0;
			if(totalCount%numPerPage == 0) {
				totalPage = totalCount/numPerPage;
			}else {
				totalPage = totalCount/numPerPage + 1;
			}
			int pageNaviSize = 5;
			
			int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
			
			String pageNavi = "<ul class='pagination style'>";
			
			if(pageNo != 1) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-each' href='/productViewPage.do?reqPage="+(pageNo-1)+"&productId="+productId+"'>";
				pageNavi += "<span class='material-icons'>arrow_back_ios</span>";
				pageNavi += "</a></li>";
			}
			for(int i=0;i<pageNaviSize;i++) {
			
				if(pageNo == reqPage) {
						pageNavi += "<li>";
						pageNavi += "<a class='page-each active-page' href='/productViewPage.do?reqPage="+(pageNo)+"&productId="+productId+"'>";
						pageNavi += pageNo;
						pageNavi += "</a></li>";
					}else {
						pageNavi += "<li>";
						pageNavi += "<a class='page-each'  href='/productViewPage.do?reqPage="+(pageNo)+"&productId="+productId+"'>";
						pageNavi += pageNo;
						pageNavi += "</a></li>";
					}
					pageNo++;
					if(pageNo > totalPage) {
						break;
					}
			}//for
			
			if(pageNo <= totalPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-each'  href='/productViewPage.do?reqPage="+(pageNo)+"&productId="+productId+"'>"; //이미 페이지번호가 위에서 +1 되어져 나옴
				pageNavi += "<span class='material-icons'>arrow_forward_ios</span>";
				pageNavi += "</a></li>";
			}
			
			pageNavi += "</ul>";
			
			ReviewPageData rpd = new ReviewPageData(list, pageNavi);
			
			JDBCTemplate.close(conn);
			return rpd;
			
			
			
			
		}

		
		
		public int selectAllReviewCount(String productId) {
			Connection conn = JDBCTemplate.getConnection();
			ReviewDao dao = new ReviewDao();
			int totalCount = dao.selectAllReviewCount(conn, productId);
			JDBCTemplate.close(conn);
			
			return totalCount;
		}
		
		
		
	/*ajax 페이지네비
		public ReviewPageData selectAllReview(String productId, int reqPage) {
			Connection conn = JDBCTemplate.getConnection();
			ReviewDao dao = new ReviewDao();
			
			
			int numPerPage = 5;
			int end = reqPage * numPerPage;
			int start = end - numPerPage + 1;
			ArrayList<Review> list = dao.selectAllReview(conn, productId, start, end);
			int totalCount = dao.selectReviewCount(conn, productId);

			int totalPage = 0;
			if(totalCount%numPerPage == 0) {
				totalPage = totalCount/numPerPage;
			}else {
				totalPage = totalCount/numPerPage + 1;
			}
			int pageNaviSize = 5;
			
			int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
			
			String pageNavi = "<ul class='pagination style'>";
			
			for(int i=0;i<pageNaviSize;i++) {
			
				if(pageNo == reqPage) {
						pageNavi += "<li>";
						pageNavi += "<a class='page-each active-page'>";
						pageNavi += pageNo;
						pageNavi += "</a></li>";
					}else {
						pageNavi += "<li>";
						pageNavi += "<a class='page-each'>";
						pageNavi += pageNo;
						pageNavi += "</a></li>";
					}
					pageNo++;
					if(pageNo > totalPage) {
						break;
					}
			}//for
			
		
			
			pageNavi += "</ul>";
			
			ReviewPageData rpd = new ReviewPageData(list, pageNavi);
			
			JDBCTemplate.close(conn);
			return rpd;	
			
		}*/
}

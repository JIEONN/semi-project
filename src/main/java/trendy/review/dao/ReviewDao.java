package trendy.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.JDBCTemplate;
import trendy.review.vo.Review;
import trendy.review.vo.ReviewComment;

public class ReviewDao {
	//후기 CRUD
		public int insertReview(Connection conn, Review r) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "insert into review_tbl values (REVIEW_SEQ.nextval,?, ?, ?, ?, ?, sysdate, 0 )";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, r.getProductId());
				pstmt.setString(2, r.getMemberId());
				pstmt.setString(3, r.getReviewContent());
				pstmt.setString(4, r.getFilename());
				pstmt.setString(5, r.getFilepath());
				result = pstmt.executeUpdate();				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}

		public ArrayList<Review> selectReviewList(Connection conn) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Review> list = new ArrayList<Review>();
			String query = "select * from review_tbl";
			try {
				pstmt = conn.prepareStatement(query);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					Review r = new Review();
					r.setReviewNo(rset.getInt("review_no"));
					r.setProductId(rset.getString("product_id"));
					r.setMemberId(rset.getString("member_id"));
					r.setReviewContent(rset.getString("review_content"));
					r.setFilename(rset.getString("filename"));
					r.setFilepath(rset.getString("filepath"));
					r.setReviewDate(rset.getDate("review_date"));
					r.setReviewRead(rset.getInt("review_read"));
					list.add(r);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
				JDBCTemplate.close(rset);
			}
			
			return list;
		}
		
		//조회수 늘리기
		public int updateReviewRead(Connection conn, int reviewNo) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query ="update review_tbl set review_read = review_read+1 where review_no=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, reviewNo);
				result = pstmt.executeUpdate();
						
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}
		
		public Review selectReview(Connection conn, int reviewNo) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Review r = null;
			String query = "select * from review_tbl where review_no=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, reviewNo);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					r = new Review();
					r.setReviewNo(rset.getInt("review_no"));
					r.setProductId(rset.getString("product_id"));
					r.setMemberId(rset.getString("member_id"));
					r.setReviewContent(rset.getString("review_content"));
					r.setFilename(rset.getString("filename"));
					r.setFilepath(rset.getString("filepath"));
					r.setReviewDate(rset.getDate("review_date"));
					r.setReviewRead(rset.getInt("review_read"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return r;
		}

		public int updateReview(Connection conn, Review r) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "update review_tbl set review_content=?, filename=?, filepath=? where review_no=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, r.getReviewContent());
				pstmt.setString(2, r.getFilename());
				pstmt.setString(3, r.getFilepath());
				pstmt.setInt(4, r.getReviewNo());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

		public int deleteReview(Connection conn, int reviewNo) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "delete from review_tbl where review_no=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, reviewNo);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

	//후기답글 CRUD
		public int insertReviewComment(Connection conn, ReviewComment rc) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "insert into review_re_tbl values (REVIEW_re_SEQ.nextval, ?, ?, sysdate, ?)";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, rc.getMemberId());
				pstmt.setInt(2, rc.getReviewNo());
				pstmt.setString(3, rc.getReviewReContent());
				result = pstmt.executeUpdate();				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}

		public ArrayList<ReviewComment> selectReviewCommentList(Connection conn) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<ReviewComment> list = new ArrayList<ReviewComment>();
			String query = "select * from review_re_tbl";
			try {
				pstmt = conn.prepareStatement(query);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					ReviewComment rc = new ReviewComment();
					rc.setReviewReNo(rset.getInt("review_re_no"));
					rc.setMemberId(rset.getString("member_id"));
					rc.setReviewNo(rset.getInt("review_no"));
					rc.setReviewReDate(rset.getDate("review_re_date"));
					rc.setReviewReContent(rset.getString("review_re_content"));
					list.add(rc);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
				JDBCTemplate.close(rset);
			}
			
			return list;
		}

		public ReviewComment selectReviewComment(Connection conn, int reviewReNo) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ReviewComment rc = null;
			String query = "select * from review_re_tbl where review_re_no=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, reviewReNo);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					rc = new ReviewComment();
					rc.setReviewReNo(rset.getInt("review_re_no"));
					rc.setMemberId(rset.getString("member_id"));
					rc.setReviewNo(rset.getInt("review_no"));
					rc.setReviewReDate(rset.getDate("review_re_date"));
					rc.setReviewReContent(rset.getString("review_re_content"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return rc;
		}

		public int updateReviewComment(Connection conn, ReviewComment rc) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "update review_re_tbl set review_re_content=? where review_re_no=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, rc.getReviewReContent());
				pstmt.setInt(2, rc.getReviewReNo());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

		public int deleteReviewComment(Connection conn, int reviewReNo) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "delete from review_re_tbl where review_re_no=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, reviewReNo);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

		public ArrayList<Review> selectAllReview(Connection conn, String productId, int start, int end) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Review> list = new ArrayList<Review>();
			String query = "select * from (select rownum as rnum, r.* from (select * from review_tbl where product_id=? order by review_no desc)r) where rnum between ? and ?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, productId);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					Review r = new Review();
					r.setReviewNo(rset.getInt("review_no"));
					r.setProductId(rset.getString("product_id"));
					r.setMemberId(rset.getString("member_id"));
					r.setReviewContent(rset.getString("review_content"));
					r.setFilename(rset.getString("filename"));
					r.setFilepath(rset.getString("filepath"));
					r.setReviewDate(rset.getDate("review_date"));
					r.setReviewRead(rset.getInt("review_read"));
					list.add(r);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
				JDBCTemplate.close(rset);
			}
			
			return list;
		}

		public int selectReviewCount(Connection conn, String productId) {
			PreparedStatement pstmt=null;
			ResultSet rset = null;
			int result=0;
			String query = "select count(*) as cnt from review_tbl where product_id=?";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, productId);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					result=rset.getInt("cnt");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}

		public int selectAllReviewCount(Connection conn, String productId) {
			PreparedStatement pstmt=null;
			ResultSet rset = null;
			int result=0;
			String query = "select count(*) as cnt from (select * from review_tbl where product_id=?)";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, productId);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					result=rset.getInt("cnt");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return result;
			
			
			
			
		}

		


}

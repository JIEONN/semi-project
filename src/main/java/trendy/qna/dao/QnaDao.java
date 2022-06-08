package trendy.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import common.JDBCTemplate;
import trendy.qna.vo.Qna;
import trendy.qna.vo.QnaComment;
import trendy.qna.vo.QnaReply;

public class QnaDao {

	public ArrayList<Qna> selectAllQna(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Qna> list = new ArrayList<Qna>();
		String query = "select * from (select rownum as rnum,n.* from(select * from qna_tbl order by qna_no desc)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Qna q = new Qna();
				q.setQnaNo(rset.getInt("qna_no"));
				q.setMemberId(rset.getString("member_id"));
				q.setProductId(rset.getString("product_id"));
				q.setQnaContent(rset.getString("qna_content"));
				q.setQnaDate(rset.getDate("qna_date"));
				q.setQnaReadcount(rset.getInt("qna_readcount"));
				q.setQnaCategory(rset.getString("qna_category"));
				q.setFilepath(rset.getString("filepath"));
				q.setReQna(rset.getInt("reqna"));
				list.add(q);
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

	public Qna selectOneQna(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Qna q = new Qna();
		String query = "select * from qna_tbl where qna_no = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				q.setQnaNo(rset.getInt("qna_no"));
				q.setMemberId(rset.getString("member_id"));
				q.setProductId(rset.getString("product_id"));
				q.setQnaContent(rset.getString("qna_content"));
				q.setQnaDate(rset.getDate("qna_date"));
				q.setQnaReadcount(rset.getInt("qna_readcount"));
				q.setQnaCategory(rset.getString("qna_category"));
				q.setFilepath(rset.getString("filepath"));
				q.setReQna(rset.getInt("reqna"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return q;
	}

	public int insertQna(Connection conn, Qna q) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into qna_tbl values(QNA_SEQ.NEXTVAL, ?, ?, ?, TO_DATE(SYSDATE,'YY-MM-DD'), ?, ?, ?, 0)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, q.getMemberId());
			pstmt.setString(2, q.getProductId());
			pstmt.setString(3, q.getQnaContent());
			pstmt.setInt(4, q.getQnaReadcount());
			pstmt.setString(5, q.getQnaCategory());
			pstmt.setString(6, q.getFilepath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteQna(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from qna_tbl where memberid = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<QnaComment> selectAllQnaComment(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QnaComment> list = new ArrayList<QnaComment>();
		String query = "select * from qna_comment_tbl";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				QnaComment qc = new QnaComment();
				qc.setQnaCommentNo(rset.getInt("qna_comment_no"));
				qc.setQnaNo(rset.getInt("qna_no"));
				qc.setMemberId(rset.getString("member_id"));
				qc.setQnaCommentDate(rset.getDate("qna_comment_date"));
				qc.setQnaCommentContent(rset.getString("qna_comment_content"));
				list.add(qc);
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

	public QnaComment selectOneQnaComment(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		QnaComment qc = new QnaComment();
		String query = "select * from qna_comment_tbl where member_id = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				qc.setQnaCommentNo(rset.getInt("qna_comment_no"));
				qc.setQnaNo(rset.getInt("qna_no"));
				qc.setMemberId(rset.getString("member_id"));
				qc.setQnaCommentDate(rset.getDate("qna_comment_date"));
				qc.setQnaCommentContent(rset.getString("qna_comment_content"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return qc;
	}

	public int insertQnaComment(Connection conn, QnaComment qc) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into qna_comment_tbl values(QNA_COMMENT_SEQ.NEXTVAL, ?, ?, TO_DATE(SYSDATE,'YY-MM-DD'), ?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qc.getQnaNo());
			pstmt.setString(2, qc.getMemberId());
			//pstmt.setString(2, qc.getMemberId());
			pstmt.setString(3, qc.getQnaCommentContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteQnaComment(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from qna_comment_tbl where memberid = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int totalQnaCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result=0;
		String query = "select count(*) as cnt from qna_tbl"; //cnt로 qna의 총 갯수를 가져옴
		try {
			pstmt=conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<QnaComment> selectQnaComment(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		ArrayList<QnaComment> list = new ArrayList<QnaComment>();
		String query = "select * from qna_comment_tbl where qna_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				QnaComment qc = new QnaComment();
				qc.setQnaNo(rset.getInt("qna_no"));
				qc.setMemberId(rset.getString("member_id"));
				qc.setQnaCommentContent(rset.getString("qna_comment_content"));
				qc.setQnaCommentDate(rset.getDate("qna_comment_date"));
				qc.setQnaCommentNo(rset.getInt("qna_comment_no"));
				list.add(qc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int insertReQna(Connection conn, QnaReply qr) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into qna_reply_tbl values (?,?,'outcoa36',TO_DATE(SYSDATE,'YY-MM-DD'),?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qr.getQnaNo());
			pstmt.setString(2,qr.getMemberId());
			//pstmt.setString(3,qr.getProductId());
			pstmt.setString(3, qr.getQnaReplyContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public QnaReply selectOneQR(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		QnaReply qr = new QnaReply();
		String query = "select * from qna_reply_tbl where qna_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				qr.setQnaNo(rset.getInt("qna_no"));
				qr.setMemberId(rset.getString("member_id"));
				qr.setProductId(rset.getString("product_id"));
				qr.setQnaReplyDate(rset.getDate("qna_reply_date"));
				qr.setQnaReplyContent(rset.getString("qna_reply_content"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return qr;
	}

	public int updateQnaReadCount(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update qna_tbl set qna_readcount = qna_readcount + 1 where qna_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}

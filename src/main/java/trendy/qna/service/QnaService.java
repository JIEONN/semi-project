package trendy.qna.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import common.JDBCTemplate;
import trendy.qna.dao.QnaDao;
import trendy.qna.vo.Qna;
import trendy.qna.vo.QnaComment;
import trendy.qna.vo.QnaPageData;
import trendy.qna.vo.QnaReply;
import trendy.qna.vo.QnaViewData;

public class QnaService {



	public QnaViewData selectOneQna(int qnaNo) {
		Connection conn = JDBCTemplate.getConnection();
		QnaDao dao = new QnaDao();
		//QNA 정보
		Qna q = dao.selectOneQna(conn, qnaNo);
		//QNA 일반댓글
		ArrayList<QnaComment> commentList = dao.selectQnaComment(conn, qnaNo);
		JDBCTemplate.close(conn);
		QnaViewData qvd = new QnaViewData(q, commentList);
		return qvd;
	}

	public int InsertQna(Qna q) {
		Connection conn = JDBCTemplate.getConnection();
		QnaDao dao = new QnaDao();
		int result = dao.insertQna(conn, q);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteQna(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		QnaDao dao = new QnaDao();
		int result = dao.deleteQna(conn, memberId);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<QnaComment> selectAllQnaComment(QnaComment q) {
		Connection conn = JDBCTemplate.getConnection();
		QnaDao dao = new QnaDao();
		ArrayList<QnaComment> list = dao.selectAllQnaComment(conn);
		return list;
	}

	public QnaComment selectOneQnaComment(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		QnaDao dao = new QnaDao();
		QnaComment qc = dao.selectOneQnaComment(conn, memberId);
		return qc;
	}

	public int InsertQnaComment(QnaComment qc) {
		Connection conn = JDBCTemplate.getConnection();
		QnaDao dao = new QnaDao();
		int result = dao.insertQnaComment(conn, qc);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteQnaComment(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		QnaDao dao = new QnaDao();
		int result = dao.deleteQnaComment(conn, memberId);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public QnaPageData selectQnaList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		QnaDao dao = new QnaDao();
		// 페이지 당 최대 게시물 수
		int numPerPage = 10;
		int end = reqPage*numPerPage;//마지막
		int start = end - numPerPage +1;//초기
		ArrayList<Qna> list = dao.selectAllQna(conn, start, end);
		//위에 end와 start를 사용하여 10개씩 qna를 가져왔습니다.
		
		for(Qna q : list) {
			int qnaNo = q.getQnaNo();
			QnaReply qr = dao.selectOneQR(conn,qnaNo);
			//여기서 답변이 있으면 qr 객체 / 없으면 null
			if(qr.getProductId() != null) {
			q.setQr(qr);
			//이렇게해주시면
			}else {
				q.setQr(null);
			}
		}
		
		
		int totalCount = dao.totalQnaCount(conn); //전체 페이지 계산을 위해 문의글의 수를 계산했습니다.
		int totalPage = 0; //전체 페이지수
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		
		int pageNaviSize = 5; //페이지 네비게이션 길이
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1; //네비게이션 시작번호
		String pageNavi = "<ul class='navi-ul'>"; //!!!클래스 스타일 추가예정!!! 
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li class='navi-ul-li'>";
			pageNavi += "<a class='navi-ul-li-a' href='/qnaList.do?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<"; //"<span class='material-icons'>navigate_before</span>"
			pageNavi += "</a></li>";
		}
		
		//페이지 숫자
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li class='navi-ul-li'>";
				pageNavi += "<a class='navi-ul-li-a active-page' href='/qnaList.do?reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li class='navi-ul-li'>";
				pageNavi += "<a class='navi-ul-li-a' href='/qnaList.do?reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		
		//다음버튼
		if(pageNo<=totalPage) {
			pageNavi += "<li class='navi-ul-li'>";
			pageNavi += "<a class='navi-ul-li-a' href='/qnaList.do?reqPage="+pageNo+"'>";
			pageNavi += ">";//"<span class='material-icons'>navigate_next</span>"
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		//System.out.println(pageNavi);
		QnaPageData qpd = new QnaPageData(list,pageNavi);
		JDBCTemplate.close(conn);
		return qpd;
		
	}

	public int insertReQna(QnaReply qr) {
		Connection conn = JDBCTemplate.getConnection();
		QnaDao dao = new QnaDao();
		int result = dao.insertReQna(conn,qr);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public QnaReply selectOneQnaRe(int qnaNo) {
		Connection conn = JDBCTemplate.getConnection();
		QnaDao dao = new QnaDao();
		QnaReply qr = dao.selectOneQR(conn,qnaNo);
		JDBCTemplate.close(conn);
		return qr;
	}

	public int updateQnaReadCount(int qnaNo) {
		Connection conn = JDBCTemplate.getConnection();
		QnaDao dao = new QnaDao();
		int result = dao.updateQnaReadCount(conn, qnaNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}



	
	
	

}

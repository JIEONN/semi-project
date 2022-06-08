package trendy.qna.vo;

import java.sql.Date;

public class QnaComment {
	private int qnaCommentNo;
	private int qnaNo;
	private String memberId;
	private Date qnaCommentDate;
	private String qnaCommentContent;
	public QnaComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaComment(int qnaCommentNo, int qnaNo, String memberId, Date qnaCommentDate, String qnaCommentContent) {
		super();
		this.qnaCommentNo = qnaCommentNo;
		this.qnaNo = qnaNo;
		this.memberId = memberId;
		this.qnaCommentDate = qnaCommentDate;
		this.qnaCommentContent = qnaCommentContent;
	}
	public int getQnaCommentNo() {
		return qnaCommentNo;
	}
	public void setQnaCommentNo(int qnaCommentNo) {
		this.qnaCommentNo = qnaCommentNo;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Date getQnaCommentDate() {
		return qnaCommentDate;
	}
	public void setQnaCommentDate(Date qnaCommentDate) {
		this.qnaCommentDate = qnaCommentDate;
	}
	public String getQnaCommentContent() {
		return qnaCommentContent;
	}
	public void setQnaCommentContent(String qnaCommentContent) {
		this.qnaCommentContent = qnaCommentContent;
	}
	
	
}

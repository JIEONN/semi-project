package trendy.qna.vo;

import java.sql.Date;

public class QnaReply {
	private int qnaNo;
	private String memberId;
	private String productId;
	private Date QnaReplyDate;
	private String QnaReplyContent;

	public QnaReply() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaReply(int qnaNo, String memberId, String productId, Date qnaReplyDate, String qnaReplyContent) {
		super();
		this.qnaNo = qnaNo;
		this.memberId = memberId;
		this.productId = productId;
		QnaReplyDate = qnaReplyDate;
		QnaReplyContent = qnaReplyContent;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Date getQnaReplyDate() {
		return QnaReplyDate;
	}
	public void setQnaReplyDate(Date qnaReplyDate) {
		QnaReplyDate = qnaReplyDate;
	}
	public String getQnaReplyContent() {
		return QnaReplyContent;
	}
	public void setQnaReplyContent(String qnaReplyContent) {
		QnaReplyContent = qnaReplyContent;
	}
	
	
}

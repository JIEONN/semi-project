package trendy.notice.vo;

import java.sql.Date;

public class NoticeComment {
	private int noticeCommentNo;
	private String memberId;
	private int noticeNo;
	private Date noticeCommentDate;
	private String noticeCommentContent;
	
	public NoticeComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NoticeComment(int noticeCommentNo, String memberId, int noticeNo, Date noticeCommentDate,
			String noticeCommentContent) {
		super();
		this.noticeCommentNo = noticeCommentNo;
		this.memberId = memberId;
		this.noticeNo = noticeNo;
		this.noticeCommentDate = noticeCommentDate;
		this.noticeCommentContent = noticeCommentContent;
	}
	public int getNoticeCommentNo() {
		return noticeCommentNo;
	}
	public void setNoticeCommentNo(int noticeCommentNo) {
		this.noticeCommentNo = noticeCommentNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public Date getNoticeCommentDate() {
		return noticeCommentDate;
	}
	public void setNoticeCommentDate(Date noticeCommentDate) {
		this.noticeCommentDate = noticeCommentDate;
	}
	public String getNoticeCommentContent() {
		return noticeCommentContent;
	}
	public void setNoticeCommentContent(String noticeCommentContent) {
		this.noticeCommentContent = noticeCommentContent;
	}
}

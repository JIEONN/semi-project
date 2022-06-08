package trendy.review.vo;

import java.sql.Date;

public class ReviewComment {
	private int reviewReNo;
	private String memberId;
	private int reviewNo;
	private Date reviewReDate;
	private String reviewReContent;
	public ReviewComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewComment(int reviewReNo, String memberId, int reviewNo, Date reviewReDate, String reviewReContent) {
		super();
		this.reviewReNo = reviewReNo;
		this.memberId = memberId;
		this.reviewNo = reviewNo;
		this.reviewReDate = reviewReDate;
		this.reviewReContent = reviewReContent;
	}
	public int getReviewReNo() {
		return reviewReNo;
	}
	public void setReviewReNo(int reviewReNo) {
		this.reviewReNo = reviewReNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public Date getReviewReDate() {
		return reviewReDate;
	}
	public void setReviewReDate(Date reviewReDate) {
		this.reviewReDate = reviewReDate;
	}
	public String getReviewReContent() {
		return reviewReContent;
	}
	
	//br 포함된 내용게터
		public String getReviewReContentBr() {
			return reviewReContent.replaceAll("\r\n", "<br>");
		}
	public void setReviewReContent(String reviewReContent) {
		this.reviewReContent = reviewReContent;
	}
	
	
}

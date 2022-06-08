package trendy.review.vo;

import java.sql.Date;

public class Review {
	private int reviewNo;
	private String productId;
	private String memberId;
	private String reviewContent;
	private String filename;
	private String filepath;
	private Date reviewDate;
	private int reviewRead;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int reviewNo, String productId, String memberId, String reviewContent, String filename,
			String filepath, Date reviewDate, int reviewRead) {
		super();
		this.reviewNo = reviewNo;
		this.productId = productId;
		this.memberId = memberId;
		this.reviewContent = reviewContent;
		this.filename = filename;
		this.filepath = filepath;
		this.reviewDate = reviewDate;
		this.reviewRead = reviewRead;
	}

	// br 포함된 내용게터
	public String getReviewContentBr() {
		return reviewContent.replaceAll("\r\n", "<br>");
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getReviewRead() {
		return reviewRead;
	}

	public void setReviewRead(int reviewRead) {
		this.reviewRead = reviewRead;
	}

}

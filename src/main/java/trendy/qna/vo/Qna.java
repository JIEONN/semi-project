package trendy.qna.vo;

import java.sql.Date;

public class Qna {
	private int qnaNo;
	private String memberId;
	private String productId;
	private String qnaContent;
	private Date qnaDate;
	private int qnaReadcount;
	private String qnaCategory;
	private String filepath;
	private int reQna;
	private QnaReply qr;
	public Qna() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Qna(int qnaNo, String memberId, String productId, String qnaContent, Date qnaDate, int qnaReadcount,
			String qnaCategory, String filepath, int reQna, QnaReply qr) {
		super();
		this.qnaNo = qnaNo;
		this.memberId = memberId;
		this.productId = productId;
		this.qnaContent = qnaContent;
		this.qnaDate = qnaDate;
		this.qnaReadcount = qnaReadcount;
		this.qnaCategory = qnaCategory;
		this.filepath = filepath;
		this.reQna = reQna;
		this.qr = qr;
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
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public Date getQnaDate() {
		return qnaDate;
	}
	public void setQnaDate(Date qnaDate) {
		this.qnaDate = qnaDate;
	}
	public int getQnaReadcount() {
		return qnaReadcount;
	}
	public void setQnaReadcount(int qnaReadcount) {
		this.qnaReadcount = qnaReadcount;
	}
	public String getQnaCategory() {
		return qnaCategory;
	}
	public void setQnaCategory(String qnaCategory) {
		this.qnaCategory = qnaCategory;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public int getReQna() {
		return reQna;
	}
	public void setReQna(int reQna) {
		this.reQna = reQna;
	}
	public QnaReply getQr() {
		return qr;
	}
	public void setQr(QnaReply qr) {
		this.qr = qr;
	}

	
	
}

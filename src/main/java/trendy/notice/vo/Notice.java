package trendy.notice.vo;

import java.sql.Date;

public class Notice {
	private int noticeNo;
	private String memberId;
	private String noticeTitle;
	private String NoticeContent;
	private int readCount;
	private Date regDate;
	private String filepath;
	
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notice(int noticeNo, String memberId, String noticeTitle, String noticeContent, int readCount, Date regDate,
			String filepath) {
		super();
		this.noticeNo = noticeNo;
		this.memberId = memberId;
		this.noticeTitle = noticeTitle;
		NoticeContent = noticeContent;
		this.readCount = readCount;
		this.regDate = regDate;
		this.filepath = filepath;
	}
	
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return NoticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		NoticeContent = noticeContent;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
}

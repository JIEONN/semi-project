package trendy.qna.vo;

import java.util.ArrayList;

public class QnaViewData {
	private Qna n;
	private ArrayList<QnaComment> commentList;
	
	public QnaViewData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QnaViewData(Qna n, ArrayList<QnaComment> commentList) {
		super();
		this.n = n;
		this.commentList = commentList;
	}

	public Qna getN() {
		return n;
	}

	public void setN(Qna n) {
		this.n = n;
	}

	public ArrayList<QnaComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList<QnaComment> commentList) {
		this.commentList = commentList;
	}
	
	
}
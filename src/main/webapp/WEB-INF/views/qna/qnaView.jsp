<%@page import="trendy.qna.vo.QnaComment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="trendy.qna.vo.Qna"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Qna q = (Qna) request.getAttribute("q");
ArrayList<QnaComment> commentList = (ArrayList<QnaComment>) request.getAttribute("commentList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>




.last-tr {
	border-bottom: 1px solid black;
	border-top: 1px solid black;
	
}
.last-tr th{
		border-right: 1px solid black;
	}
#qnaView tr {
	border-bottom: 1px solid black;
	border-top: 1px solid black;
	
}
#qnaView {
	width: 60%;
	text-align: center;
	margin-left: auto;
	margin-right: auto;
	table-layout: fixed;
	border-collapse: collapse;
}

#qnaContent {
	height: 400px;
	text-align: left;
}

#qnaContent2 {
	width: 100%;
	height: 100%;
	overflow: scroll;
}

#qnaContent2::-webkit-scrollbar {
	display: none; /* Chrome, Safari, Opera*/
}

.page-title {
	text-align: center;
	font-size: 30px;
	padding-top: 15px;
	padding-bottom: 20px;
}

.inputCommentBox {
	width: 100%;
	margin: 50px;
}

.inputCommentBox>form>ul {
	list-style-type: none;
	display: flex;
	justify-content: center;
	align-items: center;
	padding: 0 0;
}

.commentBox{
	
	width:800px;
	margin: 0 auto;
}

.commentBox>ul{
	list-style-type: none;
	display: flex;
	justify-content: left;
	align-items: center;
	padding: 0 0;
	text-align: center;
}

.inputCommentBox>form>ul>li {
	text-align: center;
}
.commentBox>ul>li{
	text-align: center;
}
.input-form {
	resize: none;
	width: 600px;
	height: 50px;
}

.inputCommentBox>form>ul>li>span {
	font-size: 50px;
	padding: 10px;
}

.commentBox>ul>li>span {
	font-size: 20px;
	padding: 10px;
}

.btn2 {
	border: 1px solid rgba(255, 165, 0, 0.5);
	background-color: white;
	color: rgba(255, 165, 0, 0.5);
	padding: 5px;
	border-radius: 5px;
	margin-left: 10px;
	width: 100px;
	height: 50px;
}

.btn2:hover {
	color: white;
	background-color: rgba(255, 165, 0, 0.5);
}

#comid{
	font-size:18px;
}

#comdate{
	font-size:12px;
}
#comcon{
	font-size:16px;
}

</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="page-content">
		<div class="page-title">문의사항</div>
		<table class="" id="qnaView" style="table-layout: fixed">
			<tr>
				<th>Q&A</th>
				<td colspan="3"><%=q.getQnaCategory()%></td>
			</tr>
			<tr>
				<th>POST BY</th>
				<td><%=q.getMemberId()%></td>
				<th>READING</th>
				<td><%=q.getQnaReadcount()%></td>
			</tr>
			<tr>
				<th>DATE</th>
				<td colspan="3"><%=q.getQnaDate()%></td>
			</tr>
			<tr class="last-tr">
				<th>CONTENT</th>
				<td colspan="3" id="qnaContent">
					<div id="qnaContent2"><%=q.getQnaContent()%></div>
				</td>
			</tr>
		</table>
	</div>
	<div class="inputCommentBox">
		<form action="/insertComment.do" method="post">
			<ul>
				<li><span class="material-icons">face</span></li>
				<li><input type="hidden" name="qcWriter" value="<%=m.getMemberId()%>"><%//수정예정 %>
				<input type="hidden" name="qnaRef" value="<%=q.getQnaNo()%>">
				<textarea class="input-form" name="qcContent"></textarea></li>
				<li>
					<button type="submit" class="btn2">SUBMIT</button>
				</li>
			</ul>
		</form>
	</div>
	<div class="commentBox">
		<%for(QnaComment qc : commentList) {%>
		<ul class="qna-comment">
			<li>
				<span class="material-icons" style="border-right: 1px solid black;">account_circle</span>
			</li>
			<li>				
				<span id="comid"><%=qc.getMemberId() %>'s</span>
			</li>			
			<li>	
				<span id="comcon"><%=qc.getQnaCommentContent() %></span>
			</li>
			<li>
				<span id="comdate"><%=qc.getQnaCommentDate() %></span>
			</li>
		</ul>
		<%} %>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
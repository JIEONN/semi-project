
<%@page import="java.util.ArrayList"%>
<%@page import="trendy.qna.vo.Qna"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<Qna> list = (ArrayList<Qna>) request.getAttribute("list");
	String pageNavi = (String) request.getAttribute("pageNavi");
	String qnaWriter = (String)request.getAttribute("qnaWriter");
	int qnaRef = (Integer)request.getAttribute("qnaRef");
	String qnaProductId = (String)request.getAttribute("qnaProductId");
	String qnaReContent = (String)request.getAttribute("qnaReContent");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.active-page {
	background-color: orange;
	color: black;
}

*{
	color:#333333;
}

.w-btn {
	position: relative;
	border: none;
	display: inline-block;
	padding: 15px 30px;
	border-radius: 15px;
	font-family: "paybooc-Light", sans-serif;
	box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
	text-decoration: none;
	font-weight: 600;
	transition: 0.25s;
}

.w-btn-indigo {
	background-color: orange;
	color: white;
	float: right;
	margin-bottom: 20px;
}
.w-btn-indigo:hover{
	background-color: white;
	color: orange;
	transition:0.5s;
}
.tbl{
	margin-bottom: 20px;
}

.qna-content{
	margin-bottom: 20px;
}
#pageNavi{
	margin-bottom: 20px;
}


.navi-ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	text-align: center;
}

.navi-ul-li {
	display: inline-block;
	margin: 5px;
}

.navi-ul-li-a {
	display: block;
	color: black;
	text-align: center;
	width:30px;
	height:30px;
	text-decoration: none;
	border-radius: 50px;
}

.navi-ul-li-a:hover:not(.active) {
	
	background-color: #FFA500;
	color: black;
}



.material-icons {
	padding-bottom: 14px;
	padding-top: 14px;
}
.qna-tbl-title>h1{
	color:#333333;
	font-size: 50px;
}

a{
	text-decoration: none;
	font-weight: bold;
}

a:hover {
	color:orange;
	transition: 0.1s;
}
.material-icons{
	padding-bottom: 0px;
	padding-top: 0px;
}
#tr-title{
	border: 1px solid white;
	background: orange;
	
}

#tr-title th{
	color:white;
	
}

.reqna-btn{
	background-color: orange;
	color: white;
	border: 1px solid white;
	border-radius:5px;
}

.reqna-btn:hover{
	background-color:white;
	color: orange;
	border: 1px solid orange;
	transition:0.5s;
}
.tbl{
	margin-bottom: 20px;
}

.qna-content{
	margin-bottom: 20px;
}
#pageNavi{
	margin-bottom: 20px;
}


</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="qna-content">
		<div class="qna-tbl-title"><h1>QnA</h1></div>
	  
		<%if (m != null && m.getAdmin() == 1) { %>

		<a class="writeBtn w-btn w-btn-indigo" href="/qnaWriteFrm.do">문의하기</a>
		<%} %>
		<table class="tbl tbl-hover" style="text-align: center">
			<tr id="tr-title">
				<th>NO</th>
				<th>TITLE</th>
				<th>MEMBER</th>
				<th>DATE</th>
				<th>VIEWS</th>
			</tr>
			<%
			for (Qna q : list) {
			%>
			<tr class="tr-content">
				<td><%=q.getQnaNo()%></td>
				<td><a href="/qnaView.do?qnaNo=<%=q.getQnaNo()%>"> <%=q.getQnaCategory()%>
				</a></td>
				<td><%=q.getMemberId()%></td>
				<td><%=q.getQnaDate()%></td>
				<td><%=q.getQnaReadcount()%>
					<%if (q.getQr() == null && m.getAdmin() == 0) {//관리자if문추가예정%>
					<form action="/insertReQna.do" class="reqna-form">
						<input type="hidden" name="qnaWriter" value="<%=q.getMemberId()%>">
						<input type="hidden" name="qnaRef" value="<%=q.getQnaNo()%>">
						<input type="hidden" name="qnaProductId" value="<%=q.getProductId()%>">
						<button type="submit" class="reqna-btn">답글달기</button>
					</form>
					<%} %>
				</td>
			</tr>
			<%if(q.getQr() != null) {%>
				<%//q.setReQna(1); %>
				<tr>
					<td colspan=4 style="text-align: center;"><a href="/qnaReView.do?qnaNo=<%=q.getQr().getQnaNo()%>"><span class="material-icons">subdirectory_arrow_right</span><%=q.getQr().getMemberId()%>님의 문의내역 답변입니다.</a><td>
				</tr>
			<%} %>
			<%
			}
			%>
		</table>
		<div id="pageNavi"><%=pageNavi%></div>
	</div>

	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
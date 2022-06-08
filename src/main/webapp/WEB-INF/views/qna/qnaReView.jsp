<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String memberId = (String) request.getAttribute("memberId");
	int qnaNo = (Integer) request.getAttribute("qnaNo");
	String productId = (String) request.getAttribute("productId");
	Date qnaReDate = (Date) request.getAttribute("qnaReDate");
	String qnaReContent = (String) request.getAttribute("qnaReContent");
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
		width: 40%;
		text-align: center;
		margin-left: auto;
		margin-right: auto;
		table-layout: fixed;
		border-collapse: collapse;
		margin-bottom: 20px;
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
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="page-content">
		<div class="page-title">문의사항</div>
		<table class="" id="qnaView" style="table-layout: fixed">
			<tr>
				<th colspan="2"><%=memberId%>님의 문의 답변입니다.</th>
			</tr>
			<tr>
				<th>DATE</th>
				<td><%=qnaReDate%></td>
			</tr>
			<tr class="last-tr">
				<th>CONTENT</th>
				<td id="qnaContent">
					<div id="qnaContent2"><%=qnaReContent%></div>
				</td>
			</tr>
		</table>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
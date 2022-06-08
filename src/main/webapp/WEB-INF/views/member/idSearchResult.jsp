<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
   		Member member = (Member)request.getAttribute("member");
   	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.page-content{
	text-align:center;
}
a{
	display: block;
	margin: 30px auto;	
	text-decoration: none;
	transition: 0.5s;
	color : #333333;
}
a:hover{
	color:orange
}
</style>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<h1><%=member.getMemberName() %>님의 아이디는 <%=member.getMemberId() %>입니다.</h1>
		<a href="/pwSearchFrm.do">비밀번호 찾기 페이지로</a>
	</div>	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
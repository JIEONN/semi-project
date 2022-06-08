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
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<h1><%=member.getMemberName() %>님의 임시 비밀번호는 <%=member.getMemberPw() %>입니다.<br>회원정보에서 수정해주세요</h1>
	</div>	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
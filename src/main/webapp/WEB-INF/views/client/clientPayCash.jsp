<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="page-content">
		<h1><%=m.getMemberName() %>님의 주문이 완료되었습니다.<br> 농협 (주)트랜디 341293759385로 7일이내로 입금해주세요.</h1>
	</div>	
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
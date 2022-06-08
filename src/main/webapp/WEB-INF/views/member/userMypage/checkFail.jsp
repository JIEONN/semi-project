<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증 실패</title>
<style>
.fail-content{
	text-align: center;
	item-align: center;
}
button{
  background-color: rgba(57, 62, 70, 0.9);
  color: #fff;
  border: 2px solid rgba(57, 62, 70, 0.9);
  margin-top: 10px;
  margin-bottom: 100px;
  width:15%;
  height:40px;
}
button:hover {
  background-color: rgba(57, 62, 70, 1);
  cursor: pointer;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
		<div class="fail-content">
			<p>비밀번호가 일치하지않습니다.</p>
			<button  type="button" class="mypage-btn" onClick="location.href='/userMypage.do'">마이페이지로 돌아가기</button>
		</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 본인인증</title>
</head>
<style>
.userCheck{
	text-align: center;
	item-align: center;
}
.submit-btn>button{
  background-color: orange;
  color: white;
  border: white;
  margin-top: 10px;
  margin-bottom: 100px;
  width:15%;
  height:40px;
  border-radius: 10px;
}
.submit-btn>button:hover {
  background-color: white;
  border:1px solid orange;
  color: orange;
  cursor: pointer;
}
</style>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="userCheck">
		<form action="userCheck.do" method="post">
			<h2>회원 본인인증 페이지</h2>
			<p>계정의 비밀번호를 입력해주세요</p>
			<label for="memberPw">비밀번호 입력</label>
			<input type="password" name="memberPw" id="memberPw" class="input-form" autocomplete="off">
			<input type="hidden" name="memberId" id="memberId" class="input-form" value="<%=m.getMemberId() %>">
			<div class="submit-btn">
				<button type="submit">확인</button>
			</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
<script>
	
</script>
</html>
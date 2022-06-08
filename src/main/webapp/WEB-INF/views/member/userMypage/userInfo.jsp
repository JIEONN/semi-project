<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
    	Member member = (Member)request.getAttribute("member");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<style>
label {
	margin: 30px 100px;
	margin-left:30px;
	font-size : 1.3em;
	display: inline-block;
	width: 30%;
}
span{
	font-size: 1.3em;
	width: 30%
}
.update-btn{
	/*margin: 30px auto;*/
	
	background-color: orange;
 	color: white;
  	border: 1px solid white;
  	display: inline-block;
  	width: 300px;
  	font-size: 1.2em;
  	height: 50px;
  	border-radius: 10px;
}
.update-btn:hover{
	background-color: white;
	border:1px solid orange;
	color:orange;
  	cursor: pointer;
  	transition:0.5s;
}
.info-wrap{
	width: 1200px;
	
}
.info-footer{
	text-align:center;
	width:900px;
	
}
.page-content{
	width:900px;
	margin: 0 auto;
}
.info-content{
	border-bottom: 1px solid #333333;
	width: 600px;
	margin-left:160px;
}
.info-content2 {
	width: 600px;
	margin-left:160px;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<h1>MY PAGE</h1>
		<br>
		<div class="info-wrap">
			<div class="info-content">
				<label for="Id">아이디</label><span id="Id" name="memberId"><%=member.getMemberId() %></span>
			</div>
			<div class="info-content">
				<label for="Pw">비밀번호</label><span id="Pw" name="memberPw">************</span>
			</div>
			<div class="info-content">
				<label for="memberName">이름</label><span id="memberName" name="memberName"><%=member.getMemberName() %></span>
			</div>
			<div class="info-content">
				<label for="address">주소</label><span id="address" name="address"><%=member.getAddress() %></span>
			</div>
			<div class="info-content">
				<label for="phone">전화번호</label><span id="phone" name="phone"><%=member.getPhone() %></span>
			</div>
			<div class="info-content">
				<label for="email">이메일</label><span id="eamil" name="email"><%=member.getEmail() %></span>
			</div>
			<div class="info-content2">
				<label for="point">point</label><span id="point" name="point"><%=member.getPoint() %>P</span>
			</div>
			<br>
			<div class="info-footer">
				<button type="button" class="update-btn" onClick="location.href='/userInfoUpdateFrm.do'">정보수정페이지로 이동하기</button>
			</div>
			<br>
		</div>	
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<style>
.userInfo{
	text-align:center;
	margin: 50px auto;
}
.userInfo>p{
	font-size:1.3em;
}
.mypage-content{
	margin: 10px auto;
	text-align: center;
}
.mypage-btn{
  background-color: orange;
  color: #fff;
  border: 1px solid white;
  width:15%;
  height:80px;
  font-size:1.2em;
  margin: 50px 70px;
  border-radius: 25px;
}
.mypage-btn:hover{
	background-color: white;
	color : orange;
	border: 1px solid orange;
  	cursor: pointer;
}
</style>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<h1><%=m.getMemberName() %>님 마이페이지</h1>
		<div class="userInfo">
			<p><%=m.getMemberName() %>님은 현재 '<%=m.getGrade() %>' 등급이며, 누적 포인트는 <%=m.getPoint() %>p 입니다.</p>
		</div>
		<div class="mypage-content">
			<button type="button" class="mypage-btn" onClick="location.href='/clientPayment.do?memberId=<%=m.getMemberId() %>&reqPage=1'">주문 - 주문내역 조회</button>
			<button type="button" class="mypage-btn" onClick="location.href='/userCheckFrm.do'">회원 정보</button><br>
			<button type="button" class="mypage-btn" onClick="location.href='/qnaList.do?reqPage=1'">문의게시판</button>
			<button type="button" class="mypage-btn cart-click" onclick="cart('<%=m.getMemberId()%>');">장바구니</button>
		</div>
	</div>	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>

<script>
	function cart(memberId){
    	location.href="/myCart.do?memberIdCart="+memberId;
	}
</script>
</html>
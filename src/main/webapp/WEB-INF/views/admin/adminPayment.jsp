<%@page import="java.text.DecimalFormat"%>
<%@page import="trendy.payment.vo.Payment"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
ArrayList<Payment> paymentList = (ArrayList<Payment>) request.getAttribute("paymentList");
String pageNavi = (String) request.getAttribute("pageNavi");
String reqPage = (String) request.getParameter("reqPage");
String type = (String) request.getParameter("type");
DecimalFormat formatter = new DecimalFormat("###,###");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#orderIcons {
	text-decoration: none;
}

.btn>a {
	text-decoration: none;
	color: black;
}

.tbl th, .tbl td {
	padding: 1rem;
	text-align: center;
}

.tbl .tr-1 {
	background-color: orange;
	border: 1px solid white;
	color: white;
}
.tr-1 th{
	color: white !important;
}

.page-title {
	text-align: center;
}

.tbl {
	margin: auto;
	margin-top: 15px;
	padding-top: 50px;
}

.btn {
	border: none;
	padding: 1rem;
	display: inline-block;
	box-sizing: border-box;
	font-size: 16px;
	background: #e0e0e0;
	cursor: pointer;
	text-decoration: none;
	color: black;
}

.left-content {
	width: 20%;
	float: left;
	margin: auto;
	text-align: center;
	/*background: yellow;*/
}

.right-content {
	width: 80%;
	float: left;
	/*background: blue;*/
}

.btn-wrap, .tbl {
	width: 100%;
	margin-left: 30px;
}

#btn1 {
	margin-top: 50px;
	padding: 1rem;
	display: inline-block;
	box-sizing: border-box;
	transition-duration: 0.5s;
	font-size: 16px;
	font-family: ns-light;
	border: 2px solid rgba(238, 238, 238, 0.9);
	text-decoration: none;
	background-color: orange;
	border: 1px solid white;
	color: white;
	border-radius: 5px;
}

#btn1:hover {
	background-color: white;
	border: 1px solid orange;
	color: orange;
}

#submit {
	padding: 0;
	border: none;
	background: none;
	cursor: pointer;
}

#orderSerachinput {
	margin-top: 10px;
	width: 200px;
	height: 30px;
	margin-left: 30px;
}

li>a {
	text-decoration: none;
}

.menu {
	margin-top: 50px;
	list-style-type: none;
	padding: 0px;
	background-color: rgba(238, 238, 238, 0.9);
}
.menu-btn{
	background-color: orange;
	border: 1px solid white;
	border-radius: 5px;
	transition: 0.5s;
}
.menu-btn>a{
	display: block;
	width: 100%;
	color:white;
}
.menu-btn:hover{
	background-color: white;
	border: 1px solid orange;
}
.menu-btn:hover>a {
	color: orange;
}
tr:hover{
	transition:0.5s;
	background-color: rgba(0, 0, 0, 0.05);
}
.tr>td>a{
	color : #333333;
	text-decoration: none;
}
.tr>td>a:hover{
	color : orange;
	text-decoration: none;
	transition: 0.5s;
}
	.pagination{
		list-style-type:none;
		padding: 0;
		overflow: hidden;
		text-align: center;
		display: flex;
		justify-content: center;
	}
	.page-each{
		border-radius: 50px;
	}
	.pagination .page-each{
		text-decoration:none;
		display: block;
		height: 30px;
		line-height: 30px;
		width: 30px;
		color: black;
		margin: 70px auto;
	}
	.pagination>li:hover{
		font-size: 1.2em;
	}
	.active-page{
		background-color: orange;
		color: white;
	}

</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="page-content">
		<div class="page-title">
			<h3>주문관리</h3>
		</div>
		<div class="left-content">
			<ul class="menu">
				<li class="menu-btn"><a  href="/searchProduct.do?reqPage=1&mainCategory=all&searchBox=">상품관리</a></li>
				<li class="menu-btn"><a href="/paymentList.do?reqPage=1&type=전체">주문관리</a></li>
			</ul>
		</div>
		<div class="right-content">

			<div class="btn-wrap">
				<!--<button class="btn allSelete">전체조회</button>  처음 오답 -->
				<a id="btn1" href="/paymentList.do?reqPage=1&type=전체">전체조회</a> <a
					id="btn1" href="/paymentList.do?reqPage=1&type=주문접수">주문접수</a>  
					<a id="btn1" href="/paymentList.do?reqPage=1&type=배송준비중">배송준비중</a> <a
					id="btn1" href="/paymentList.do?reqPage=1&type=취소/교환/환불">취소/교환/환불</a>
				<a id="btn1" href="/paymentList.do?reqPage=1&type=배송완료">배송완료</a>
			</div>
			<div class="orderSearch">
				<form action="/paymentList.do" method="get">
					<input type="hidden" name="reqPage" value="1"> <input
						type="hidden" name="type" value=<%=type%>> <input
						type="text" name="orderSearchinput" id="orderSerachinput"
						placeholder="아이디검색">
					<button type="submit" id="submit">
						<span class="material-icons" id="orderIcons">search</span>
					</button>
				</form>
			</div>
			<table class="tbl">
				<tr class="tr-1">
					<th width="60px">주문번호</th>
					<th width="80px">주문일</th>
					<th width="70px">아이디</th>
					<th width="70px">주문자명</th>
					<th width="70px">결제금액</th>
					<th width="70px">결제방법</th>
					<th width="70px">주문상태</th>
				</tr>
				<%
				for (Payment payment : paymentList) {
				%>
				<tr class="tr">
					<td><a
						href="/orderDetails.do?orderNo=<%=payment.getOrderNo()%>"><%=payment.getOrderNo()%></a></td>
					<td><%=payment.getPayDate()%></td>
					<td><%=payment.getMemberId()%></td>
					<td><%=payment.getMemberName()%></td>
					<td><%=formatter.format(payment.getPayment())%></td>
					<td><%=payment.getPayMethod()%></td>
					<td><%=payment.getOrderState()%></td>
				</tr>
				<%
				}
				%>
			</table>
			<%=pageNavi%>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
	<script>
			$("#submit").on("click",function(){
				if($("#orderSerachinput").val().length == 0){
					alert('아이디를 입력해주세요');
					return false;
				}
			});
	</script>
</body>
</html>
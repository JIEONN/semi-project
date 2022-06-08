<%@page import="java.text.DecimalFormat"%>
<%@page import="trendy.orderDetails.vo.OrderDetailasReturn"%>
<%@page import="java.util.ArrayList"%>
<%@page import="trendy.orderDetails.vo.OrderDetails"%>
<%@page import="trendy.product.vo.Product"%>
<%@page import="trendy.payment.vo.Payment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<OrderDetails> orderList = (ArrayList<OrderDetails>)request.getAttribute("orderList");
	Payment payment = (Payment)request.getAttribute("payment");
	DecimalFormat formatter = new DecimalFormat("###,###");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/jquery-3.6.0.js"></script>
<style>

	
	.tbl th, .tbl td {
		padding: 1rem;
	}
	
	th,td{
		border-bottom: 1px solid black;
	}
	
	
	th{
		padding: 20px;
	}
	
	.productOp{
		text-align: center;
	}
	
	.price{
		border: none;
		text-align: right;
		font-weight: bold;
	}
	
	table{ 
	 	border-spacing:0px;
		width:100%;
	}
	
	.sub-title{
		margin-left: 20px;
		padding-top:50px;
	}
	
	.title{
		margin: 0px;
		padding-top: 10px;
	}
	
	.td1{
	}
	
	.submitBtn{
		padding: 100px;
		text-align: center;
	}
	

	
	#delete{
		width: 180px;
		margin-left: 40px;
  		padding: 1rem;
  		display: inline-block;
  		box-sizing: border-box;
 		transition-duration: 0.5s;
  		font-size: 16px;
 	 	font-family: ns-light;
  		background-color: lightcoral;
  		border-radius: 10px;
  		color: white;
  		border: 1px solid lightcoral;
		text-decoration: none;	
		transition: 0.5s;
	}
	#close{
		width: 180px;
		margin-left: 40px;
		border: none;
  		padding: 1rem;
  		display: inline-block;
  		box-sizing: border-box;
 		transition-duration: 0.5s;
  		font-size: 16px;
 	 	font-family: ns-light;
  		background-color: orange;
  		border-radius: 10px;
  		color: white;
  		border: 1px solid orange;
		text-decoration: none;
		transition: 0.5s;
	}
	
	#delete:hover{
		background-color: white;
		color: lightcoral;
  		border: 1px solid lightcoral;
	}
	#close:hover{
		background-color: white;
		color: orange;
  		border: 1px solid orange;
	}
.product-img{
	text-align:center;
}
	
</style>
</head>
<body>

	<div class="wrap">
		<div class="page-title">
			<h2 class="title">주문상세정보</h2>
			<h4 class="title">주문번호 : <%=payment.getOrderNo() %></h4>
		</div>
		<div class="page-content">
			<table class="tbl product">
			<h4 class="sub-title">상품정보</h4>
			<th class="productOp">이미지</th>
			<th class="productOp" width=300px>옵션</th>
			<th class="productOp" width=100px>수량</th>
			<th class="productOp" width=100px>적립포인트</th>
			<th class="productOp" width=100px>가격</th>
			<th class="productOp" width=100px>주문상태</th>
			<%for(OrderDetails order : orderList){ %>
			<tr>
				<td class="product-img" width=200px><img src="/upload/product/<%=order.getProductFilepath()%>" id="img" width="150px"></td>
				<td class="productOp"><%=order.getProductName()%><br> 
				size : <%=order.getProductSize() %><br>
				color : <%=order.getProductColor() %> </td>
				<td class="productOp"><%=order.getCount() %></td>
				<td class="productOp"><%=payment.getPointAdd() %></td>
				<td class="productOp"><%=formatter.format(order.getProductPrice()) %></td>
				<td class="productOp"><%=payment.getOrderState() %></td>
			</tr>
			<tr>
			<%} %>
				<td colspan=6 class=price>상품 구매 금액 = <%=formatter.format(payment.getPayment())%>원</td>
			</tr>
			</table>
		</div>
		<div class="page-content">
			<table class="tbl product">
			<h4 class="sub-title">결제정보</h4>
			<tr>
				<td class="td1" width="80px">결제방법</td>
				<td width="500px"><%=payment.getPayMethod()%></td>
			</tr>
			<tr>
				<td class="td1">주문상태</td>
				<td><%=payment.getOrderState()%></td>
			</tr>
			<tr>
				<td class="td1">주문일</td>
				<td><%=payment.getPayDate()%></td>
			</tr>
			<tr>
				<td class="td1">포인트 사용금액</td>
				<td><%=formatter.format(payment.getUsePoint())%></td>
			</tr>
			<tr>
				<td class="td1">결제금액</td>
				<td><%=formatter.format(payment.getPayment())%></td>
			</tr>
			</table>
		</div>
		<div class="page-content">
			<h4 class="sub-title">배송정보</h4>
			<table class="tbl product">
			<tr>
				<td class="td1" width="80px">배송 받는 사람</td>
				<td width="500px"><%=payment.getReceiverName()%></td>
			</tr>
			<tr>
				<td class="td1">연락처</td>
				<td><%=payment.getReceiverPhone()%></td>
			</tr>
			<tr>
				<td class="td1">주소</td>
				<td><%=payment.getReceiverAddress()%></td>
			</tr>
			<tr>
				<td class="td1">요청사항</td>
				<td><%=payment.getReceiverRequest()%></td>
			</tr>
			</table>
		</div>
	</div>
	<div class="submitBtn">
		<a id="delete" href="#">주문취소</a> <a id="close" href="/paymentList.do?reqPage=1&type=전체">닫기</a>
	</div>

	<script>
		$("#delete").on("click",function(orderNo){
			if(prompt('주문을 취소하시겠습니까? 취소사유를 입력해주세요.')) {
				alert("주문이 취소되었습니다.")
				location.href="/paymentCencel.do?orderNo="+<%=payment.getOrderNo()%>;					
			}else {
				console.log(222);
			}
		});
	</script>
</body>
</html>
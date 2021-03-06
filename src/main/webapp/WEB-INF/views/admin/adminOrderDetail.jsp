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
			<h2 class="title">??????????????????</h2>
			<h4 class="title">???????????? : <%=payment.getOrderNo() %></h4>
		</div>
		<div class="page-content">
			<table class="tbl product">
			<h4 class="sub-title">????????????</h4>
			<th class="productOp">?????????</th>
			<th class="productOp" width=300px>??????</th>
			<th class="productOp" width=100px>??????</th>
			<th class="productOp" width=100px>???????????????</th>
			<th class="productOp" width=100px>??????</th>
			<th class="productOp" width=100px>????????????</th>
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
				<td colspan=6 class=price>?????? ?????? ?????? = <%=formatter.format(payment.getPayment())%>???</td>
			</tr>
			</table>
		</div>
		<div class="page-content">
			<table class="tbl product">
			<h4 class="sub-title">????????????</h4>
			<tr>
				<td class="td1" width="80px">????????????</td>
				<td width="500px"><%=payment.getPayMethod()%></td>
			</tr>
			<tr>
				<td class="td1">????????????</td>
				<td><%=payment.getOrderState()%></td>
			</tr>
			<tr>
				<td class="td1">?????????</td>
				<td><%=payment.getPayDate()%></td>
			</tr>
			<tr>
				<td class="td1">????????? ????????????</td>
				<td><%=formatter.format(payment.getUsePoint())%></td>
			</tr>
			<tr>
				<td class="td1">????????????</td>
				<td><%=formatter.format(payment.getPayment())%></td>
			</tr>
			</table>
		</div>
		<div class="page-content">
			<h4 class="sub-title">????????????</h4>
			<table class="tbl product">
			<tr>
				<td class="td1" width="80px">?????? ?????? ??????</td>
				<td width="500px"><%=payment.getReceiverName()%></td>
			</tr>
			<tr>
				<td class="td1">?????????</td>
				<td><%=payment.getReceiverPhone()%></td>
			</tr>
			<tr>
				<td class="td1">??????</td>
				<td><%=payment.getReceiverAddress()%></td>
			</tr>
			<tr>
				<td class="td1">????????????</td>
				<td><%=payment.getReceiverRequest()%></td>
			</tr>
			</table>
		</div>
	</div>
	<div class="submitBtn">
		<a id="delete" href="#">????????????</a> <a id="close" href="/paymentList.do?reqPage=1&type=??????">??????</a>
	</div>

	<script>
		$("#delete").on("click",function(orderNo){
			if(prompt('????????? ????????????????????????? ??????????????? ??????????????????.')) {
				alert("????????? ?????????????????????.")
				location.href="/paymentCencel.do?orderNo="+<%=payment.getOrderNo()%>;					
			}else {
				console.log(222);
			}
		});
	</script>
</body>
</html>
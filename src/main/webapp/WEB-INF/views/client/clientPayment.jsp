<%@page import="trendy.payment.vo.Payment"%>
<%@page import="java.text.Format"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="trendy.orderDetails.vo.OrderDetails"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	ArrayList<Payment> paymentList = (ArrayList<Payment>)request.getAttribute("paymentList");    	
    	DecimalFormat formatter = new DecimalFormat("###,###");
    	String pageNavi = (String)request.getAttribute("pageNavi");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#img{
		width:200px;
		vertical-align:middle;
		padding: 20px
	}
	
	th,td{
		border-bottom: 1px solid black;
	}
	
	th{
		padding: 20px;
	}
	
	.content-page{
		height:1000px;
		width:100%;
		border-bottom: 1px soild black;
		float:left;
		display: block;
	}
	.content-page>h2{
		margin:50px;
		text-align: center;
	}
	.content-page>table{
		/*
		position: relative;
		top:50%;
		left:50%;
		transform: translate(-50%, -50%);
		text-align: center;
		border-collapse: collapse;
		*/
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
	<div class="content-page">
	<h2>주문내역조회</h2>
		<table>
			<tr>
				<th>상품정보</th>
				<th width="250px">옵션</th>
				<th width="150px">주문일</th>
				<th width="200px">주문번호</th>
				<th width="150px">주문금액</th>
				<th width="150px">주문상태</th>
			</tr>
			<%for(Payment payment : paymentList){ %>
			<tr>
				<%
					OrderDetails order = payment.getOrderList().get(0);
				%>
				<td><img src="/upload/product/<%=order.getProductFilepath()%>" id="img" width="250px"></td>
				<td><%=order.getProductName() %><br>size : <%=order.getProductSize() %> <br>color : <%=order.getProductColor() %></td>				
			
				<td><%=payment.getPayDate() %></td><td><a href="/clientOrderDetails.do?orderNo=<%=payment.getOrderNo()%>"><%=payment.getOrderNo() %></a></td>
				<td><%=formatter.format(payment.getPayment())%></td>
				<td><%=payment.getOrderState() %></td>
			</tr>
			<%} %>	
		</table>
		<%=pageNavi%>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
</body>
</html>
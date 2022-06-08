<%@page import="trendy.cart.vo.CartWithProduct"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  	<%
    	ArrayList<CartWithProduct> list = (ArrayList<CartWithProduct>)request.getAttribute("myCart");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<style>
.page-title>h2{
	text-align: center;
	font-size: 40px;
}
.all-del-btn{
	margin-bottom: 10px;
	float: right;
}
.all-del-btn:hover{
	background-color: rgba(57, 62, 70, 1);
  	cursor: pointer;
  	transition:0.5s;
}
button{
	border-radius: 5px;
}
.cart-tr th{
	 color:black !important;
	
}
.content-td{
	line-height: 80px;
}

.p-info{
	
}

.product-img{
	width: 80%;
	height: 80%;
}
.p-img{
	width: 40%;
	display:inline-block;
}
.p-info{
	width: 50%;
	display:inline-block;
	text-align: center;
}
.p-info>a{
	text-decoration: none;
	color: gray;
}
.cart-tbl tr>th{
	text-align: center;
}
.cart-tbl tr>td{
	text-align: center;
}
.cart-tbl tr:first-child{
	border-bottom: 3px solid black;
}
.cart-tbl tr>th:first-child{
	width: 10%;
}
.cart-tbl tr>th:nth-child(2){
	width: 30%;
}
.cart-tbl tr>th:nth-child(3){
	width: 15%;
}
.cart-tbl tr>th:nth-child(4){
	width: 15%;
}
.cart-tbl tr>th:nth-child(5){
 	width:15%;
}
.cart-tbl tr>th:nth-child(7){
	width::15%;
}
.sum{
	text-align: center;
}
.sum div{
	text-align: center;
	display: inline-block;
	width: 30%
}
.sum>span{
	text-align: center;
	font-size: 2em;
}
.pay-btn{
	text-align: center;
}
.pay-btn>button:hover{
	background-color: white;
	color:orange;
	border: 1px solid orange;
  	cursor: pointer;
  	transition:0.5s;
}
.pay-btn>button{
  background-color: orange;
  color: #fff;
  border: 1px solid white;
  width: 30%;
  height:80px;
  font-size:1.2em;
  margin: 10px auto;
}
.del-btn:hover{
	background-color: white;
	border:1px solid lightcoral;
	color : lightcoral;
  	cursor: pointer;
  	transition:0.5s;
}
.del-btn{
	text-align: center;
}
.del-btn{
  background-color: lightcoral;
  color: #fff;
  border: 1px solid white;
  width: 60px;
  height:40px;
  margin: 10px auto;
}

.sum{
	margin-top: 50px;
	margin-bottom: 30px;
}
.bc3 {
  background-color: rgba(238, 238, 238, 0.9);
  color: #252a34;
  border: 2px solid rgba(238, 238, 238, 0.9);
}
.bc3:hover {
  background-color: rgba(238, 238, 238, 1);
  cursor: pointer;
}
.all-del-btn {
  background-color: lightcoral;
  color: #fff;
  border: 1px solid white;
}
.all-del-btn:hover {
  background-color: white;
  color : lightcoral;
  border : 1px solid lightcoral;
  cursor: pointer;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<form action="/clientPaymentFrm.do?memberId=<%=m.getMemberId()%>">
		<%!int cartSum = 0; %>
		<%cartSum=0; %>
		<input type="hidden" name="memberId" value="<%=m.getMemberId()%>">
		<div class="page-content">
			<div class="page-title"><h2>장바구니</h2></div>
			<button type="button" class="all-del-btn" onclick="deleteCheckBox('<%=m.getMemberId()%>');">선택항목 삭제</button>
			<table class="tbl cart-tbl">
				<tr class="cart-tr" style="color:black;">
					<th><label for="all-product-check">전체선택</label><input type="checkbox" onclick="selectAllBox();" name="all-product-check" id="all-product-check"></th><th>상품정보</th><th>수량</th><th>합계금액</th><th>배송비</th><th>주문관리</th>
				</tr>
				<%for(CartWithProduct c : list) {%>
				<tr class="tr-1">
					<td><input type="checkbox" value=<%=c.getProductId() %> id="product-check" name="product-check"></td>
					<td class="content-td">
						<div class="p-img">
							<a href="/productView.do?product_id=<%=c.getProductId() %>"><img class="product-img" src="/upload/product/<%=c.getProductFileName() %>"></a>
						</div>
						<div class="p-info">
							<a href="/productView.do?product_id=<%=c.getProductId() %> "><%=c.getProductName() %></a><br>
							<a href="/productView.do?product_id=<%=c.getProductId() %>">[Option: <%=c.getProductColor() %> / <%=c.getProductSize() %>]</a><br>
							<a href="/productView.do?product_id=<%=c.getProductId() %>">[Price: <%=c.getProductPrice()%>]</a>
						</div>
					</td>
					<td>
						<div class="count">
							<button type="button" class="bc3" name="<%=c.getProductId() %>minus" onclick="minusAmount('<%=c.getProductId()%>','<%=c.getMemberId()%>','<%=c.getProductPrice()%>');">-</button>
							<span class="amount"><%=c.getAmount() %></span>
							<button type="button" class="bc3" name="<%=c.getProductId() %>plus" onclick="plusAmount('<%=c.getProductId()%>','<%=c.getMemberId()%>','<%=c.getProductPrice()%>');">+</button>
						</div>
					</td>
					<td><span class="<%=c.getProductId() %>price"><%=c.getProductPrice()*c.getAmount() %></span></td>
					<%cartSum+=c.getProductPrice()*c.getAmount(); %>
					<td>
						3,000<br>
						(50,000원 이상<br> 
						무료배송)
					</td>
					<td><button class="del-btn" type="button" value="<%=c.getProductId()%>">삭제</button></td>
				</tr>
				<%} %>
				
			</table>
			<div class="payment">
				<p>*배송비 정책 : 50,000원 이상 구매 시 무료배송</p>
				<div class="sum">
					<div>
						<span>총 상품금액</span><br>
						<span class="product-sum"><%=cartSum %></span><span>원</span>
						<input type="hidden" name="product-sum" value="<%=cartSum %>">
					</div>
					<span>+</span>
					<div>
						<span id="delivery">배송비</span><br>
						<%if(cartSum>=50000) {%>
							<span>0 원</span>
							<input type="hidden" name="delivery-pay" value="0">
						<%}else{ %>
							<span>3000 원</span>
							<input type="hidden" name="delivery-pay" value="3000">
						<%} %>
					</div>
					<span>=</span>
					<div>
						<span>결제예정금액</span><br>
						<%if(cartSum>=50000) {%>
							<span id="sum"><%=cartSum %></span><span> 원</span>
							<input type="hidden" name="total-pay" value="<%=cartSum %>">
						<%}else{ %>
							<span id="sum"><%=cartSum+3000 %></span><span> 원</span>
							<input type="hidden" name="total-pay" value="<%=cartSum +3000%>">
						<%} %>
					</div>
				</div>
			</div>
			<div class="pay-btn">
				<button type="submit">결제하기</button>
			</div>
		</div>
	</form>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
<script>
$(".del-btn").on("click",function(){
	const productId = $(this).val();
	const memberId = $("#memberIdCart").val();
	console.log(productId);
	console.log(memberId)
	$.ajax({
		type : "post",
		url :"/deleteCart.do",
		data : {memberId:memberId,productId:productId},
		success: function(data){
	  		console.log(data);
	  		location.reload();
	    },
	    error: function(data) {
	   		alert("error");
	    }  
	});
});
Number.prototype.formatNumber = function(){
    if(this==0) return 0;
    let regex = /(^[+-]?\d+)(\d)/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};

function selectAllBox(){
	if($("[name=all-product-check]").prop('checked',false)){
		if($("[name=product-check]").prop('checked',false)){
			$("[name=product-check]").trigger('click');		
		}
	}
}

function deleteCheckBox(memberId){
	var checkBoxArr = [];
	console.log(memberId);
	$("input:checkbox[name='product-check']:checked").each(function() {
	  checkBoxArr.push($(this).val());
	  console.log(checkBoxArr);
	});
	if(checkBoxArr.length==0){
		alert("선택된 상품이 없습니다.");
		return;
	}	
	$.ajax({
	    type  : "POST",
	    url    : "/deleteCheckBox.do",
	    data: {memberId:memberId,checkBoxArr:checkBoxArr.join("/")},
	    success: function(data){
	  		console.log(data);
	  		location.reload();
	    },
	    error: function(data) {
	   		alert("error");
	    }  
  });
}
function add(sum, productPrice){
	var result = sum + productPrice;
	return result;
}
function plusAmount(productId, memberId, productPrice){
	let currAmount = Number($("[name="+productId+"plus]").prev().text());
	var sum = Number($(".product-sum").text());
	var deliveryPay = $("[name=delivery-pay]").val();
	$.ajax({
		url: "/plusCartAmount.do",
		type: "post",
		data: {memberId:memberId,productId:productId,currAmount:currAmount},
		success: function(data){
			$("[name="+productId+"plus]").prev().text(data);
			$("."+productId+"price").text(data*productPrice);
			sum=(sum+Number(productPrice));
			$(".product-sum").text(sum);
			$("[name=product-sum]").val(sum);
			if(deliveryPay!="0"){
				if(sum>=50000){
					$("[name=delivery-pay]").val("0");
					$("[name=delivery-pay]").prev().text("0 원");
					$("[name=total-pay]").val(sum);
					$("[name=total-pay]").prev().prev().text(sum);
				}				
			}else{
				$("[name=total-pay]").val(sum+3000);
				$("[name=total-pay]").prev().prev().text(sum+Number(3000));
			}
		},
		error: function(data){
			console.log("에러발생");
		},
	});	
};

function minusAmount(productId, memberId,productPrice){
	let currAmount = Number($("[name="+productId+"minus]").next().text());
	var sum = Number($(".product-sum").text());
	var deliveryPay = $("[name=delivery-pay]").val();
	if(currAmount !=1){
		$.ajax({
			url: "/minusCartAmount.do",
			type: "post",
			data: {memberId:memberId,productId:productId,currAmount:currAmount},			
			success: function(data){
				$("[name="+productId+"minus]").next().text(data);
				$("."+productId+"price").text(data*productPrice);
				sum=Number(sum-productPrice);
				$(".product-sum").text(sum);
				$("[name=product-sum]").val(sum);
				if(deliveryPay=="0"){
					if(sum<50000){
						$("[name=delivery-pay]").val("3000");
						$("[name=delivery-pay]").prev().text("3000 원");
						$("[name=total-pay]").val(sum+3000);
						$("[name=total-pay]").prev().prev().text(sum+Number(3000));
					}else{
						$("[name=total-pay]").val(sum);
						$("[name=total-pay]").prev().prev().text(sum);
					}			
				}
			},
			error: function(data){
				console.log("에러발생");
			},
		});	
	}
}
</script>
</html>
<%@page import="trendy.product.vo.Product"%>
<%@page import="trendy.product.vo.ProductWithCount"%>
<%@page import="java.util.ArrayList"%>
<%@page import="trendy.payment.vo.Payment"%>
<%@page import="trendy.member.vo.Member"%>
<%@page import="trendy.orderDetails.vo.OrderDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
		Payment payment = (Payment)request.getAttribute("payment");
		OrderDetails order = (OrderDetails)request.getAttribute("orderDetails");
		ArrayList<ProductWithCount> pList = (ArrayList<ProductWithCount>)request.getAttribute("productWithCountList");
	%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- 결제 API 사용을 위한 라이브러리 경로 추가 -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<style>

	input{
		border: none;
  		border-bottom: 1px solid #ccc;
  		font-size: 20px;
  		outline: none;
  		padding-bottom: 10px;
  		font-size: 15px;
	}
	.payment{

		width: 35%;
		position: relative;
		float: left;
		padding-left:10px;
		
	}
	
	.payment-content{
		width: 300px;
	}
	
	.delivery-wrap{
		float: left;
		width: 55%;
		position: relative;
	}
	
	.labelVelue{
		width: 100%;
		padding-top: 15px;
		border-bottom:1px solid black;
		padding-bottom:15px;
	}
	
	td{
		padding: 5px;
	}
	
	td>input,th>input{
		padding: 15px;
	}
	
	th{
		text-align: left;
	}
	
	.size1>input{
		width: 250px;
	}
	.size2>input{
		width: 120px;
	}
	.size3>input{
		width: 400px;
	}
	
	.size4>input{
		width: 60px;
	}

	.pointTbl, .paymentMethod{
		border-bottom: 1px solid black;
		padding-bottom: 15px;
	}
	
	#bank{
		width:250px;
		height: 37px;
		font-size: 15px;
	}
	.btn, #usePoint{
		height: 40px;
		background-color: orange;
		color: white;
		border: 1px solid white;
		border-radius: 5px;
		transition: 0.5s;
	}
	.btn:hover, #usePoint:hover{
		background-color: white;
		color: orange;
		border: 1px solid orange;
	}
	#pay{
		margin-top: 10px;
		width: 100%;
		height: 40px;
		background-color: orange;
		color: white;
		border: 1px solid white;
		border-radius: 5px;
		transition: 0.5s;
	}
	#pay:hover{
		background-color: white;
		color: orange;
		border: 1px solid orange;
	}
	.product-info-detail>ul{
		list-style: none;
	}
	.payment{
		border: 1px solid #445548;
		margin-top: 20px;
		margin-left: 30px;
		padding: 10px;
	}
	
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%> 
	<div class="all-wrap">
		<div class="delivery-wrap">
			<form action="/clientPaymentFrm.do" method="post">
			<div class="delivery-input"> 
				<h3>배송정보</h3>
			<table class="labelVelue">
				<%--수령인 --%>
				<tr>
					<th><label for="receiverName">수령인</label></th>
					<td class="size1"><input type="text" name="receiverName" id="receiverName" placeholder="이름"><br></td>
				</tr>
				<%--배송지 --%>
				<tr>
					<th><label for="adBtn">배송지주소</label></th>
					<td class="size2"><input type="text" name="postcode" id="postcode" class="input-form" placeholder="우편번호" readonly>
					<button class="btn bc1" type="button" id="adBtn" onclick="searchAddr();">주소찾기</button><br></td>
				</tr>
				<tr>
					<th></th>
					<td class="size3"><input type="text" name="address" id="address" class="input-form" placeholder="주소찾기 버튼을 눌러주세요." readonly><br></td>
				</tr>
				<tr>
					<th></th>
					<td class="size3"><input type="text" name="detailAddress" id="detailAddress" class="input-form" placeholder="상세주소"></td>
				</tr>
				<%--연락처 --%>
				<tr>
					<th><label for="phone1">연락처1</label></th>
					<td class="size4 phone"><input type="text" name="phone1" id="phone" placeholder="010" maxlength="3"> 
					<input class="phone" type="text" name="phone2" id="phone2" placeholder="0000" maxlength='4'> 
					<input class="phone" type="text" name="phone3" id="phone3" placeholder="0000" maxlength='4'></td>
				</tr>
				<%--배송메세지 --%>
				<tr>
					<th><label for="receiverRequest">배송메세지</label></th>
					<td class="size3"><input type="text" name="receiverRequest" id="receiverRequest" placeholder="배송메세지"></td>
				</tr>
			</table>
			<%--포인트 --%>
			<div class="pointTbl">
			<h3>포인트</h3>
			<table>
				<tr>
					<th width=150><label for="point">포인트</label></th>
					<td class="size2"><input type="text" name="point" id="point" placeholder="0" value="0"> 
					<button name="usePoint" id="usePoint" type="button">모두사용</button> 보유포인트<%= m.getPoint() %></td>
				</tr>
			</table>
			</div>
			<div class="paymentMethod">
			<h3>결제방법</h3>
			<table>
				<tr>
				<%--결제방법 선택 --%>
				<td><label><input type="radio" value="신용카드" id="payMethod1" name="payMethod" checked>신용카드</label><td>
				<td><label><input type="radio" value="무통장입금" id="payMethod2" name="payMethod">무통장입금</label><td>
				<tr>
			</table>
			</div>
			</div>
			</form>
		</div>
	</div>
	
	<div class="payment">
		<div class="product-info">
			<span>주문상품 정보 / 총 <%=pList.size() %>개</span>
			<div class="product-info-detail">
				<ul>
					<%
					int total = 0;
					for(int i = 0; i < pList.size(); i++) {
						Product p = pList.get(i).getProduct();
						int cartNo = pList.get(i).getCartNo();
						int amount = pList.get(i).getAmount();
						total += (p.getProductPrice() * amount);
					%>
					<%if(cartNo == 0) {%>
					<li value="<%=amount + "_"+p.getProductId()%>"><%=p.getProductName()%> / <%=p.getProductSize() %> / <%=p.getProductColor() %> / <%=p.getProductPrice() %> / <%=amount%></li>
					<%}else{ %>
					<li value="<%=cartNo%>"><%=p.getProductName()%> / <%=p.getProductSize() %> / <%=p.getProductColor() %> / <%=p.getProductPrice() %> / <%=amount%></li>
					<%} %>
					<%
					} 
					int delivery = total > 50000 ? 0 : 3000;
					%>
				</ul>
			</div>
		</div>
		<div class="product-cost" >
			<label>총 상품 금액</label>
			<span><%=total %></span>
			<span>원</span>
		</div>
		<div class="point" >
			<label>포인트 사용</label>
			<span id="use-point">0</span>
			<span>P</span>
		</div>
		<div class="delivery-cost" >
			<label>배송비</label>
			<span><%=delivery%></span>
			<span>원</span>
		</div>
		<div class="final-cost" >
			<label>총 결제금액</label>
			<span id="final"><%=total + delivery%></span>
			<span>원</span>
		</div>
		<div>
			<button id="pay">결제하기</button>
		</div>
	</div>
	
	
	<script>
	<%--배송지 스크립트 --%>
	function searchAddr(){
		new daum.Postcode({
			oncomplete: function(data) { //주소를 클릭하고 나면
		 	console.log(data);
			$("#postcode").val(data.zonecode);
			$("#address").val(data.roadAddress);
			$("#detailAddress").focus();
			}
		}).open();
	}
	
	<%--포인트 스크립트--%>
	var finalCost = <%=total + delivery %>;
	var point = <%=m.getPoint()%>;
	
	$("#usePoint").on("click", function(){
		$("#point").val(point);
		$("#use-point").text(point*-1);
		changePoint(point*-1);
	});
	
	$("#point").on("keyup", function(event) {
		if(point < event.target.value) {
			$("#usePoint").trigger('click');
			return;
		}else {
			var usePoint = event.target.value;
			$("#use-point").text(usePoint*-1);
			changePoint(usePoint*-1);
		}
	});
	
	function changePoint(calcPoint) {
		$("#final").text(finalCost + calcPoint);
	}
	
	
	
	
	//var getPhone = /^\d{3}-\d{4}-\d{4}$/;
	var getReceiverName = /^[a-zA-z가-힣]{2,5}$/;
	var getReceiverRequest = /^[A-Za-z가-힣ㄱ-ㅎ0-9]{0,20}$/;
		/*
		if(!getPhone.test($("#phone").val())){
			alert("전화번호를 형식에 맞게 입력해주세요(숫자 3자리 / 4자리 / 4자리)");
			$("#phone").focus();
		}
		*/
		
	
	
	$("#pay").on("click",function(){
		
		if(!getReceiverName.test($("[name=receiverName]").val())){
			alert("수령인을 다시 확인해주세요.(영문, 한글 2~5글자)");
			$("[name=receiverName]").focus();
			return;
		}
		
		if ($("[name=address]").val() == "") {
			alert("주소를 입력해주세요.");
			$("[name=address]").focus();
			return;
		}
		
		if (($("[name=phone1]").val() == "")||($("[name=phone2]").val() == "")||($("[name=phone3]").val() == "")) {
			alert("전화번호를 입력해주세요.(3글자, 4글자, 4글자)");
			$("[name=phone1]").focus();
			return;
		}
		
		if(!getReceiverRequest.test($("[name=receiverRequest]").val())){
			alert("배송메세지를 다시 확인해주세요.(영문, 한글, 숫자 20자 이내)");
			$("[name=receiverRequest]").focus();
			return;
		}
	
		const arr = new Array();
		$(".product-info-detail li").each(function(index,item){
			arr.push($(item).attr("value"));
		});
		//console.log(arr);
		//return;
		if($("input[name='payMethod']:checked").val() == "신용카드"){
			const price = $("#final").text();
			//거래 고유ID생성을 위해 현재 결제 날짜를 이용해서 처리
			const d = new Date();
			//date 값 생성 시 ""를 더하지 않으면 숫자 + 연산되므로 문자 덧셈을 위해 추가
			const date = d.getFullYear()+""+(d.getMonth()+1)+""+d.getDate()+""+d.getHours()+""+d.getMinutes()+""+d.getSeconds();
			
			IMP.init("imp92213360"); //결제 API 사용을 위한 식별코드 입력
			IMP.request_pay({
				merchant_uid : "상품코드_"+date, //거래 ID
				name : "의류", //결제이름
				amount : price, //결제금액
				buyer_email : "",//구매자 이메일
				buyer_name : "***", //구매자이름
				buyer_tel : "010-1234-1234", //구매자전화번호
				buyer_addr : "서울시 마포구", //구매자 주소
				buyer_postcode : "12345" //구매자 우편번호
			},function(rsp){
				if(rsp.success){
					<%--번호 합치기_영우님 코드--%>
					var phone1 = $("#phone").val();
					var phone2 = $("#phone2").val();
					var phone3 = $("#phone3").val();
					var phone = (phone1 + "-" + phone2 + "-" + phone3);
					$("#phone").val(phone);
					
					<%--주소합치기--%>
					var postcode = $("#postcode").val();
					var address = $("#address").val();
					var detailAddress = $("#detailAddress").val();
					var clientAddress = (postcode+"/"+address+"/"+detailAddress);
					$("#address").val(clientAddress);
					
					//추가 DB작업이 필요한 경우 이 부분이 결제내역을 DB에 저장하는 코드 작성
					//
					$.ajax({
						url : "/clientPaymentSubmit.do",
						type : "post",
						data : {
							receiverName : $("#receiverName").val(),
							address : $("#address").val(),
							phone : $("#phone").val(),
							receiverRequest : $("#receiverRequest").val(),
							payMethod : $("input[name='payMethod']:checked").val(),
							point : $("#point").val(),
							memberId : '<%=m.getMemberId()%>',
							cartNo : arr.join("/")
							
							//리스트 어떻게 보내야 하는지...xxxx
							
						},
						success : function(){
							location.href="/clientPaymentCard.do"
							console.log("전송완료");
							
						},
						error : function(){
							console.log("전송실패");
						}
					});
				}else{
					alert("에러내용 : " +rsp.err_msg);
				}
			});
		}else{
			<%--번호 합치기_영우님 코드--%>
			var phone1 = $("#phone").val();
			var phone2 = $("#phone2").val();
			var phone3 = $("#phone3").val();
			var phone = (phone1 + "-" + phone2 + "-" + phone3);
			$("#phone").val(phone);
			
			<%--주소합치기--%>
			var postcode = $("#postcode").val(); 
			var address = $("#address").val();
			var detailAddress = $("#detailAddress").val();
			var clientAddress = (postcode+"/"+address+"/"+detailAddress);
			$("#address").val(clientAddress);
			
			$.ajax({
				url : "/clientPaymentSubmit.do",
				type : "post",
				data : {
					receiverName : $("#receiverName").val(),
					address : $("#address").val(),
					phone : $("#phone").val(),
					receiverRequest : $("#receiverRequest").val(),
					payMethod : $("input[name='payMethod']:checked").val(),
					point : $("#point").val(),
					memberId : '<%=m.getMemberId()%>',
					cartNo : arr.join("/")
					//productcode : 
					//amount : 
					//리스트 어떻게 보내야 하는지...
				},
				success : function(){
					location.href="/clientPaymentCash.do"
					console.log("전송완료");
					
				},
				error : function(){
					console.log("전송실패");
				}
			});
			
		}
	});
	
	
	
	
	/*
	
	$("#pay").on("click",function(){
		if($("input:radio[name='payment']:checked").val() == "card"){
			const price = $("#final").text();
			//거래 고유ID생성을 위해 현재 결제 날짜를 이용해서 처리
			const d = new Date();
			//date 값 생성 시 ""를 더하지 않으면 숫자 + 연산되므로 문자 덧셈을 위해 추가
			const date = d.getFullYear()+""+(d.getMonth()+1)+""+d.getDate()+""+d.getHours()+""+d.getMinutes()+""+d.getSeconds();
			
			IMP.init("imp92213360"); //결제 API 사용을 위한 식별코드 입력
			IMP.request_pay({
				merchant_uid : "상품코드_"+date, //거래 ID
				name : "의류", //결제이름
				amount : price, //결제금액
				buyer_email : "",//구매자 이메일
				buyer_name : "***", //구매자이름
				buyer_tel : "010-1234-1234", //구매자전화번호
				buyer_addr : "서울시 마포구", //구매자 주소
				buyer_postcode : "12345" //구매자 우편번호
			},function(rsp){
				if(rsp.success){
					//추가 DB작업이 필요한 경우 이 부분이 결제내역을 DB에 저장하는 코드 작성
					var param = {
							addressName : $("#addressName").val(),
							receiverName : $("#receiverName").val(),
							productWithAmountList : []
						};
						
						$.post("clientPaymentSubmit.do", param, function() {
							// 성공했을 때
							alert("결제되었습니다.");
							location.href="clientPaySubmit.do";
						});
				}else{
					alert("에러내용 : " +rsp.err_msg);
				}
			});
			
			
		
	});
	
	}else {
		$.ajax({
			url : "clientPaymentSubmit.do",
			type : "post",
			data {addressName : $("#addressName").val(),
				receiverName : $("#receiverName").val()
			},
			success : function(){
				console.log("전송완료");
				
			},
			error : function(){
				console.log("전송실패");
			}
		});
		
		*/
	
	/*
	$("#pay").on('click', function() {
		var param = {
			addressName : $("#addressName").val(),
			receiverName : $("#receiverName").val(),
			productWithAmountList : []
		};
		
		$.post("clientPaymentSubmit.do", param, function() {
			// 성공했을 때....
			alert("결제되었습니다.");
		});


		
	});
	*/
	
	
	
	/*
	var paymentChecked = $("input:radio[name='payment']:checked").val();
	var card = "card";
	if(paymentChecked =="card") {
		console.log();
	}else {
		console.log(2222);
	}
	*/
	
	/*
	$("#pay").on("click",function(){
		const price = $("#final").text();
		//거래 고유ID생성을 위해 현재 결제 날짜를 이용해서 처리
		const d = new Date();
		//date 값 생성 시 ""를 더하지 않으면 숫자 + 연산되므로 문자 덧셈을 위해 추가
		const date = d.getFullYear()+""+(d.getMonth()+1)+""+d.getDate()+""+d.getHours()+""+d.getMinutes()+""+d.getSeconds();
		
		IMP.init("imp92213360"); //결제 API 사용을 위한 식별코드 입력
		IMP.request_pay({
			merchant_uid : "상품코드_"+date, //거래 ID
			name : "의류", //결제이름
			amount : price, //결제금액
			buyer_email : "hello.yun.xx@gmail.com",//구매자 이메일
			buyer_name : "구매자", //구매자이름
			buyer_tel : "010-1234-1234", //구매자전화번호
			buyer_addr : "서울시 마포구", //구매자 주소
			buyer_postcode : "12345" //구매자 우편번호
		},function(rsp){
			if(rsp.success){
				console.log("결제가 완료되었습니다.")
				console.log("고유ID :"+rsp.imp_uid);
				console.log("상점거래ID : "+rsp.merchant_uid);
				console.log("결제금액 : "+rsp.paid_amount);
				console.log("카드승인번호 : "+rsp.apply_num);
				//추가 DB작업이 필요한 경우 이 부분이 결제내역을 DB에 저장하는 코드 작성
				
			}else{
				alert("에러내용 : " +rsp.err_msg);
			}
		});
	});
	*/
	
	<%--
	function checkAll() {
		var getPhone = RegExp(/^\d{3}-\d{4}-\d{4}$/);
		var phone1 = $("#phone1").val();
		var phone2 = $("#phone2").val();
		var phone3 = $("#phone3").val();
		var phone = (phone1 + "-" + phone2 + "-" + phone3);
		$("#phone").val(phone);
		if (!getPhone.test($("#phone").val())) {
			alert("전화번호를 형식에 맞게 입력해주세요(숫자 3자리 / 4자리 / 4자리)");
			$("#phone1").focus();
			return false;
		}
	}
	--%>
	
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록 페이지</title>
<style>
	.page-content{
		width: 1200px;
		margin : 0 auto;
		padding-bottom : 30px;
	}
	.page-title{
		text-align: center;
		font-size: 35px;
		padding : 30px;
	}
	.tble {
	  width: 100%;
	  border-collapse: collapse;
	}
	.tble tr:not(:last-child){
		border-bottom:1px solid #ccc; 
	}

	.tble th{
	  width: 20%;
	  height : 50px;
	  padding: 5px;
	  background-color: white;
	}
	.tble td {
	  width: 80%;
	  height : 50px;
	  padding: 5px;
	}
	.submit{
		width:100%;
		height : 100px;
		font-size : 20px;
		
	}
	.tble input, .tble select{
		width: 200px;
		height: 25px;
		font-size : 15px;
	}
	.required{
		color: orange;
	}
	.submit{
		background-color: orange;
		color: white;
		border: 1px solid transparent;
		border-radius: 10px;
		margin-top: 10px;
	}
	.submit:hover{
		background-color: #fff;
		color: orange;
		border: 1px solid orange;
		cursor:pointer;
		transition:0.5s;
	}
	.page-title>h3{
		margin-bottom: 0px;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">

		<div class="page-content">
			<div class="page-title"><h3>상품등록</h3></div>
			<form action="insertProduct.do" method="post" enctype="multipart/form-data" onsubmit="return contentCheck();">
			<span class="required">*필수입력사항</span>
			<table class="tble">
				<tr>
					<th><span class="required">*</span>상품명</th>
					<td>
						<input type="text" name="productName" id="productName">
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>가격</th>
					<td>
						<input type="text" name="productPrice" id="productPrice">
						<span>원</span>
						<span></span>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>수량</th>
					<td>
						<input type="text" name="productCount" id="productCount">
						<span></span>
					</td>
				</tr>
				<tr>
					<th>상품옵션</th>
					<td>
						<input name="productColor" value="black" readonly>
						<input name="productSize" value="free" readonly>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>상품대표이미지</th>
					<td><input type="file" name="file" id="file"></td>
				</tr>
				<tr>
					<th><span class="required">*</span>상품분류</th>
					<td>
						<select id="mainCategory" name="mainCategory" onchange="changeSelect();">
							<option value="outer" selected>OUTER</option>
							<option value="top">TOP</option>
							<option value="bottom">BOTTOM</option>
						    <option value="acc">ACC</option>
						</select>
						<select id="subCategory" name="subCategory">
							<option value="jumper" selected>JUMPER</option>
							<option value="suit">SUIT</option>
							<option value="cardigan">CARDIGAN</option>
							<option value="coat">COAT</option>
						</select>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>상품상세설명</th>
					<td>
						<textarea id="productDetail" name="productDetail" id="productDetail"></textarea>
					</td>
				</tr>
			</table>
			<button class="submit" type="submit">상품등록</button>
			</form>
		</div>
		<script>
		//제출 버튼 이벤트
		function contentCheck(){
			const name = $("#productName").val();
			const price = $("#productPrice").val();
			const count = $("#productCount").val();
			const file = $("#file").val();
			const detail = $("#productDetail").val();
			const priceReg = /^[0-9]+$/;
			const countReg = /^[0-9]+$/;
			if(name=="" || price=="" || count=="" || file=="" || detail==""){
				alert("필수입력값을 확인하세요.");
				return false;
			}else if(!priceReg.test(price)){
				alert("가격을 확인해 주세요.")
				return false;
			}else if(!countReg.test(count)){
				alert("수량을 확인해 주세요.")
				return false;
			}
		}
		
		const price = $("#productPrice");
		price.on("change",function(){
			const priceReg = /^[0-9]+$/;
			if(!priceReg.test(price.val())){
				price.next().next().text(" *숫자를 입력해주세요.");
				price.next().next().css("color","red");
			}else{
				price.next().next().text("");
			}
		});
		
		const count = $("#productCount");
		count.on("change",function(){
			const countReg = /^[0-9]+$/;
			if(!countReg.test(count.val())){
				count.next().text(" *숫자를 입력해주세요.");
				count.next().css("color","red");
			}else{
				count.next().text("");
			}
		});
		
		//섬머노트
			$("#productDetail").summernote({
				height: 400,
				lang : "ko-KR",
				callbacks:{
					onImageUpload : function(files){
						uploadImage(files[0],this);
					}
				}
			});
			function uploadImage(file,editor){
				
				const form = new FormData();
				form.append("file",file);	
				$.ajax({
					url : "/uploadImage.do",
					type : "post",
					data : form,
					processData : false,
					contentType : false,
					success : function(data){
						$(editor).summernote("insertImage",data);
					}
				});
				
			}
		
		//select 서브카테고리
		function changeSelect(){
			const val = $("#mainCategory option:selected").val();
			if(val == "outer"){
				$("#subCategory").empty();
				$("#subCategory").append(
						"<option value='jumper' selected>JUMPER</option>"
						+"<option value='suit'>SUIT</option>"
						+"<option value='cardigan'>CARDIGAN</option>"
						+"<option value='coat'>COAT</option>");
			}else if(val =="top"){
				$("#subCategory").empty();
				$("#subCategory").append(
						"<option value='tshirt' selected>TSHIRT</option>"
						+"<option value='hood'>HOOD</option>"
						+"<option value='knit'>KNIT</option>"
						+"<option value='shirt'>SHIRT</option>");
			}else if(val =="bottom"){
				$("#subCategory").empty();
				$("#subCategory").append(
						"<option value='slacks' selected>SLACKS</option>"
						+"<option value='denim'>DENIM</option>");
			}else if(val =="acc"){
				$("#subCategory").empty();
				$("#subCategory").append(
						"<option value='shoes' selected>SHOES</option>"
						+"<option value='bag'>BAG</option>"
						+"<option value='cap'>CAP</option>");
			}
		}
			
		</script>
		
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
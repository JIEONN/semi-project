<%@page import="trendy.product.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Product p = (Product)request.getAttribute("p");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품정보수정</title>
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
	}

	.tble th{
	  width: 20%;
	  height : 50px;
	}
	.tble td {
	  width: 80%;
	  height : 50px;
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
		background-color: #ccc;
		color: white;
		border: 2px solid #F2F2F2;
		cursor:pointer;
	}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
		<div class="page-content">
			<div class="page-title">상품정보 수정</div>
			<form action="modifyProduct.do" method="post" enctype="multipart/form-data" onsubmit="return contentCheck();">
			<span class="required">*필수입력사항</span>
			<table class="tble" border="1">
				<tr>
					<th>상품코드</th>
					<td>
						<input type="text" name="productId" value="<%=p.getProductId() %>" readonly>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>상품명</th>
					<td>
						<input type="text" name="productName" id="productName"   value="<%=p.getProductName() %>">
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>가격</th>
					<td>
						<input type="text" name="productPrice" id="productPrice" value="<%=p.getProductPrice() %>">
						<span>원</span>
						<span></span>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>수량</th>
					<td>
						<input type="text" name="productCount" id="productCount"  value="<%=p.getProductCount() %>">
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
					<td>
					
						<!-- 기존파일 유무에 따라, 파일인풋박스 화면에 나타내는것 다르게 해주기 -->
							<input type="hidden" name="status" value="stay">
							<%if(p.getProductFilepath() != null){ %> 
								<div class="file-box">
									<span class="delFile"><%=p.getProductFilename() %></span>
									<button type="button" class="delFile" id="fileDelBtn">삭제</button>
									<!-- 삭제버튼 누르면 아래 새 [파일등록] 버튼 나타내기 -->
									<input type="file" name="file" id="file" style="display:none;">
									<input type="hidden" name="oldFilename" value="<%=p.getProductFilename() %>">
									<input type="hidden" name="oldFilepath" value="<%=p.getProductFilepath() %>">
								</div>
							<%} else { %> 
								<input type="file" name="file" id="file">
							<%} %>
					
					</td>
				</tr>
				<tr>
					<th>상품분류</th>
					<td>
						<input name="main_category" value="<%=p.getMainCategory()%>" readonly>
						<input name="main_category" value="<%=p.getSubCategory()%>" readonly>
						
					</td>
				</tr>
				<tr>
					<th>상품상세설명</th>
					<td>
						<textarea id="productDetail" name="productDetail" id="productDetail" ><%=p.getProductDetailBr() %></textarea>
					</td>
				</tr>
			</table>
			<button class="submit" type="submit">상품수정</button>
			</form>
		</div>
		
		<script>
		//제약조건
		function contentCheck(){
			const name = $("#productName").val();
			const price = $("#productPrice").val();
			const count = $("#productCount").val();
			const file = $("#file").val();
			const delfile = $(".delFile").text();
			const detail = $("#productDetail").val();
			const priceReg = /^[0-9]+$/;
			const countReg = /^[0-9]+$/;
			if(name=="" || price=="" || count=="" || detail=="" ){
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
					//결과로받은 이미지파일 경로를 에디터에 추가
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
		
		
				$("#fileDelBtn").on("click",function(){
					$(".delFile").hide();
					$(this).next().show();
					$("[name=status]").val("delete");
				});
		
		
		</script>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
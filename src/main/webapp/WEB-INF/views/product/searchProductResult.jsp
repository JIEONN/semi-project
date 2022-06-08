<%@page import="java.text.DecimalFormat"%>
<%@page import="trendy.product.vo.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
    	String pageNavi = (String)request.getAttribute("pageNavi");
    	String mainCategory = (String)request.getAttribute("mainCategory");
    	int reqPage = (Integer)request.getAttribute("reqPage");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리 페이지</title>
</head>
<style>
	.page-content{
		width: 1200px;
		margin : 0 auto;
		padding-bottom : 30px;
	}
	.page-title{
		text-align: center;
		font-size: 35px;
		padding : 20px;
		clear:left;
		
	}
	.page-title>h3{
		margin-top:0px;
		margin-bottom: 0px; 
	}
	
	.page-top{
		text-align : center;
		margin : 70px;
	}
	.page-top>div{
		overflow: hidden;
		display : inline-block;
	}
	.page-top>div>*{
		font-size : 15px;
		float:left;
		height : 30px;
		margin: 5px;
	}
	.btn{
		width : 80px;
		height : 30px;
		margin : 2px;
	}
	.btn:hover{
		cursor: pointer;
	}
	.modify, .delete{
		float:left;
		margin: 9px;
	}
	.modify, .searchbtn{
		background-color: orange;
		border: 1px solid white;
		border-radius: 5px;
		color: white;
	}
	.modify:hover, .searchbtn:hover{
		transition : 0.5s;
		color:orange;
		border: 1px solid orange;
		background-color: white;
	}
	.delete{
		background-color: lightcoral;
		border: 1px solid white;
		border-radius: 5px;
		color: white;
	}
	.delete:hover{
		transition : 0.5s;
		color:lightcoral;
		border: 1px solid lightcoral;
		background-color: white;
	}
	.search-box{
		width: 300px;
	}
	.search-box>*{
		display: inline-block;
		float : left;
	}
	#icons{
		font-size : 30px;
	}
	.search-box>input{
		font-size : 15px;
		width: 270px;
		height : 30px;
		box-sizing : border-box;
	}
	
	.notfound{
		text-align: center;
		font-size: 20px;
		padding : 50px;
	}
	
	.tble {
	  width: 100%;
	  border-collapse: collapse;
	}
	.tble .tr-title>*{
		background-color: orange;
		color: white;
	}
	.tble th,
	.tble td {
	  height : 40px;
	  text-align: center;
	  width: 150px;
	}
	.tble tr:hover{
		transition:0.5s;
		background-color: rgba(0, 0, 0, 0.05);
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
	
	.left-content {
		width: 20%;
		margin: auto;
		text-align: center;
		float:left;
	}
	.menu{
		margin: 10px auto;
		list-style-type:none;
		padding: 0;
		overflow: hidden;
		text-align: center;
		width: 200px;
	}
	.menu li{
	
	}
	.menu-btn>a{
		display: block;
		text-decoration:none;
		height: 30px;
		line-height: 30px;
		color: white;
		background-color: orange;
		border: 1px solid white;
		border-radius: 5px;
	}
	.menu-btn>a:hover{
		background-color: white;
		border: 1px solid orange;
		color: orange;
		font-weight: bolder;
		transition:0.5s;
	}
	
	.insert{
		background-color: orange;
		border: 1px solid white;
		border-radius: 5px;
		color: white;
	}
		
	.insert:hover{
		transition : 0.5s;
		color:orange;
		border: 1px solid orange;
		background-color: white;
	}	
	
</style>
<body>

	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	
	<div class="page-content">
		
		<div class="left-content">
			<ul class="menu">
				<li class="menu-btn"><a  href="/searchProduct.do?reqPage=1&mainCategory=all&searchBox=">상품관리</a></li>
				<li class="menu-btn"><a href="/paymentList.do?reqPage=1&type=전체">주문관리</a></li>
			</ul>
		</div>
		<div class="page-title"><h3>상품관리</h3></div>
		
		<div class="page-top">
			<div>
				<%if(mainCategory.equals("outer")){ %>
					<select id="mainCategory" name="main-category">
						<option value = "all">ALL</option>
						<option value="outer" selected>OUTER</option>
						<option value="top">TOP</option>
						<option value="bottom">BOTTOM</option>
					    <option value="acc">ACC</option>
					</select>
				<%}else if(mainCategory.equals("top")){ %>
					<select id="mainCategory" name="main-category">
						<option value = "all">ALL</option>
						<option value="outer" >OUTER</option>
						<option value="top" selected>TOP</option>
						<option value="bottom">BOTTOM</option>
					    <option value="acc">ACC</option>
					</select>
				<%}else if(mainCategory.equals("bottom")){ %>
					<select id="mainCategory" name="main-category">
						<option value = "all">ALL</option>
						<option value="outer">OUTER</option>
						<option value="top">TOP</option>
						<option value="bottom" selected>BOTTOM</option>
					    <option value="acc">ACC</option>
					</select>
				<%}else if(mainCategory.equals("acc")){ %>
					<select id="mainCategory" name="main-category">
						<option value = "all">ALL</option>
						<option value="outer">OUTER</option>
						<option value="top">TOP</option>
						<option value="bottom">BOTTOM</option>
					    <option value="acc" selected>ACC</option>
					</select>
				<%}else if(mainCategory.equals("all")){ %>
					<select id="mainCategory" name="main-category">
						<option value = "all" selected>ALL</option>
						<option value="outer">OUTER</option>
						<option value="top">TOP</option>
						<option value="bottom">BOTTOM</option>
					    <option value="acc">ACC</option>
					</select>
				<%} %>
						
				<!-- 검색박스 -->
				<div class="search-box">
					<input type="text" id="search" name="search" value="" placeholder="상품코드를 입력해주세요">
					<span class="material-icons" id="icons" >search</span>
				</div>
				<button type="button" class="btn searchbtn">검색</button>
			</div>
		</div>
		<div class="page-main">
			<button type="button" class="btn insert" >등록</button>
			<div class="tableDiv">
			
				<%if(list.size() == 0){ %>
						<div class='notfound' >조회결과가 없습니다.</div>
				<%}else{ %>
				<table class="tble">
					<tr class="tr-title">
						<!-- <th>번호</th> -->
						<th>상품코드</th>
						<th>상품명</th>
						<th>판매가</th>
						<th>재고수량</th>
						<th>서브카테고리</th>
						<th>수정/삭제</th>
					</tr>
					<%for(int i=1;i<list.size()+1;i++){ 
						Product p = list.get(i-1);
						
						%>
					<tr>
						<!-- <td><%=i%></td> -->
						<td><%=p.getProductId() %></td>
						<td><%=p.getProductName() %></td>
						<td>
								<%DecimalFormat df = new DecimalFormat("#,###");
								String price = df.format(p.getProductPrice());%>
								<%=price %>
						</td>
						<td><%=p.getProductCount() %></td>
						<td><%=p.getSubCategory() %></td>
						<td>
							<button class="btn modify">수정</button>
							<button class="btn delete">삭제</button>
						</td>
					</tr>
					<%} %>
				</table>
			</div>
			<div class="pageNaviDiv">
				<!-- pageNavi 불러오는 자리 -->
				<%=pageNavi %>
				
			</div>
			<%} %>
		</div>
	</div>
	<script>
		//등록버튼
		$(".insert").on("click",function(){
			location.href="/insertProductFrm.do";
		});
	
		//검색버튼
		$(".searchbtn").on("click",function(){
			const mainCategory = $("option:selected").val();
			const searchBox = $("#search").val();
			location.href="/searchProduct.do?reqPage=1&mainCategory="+mainCategory+"&searchBox="+searchBox;
		});
		$("#search").on("keyup",function(e){  
    	    if(e.keyCode == 13){
    	    	$(".searchbtn").click();
    	    }
    	});
		
		
		//수정버튼
		$(".modify").on("click",function(){
			const productId = $(this).parent().parent().children().eq(0).text();
			location.href="/modifyProductFrm.do?productId="+productId;
		});
		
		//삭제버튼
		$(".delete").on("click",function(){
			const productId = $(this).parent().parent().children().eq(0).text();
			console.log(productId);
			if(confirm("삭제하시겠습니까?")){
				location.href="/deleteProduct.do?productId="+productId;
			}
		});
	</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>

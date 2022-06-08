<%@page import="java.text.DecimalFormat"%>
<%@page import="trendy.product.vo.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  	<%
    	ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
    	String pageNavi = (String)request.getAttribute("pageNavi");
    	int total = (Integer)request.getAttribute("total");
    	String searchValue = (String)request.getAttribute("searchValue");
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품검색결과</title>
<style>
	.page-content{
		width: 1200px;
		margin : 0 auto;
		padding-bottom : 30px;
		paddong-top : 30px;
	}
	.page-title{
		text-align: center;
		font-size: 35px;
		padding : 30px;
	}
	.page-top{
		overflow:hidden;
		border: 1px solid #ccc;
		border-radius: 10px;
		padding: 5px;
		margin-bottom: 30px;
	}
	.total{
		height: 30px;
		line-height: 30px;
		float: left;
	}
	.sort{
		overflow: hidden;
	}
	.sort>.sort-navi{
		list-style-type:none;
		padding: 0;
		margin: 0;
		text-align: center;
		display:block;
		float: right;
		oveflow: hidden;
	}
	.sort>.sort-navi>li{
		width: 90px;
		float: left;
	}
	.sort>.sort-navi>li>a{
		display:block;
		height: 30px;
		line-height: 30px;
		text-decoration:none;
		color: black;
	}
	.sort>.sort-navi>li:hover{
		font-weight: bolder;
	}
	.page-main{
		overflow: hidden;
	}
	.page-main>.item{
		box-sizing: border-box;
  		width: calc(100% / 4);
  		padding: 10px;
  		float: left;
	}
	.page-main>.item>ul{
		list-style-type:none;
		padding: 0;
		text-align: center;
	}
	.page-main>.item>ul>li{
		text-decoration:none;
		overflow:hidden;
	}
	#img{
		width: 280px;
		height: 370px;
		
	}
	#img:hover{
    	transform: scale(1.2, 1.2); 
    	cursor: pointer;
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
	}
	.pagination>li:hover{
		font-size: 1.2em;
	}
	.active-page{
		background-color: orange;
		color: white;
	}
	.notfound{
		text-align: center;
		font-size: 20px;
		padding : 50px;
	}
</style>

</head>
<body>
<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
	<hr><br>
	<%if(list.size() == 0){ %>
				<div class="notfound">조회된 상품이 없습니다.</div>
	<%}else {%>
		<div class="page-top">
			<div class="total">
				총 <%=total %>개의 상품이 검색되었습니다.
			</div>
			<div class="sort">
				<ul class="sort-navi">
					<li><a href="/mainSearchResult.do?reqPage=1&sort=new&searchValue=<%=searchValue %>">최신순</a></li>
					<li><a href="/mainSearchResult.do?reqPage=1&sort=high&searchValue=<%=searchValue %>">높은가격순</a></li>
					<li><a href="/mainSearchResult.do?reqPage=1&sort=low&searchValue=<%=searchValue %>">낮은가격순</a></li>
				</ul>
			</div>
		</div>
		<div class="page-main">
				<%for(Product p : list) {%>
					<div class="item">
					<ul>
						<li><a href="/productViewPage.do?productId=<%=p.getProductId() %>&reqPage=1"><img src="/upload/product/<%=p.getProductFilepath() %>" id="img"></a> </li>
						<li><%=p.getProductName() %></li>
						<li>
							<%DecimalFormat df = new DecimalFormat("#,###");
								String price = df.format(p.getProductPrice());%>
							<%=price %>
						</li>
					</ul>
				</div>
				<%} %>
		</div>
		<div class="pageNavi">
					<%=pageNavi %>
		</div>
	<%} %>	
	</div>

<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
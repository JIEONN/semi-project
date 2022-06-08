<%@page import="java.text.DecimalFormat"%>
<%@page import="trendy.product.vo.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
    	ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
    	String pageNavi = (String)request.getAttribute("pageNavi");
    	String mainCategory = (String)request.getAttribute("mainCategory");
    	String subCategory = (String)request.getAttribute("subCategory");
    	int totalCount = (Integer)request.getAttribute("totalCount");
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=mainCategory %></title>
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
		font-weight: 500;
	}
	.page-navi{
		padding: 40px;
	}
	.navi-menu{
		list-style-type:none;
		padding: 0;
		overflow: hidden;
		text-align: center;
		display: flex;
		justify-content: center;
	}
	.navi-menu>li{
		float: left;
	}
	.navi-menu>li>a{
		text-decoration:none;
		display: block;
		height: 30px;
		line-height: 30px;
		color: black;
	}
	.page-navi>.navi-menu>li>a:hover{
		font-weight: bolder;
	}
	.page-navi>.navi-menu>li{
		width: 150px;
	}
	.sort>.navi-menu>li{
		width: 80px;
	}
	.sort{
		overflow: hidden;
		border-bottom: 1px solid black;
		height: 50px;
	}
	.sort>.navi-menu{
		display:block;
		float: right;
	}
	.sort>.total{
		height: 50px;
		line-height: 50px;
		float: left;
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
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>
	<div class="page-content">
		<div class="page-title">
			<%if(mainCategory.equals("outer")) {%>
				OUTER
			<%} else if(mainCategory.equals("top")) {%>
				TOP
			<%} else if(mainCategory.equals("bottom")) {%>
				BOTTOM
			<%} else if(mainCategory.equals("acc")) {%>
				ACC
			<%} %>
		</div>
		<div class="page-top">
			<div class="page-navi">
			<%if(mainCategory.equals("outer")) {%>
				<ul class="navi-menu">
					<li><a href="/productListView.do?reqPage=1&mainCategory=outer&subCategory=all&sort=new">All</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=outer&subCategory=jumper&sort=new">JUMPER</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=outer&subCategory=suit&sort=new">SUIT</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=outer&subCategory=cardigan&sort=new">CARDIGAN</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=outer&subCategory=coat&sort=new">COAT</a></li>
				</ul>
			<%} else if(mainCategory.equals("top")) {%>
				<ul class="navi-menu">
					<li><a href="/productListView.do?reqPage=1&mainCategory=top&subCategory=all&sort=new">ALL</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=top&subCategory=knit&sort=new">KNIT</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=top&subCategory=hood&sort=new">HOOD</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=top&subCategory=shirt&sort=new">SHIRT</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=top&subCategory=tshirt&sort=new">TSHIRT</a></li>
				</ul>
			<%} else if(mainCategory.equals("bottom")) {%>
				<ul class="navi-menu">
					<li><a href="/productListView.do?reqPage=1&mainCategory=bottom&subCategory=all&sort=new">ALL</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=bottom&subCategory=slacks&sort=new">SLACKS</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=bottom&subCategory=denim&sort=new">DENIM</a></li>
				</ul>
			<%} else if(mainCategory.equals("acc")) {%>
				<ul class="navi-menu">
					<li><a href="/productListView.do?reqPage=1&mainCategory=acc&subCategory=all&sort=new">ALL</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=acc&subCategory=shoes&sort=new">SHOES</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=acc&subCategory=bag&sort=new">BAG</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=acc&subCategory=cap&sort=new">CAP</a></li>
				</ul>
			<%} %>
			</div>
			<div class="sort">
				<div class="total">ALL PRODUCT : <%=totalCount%></div>
				<ul class="navi-menu">
					<li><a href="/productListView.do?reqPage=1&mainCategory=<%=mainCategory %>&subCategory=<%=subCategory %>&sort=new">신상품</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=<%=mainCategory %>&subCategory=<%=subCategory %>&sort=low">낮은가격</a></li>
					<li><a href="/productListView.do?reqPage=1&mainCategory=<%=mainCategory %>&subCategory=<%=subCategory %>&sort=high">높은가격</a></li>
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
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>

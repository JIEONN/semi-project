<%@page import="trendy.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Member m = (Member) session.getAttribute("m");
%>
<!DOCTYPE html>
<html>
<style>
#login-modal .input-box {
	width: 80%;
	margin: 0 auto;
	border-bottom: none;
}

#login-modal .input-box>a:hover {
	text-decoration: none;
}

#login-modal .login-btn {
	width: 100%;
	margin: 0 auto;
	padding-left: 0;
}

#memberId{
	border: none;
	border-bottom: 1px solid #ccc;
	font-size: 20px;
	outline: none;
	padding-bottom: 10px;
	padding: 0.8rem;
}
#memberPw{
	border: none;
	border-bottom: 1px solid #ccc;
	font-size: 20px;
	outline: none;
	padding-bottom: 10px;
	padding: 1.0rem;
}

.login-btn{
	text-align: center;
	
}
.login-btn>button {
	width: 80%;
	height:65px;
	margin-top: 30px;
	border: none;
	border-radius:10px;
	font-size: 25px;
	font-weight: bold;
	background-color: orange;
	color: white;
}



.login-btn>button:hover {
	cursor: pointer;
	color:orange;
	background-color: white;
	border: 1px solid orange;
	transition:0.5s;
}

#login-modal .input-box.link-box {
	text-align: left;
}

.modal-bg {
	position: fixed; /*fixed가 의미하는 바가 뭔지 알아보기*/
	width: 100%;
	height: 100vh;
	background-color: rgba(0, 0, 0, 0.3);
	top: 0;
	left: 0;
	display: none;
	z-index: 999;
}

.modal-wrap {
	margin-top: 150px;
	width: 600px;
	background: #fff;
	height: 500px;
	margin: 150px auto;
	z-index: 1050;
	border-radius: 10px;
	
}

.modal-wrap div {
	padding: 1rem;
	line-height: 
	
}
.link-box{
	padding: 0px;
}


.modal-wrap div.modal-head {
	position: relative;
}

.modal-head>h2{
	text-align: center;
	font-size: 2.5em;
	margin-bottom: 0px;
	color: #333333;
}

.close-icon {
	position: absolute;
	top: 30px;
	right: 30px;
	font-size: 30px;
}

.close-icon:hover {
	cursor: pointer;
}

.modal-content .input-box>label {
	display: inline-block;
	width: 30%;
	margin-left: 20px;
	font-size: 1.2em;
	
}

.modal-content .input-box>input {
	width: 50%;
	height: 20px;
}

.link-box>a {
	display: inline-block;
	text-decoration: none;
	color: #333333;
}

.link-box>a:first-child{
	margin-left: 40px;
}

.link-box>a:last-child {
	float: right;
	margin-right: 40px;
}

.link-box>a:hover {
	color: orange;
	transition:0.5s;
}

.cart-btn {
	display: none;
}

.navi{
	z-index: 800;
}
.header-content{
	background-color: #ccc;
	
}
.logo{
	text-decoration: none;
}

*{
	font-family: 'Noto Sans KR', sans-serif;
}

</style>
<head>
<meta charset="UTF-8">
<title>trendy</title>
<!-- 구글 아이콘 -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!-- jquery -->
<script src="/js/jquery-3.6.0.js"></script>
<!-- 기본 CSS -->
<link rel="stylesheet" href="/css/trendy.css" />
<!-- 구글폰트 Noto Sans Korean -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500&display=swap" rel="stylesheet">
<!-- 구글폰트 Noto Sans Korean 끝  -->
<header>
	<a class="logo" href="/">
		<h1 class="logo-title">trendy</h1>
	</a>
	<div class="header-content">
		<div class="search">
	        <input type="text" id="searchinput" placeholder="검색">
	        <span class="material-icons search-icon" id="icons">search</span>
	    </div>
		<div class="memberInfo">
			<%
			if (m == null) {
			%>
			<a href="#" class="modal-open">LOGIN </a> <a href="/signUpFrm.do"> SIGN
				UP</a>
			<%
			} else if (m.getAdmin() == 0) {
			%>
			<a href="/paymentList.do?reqPage=1&type=전체"><%=m.getMemberName()%>관리자님 </a> <a
				href="/logout.do"> LOGOUT</a>
			<%
			} else if (m.getAdmin() == 1) {
			%>
			<a href="/userMypage.do"><%=m.getMemberName()%>님 </a> <a
				href="/logout.do"> LOGOUT</a>
			<%
			}
			%>
		</div>
	</div>
	<nav>
		<ul class="navi">
			<li><a href="#">SHOP</a>
				<ul class="sub-navi">
                    <li><a href="/productListView.do?reqPage=1&mainCategory=outer&subCategory=all&sort=new">OUTER</a></li>
                    <li><a href="/productListView.do?reqPage=1&mainCategory=top&subCategory=all&sort=new">TOP</a></li>
                    <li><a href="/productListView.do?reqPage=1&mainCategory=bottom&subCategory=all&sort=new">BOTTOM</a></li>
                    <li><a href="/productListView.do?reqPage=1&mainCategory=acc&subCategory=all&sort=new">ACC</a></li>
                </ul>
            </li>
            <li><a href="#">COMMUNITY</a>
				<ul class="sub-navi">
					<%if(m!=null) {%>
					<li><a href="/qnaList.do?reqPage=1">QNA</a></li>
					<%} else{%>
					<li><a href="#" class="modal-open">QNA</a></li>
					<%} %>
				</ul></li>
			<li><a href="#">ACCOUNT</a>
				<ul class="sub-navi">
					<%
					if (m == null) {
					%>

					<%
					} else if (m.getAdmin() == 0) {
					%>
					<li><a href="/paymentList.do?reqPage=1&type=전체">ADMIN PAGE</a></li>
					<%
					} else if (m.getAdmin() == 1) {
					%>
					<li><a href="/userMypage.do">MY PAGE</a></li>
					<%
					}
					%>
					<%
					if (m == null) {
					%>
					<li><a href="#" class="modal-open">CART</a></li>
					<%
					} else if (m.getAdmin() == 0) {
					%>

					<%
					} else if (m.getAdmin() == 1) {
					%>
					<li><a href="#" class="cart-click">CART</a></li>
					<form action="/myCart.do" method="post">
						<input type="hidden" name="memberIdCart" id="memberIdCart"
							value="<%=m.getMemberId()%>">
						<button type="submit" class="cart-btn"></button>
					</form>
					<%
					}
					%>
				</ul></li>
			<li><a href="/event.do">EVENT</a></li>
		</ul>
	</nav>
</header>
<%
if (m == null) {
%>
<div id="login-modal" class="modal-bg">
	<div class="modal-wrap">
		<div class="modal-head">
			<h2>LOGIN</h2>
			<span class="material-icons close-icon modal-close">close</span>
		</div>
		<form action="/signIn.do" method="post">
			<div class="modal-content">
				<div class="input-box">
					<label for="input-box" for="memberId">아이디</label> <input
						type="text" class="input-form" name="memberId" id="memberId"
						placeholder="아이디입력">
				</div>
				<div class="input-box">
					<label for="input-box" for="memberPw">비밀번호</label> <input
						type="password" class="input-form" name="memberPw" id="memberPw"
						placeholder="비밀번호입력">
				</div>
				<div class="login-btn">
					<button type="submit">로그인</button>
					<!-- submit을 안해도 괜찮지만, 하면 엔터로도 submit하게해준다. -->
				</div>
				<div class="link-box">
                <a href="/idSearchFrm.do">아이디</a> / 
                <a href="/pwSearchFrm.do">비밀번호 찾기</a>
                <a href="/signUpFrm.do">회원 가입</a>
             </div>
			</div>
		</form>
	</div>
</div>
<%
}
%>
</head>
<script>
	$(".cart-click").on("click", function() {
		$(".cart-btn").trigger("click");
	});
	
	$(function () {
		  $(document).on("click", ".modal-open", function () {
		    $($(".modal-wrap").parent().css("display", "flex"));
		  });
		  $(document).on("click", ".modal-close", function () {
		    $(this).parents(".modal-wrap").parent().css("display", "none");
		  }); 
		});
	
	$(function(){
		$(".search-icon").on("click",function(){
			searchValue = $("#searchinput").val();
			location.href="/mainSearchResult.do?reqPage=1&sort=new&searchValue="+searchValue;
		});
		$("#searchinput").on("keyup",function(e){  
    	    if(e.keyCode == 13){
    	    	searchValue = $("#searchinput").val();
    			location.href="/mainSearchResult.do?reqPage=1&sort=new&searchValue="+searchValue;
    	    }
    	});
	});
</script>
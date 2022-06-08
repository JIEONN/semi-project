<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.page-content {
	margin-top: 80px;
	margin-bottom:150px;
}

.imgs li img {
	width: 650px;
	height: 750px;
	padding: 0;
	margin: 0;
	margin: 30px;
}

ul.imgs {
	padding: 0;
	margin: 0;
}

ul.imgs li {
	position: absolute;
	list-style: none;
	padding: 0;
	margin: 0;
	border-radius: 30px;
}

.slider {
	width: 650px;
	height: 750px;
	position: relative;
	margin: 0 auto;
}

.slider input[type=radio] {
	display: none;
}

.bullets {
	position: absolute;
	left: 50%;
	transform: translateX(-50%);
	bottom: 20px;
	z-index: 2;
}

.bullets label {
	display: inline-block;
	border-radius: 50%;
	background-color: rgba(0, 0, 0, 0.55);
	width: 20px;
	height: 20px;
	cursor: pointer;
}

/* 1 */
.slider input[type=radio]:nth-child(1):checked ~ul.imgs>li:nth-child(1)
	{
	left: 0;
	transition: 0.5s;
	z-index: 1;
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

.slider input[type=radio]:nth-child(1):checked ~ul.imgs>li:nth-child(2)
	{
	left: 90px;
	transition: 0.5s;
	z-index: 0;
	transform: scale(0.9);
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

.slider input[type=radio]:nth-child(1):checked ~ul.imgs>li:nth-child(3)
	{
	left: 170px;
	transition: 0.5s;
	z-index: -1;
	transform: scale(0.8);
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

.slider input[type=radio]:nth-child(1):checked ~ul.imgs>li:nth-child(4)
	{
	left: 230px;
	transition: 0.5s;
	z-index: -2;
	transform: scale(0.7);
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

/* 2 */
.slider input[type=radio]:nth-child(2):checked ~ul.imgs>li:nth-child(1)
	{
	left: -90px;
	transition: 0.5s;
	z-index: 0;
	transform: scale(0.9);
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

.slider input[type=radio]:nth-child(2):checked ~ul.imgs>li:nth-child(2)
	{
	left: 0;
	transition: 0.5s;
	z-index: 1;
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

.slider input[type=radio]:nth-child(2):checked ~ul.imgs>li:nth-child(3)
	{
	left: 90px;
	transition: 0.5s;
	z-index: 0;
	transform: scale(0.9);
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

.slider input[type=radio]:nth-child(2):checked ~ul.imgs>li:nth-child(4)
	{
	left: 170px;
	transition: 0.5s;
	z-index: -1;
	transform: scale(0.8);
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

/* 3 */
.slider input[type=radio]:nth-child(3):checked ~ul.imgs>li:nth-child(1)
	{
	left: -170px;
	transition: 0.5s;
	z-index: -1;
	transform: scale(0.8);
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

.slider input[type=radio]:nth-child(3):checked ~ul.imgs>li:nth-child(2)
	{
	left: -90px;
	transition: 0.5s;
	z-index: 0;
	transform: scale(0.9);
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

.slider input[type=radio]:nth-child(3):checked ~ul.imgs>li:nth-child(3)
	{
	left: 0;
	transition: 0.5s;
	z-index: 1;
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

.slider input[type=radio]:nth-child(3):checked ~ul.imgs>li:nth-child(4)
	{
	left: 90px;
	transition: 0.5s;
	z-index: 0;
	transform: scale(0.9);
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

/* 4 */
.slider input[type=radio]:nth-child(4):checked ~ul.imgs>li:nth-child(1)
	{
	left: -230px;
	transition: 0.5s;
	z-index: -2;
	transform: scale(0.7);
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

.slider input[type=radio]:nth-child(4):checked ~ul.imgs>li:nth-child(2)
	{
	left: -170px;
	transition: 0.5s;
	z-index: -1;
	transform: scale(0.8);
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

.slider input[type=radio]:nth-child(4):checked ~ul.imgs>li:nth-child(3)
	{
	left: -90px;
	transition: 0.5s;
	z-index: 0;
	transform: scale(0.9);
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}

.slider input[type=radio]:nth-child(4):checked ~ul.imgs>li:nth-child(4)
	{
	left: 0;
	transition: 0.5s;
	z-index: 1;
	box-shadow: 14px -5px 35px -21px rgba(0, 0, 0, 0.66);
}
/* 현재 선택된 불릿 배경 흰색으로 구분 표시 */
.slider input[type=radio]:nth-child(1):checked ~.bullets>label:nth-child(1)
	{
	background-color: #fff;
}

.slider input[type=radio]:nth-child(2):checked ~.bullets>label:nth-child(2)
	{
	background-color: #fff;
}

.slider input[type=radio]:nth-child(3):checked ~.bullets>label:nth-child(3)
	{
	background-color: #fff;
}

.slider input[type=radio]:nth-child(4):checked ~.bullets>label:nth-child(4)
	{
	background-color: #fff;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="page-content">
		<div class="slider">
			<input type="radio" name="slide" id="slide1" checked> <input
				type="radio" name="slide" id="slide2"> <input type="radio"
				name="slide" id="slide3"> <input type="radio" name="slide"
				id="slide4">
			<ul id="imgholder" class="imgs">
				<li><img src="/upload/mainpage/main2.png"></li>
				<li><img src="/upload/mainpage/main1.png"></li>
				<li><img src="/upload/mainpage/main3.png"></li>
				<li><img src="/upload/mainpage/main4.png"></li>
			</ul>
			<div class="bullets">
				<label for="slide1">&nbsp;</label> <label for="slide2">&nbsp;</label>
				<label for="slide3">&nbsp;</label> <label for="slide4">&nbsp;</label>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
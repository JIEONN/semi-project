<%@page import="trendy.qna.vo.QnaReply"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
		String qnaWriter = (String)request.getAttribute("qnaWriter");
		int qnaRef = (Integer)request.getAttribute("qnaRef");
		String qnaProductId = (String)request.getAttribute("qnaProductId");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.w-btn {
	position: relative;
	display: inline-block;
	padding: 15px 30px;
	font-family: "paybooc-Light", sans-serif;
	box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
	text-decoration: none;
	font-weight: 600;
	background-color: orange;
	color: white;
	border: 1px solid orange;
	border-radius: 10px;
	transition: 0.5s;
	margin-bottom: 20px;
	float: right;
}
.w-btn:hover{
	background-color: white;
	color: orange;
	border: 1px solid orange;
}
.page-content {
	text-align: center;
}

.page-content>form {
	display: inline-block;
}

.qnatbl {
	margin: 0 0;
}

textarea {
	height: 40em;
	border: none;
	resize: none;
	box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
	width: 500px;
}

.qnaCategory {
	width: 200px;
	height: 40px;
	border: none;
	color: gray;
	text-align: center;
	font-size: 18px;
}

th {
	text-align: right;
}

.page-content {
	font-size: 20px;
}

.page-title {
	font-size: 30px;
	margin-bottom: 15px;
}
	
</style>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="page-content">
		<div class="page-title" style="font-weight: bold">답글달기</div>
		<form action="/qnaReWriter.do" method="post" onsubmit="check();">
			<table id="writertbl">
				<tr>
					<td colspan="4"><textarea id="qnaContent" name="qnaReContent"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: right">
						<button type="submit" class="w-btn w-btn-indigo" id="test">답글달기</button>
					</td>
				</tr>
			</table>
			<input type="hidden" name="qnaWriter" value="<%=qnaWriter%>">
			<input type="hidden" name="qnaRef" value=<%=qnaRef%>>
			<input type="hidden" name="qnaProductId" value="<%=qnaProductId%>">			
		</form>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
<script>
function check(){
	alert("답글 작성을 완료했습니다.");
}
</script>
</html>
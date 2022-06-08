<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	width: 100%;
}
.qnaCategory {
	width: 200px;
	height: 40px;
	border:none;
	color:gray;
	text-align: center;
	font-size: 18px;
}
th {
	text-align: right;
}

.page-content{
	font-size: 20px;
}
.page-title{
	font-size: 30px;
	margin-bottom:15px;
}

</style>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<script src="/summernote/summernote-lite.js"></script>
	<script src="/summernote/lang/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="/summernote/summernote-lite.css">
	
	<div class="page-content">
		<div class="page-title" style="font-weight: bold">문의하기</div>
		<form action="/qnaWrite.do" method="post" enctype="multipart/form-data" onsubmit="check();">
			<table id="writertbl">
				<tr>
					<td>
					QnA
						<select class="qnaCategory" name="qnaCategory">
							<option value="상품문의">상품문의</option>
							<option value="반품문의">반품문의</option>
							<option value="교환문의">교환문의</option>
							<option value="배송문의">배송문의</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="4"><textarea id="qnaContent" name="qnaContent"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" style="text-align: right">
						<button type="submit" class="w-btn w-btn-indigo">제출하기</button>
					</td>
				</tr>
			</table>
			<input type="hidden" name="qnaWriter" value="<%=m.getMemberId()%>">
		</form>
	</div>
	<script>
	//섬머노트
	$("#qnaContent").summernote({
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
			url : "/uploadImage2.do",
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
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
<script>
function check(){
	alert("문의 작성을 완료했습니다.");
}
</script>
</html>
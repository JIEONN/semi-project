<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호찾기</title>
<style>
.input-form:not(.input-box>.input-form) {
	width: 100%;
	display: inline-block;
	padding: 0.8rem;
	background-color: #fff;
	outline: none;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

.input-form:focus {
	box-shadow: 0 0 0 0.1rem #eee;
}

textarea.input-form {
	resize: none;
	min-height: 300px;
}

.input-form:read-only:not(select) {
	background-color: #f7f7f9;
}
h3{
	float:left;
	display: inline-block;
}
.input-wrap {
	width: 50%;
	padding: 15px;
	margin: 0px auto;
	text-align: center;
}

.input-wrap label {
	width: 100px;
	display: block;
}

.submit-btn {
	padding: 15px;
	margin: 20px 0px;
}

select {
	width: 200px;
	padding: .8em .5em;
	border: 1px solid #999;
	border-radius: 1px;
}

.submit-btn {
	text-align: center;
}

.submit-btn>button {
	background-color: orange;
	color: #fff;
	border: 1px solid white;
	border-radius: 10px; 
	
	width: 30%;
	height: 80px;
	font-size: 1.2em;
	margin: 10px auto;
}

.submit-btn>button:hover {
	background-color: white;
	color: orange;
	border:1px solid orange;
	cursor: pointer;
	transition:0.5s;
}

.btn {
	border: none;
	padding: 1rem;
	display: inline-block;
	box-sizing: border-box;
	transition-duration: 0.5s;
	font-size: 15px;
	font-family: ns-light;
	width: 100%;
	text-align: center;
	margin-top:20px;
	
}

.bc1 {
	background-color: orange;
	color: #fff;
	border: 1px solid white;
	border-radius: 10px; 
}

.bc1:hover {
	background-color: white;
	color: orange;
	border:1px solid orange;
	cursor: pointer;
}
#auth span{
	font-size: 13px;
	
}

#auth>span{
	display:inline-block;
	margin-top: 25px;
	margin-bottom: 10px;
	
}

</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<form action="/pwSearch.do" method="post" onsubmit="return check()">
		<div class="input-wrap">
			<label for="memberId"><h3>아이디</h3></label> <input type="text" name="memberIdp" id="memberIdp" class="input-form">
		</div>
		<div class="input-wrap">
			<label for="email"><h3>이메일</h3></label> <input type="text" name="email" id="email" class="input-form">
			<button type="button" class="btn bc1" onclick="checkMember();">인증메일 전송</button>
		</div>
		<div class="input-wrap">
			<div id="auth" style="display:none">
				<label for="checkNum"><h3>인증번호</h3></label><span id="timeZone"></span>
				<span id="authMsg"></span> <input  type="text" id="authCode" placeholder="인증코드입력" class="input-form"> 
				<button type="button" class="btn bc1" id="authBtn">인증하기</button>
				
			</div>
		</div>
		<div class="submit-btn">
			<button id="submitBtn" type="submit">확인</button>
		</div>
	</form>
	<script>		
		let mailCode;
		let email;
		function checkMember(){
			const memberId = $("#memberIdp").val();
			email = $("#email").val();
			console.log(email);
			$.ajax({
				url: "/checkMemberPw.do",
				data: {memberId:memberId,email:email},
				type : "post",
				success : function(data){
					if(data == "이메일을 입력해주세요."){
						alert(data);
					}else if(data == "일치하는 계정이 없습니다."){
						alert(data);						
					}else{
						console.log(data);
						sendMail();
					}
				},
				error : function(){
					console.log("에러");
				}
			});
		}
		function sendMail() {
			email = $("#email").val();
			$.ajax({
				url : "/mailSend.do",
				data : {email : email},
				type : "post",
				success : function(data) {
					mailCode = data;
					console.log(mailCode);
					$("#auth").slideDown();
					authTime();
				},
				error : function() {
					console.log("에러");
				}
			});
		}
		function authTime() {
			$("#timeZone").html(
					"<span id='min'>3</span> : <span id='sec'>00</span>");
			intervalId = window.setInterval(function() {
				timeCount();
			}, 1000);
		}
		function timeCount() {
			const min = Number($("#min").text());
			const sec = $("#sec").text();
			if (sec == "00") {
				if (min == 0) {
					mailCode = null;
					clearInterval(intervalId);
				} else {
					$("#min").text(min - 1);
					$("#sec").text(59);
				}
			} else {
				const newSec = Number(sec) - 1;
				if (newSec < 10) {
					$("#sec").text("0" + newSec);
				} else {
					$("#sec").text(newSec);
				}
			}
		}
		$("#authBtn").on("click", function() {
			const msg = $("#authMsg");
			if (mailCode == null) {
				msg.text("인증시간이 만료되었습니다.");
				msg.css("color", "red");
			} else {
				if ($("#authCode").val() == mailCode) {
					msg.text("인증성공");
					msg.css("color", "blue");
					clearInterval(intervalId);
					$("#timeZone").hide();
				} else {
					msg.text("인증코드를 확인하세요");
					msg.css("color", "red");
				}
			}
		});
		function check(){
			const msg = $("#authMsg").text();
			console.log(msg);
			if(msg == "인증성공"){
				return true;
			}else{
				alert("이메일을 인증해주세요!");				
				return false;
			}
		}
	</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
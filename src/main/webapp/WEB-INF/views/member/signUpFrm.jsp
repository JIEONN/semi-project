<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<style>
#postcode{
	margin-bottom: 10px;
}
.input-form{
  width: 100%;
  display: block;
  padding: 0.8rem;
  background-color: #fff;
  box-sizing: border-box;
  display: inline-block;
  border: none;
  border-bottom: 1px solid #ccc;
  font-size: 20px;
  outline: none;
  padding-bottom: 10px;
}
.input-form:focus {
  box-shadow: 0 0 0 0.1rem #eee;
}

textarea.input-form {
  resize: none;
  min-height: 300px;
}
.input-form:read-only:not(select){
  /*background-color: #f7f7f9*/;
  background-color: #f5f5f5;
  border: none !important;
}
.input-wrap{
     width: 40%;
    padding: 15px;
    margin: 10px auto;
}

.phone-input>input{
	width:32%;
	display: inline-block;
}
.input-wrap>label{
    font-size: 1.1em;
    margin-bottom: 10px;
    display: block;
}
.submit-btn{
    padding : 15px;
    margin: 20px 0px;
}
.input-wrap>.id-wrap{
    display:flex
}
select { 
    width: 200px;
    padding: .8em .5em;
    border: 1px solid #999; border-radius: 1px; 
}
.submit-btn{
    text-align: center;
}
.submit-btn>button{
  background-color: orange;
  color: white;
  border: 1px solid white;
  transition : 0.5s;
  width: 40%;
  height:80px;
  font-size:1.2em;
  margin: 10px auto;
  border-radius:5px; 
}
.submit-btn>button:hover{
    background-color: white;
	border: 1px solid orange;
	color:orange;
  	cursor: pointer;
}
.page-content>h1{
	margin-bottom: 40px;
}
.phone-input>input{
    width:30%;
    display: inline-block;
}
#addr-btn{
	margin-bottom: 10px;
	background-color: orange;
	color: white;
	border: 1px solid orange;
	border-radius:5px; 
}
#addr-btn:hover{
	background-color:white;
	color:orange;
	border: 1px solid orange;
	transition:0.5s;
}
h4{
	display: inline-block;
}
.phone-input > input{
	text-align: center
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<form action="/signUp.do" method="post" onsubmit="return checkAll()">
			<div class="input-wrap">
				<label for="memberId"><h3>아이디</h3><span id="ajaxIdCheckResult"></span></label>
				<div class="id-wrap">
					<input type="text" name="memberId" id="memberId" class="input-form">
				</div>
			</div>
			<div class="input-wrap">
				<label for="memberPw"><h3>비밀번호</h3></label>
				<input type="password" name="memberPw" id="memberPw" class="input-form" autocomplete="off">
			</div>
			<div class="input-wrap">
				<label for="memberPw2"><h3>비밀번호확인</h3><span id="ajaxPwReCheckResult"></span></label>
				<input type="password" name="memberPw2" id="memberPw2" class="input-form" autocomplete="off">
			</div>
			<div class="input-wrap">
				<label for="memberName"><h3>이름</h3></label>
				<input type="text" name="memberName" id="memberName" class="input-form">
			</div>
			<div class="input-wrap">
				<label for="age"><h3>나이</h3></label>
				<input type="text" name="age" id="age" class="input-form">
			</div>
			<div class="input-wrap">
				<label for="email"><h3>이메일</h3></label>
				<input type="text" name="email" id="email" class="input-form">
			</div>	
			<div class="input-wrap">					
					<label for="address"><h3>주소</h3></label>
					<input type="hidden" name="address" id="address" class="input-from">
					<button  type="button" onclick="searchAddr();" id="addr-btn">주소찾기</button><br>
					<input type="text" name="postcode" id="postcode" class="input-form" placeholder="우편번호" readonly>
					<input type="text" name="address1" id="address1" class="input-form" placeholder="주소" readonly>
					<input type="text" name="detailAddress" id="detailAddress" class="input-form" placeholder="상세주소">
			</div>
			<div class="input-wrap phone-input" >
				<label for="phone"><h3>전화번호</h3></label>
				<input type="text" name="phone1" id="phone1" class="input-form" maxlength='3' value="010" readonly>
				<input type="text" name="phone2" id="phone2" class="input-form" maxlength='4'>
				<input type="text" name="phone3" id="phone3" class="input-form" maxlength='4'>
				<input type="hidden" name="phone" id="phone" class="input-form">
			</div>
			<div class="input-wrap">
				  <label for="gender"><h3>성별</h3></label>
				  <select name="gender" id="gender">
				  	<option value="M">남자</option>
				  	<option value="F">여자</option>
				  </select>
			</div>
			
			<div class="submit-btn">
				<button type="submit" name="signUp-btn">회원가입</button>
			</div>
	</form>
</body>
<script>
//daum주소입력코드
function searchAddr(){
	new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.		        
            $("#postcode").val(data.zonecode);
            $("#address1").val(data.roadAddress);
            $("#detailAddress").focus();
        }
    }).open();
}

//아이디 중복검사
$("[name=memberId]").eq(1).on("change",function(){
	const memberId = $(this).val();
	
	$.ajax({
		url: "/ajaxCheckId.do",
		type: "get",
		data: {memberId : memberId},
		success: function(data){
			if(data=="1"){
				$("#ajaxIdCheckResult").text("  이미 사용중인 아이디입니다.");
				$("#ajaxIdCheckResult").css("color","#ff2e63");
			}else if(data=="0"){
				$("#ajaxIdCheckResult").text("  사용가능한 아이디입니다.");
				$("#ajaxIdCheckResult").css("color","#00adb5");
			}
		},
		error: function(){
			console.log("서버요청실패");
		}
	});
});
//비밀번호 확인 비밀번호와 일치하는지 확인
$("[name=memberPw2]").on("change",function(){
	const memberPwCheck = $(this).val();
	
	if(memberPwCheck!=($("[name=memberPw]").eq(1).val())){
		$("#ajaxPwReCheckResult").text("  비밀번호가 일치하지 않습니다.");
		$("#ajaxPwReCheckResult").css("color","#ff2e63");
	}else{
		$("#ajaxPwReCheckResult").text("");
	}
});

function checkAll() {
    var getId= RegExp(/^[a-zA-Z0-9]{4,12}$/);
    var getPw= RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/);
    var getName= RegExp(/^[가-힣]{2,8}$/);
    var getAge = RegExp(/^[0-9]{1,3}$/);
    var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
    var getPhone = RegExp(/^\d{3}-\d{4}-\d{4}$/);
    
  	//주소 합치는 코드
    var address1 = $("#address1").val();
    var detailAddress = $("#detailAddress").val();
    var address = (address1+" "+detailAddress);
    console.log(address);
    $("#address").val(address);	

    
    //핸드폰 번호 묶어서 주는 코드
    var phone1 = $("#phone1").val();
    var phone2 = $("#phone2").val();
    var phone3 = $("#phone3").val();
    var phone = (phone1+"-"+phone2+"-"+phone3);
    $("#phone").val(phone);
    
    //비밀번호 일치 확인 제한
    if($("#memberPw2").val()!=($("[name=memberPw]").eq(1).val())){
    	alert("비밀번호 확인창을 확인해주세요!");
    	$("#memberPw2").focus();
		return false;
	}
    
    //입력란 확인
    if($("[name=memberId]").eq(1).val() == ""){
        alert("아이디를 입력해주세요!");
        $("[name=memberId]").eq(1).focus();
        return false;
    }
    if( $("[name=memberPw]").eq(1).val() == ""){
        alert("비밀번호를 입력해주세요!");
        $("[name=memberPw]").eq(1).focus();
        return false;
    }
    if($("#memberPw2").val() == ""){
        alert("비밀번호 확인을 입력해주세요!");
        $("#memberPw2").focus();
        return false;
    }
    if($("#memberName").val() == ""){
        alert("이름을 입력해주세요!");
        $("#memberName").focus();
        return false;
    }
    if($("#age").val() == ""){
        alert("나이를 입력해주세요!");
        $("#age").focus();
        return false;
    }
    if($("#email").val() == ""){
        alert("이메일을 입력해주세요!");
        $("#email").focus();
        return false;
    }
    if($("#address1").val() == ""){
        alert("주소를 입력해주세요!");
        $("#detailAddress").focus();
        return false;
    }
    if($("#phone").val() == ""){
        alert("전화번호를 입력해주세요!");
        $("#phone").focus();
        return false;
    }
    
    //아이디 유효성 검사
    if(!getId.test($("[name=memberId]").eq(1).val())){
        alert("아이디를 형식에 맞게 입력해주세요(영문, 숫자 조합으로 4~12자리)");
        $("[name=memberId]").eq(1).focus();
        return false;
    }
    
    //비밀번호 유효성 검사
    if(!getPw.test($("[name=memberPw]").eq(1).val())){
        alert("형식에 맞게 입력해주세요(영문, 숫자, 특수기호 조합으로 8~20자리.)");
        $("[name=memberPw]").eq(1).val("");
        $("[name=memberPw]").eq(1).focus();
        return false;
    }
    
    //이름 유효성 검사
    if(!getName.test($("#memberName").val())){
    	alert("이름을 형식에 맞게 입력해주세요(한글 2~8글자)");
    	$("#memberName").focus();
    	return false;
    }
    
    //나이 유효성 검사
    if(!getAge.test($("#age").val())){
    	alert("나이를 형식에 맞게 입력해주세요(1~3자리 숫자)");
    	$("#age").focus();
    	return false;
    }
    
    //이메일 유효성 검사
    if(!getMail.test($("#email").val())){
    	alert("이메일을 이메일의 형식에 맞게 입력해주세요");
    	$("#email").focus();
    	return false;
    }
    
    //전화번호 유효성 검사
    if(!getPhone.test($("#phone").val())){
    	alert("전화번호를 형식에 맞게 입력해주세요(숫자 3자리 / 4자리 / 4자리)");
    	$("#phone1").focus();
    	return false;
    }    
}
</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</html>
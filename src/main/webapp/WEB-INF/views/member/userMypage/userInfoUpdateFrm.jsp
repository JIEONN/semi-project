<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
#ajaxPwReCheckResult{
	color: coral;
} 
.input-form {
  width: 100%;
  display: block;
  padding: 0.8rem;
  background-color: #fff;
  box-sizing: border-box;
  
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
  /*background-color: #f7f7f9;*/
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
.del-btn>button{
	background-color: lightcoral;
	color: white;
	border: 1px solid white;
	transition : 0.5s;
	border-radius:5px;
}
.del-btn>button:hover{
	background-color: white;
	border:1px solid lightcoral;
	color:lightcoral;
  	cursor: pointer;
}
.page-content>h1{
	margin-bottom: 40px;
}
#postcode{
	margin-bottom: 10px;
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
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div class="page-content">
		<h1>UPDATE INFO</h1>
		
		<form action="/userInfoUpdate.do" method="post" onsubmit="return checkUpdate()">
			<div class="update-wrap">
				
				<div class="input-wrap">
					<label for="memberId"><h3>아이디</h3></label>
					<input type="text" name="memberId" id="memberId" class="input-form" value="<%=m.getMemberId() %>" readonly>
				</div>
				<div class="input-wrap">
					<label for="memberPw"><h3>비밀번호</h3></label>
					<input type="hidden" name="memberPwNow" id="memberPwNow" class="input-form" value="<%=m.getMemberPw()%>">
					<input type="password" name="memberPw" id="memberPw" class="input-form" value="<%=m.getMemberPw() %>" autocomplete="off">
				</div>
				<div class="input-wrap">
					<label for="memberPw2"><h3>비밀번호확인</h3><span id="ajaxPwReCheckResult"> 비밀번호를 수정 시 입력해주세요</span></label>
					<input type="password" name="memberPw2" id="memberPw2" class="input-form" autocomplete="off">
				</div>
				<div class="input-wrap">
					<label for="memberName"><h3>이름</h3></label> 
					<input type="text" name="memberName" id="memberName" class="input-form" value="<%=m.getMemberName() %>">
				</div>
				<div class="input-wrap">					
						<label for="address"><h3>주소</h3></label>
						<span><h4>현재주소 | </h4> <%=m.getAddress() %></span>
						<input type="hidden" name="address" id="address" class="input-from" value="<%=m.getAddress() %>">
						<button type="button" id="addr-btn" onclick="searchAddr();">주소찾기</button><br>
						<input type="text" name="postcode" id="postcode" class="input-form" placeholder="우편번호" readonly>
						<input type="text" name="address1" id="address1" class="input-form" placeholder="주소" readonly><br>
						<input type="text" name="detailAddress" id="detailAddress" class="input-form" placeholder="상세주소">
				</div>
				<div class="input-wrap phone-input">
					<label for="phone"><h3>전화번호</h3></label>
					<span><h4>현재 전화번호 | </h4>  <%=m.getPhone() %></span><br>
					<span>수정할 전화번호 입력  </span><br>
					<input type="text" name="phone1" id="phone1" class="input-form" maxlength='3' value="010" readonly>
					<input type="text" name="phone2" id="phone2" class="input-form" maxlength='4'>
					<input type="text" name="phone3" id="phone3" class="input-form" maxlength='4'> 
					<input type="hidden" name="phone" id="phone" class="input-form" value="<%=m.getPhone()%>">
				</div>
				<div class="input-wrap">
					<label for="email"><h3>이메일</h3></label> 
					<input type="text" name="email"	id="email" class="input-form" value="<%=m.getEmail()%>">
				</div>
				<div class="submit-btn">
					<button type="submit" name="infoUpdate-btn">정보수정</button>
				</div>
			</div>
		</form>
		<div class="submit-btn del-btn">
			<button type="button" name="del-member-btn" onclick="checkDel()">회원 탈퇴</button>
		</div>
	</div>
<script>
function checkDel(){
	var result = confirm("정말 탈퇴하시겠습니까?");
	if(result){
		delMember();
	}	
}
function delMember(){
	const memberId = $("#memberId").val();
	console.log(memberId);
	location.href="/delete.do?memberId="+memberId;
}
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
//비밀번호 일치 확인
$("[name=memberPw2]").on("change",function(){
	const memberPwCheck = $(this).val();
	
	if(memberPwCheck!=($("[name=memberPw]").val())){
		$("#ajaxPwReCheckResult").text("  비밀번호가 일치하지 않습니다.");
		$("#ajaxPwReCheckResult").css("color","#ff2e63");
	}else{
		$("#ajaxPwReCheckResult").text("");
	}
});
function checkUpdate(){
	var result = confirm("정보를 수정하시겠습니까?");
	if(result){
	   
	}else{
	   return false;
	}
	
    var getPw= RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/);
    var getName= RegExp(/^[가-힣]{2,8}$/);
    var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
    var getPhone = RegExp(/^\d{3}-\d{4}-\d{4}$/);
    
 	
	
	//주소 묶어주는 코드
	if($("#postcode").val()!=""){
		var address1 = $("#address1").val();
		var postcode = $("#postcode").val();
		var detailAddress = $("#detailAddress").val();
		var address = (address1+" "+detailAddress);
		console.log(address);
		$("#address").val(address);		
	}
	
	
 	//비밀번호 수정을 원하는지 확인
    if($("#memberPw").val()!=$("#memberPwNow").val()){
	    if($("#memberPw2").val() == ""){
	        alert("비밀번호 확인을 입력해주세요!");
	        $("#memberPw2").focus();
	        return false;
	    }
	  	//비밀번호 일치 확인 제한
	    if($("#memberPw2").val()!=($("[name=memberPw]").val())){
	    	alert("비밀번호 확인창을 확인해주세요!");
	    	$("#memberPw2").focus();
			return false;
		}
    }
  
	//입력란 확인
    if( $("[name=memberPw]").val() == ""){
        alert("수정할 비밀번호를 입력해주세요!");
        $("[name=memberPw]").focus();
        return false;
    }
    if($("#memberName").val() == ""){
        alert("수정할 이름을 입력해주세요!");
        $("#memberName").focus();
        return false;
    }
    if($("#phone").val() == ""){
        alert("수정할 전화번호를 입력해주세요!");
        $("#phone1").focus();
        return false;
    }
    if($("#email").val() == ""){
        alert("수정할 이메일을 입력해주세요!");
        $("#email").focus();
        return false;
    }
    if($("#address").val() == ""){
        alert("수정할 주소를 입력해주세요!");
        $("#memberName").focus();
        return false;
    }
    
    //비밀번호 유효성 검사
    if(!getPw.test($("[name=memberPw]").val())){
        alert("형식에 맞게 입력해주세요(영문, 숫자, 특수기호 조합으로 8~20자리.)");
        $("[name=memberPw]").focus();
        return false;
    }
    
    //이름 유효성 검사
    if(!getName.test($("#memberName").val())){
    	alert("이름을 형식에 맞게 입력해주세요(한글 2~8글자)");
    	$("#memberName").focus();
    	return false;
    }
    
  //핸드폰 번호 묶어서 주는 코드
    var phone1 = $("#phone1").val();
    var phone2 = $("#phone2").val();
    var phone3 = $("#phone3").val();
    //전화번호 유효성 검사
    var phoneCheck = (phone2+phone3);
    console.log(phoneCheck);
    if(phoneCheck != ""){
    	var phone = (phone1+"-"+phone2+"-"+phone3);
        $("#phone").val(phone);
	    if(!getPhone.test($("#phone").val())){
	    	alert("전화번호를 형식에 맞게 입력해주세요(숫자 3자리 / 4자리 / 4자리)");
	    	$("#phone1").focus();
	    	return false;
	    }    	
    }
    
    //이메일 유효성 검사
    if(!getMail.test($("#email").val())){
    	alert("이메일을 이메일의 형식에 맞게 입력해주세요");
    	$("#email").focus();
    	return false;
    }
    
}
</script>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
</body>
</html>
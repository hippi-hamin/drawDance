<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/signup.css">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<script>
	$(function() {
		let msg = "${msg}";
		if(msg != ""){
			alert(msg);
		}
	});

</script>
</head>


<body>
   <div class="logo-home">
      <button class="Home-btn" onclick="location.href='./'">
         <img src="resources/images/lg.png" class="logo">
      </button>
   </div>
   <form action="signUpProc" method="post" enctype="multipart/form-data"
     onsubmit="return validateForm()" class="joinForm">
      <h2>SIGN UP</h2>
      <div class="textForm">
         <input type="text" class="id" name="m_email" placeholder="E-mail" id="signUpEmail">
      <span id="emailCheckResult"></span>
      </div>
      <div class="textForm">
         <input type="text" class="pw" name="m_password" placeholder="Password">
      </div>
      <div class="textForm">
         <input type="text" class="name" name="m_name" placeholder="Name">
      </div>
      <div class="textForm">
         <input type="text" class="phoneNumber" name="m_phoneNumber" placeholder="Phone number">
      </div>
      <div class="textForm">
         <input type="text" class="address" name="m_address" placeholder="Address">
      </div>
      <div class="textForm">
         <input type="text" class="age" name="m_age" placeholder="Age">
      </div>
      <div class ="textForm">
             <select id="selectOption" name="sORt">
              <option value="student">학생</option>
              <option value="teacher">선생님</option>
          </select>
      </div>

      <button type="submit">
         <span></span>
         <span></span>
         <span></span>
         <span></span>
         SIGN UP
      </button>
   </form>
</body>
<script>
function validateForm() {
    // 이메일 중복 체크 결과를 가져옴
    let result = $("#emailCheckResult").text();

    // 중복된 경우
    if (result === "중복된 이메일입니다.") {
        alert("중복된 이메일입니다. 다른 이메일을 입력해주세요.");
        return false; // 회원가입 막기
    }
    
    // 사용 가능한 경우
    return true; // 회원가입 허용
}


$(document).ready(function() {
    // 문서가 준비되면 실행되는 함수
    $("#signUpEmail").blur(function() {
        // 'signUpEamil'이라는 ID를 가진 입력 필드에서 blur 이벤트가 발생했을 때 실행되는 함수
        let email = $(this).val();
        // 'signUpEamil' 필드의 값(value)을 가져와서 'email' 변수에 저장
        if (email.trim() === "") {
            $("#emailCheckResult").text("이메일을 입력해주세요.");
            return;
        }else if (email.indexOf('@') === -1) {
        	//indexOf("")는 문자열이 포함되지 안흥면 무조건 -1값이 반환됨.
            $("#emailCheckResult").text("올바르지 않은 이메일입니다.")
        } 
        $.post("emailCheckResult", { m_email: email }, function(result) {
            // 서버로 POST 요청을 보내는데, 요청 경로는 "emailCheckResult"이고, 데이터로 'm_email'을 키로 하는 'email' 값을 전송
            // 성공적으로 요청이 완료되면 실행되는 함수, 서버에서 반환한 결과가 'result' 매개변수로 전달됨
            $("#emailCheckResult").text(result);
            // 'emailCheckResult'라는 ID를 가진 요소에 서버에서 받은 결과를 텍스트로 설정하여 표시	  
      		  	});
        	
        });
    });
</script>
</html>
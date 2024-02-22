<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MyPage</title>
</head>

<body>
    <h1>MyPage</h1>

	<form action="mypage" method="get">
    <p>이름 : ${memberDto.m_name}</p>
    <p>이메일 : ${memberDto.m_email}</p>
    <p>전화번호 : ${memberDto.m_phoneNumber}</p>
    <p>주소 : ${memberDto.m_address}</p>
    <p>나이 : ${memberDto.m_age}</p>
   	<p>내 수강신청 확인 :</p>
    <ul>
        <c:forEach var="className" items="${memberDto.classList}">
            <li>${className}</li>
        </c:forEach>
    </ul>
	</form>
    
    	<form action="memout" method="get">
    	<input type="submit" value="회원탈퇴">
    	</form>
    <button onclick="location.href='./setting'">회원정보수정</button>
   	<button onclick="location.href='./'">홈페이지 돌아가기</button>
    
   
</body>
<script type="text/javascript">
let msg = "${msg}";
if(msg != ""){
	alert(msg);
}	

</script>

</html>
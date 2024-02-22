<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>회원 정보 수정</h4>

	<form action="updateMember" method="post" enctype="multipart/form-data">
	<c:if test="${loggedInMember ne null}">
	<input type="hidden" name="m_id" value="${m_id}"> 
    <p>이름 : ${loggedInMember.m_name} <input type=text placeholder="${loggedInMember.m_name}" name="m_name" > </p>
    <p>이메일 : ${loggedInMember.m_email} <input type=text placeholder="${loggedInMember.m_email}" name="m_email" > </p>
    <p>전화번호 : ${loggedInMember.m_phoneNumber} <input type=text placeholder="${loggedInMember.m_phoneNumber}" name="m_phoneNumber" > </p>
    <p>비밀번호 : ${loggedInMember.m_password} <input type=text placeholder="${loggedInMember.m_password}" name="m_password"  > </p>
    <p>주소 : ${loggedInMember.m_address} <input type=text placeholder="${loggedInMember.m_address}" name="m_address" > </p>
    <p>나이 : ${loggedInMember.m_age} <input type=text placeholder="${loggedInMember.m_age}" name="m_age" > </p>
    
		
		<input type="submit" value="test">
		<input type="reset" value="cencel">
	</c:if>
	</form>
	<br>
	<br>
	<button onclick="location.href='./'">홈페이지 돌아가기</button>
	
</body>
<script type="text/javascript">
let msg = "${msg}";
if(msg != ""){
	alert(msg);
}	
</script>
</html>
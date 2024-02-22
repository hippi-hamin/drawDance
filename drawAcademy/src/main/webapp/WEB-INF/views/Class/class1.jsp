<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
        // 클래스를 선택했을 때의 이벤트 처리
        $("#selectOption").change(function () {
            // 선택된 옵션 값 가져오기
            var selectedClass = $(this).val();

            // 선택된 클래스에 따라 필드 값 설정
            if (selectedClass === "classA") {
                $("#dateField").val("3/19");
                $("#dayField").val("MON");
                $("#timeField").val("7:30-8:30");
            } else if (selectedClass === "classB") {
                $("#dateField").val("3/20");
                $("#dayField").val("TUE");
                $("#timeField").val("8:30-9:30");
            } else if (selectedClass === "classC") {
                $("#dateField").val("3/21");
                $("#dayField").val("WEN");
                $("#timeField").val("3:30-4:30");
            }

            // 필드를 활성화
            $("#dateField, #dayField, #timeField").prop("disabled", false);
        });
    });
</script>
</head>
<body>
<h4>Class1</h4>
<img src="resources/images/강사 프로필 사진.PNG" alt="강사1">
<p>
    <span>date: 3/19</span>
    <span>day: Mon</span>
    <span>Time: 7:30-8:30</span>
    <span>Class A</span>
    <span>${classLimitA}/20</span>
</p>
<p>
    <span>date: 3/19</span>
    <span>day: Mon</span>
    <span>Time: 7:30-8:30</span>
    <span>Class B</span>
    <span>${classLimitB}/20</span>
</p>
<p>
    <span>date: 3/19</span>
    <span>day: Mon</span>
    <span>Time: 7:30-8:30</span>
    <span>Class C</span>
    <span>${classLimitC}/20</span>
</p>
<form action="class1proc" method="post" id="class1proc">
    <label for="selectOption">Choose an option:</label>
    <select id="selectOption" name="className">
        <option value="classA" id="classA">Class A</option>
        <option value="classB" id="classB">Class B</option>
        <option value="classC" id="classC">Class C</option>
    </select>
    <p>
        <span>Date: <input type="text" id="dateField" name="date" readonly></span>
        <br><span>Day: <input type="text" id="dayField" name="day" readonly></span>
        <br><span>Time: <input type="text" id="timeField" name="time" readonly></span>
        <input type="hidden" name="m_name" value="${m_name}"> 
        <input type="hidden" name="m_email" value="${m_email}">
    </p>
    <input type="submit" value="수강신청" >
</form>
</body>

<script>
var classLimitAValue = ${classLimitA};
var classLimitBValue = ${classLimitB};
var classLimitCValue = ${classLimitC};

$("#class1proc").submit(function (event) {
    var selectedClass = $("#selectOption").val(); // 선택된 클래스 값 가져오기

    if (selectedClass === "classA" && classLimitAValue >= 20) {
        alert("classA 정원이 초과되었습니다. 수강신청이 불가합니다.");
        event.preventDefault();
    } else if (selectedClass === "classB" && classLimitBValue >= 20) {
        alert("classB 정원이 초과되었습니다. 수강신청이 불가합니다.");
        event.preventDefault();
    } else if (selectedClass === "classC" && classLimitCValue >= 20) {
        alert("classC 정원이 초과되었습니다. 수강신청이 불가합니다.");
        event.preventDefault();
    }
});
	
</script>
</html>
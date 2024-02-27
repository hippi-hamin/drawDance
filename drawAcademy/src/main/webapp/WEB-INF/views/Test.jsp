<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>
<body>
	<div class="calendar">
		<div class="header">
			<div class="year-month"></div>
			<div class="nav">
				<button class="nav-btn go-prev" onclick="prevMonth()">&lt;</button>
				<button class="nav-btn go-today" onclick="goToday()">Today</button>
				<button class="nav-btn go-next" onclick="nextMonth()">&gt;</button>
			</div>
		</div>
		<div class="main">
			<div class="days">
				<div class="day">일</div>
				<div class="day">월</div>
				<div class="day">화</div>
				<div class="day">수</div>
				<div class="day">목</div>
				<div class="day">금</div>
				<div class="day">토</div>
			</div>
			<div class="dates"></div>
		</div>
	</div>
</body>
<script>
const date = new Date();

const viewYear = date.getFullYear();
const viewMonth = date.getMonth();

document.querySelector('.year-month').textContent = `${viewYear}년 ${viewMonth + 1}월`;
</script>
</html>
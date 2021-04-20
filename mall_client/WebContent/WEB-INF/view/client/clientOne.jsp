<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clientOne</title>
</head>
<body>

	<div>
		<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	</div>
	<h1>회원정보</h1>
	<table border="1">
		<c:forEach var="m" items="${clientOneList}">
			<tr>
				<td>회원번호</td>
				<td>${m.clientNo}</td>
			</tr>
			<tr>
				<td>메일</td>
				<td>${m.clientMail}</td>
			</tr>
			<tr>
				<td>가입날짜</td>
				<td>${m.clientDate.substring(0,10)}</td>
			</tr>
			<tr>
				<td>구입 책 갯수</td>
				<td>${m.ebookCnt}권</td>
			</tr>
			<tr>
				<td>사용한 돈</td>
				<td>&#8361;${m.sumPrice}</td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- /updateClientPwControlle.doGet() -> updateClientPw.jsp --!>
	<!-- ClientDao.updateClientPw().doPost -> 성공: session.invalidate() - redirect:/IndexController -->
	<a href="${pageContext.request.contextPath}/UpdateClientPwController">비밀번호수정</a>
	<!-- DeleteClientController - ClientDao.deleteClient() cartDao.deleteCartByClient()  - redirect:/IndexController -->
	<a href="${pageContext.request.contextPath}/DeleteClientController">회원탈퇴</a>

</body>
</html>
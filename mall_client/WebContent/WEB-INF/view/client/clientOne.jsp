<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clientOne</title>
</head>
<body>
<%
	Client clientOne = (Client)request.getAttribute("clientOne");
%>
	<div>
		<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	</div>
	<h1>회원정보</h1>
	<table border="1">
		<tr>
			<td>회원번호</td>
			<td><%=clientOne.getClientNo() %></td>
		</tr>
		<tr>
			<td>메일</td>
			<td><%=clientOne.getClientMail() %></td>
		</tr>
		<tr>
			<td>가입날짜</td>
			<td><%=clientOne.getClientDate().substring(0,10)%></td>
		</tr>
	</table>
	<a href="<%=request.getContextPath()%>/">비밀번호수정</a>
	<a href="<%=request.getContextPath()%>/">회원탈퇴</a>

</body>
</html>
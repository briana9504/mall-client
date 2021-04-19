<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "mall.client.vo.*" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clientOne</title>
</head>
<body>
<%
	List<Map<String, Object>> clientOneList = (List<Map<String, Object>>)request.getAttribute("clientOneList");
	
%>
	<div>
		<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	</div>
	<h1>회원정보</h1>
	<table border="1">
		<%
			for(Map m: clientOneList){
				String clientDate = ((String)m.get("clientDate")).substring(0,10);
				clientDate = clientDate.replaceAll(" ", "");// 공백 제거
				String[] Date = clientDate.split("-");//-를 기준으로 날짜 자름(년/월/일)
				//잘 잘려는지 디버깅
				for(String s: Date){
					System.out.println(s);
				}
		%>
		<tr>
			<td>회원번호</td>
			<td><%=m.get("clientNo") %></td>
		</tr>
		<tr>
			<td>메일</td>
			<td><%=m.get("clientMail")%></td>
		</tr>
		<tr>
			<td>가입날짜</td>
			<td><%=Date[0]%>년 <%=Date[1]%>월 <%=Date[2]%>일</td>
		</tr>
		<tr>
			<td>구입 책 갯수</td>
			<td><%=m.get("ebookCnt")%>권</td>
		</tr>
		<tr>
			<td>사용한 돈</td>
			<td>&#8361;<%=m.get("sumPrice") %></td>
		</tr>
		<%
			}
		%>
	</table>
	<!-- /updateClientPwControlle.doGet() -> updateClientPw.jsp --!>
	<!-- ClientDao.updateClientPw().doPost -> 성공: session.invalidate() - redirect:/IndexController -->
	<a href="<%=request.getContextPath()%>/UpdateClientPwController">비밀번호수정</a>
	<!-- DeleteClientController - ClientDao.deleteClient() cartDao.deleteCartByClient()  - redirect:/IndexController -->
	<a href="<%=request.getContextPath()%>/DeleteClientController">회원탈퇴</a>

</body>
</html>
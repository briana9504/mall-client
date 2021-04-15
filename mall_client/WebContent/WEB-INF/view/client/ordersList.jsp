<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import= "mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		List<Map<String, Object>> ordersList = (List<Map<String, Object>>)request.getAttribute("ordersList");
	%>
	<!-- 메인메뉴 -->
	<div>
		<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	</div>
	<h1>주문목록</h1>
	<%
			for(Map<String, Object> map: ordersList){
				String ebookTitle = (String)map.get("ebookTitle");
	%>
		<div><%=ebookTitle%></div>
	
	<%
			}
	%>
</body>
</html>
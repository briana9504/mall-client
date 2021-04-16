<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		int currentPage = (int)request.getAttribute("currentPage");
		int lastPage = (int)request.getAttribute("lastPage");
		List<Map<String, Object>> ordersList = (List<Map<String, Object>>)request.getAttribute("ordersList");
	%>
	<!-- 메인메뉴 -->
	<div>
		<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	</div>
	<h1>주문목록</h1>
	<table border="1">
		<tr>
			<td>주문번호</td>
			<td>이북번호</td>
			<td>ebook제목</td>
			<td>가격</td>
			<td>주문날짜</td>
			<td>주문상태</td>
		</tr>
	
	
	<%
			for(Map<String, Object> map: ordersList){
				int ebookNo = (int)map.get("ebookNo");
				String ebookTitle = (String)map.get("ebookTitle");
				int ebookPrice = (int)map.get("ebookPrice");
				String ordersDate = (String)map.get("ordersDate");
				String ordersState = (String)map.get("ordersState");
	%>
				<tr>
					<td><%=(Integer)map.get("ordersNo") %></td>
					<td><%=ebookNo %></td>
					<td><%=ebookTitle %></td>
					<td><%=ebookPrice %></td>
					<td><%=ordersDate.substring(0,10) %></td>
					<td><%=ordersState %></td>
				</tr>
	
	<%
			}
	%>
	</table>
	<%
		if(currentPage > 1){
	%>
			<a href="<%=request.getContextPath()%>/OrdersListController?currentPage=<%=currentPage-1%>">이전</a>
	<%
		}
	
		if(currentPage < lastPage){
	%>
			<a href="<%=request.getContextPath()%>/OrdersListController?currentPage=<%=currentPage+1%>">다음</a>
	<%
		}
	%>
</body>
</html>
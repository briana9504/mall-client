<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
	List<Map<String, Object>> cartList = (List<Map<String, Object>>)request.getAttribute("cartList");
%>
	<!-- 메인메뉴 -->
	<div>
		<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	</div>
	<!-- cartList -->
	<h1>CartList</h1>
	<table border="1">
		<tr>
			<td>cartNo</td>
			<td>ebookNo</td>
			<td>ebookTitle</td>
			<td>ebookPrice</td>
			<td>cartDate</td>
			<td>삭제</td>
			<td>주문</td>
		</tr>
		<%
			for(Map<String, Object> map: cartList){
				int cartNo = (int)map.get("cartNo");
				String ebookTitle = (String)map.get("ebookTitle");
				int ebookNo = (int)map.get("ebookNo");
				String cartDate = (String)map.get("cartDate");
				int ebookPrice = (int)map.get("ebookPrice");
		%>
			<tr>
				<td><%=cartNo %></td>
				<td><%=ebookNo %></td>
				<td><a href="<%=request.getContextPath()%>/EbookOneController?ebookNo=<%=ebookNo%>"><%=ebookTitle %></a></td>
				<td><%=ebookPrice %></td>
				<td><%=cartDate.substring(0,11)%></td>
				<!-- /DeleteCartController -> CartDao.deleteCart() ->redirect:/CartListController -->
				<td><a href="<%=request.getContextPath()%>/DeleteCartController?ebookNo=<%=ebookNo%>">삭제</a></td>
				<!-- /InsertOrdersContorller -> insertOrders(),deleteCart().ISSUE 트렌젝스처리 -> redirect:/OrdersListContoller 주문리스트-> -->
				<td><a href="<%=request.getContextPath()%>/InsertOrdersContorller?ebookNo=<%=ebookNo%>">주문</a></td>
			</tr>
		<%
			}
		%>
	</table>
</body>
</html>
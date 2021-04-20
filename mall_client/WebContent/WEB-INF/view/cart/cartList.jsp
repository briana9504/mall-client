<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

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

		<c:forEach var="m" items="${cartList}">
			<tr>
				<td>${m.cartNo}</td>
				<td>${m.ebookNo}</td>
				<td><a href="${pageContext.request.contextPath}/EbookOneController?ebookNo=${m.ebookNo}">${m.ebookTitle}</a></td>
				<td>${m.ebookPrice}</td>
				<td>${m.cartDate.substring(0.11)}</td>
				<!-- /DeleteCartController -> CartDao.deleteCart() ->redirect:/CartListController -->
				<td><a href="${pageContext.request.contextPath}/DeleteCartController?ebookNo=${m.ebookNo}">삭제</a></td>
				<!-- /InsertOrdersContorller -> insertOrders(),deleteCart().ISSUE 트렌젝스처리 -> redirect:/OrdersListContoller 주문리스트-> -->
				<td><a href="${pageContext.request.contextPath}/InsertOrdersContorller?ebookNo=${m.ebookNo}">주문</a></td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>
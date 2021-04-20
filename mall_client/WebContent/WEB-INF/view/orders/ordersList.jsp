<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	<c:forEach var="m" items="${ordersList}">
				<tr>
					<td>${m.ordersNo}</td>
					<td>${m.ebookNo}</td>
					<td>${m.ebookTitle}</td>
					<td>${m.ebookPrice}</td>
					<td>${m.ordersDate.substring(0,10)}</td>
					<td>${m.ordersState}</td>
				</tr>
	</c:forEach>

	</table>
		<c:if test="${currentPage > 1}">
			<a href="${pageContext.request.contextPath}/OrdersListController?currentPage=${currentPage-1}">이전</a>
		</c:if>
		<c:if test="${currentPage < lastPage}">
			<a href="${pageContext.request.contextPath}/OrdersListController?currentPage=${currentPage+1}">다음</a>
		</c:if>

</body>
</html>
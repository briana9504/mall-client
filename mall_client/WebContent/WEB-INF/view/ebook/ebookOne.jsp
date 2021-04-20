<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	</div>
	
	<h1>ebookOne</h1>
	<table border="1">
		<tr>
			<td>ebookTitle</td>
			<td>${ebook.ebookTitle}</td>
		</tr>
		<tr>
			<td>카테고리</td>
			<td>${ebook.categoryName}</td>
		</tr>
		<tr>
			<td>작가</td>
			<td>${ebook.ebookAuthor}</td>
		</tr>
		<tr>
			<td>출판사</td>
			<td>${ebook.ebookCompany}</td>
		</tr>
		<tr>
			<td>ISBN</td>
			<td>${ebook.ebookISBN}</td>
		</tr>
		<tr>
			<td>판매날짜</td>
			<td>${ebook.ebookDate.substring(0,11)}</td>
		</tr>
		<tr>
			<td>가격</td>
			<td>${ebook.ebookPrice}</td>
		</tr>
		<tr>
			<td>페이지 수</td>
			<td>${ebook.ebookPageCount}</td>
		</tr>
		<tr>
			<td>이미지</td>
			<td><img src="${pageContext.request.contextPath}/img/default.jpg"></td>
		</tr>
		<tr>
			<td>소개</td>
			<td>${ebook.ebookSummary}</td>
		</tr>
		<tr>
			<td>상태</td>
			<td>${ebook.ebookState}</td>
		</tr>
	</table>
	<!-- InsertCartController?ebookNo - cartDao.insertCart() -> redirect:CartListController -->
	<!-- 로그인 중이거나 판매중이 아니면 버튼을 누를 수 없음 -->
	<a href="${pageContext.request.contextPath}/InsertCartController?ebookNo=${ebook.ebookNo}">
		<c:if test="${loginClient == null || !ebook.ebookState.equals('판매중')}">
			<button type="submit" disabled="disabled">장바구니추가</button>
		</c:if>
		<c:if test="${loginClient != null && ebook.ebookState.equals('판매중')}">
			<button type="submit">장바구니추가</button>
		</c:if>
	</a>	
	<!-- 바로구매...? -->
	<!-- 리뷰 -->
	<!-- 별점...? -->
</body>
</html>
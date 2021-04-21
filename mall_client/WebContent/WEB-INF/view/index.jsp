<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	</div>	
	
	<div>
		<div>오늘 접속자 : ${statsCount}</div>
		<div>전체 접속자 : ${total}</div>
	</div>
	
	<h1>index</h1>

	<!-- best ebook  상품 5개 출력 -->
	
	<h3>Best Ebook</h3>
	<table border="1">
		<tr>
			<c:forEach var="m" items="${bestOrdersList}">
				<td>
					<div><img src="${pageContext.request.contextPath}/img/default.jpg"></div>
					<!-- EbookOneController -> EbookDao.selectEbookOne -> ebookOne.jsp -->
					<div>
						<a href="${pageContext.request.contextPath}/EbookOneController?ebookNo=${m.ebookNo}">
							${m.ebookTitle}
						</a>
					</div>
					<div>&#8361;${m.ebookPrice}</div>
				</td>
			</c:forEach>
		</tr>
	</table>
	
	
	<h3>ebookList</h3>	
	<!-- 카테고리 목록 -->
		<a href="${pageContext.request.contextPath}/IndexController">전체</a>
		<c:forEach var="cName" items="${categoryList}">
			<a href="${pageContext.request.contextPath}/IndexController?categoryName=${cName}">${cName}</a>	
		</c:forEach>
	<table border="1">
		<c:forEach begin="0" end="14" var="i" step="${i+5}">
			<tr>
				<c:forEach begin="${i}" end="${i+4}" var="ebook" items="${ebookList}">
					<td>
						<div><img src="${pageContext.request.contextPath}/img/default.jpg"></div>
						<!-- EbookOneController -> EbookDao.selectEbookOne -> ebookOne.jsp -->
						<div>
							<a href="${pageContext.request.contextPath}/EbookOneController?ebookNo=${ebook.ebookNo}">
								${ebook.ebookTitle}
							</a>
						</div>
						<div>&#8361;${ebook.ebookPrice}</div>
					</td>
				</c:forEach>
			</tr>
		</c:forEach>
		<!-- ebook 상품 출력 -->
		<!-- 5개씩 보여주기, 그 후 밑에 줄로 내리기 -->
	</table>
	<c:if test="${currentPage > 1}">
		<c:if test="${categoryName == null}">
			<c:if test="${searchWord == null}">
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage-1}">이전</a>
			</c:if>
			<c:if test="${searchWord != null}">
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a>
			</c:if>
		</c:if>
		<c:if test="${categoryName != null}">
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage-1}&categoryName=${categoryName}">이전</a>
		</c:if>
	</c:if>
	
	<c:if test="${currentPage < lastPage}">
		<c:if test="${categoryName == null}">
			<c:if test="${searchWord == null}">
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage+1}">다음</a>
			</c:if>
			<c:if test="${searchWord != null}">
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a>
			</c:if>
		</c:if>
		<c:if test="${categoryName != null}">
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage+1}&categoryName=${categoryName}">다음</a>
		</c:if>
	</c:if>
	
	<!-- 검색기능 넣기 : 책제목 검색 -->	
	<form action="${pageContext.request.contextPath}/IndexController" method="get">
		ebookTitle:
		<input type="text" name="searchWord">
		<button type="submit">검색</button>
	</form>
</body>
</html>
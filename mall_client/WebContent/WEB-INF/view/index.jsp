<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<!-- 메뉴1 -->
	<!-- 메뉴2 -->
	<h1>index</h1>
	<%
		List<Ebook> ebookList = (List<Ebook>)(request.getAttribute("ebookList"));
	%>
	<!-- 5개씩 보여주기, 그 후 밑에 줄로 내리기 -->
	<table border="1">
		<tr>
		<%
			int i = 0;
			for(Ebook ebook : ebookList){
				i += 1;
		%>
				
					<td>
						<div><img src="<%=request.getContextPath()%>/img/default.jpg"></div>
						<div><a href=""><%=ebook.getEbookTitle()%></a></div>
						<div><%=ebook.getEbookPrice()%></div>
					</td>
		<%
				if(i%5==0){
		%>
						</tr><tr>
		<%
				}
			}
		%>
		</tr>
	</table>
</body>
</html>
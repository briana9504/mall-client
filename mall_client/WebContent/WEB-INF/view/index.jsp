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
	<h1>index</h1>
	<!-- 책제목 검색-->
	<%
		List<Ebook> ebookList = (List<Ebook>)(request.getAttribute("ebookList"));
		int currnetPage = (int)request.getAttribute("currentPage");
		int lastPage = (int)request.getAttribute("lastPage");
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
						<!-- EbookOneController -> EbookDao.selectEbookOne -> ebookOne.jsp -->
						<div>
							<a href="<%=request.getContextPath()%>/EbookOneController?ebookNo=<%=ebook.getEbookNo()%>">
								<%=ebook.getEbookTitle()%>
							</a>
						</div>
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
	<%
		if(currnetPage > 1){
	%>
			<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currnetPage-1%>">이전</a>
	<%
		}
	
		if(currnetPage < lastPage){
	%>
	
			<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currnetPage+1%>">다음</a>
	<%
		}
	%>
	
</body>
</html>
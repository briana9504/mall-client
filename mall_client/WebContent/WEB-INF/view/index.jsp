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
	<div>
		<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	</div>
	
	
	<!-- 책제목 검색, 카테고리-->
	<%
		List<Ebook> ebookList = (List<Ebook>)(request.getAttribute("ebookList"));
		int currentPage = (int)request.getAttribute("currentPage");
		int lastPage = (int)request.getAttribute("lastPage");
		String categoryName = null;
		if(request.getAttribute("categoryName") != null){
			categoryName = (String)request.getAttribute("categoryName");
		}	
		String searchWord = null;
		if(request.getAttribute("searchWord") != null){
			searchWord = (String)request.getAttribute("searchWord");
		}
		//카테고리 리스트
		List<String> categoryList = (List<String>)(request.getAttribute("categoryList"));
	%>
			<a href="<%=request.getContextPath()%>/IndexController">전체</a>
	<%
		for(String cName: categoryList){
	%>
			<a href="<%=request.getContextPath()%>/IndexController?categoryName=<%=cName%>"><%=cName%></a>	
	<%
		}
	%>
	<h1>index</h1>
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
						<div>&#8361;<%=ebook.getEbookPrice()%></div>
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
		if(currentPage > 1){
			if(categoryName == null){
				if(searchWord == null){
					%>
						<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currentPage-1%>">이전</a>
					<%
				} else{
				%>
					<a href="<%=request.getContextPath()%>/SearchIndexController?currentPage=<%=currentPage-1%>&searchWord=<%=searchWord%>">이전</a>
				<%		
				}
			} else{
	%>
			<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currentPage-1%>&categoryName=<%=categoryName%>">이전</a>
	<%
			}
		}
	
		if(currentPage < lastPage){
			if(categoryName == null){
				if(searchWord == null){
				%>
					<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currentPage+1%>">다음</a>
				<%	
				} else{
			%>
				<a href="<%=request.getContextPath()%>/SearchIndexController?currentPage=<%=currentPage+1%>&searchWord=<%=searchWord%>">다음</a>
			<%	
				}
			}else{
	%>
			<a href="<%=request.getContextPath()%>/IndexController?currentPage=<%=currentPage+1%>&categoryName=<%=categoryName%>">다음</a>
	<%
			}
		}
	%>
	<!-- 검색기능 넣기 -->	
	<form action="<%=request.getContextPath()%>/SearchIndexController" method="post">
		ebookTitle:
		<input type="text" name="searchWord">
		<button type="submit">검색</button>
	</form>
</body>
</html>
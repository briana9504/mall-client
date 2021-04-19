<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
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
	<h1>Ebook Calendar</h1>
	
	<%
		List<Map<String, Object>> ebookListByMonth = (List<Map<String, Object>>)request.getAttribute("ebookListByMonth");
		int today = (Integer)request.getAttribute("today");
		int currentYear = (Integer)request.getAttribute("currentYear");
		int currentMonth = (Integer)request.getAttribute("currentMonth");
		int endDay = (Integer)request.getAttribute("endDay");
		int firstDayOfWeek = (Integer)request.getAttribute("firstDayOfWeek");
		
		int preMonth = currentMonth - 1;
		int preYear = currentYear;
		if(preMonth == 0){
			preMonth = 12;
			preYear -=1;
		}	
		int nextMonth = currentMonth + 1;
		int nextYear = currentYear;
		if(nextMonth == 13){
			nextMonth = 1;
			nextYear +=1;
		}
	%>
	
	
	<!-- n행 7열 -->
	<div>
		<a href="<%=request.getContextPath()%>/EbookCalendarController?currentYear=<%=preYear%>&currentMonth=<%=preMonth%>">이전달</a>
		<span><%=currentYear%>년</span>
		<span><%=currentMonth%>월</span>
		<a href="<%=request.getContextPath()%>/EbookCalendarController?currentYear=<%=nextYear%>&currentMonth=<%=nextMonth%>">다음달</a>
	</div>
	<table border="1">
		<tr>
			<th>일</th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
			<th>토</th>
		</tr>
		<tr>
			<%	//i는 날짜 n은 요일
				int n = 0;
				for(int i=1-(firstDayOfWeek-1); i<=endDay; i++){//35일때
					n++;
					if(i<1){
					%>
						<td>&nbsp;</td>
					<%	
					} else{
					%>	
						<td>
							<%=i%>
							<%
								if(i == today){
									out.println("오늘!!");
								}
							%>		
							<%
								for(Map m : ebookListByMonth){
									if( i == (Integer)m.get("d")){
							%>		
									<div>
										<a href="<%=request.getContextPath()%>/EbookOneController?ebookNo=<%=m.get("ebookNo")%>">
											<%
												String ebookTitle = (String)m.get("ebookTitle");
												if(ebookTitle.length() >10){
											%>
													<%=ebookTitle.substring(0,10) %>...
											<% 
												} else{
											%>		
													<%=ebookTitle %>
											<%		
												}
											%>
										</a>
									</div>
							<% 		
									}
								}
							%>
						
						</td>
						<%
							if(n%7 == 0){
						%>
							</tr><tr>
						<%
							}
					}
				}
				//for에 초기화식 안넣는 방법 없을까요?
				//int k는 의미없음
				for(int k; (n%7) != 0; n++){
				%>
						<td>&nbsp;</td>
						
				<%
				}
			%>
		</tr>
	</table>
</body>
</html>
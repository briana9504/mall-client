package mall.client.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.EbookDao;
import mall.client.vo.*;

@WebServlet("/EbookCalendarController")
public class EbookCalendarController extends HttpServlet {
	private EbookDao ebookDao;//이달에 신간 ebook

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//년/월이 매개값으로 전달되지 않으면
		Calendar dday = Calendar.getInstance();
		this.ebookDao = new EbookDao();
		
		//오늘 구하기
		int today = 0;
		if(request.getParameter("currentMonth") != null) {
			if(Integer.parseInt(request.getParameter("currentMonth")) == dday.get(Calendar.MONTH)+1) {
				today = dday.get(Calendar.DATE);
			}
		} else {
			today = dday.get(Calendar.DATE);
		}
		
		//년, 월
		if( request.getParameter("currentYear") != null){
			dday.set(Calendar.YEAR, Integer.parseInt(request.getParameter("currentYear")));
		}
		if(request.getParameter("currentMonth") != null) {
			dday.set(Calendar.MONTH, Integer.parseInt(request.getParameter("currentMonth"))-1);
		}
		int currentYear = dday.get(Calendar.YEAR);
		int currentMonth = dday.get(Calendar.MONTH) +1 ;//0부터 시작함
		
		System.out.printf("currentYear: %s\ncurrentMonth: %s\n", currentYear, currentMonth);
		
		//달의 마지막 일(ex: 31, 30, 29...)구하기
		int endDay = dday.getActualMaximum(Calendar.DAY_OF_MONTH);
		//달의 첫번째날 구하기
		Calendar firstDay = Calendar.getInstance();
		firstDay.set(Calendar.YEAR, currentYear);
		firstDay.set(Calendar.MONTH, currentMonth -1);
		firstDay.set(Calendar.DATE , 1);
		int firstDayOfWeek = firstDay.get(Calendar.DAY_OF_WEEK);//첫재날 요일
		System.out.println("시작요일: "+firstDay.get(Calendar.DAY_OF_WEEK));
		
		//달별 ebooklist - database 에는 1월은 1월이므로 
		List<Map<String, Object>> ebookListByMonth = this.ebookDao.selectEbookListByMonth(currentYear, currentMonth);
		
		//preYear, preMonth, nextYear, nextMonth
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
		request.setAttribute("preMonth", preMonth);
		request.setAttribute("preYear", preYear);
		request.setAttribute("nextMonth", nextMonth);
		request.setAttribute("nextYear", nextYear);
		request.setAttribute("today", today);
		request.setAttribute("ebookListByMonth", ebookListByMonth);
		request.setAttribute("currentYear", currentYear);
		request.setAttribute("currentMonth", currentMonth);
		request.setAttribute("endDay", endDay);
		request.setAttribute("firstDayOfWeek", firstDayOfWeek);
		request.getRequestDispatcher("/WEB-INF/view/ebook/ebookCalendarTest.jsp").forward(request, response);
	}
}

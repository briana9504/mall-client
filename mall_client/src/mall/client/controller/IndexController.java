package mall.client.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mall.client.model.*;
import java.util.*;
import mall.client.vo.*;
// C -> M -> V
@WebServlet("/IndexController")
public class IndexController extends HttpServlet {
	private EbookDao ebookDao;
	private CategoryDao categoryDao;
	private OrdersDao ordersDao;
	private StatsDao statsDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ebookDao = new EbookDao();
		this.categoryDao = new CategoryDao();
		this.ordersDao = new OrdersDao();
		this.statsDao = new StatsDao();
		Ebook ebook = new Ebook();
		
		System.out.println("/IndexController 시작...");
		
		//request 처리
		
		//카테고리
		String categoryName = null;
		
		if(request.getParameter("categoryName") != null) {
			categoryName = request.getParameter("categoryName");
		}
		System.out.println("categoryName: "+ebook.getCategoryName() +"<indexController>");
		
		//검색기능
		String searchWord = null;
		if(request.getParameter("searchWord") != null) {
			 searchWord = request.getParameter("searchWord");
		}
		System.out.println("searchWord: "+searchWord +"<indexController.searchWord>");
		
		//페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 10;
		int beginRow = (currentPage - 1)*rowPerPage;
		
		//카테고리 리스트 호출
		List<String> categoryList = this.categoryDao.CategoryList();
		
		//베스트셀러list 호출
		List<Map<String, Object>> bestOrdersList = this.ordersDao.selectBastOrdersList();
				
		
		//마지막 페이지구하기 - 검색어가 있을경우와 검색어가 없을 경우 dao 다름
		if(request.getParameter("searchWord") != null) {//검색어가 있을경우- 카테고리와 호환되지 않는다.
			
			int totalRow = ebookDao.searchTotalCount(searchWord);
			System.out.println("totalRow: "+ totalRow + "<SearchIndexController>");
			int lastPage = totalRow/rowPerPage;
			if(totalRow % rowPerPage != 0){
				lastPage +=1;
			}
			
			//ebookList호출
			List<Ebook> ebookList = this.ebookDao.selectSearchEbookListByPage(beginRow, rowPerPage, searchWord);
			
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("ebookList", ebookList);
		} else { // 검색어가 없을 경우
			int totalRow = ebookDao.totalCount(categoryName);
			int lastPage = totalRow/rowPerPage;
			if(totalRow % rowPerPage != 0){
				lastPage +=1;
			}
					
			//ebookList호출
			List<Ebook> ebookList = this.ebookDao.selectEbookListByPage(beginRow, rowPerPage, categoryName);
		
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("ebookList", ebookList);
		}
		//접속사 관련 데이터
		long total = this.statsDao.selectStatsTotal();//전체 날짜 접속자 수
		//오늘 날짜
		Stats stats = this.statsDao.selectStatsByToday();
		long statsCount = 0;
		if(stats != null) {
			statsCount = stats.getStatsCount();
		}
		String now = this.statsDao.selectStatsByToday().getStatsDay(); 
		String[] today = now.split("-");
		for(String m: today) {
			System.out.println(m);
		}
		
		//(View forward)index.jsp파일 연결
		request.setAttribute("today", today);
		request.setAttribute("total", total);
		request.setAttribute("statsCount", statsCount);
		request.setAttribute("bestOrdersList", bestOrdersList);
		request.setAttribute("searchWord", searchWord);
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/indexTest.jsp");
		rd.forward(request, response);
	}
	
	//검색 - do post 없애는게 유지보수에 쉬움 , do post없애고 검색기능도 get방식으로 바꿈

}

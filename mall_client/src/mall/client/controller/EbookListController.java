package mall.client.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.CategoryDao;
import mall.client.model.EbookDao;
import mall.client.model.OrdersDao;
import mall.client.vo.Ebook;

/**
 * Servlet implementation class EbookListController
 */
@WebServlet("/EbookListController")
public class EbookListController extends HttpServlet {
	private EbookDao ebookDao;
	private CategoryDao categoryDao;
	private OrdersDao ordersDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ebookDao = new EbookDao();
		this.categoryDao = new CategoryDao();
		this.ordersDao = new OrdersDao();
		Ebook ebook = new Ebook();
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
		int rowPerPage = 12;
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
		
		
		//(View forward)index.jsp파일 연결
		request.setAttribute("bestOrdersList", bestOrdersList);
		request.setAttribute("searchWord", searchWord);
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/ebookList.jsp");
		rd.forward(request, response);
	}
}


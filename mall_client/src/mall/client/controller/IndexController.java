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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.ebookDao = new EbookDao();
		this.categoryDao = new CategoryDao();
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
		
		//페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 15;
		int beginRow = (currentPage - 1)*rowPerPage;
		
		//마지막 페이지
		if(request.getParameter("searchWord") != null) {//검색어가 없을경우
			
			int totalRow = ebookDao.searchTotalCount(searchWord);
			System.out.println("totalRow: "+ totalRow + "<SearchIndexController>");
			int lastPage = totalRow/rowPerPage;
			if(totalRow % rowPerPage != 0){
				lastPage +=1;
			}
			
			//model 호출
			List<Ebook> ebookList = this.ebookDao.selectSearchEbookListByPage(beginRow, rowPerPage, searchWord);
				
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("ebookList", ebookList);
		} else { // 검색어가 있을 경우
			int totalRow = ebookDao.totalCount(categoryName);
			int lastPage = totalRow/rowPerPage;
			if(totalRow % rowPerPage != 0){
				lastPage +=1;
			}
					
			//model 호출
			List<Ebook> ebookList = this.ebookDao.selectEbookListByPage(beginRow, rowPerPage, categoryName);
		
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("ebookList", ebookList);
		}	
		//카테고리 리스트 호출
		List<String> categoryList = this.categoryDao.CategoryList();
		
		//(View forward)index.jsp파일 연결
		request.setAttribute("searchWord", searchWord);
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("currentPage", currentPage);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		rd.forward(request, response);
	}
	
	//검색
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			this.ebookDao = new EbookDao();
			this.categoryDao = new CategoryDao();
			Ebook ebook = new Ebook();
			//request 처리
			//검색기능
			String searchWord = request.getParameter("searchWord");
			//카테고리 - 호환x
			String categoryName = null;
			
			//페이징
			int currentPage = 1;
			
			int rowPerPage = 15;
			int beginRow = (currentPage - 1)*rowPerPage;
			
			//마지막 페이지
			int totalRow = ebookDao.searchTotalCount(searchWord);
			System.out.println("totalRow: "+ totalRow + "<SearchIndexController>");
			int lastPage = totalRow/rowPerPage;
			if(totalRow % rowPerPage != 0){
				lastPage +=1;
			}
					
			//model 호출
			List<Ebook> ebookList = this.ebookDao.selectSearchEbookListByPage(beginRow, rowPerPage, searchWord);
				
			//카테고리 리스트 호출
			List<String> categoryList = this.categoryDao.CategoryList();
			
			//(View forward)index.jsp파일 연결
			request.setAttribute("searchWord", searchWord);
			request.setAttribute("categoryName", ebook.getCategoryName());
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("ebookList", ebookList);
			request.setAttribute("currentPage", currentPage);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
			rd.forward(request, response);
		}
	
}

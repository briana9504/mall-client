package mall.client.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.CategoryDao;
import mall.client.model.EbookDao;
import mall.client.vo.Ebook;

/**
 * Servlet implementation class SearchIndexController
 */
@WebServlet("/SearchIndexController")
public class SearchIndexController extends HttpServlet {
	private EbookDao ebookDao;
	private CategoryDao categoryDao;
	//검색 후 다음과 이전버튼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
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
